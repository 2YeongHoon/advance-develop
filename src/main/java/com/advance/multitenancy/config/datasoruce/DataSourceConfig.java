package com.advance.multitenancy.config.datasoruce;

import com.advance.multitenancy.config.hikari.HikariProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.advance",
    transactionManagerRef = "transactionManager",
    entityManagerFactoryRef = "entityManager")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DataSourceConfig {

    private final CustomDatasourceProperties properties;
    private final HikariProperties hikariProperties;
    private final JpaProperties jpaProperties;

    @Bean
    public DataSource routingDataSource() {
        DynamicRoutingDataSource routingDataSource = new DynamicRoutingDataSource();

        // 접근 가능한 데이터소스 목록 설정
        routingDataSource.setTargetDataSources(this.getTargetDataSources());
        // 기본 데이터소스 설정
        routingDataSource.setDefaultTargetDataSource(this.getDefaultTargetDataSource());
        // 설정 내용 반영
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    /**
     * 실제 쿼리가 실행될 때 Connection을 가져오도록 설정
     *
     * @return 데이터소스
     */
    @Bean
    @Primary
    public DataSource lazyDataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean
    @Primary
    public EntityManagerFactory entityManager(DataSource lazyDataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(lazyDataSource);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaPropertyMap(jpaProperties.getProperties());
        emf.setPackagesToScan("com.advance");
        emf.afterPropertiesSet();

        return emf.getObject();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManager) {
        return new JpaTransactionManager(entityManager);
    }

    /**
     * 접근 가능한 데이터소스 목록 반환
     *
     * @return 접근 가능한 데이터소스 목록
     */
    private Map<Object, Object> getTargetDataSources() {
        Map<Object, Object> dataSources = new HashMap<>();

        properties.getTargets()
                .forEach(tds ->
                        dataSources.put(
                                tds.getName(),
                                createDataSource(
                                        getUrl(tds.getSchema()),
                                        tds.getUsername(),
                                        tds.getPassword())));

        return dataSources;
    }

    /**
     * 기본 데이터소스 반환
     *
     * @return 기본 데이터소스
     */
    private Object getDefaultTargetDataSource() {
        return createDataSource(
                getUrl(properties.getSchema()),
                properties.getUsername(),
                properties.getPassword());
    }

    /**
     * 데이터소스 생성
     *
     * @param url      주소
     * @param username 아이디
     * @param password 비밀번호
     * @return 데이터소스
     */
    private DataSource createDataSource(String url, String username, String password) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(hikariProperties.getMaximumPoolSize());
        config.setConnectionTimeout(hikariProperties.getConnectionTimeout());
        config.setIdleTimeout(hikariProperties.getIdleTimeout());
        config.setMinimumIdle(hikariProperties.getMinimumIdle());

        return new HikariDataSource(config);
    }

    /**
     * db 주소 반환
     *
     * @param schema 스키마
     * @return db 접속 주소
     */
    private String getUrl(String schema) {
        return properties.getUrl() + schema + properties.getUrlOption();
    }
}
