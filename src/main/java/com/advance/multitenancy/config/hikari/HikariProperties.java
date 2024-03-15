package com.advance.multitenancy.config.hikari;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 커스텀 HikariCP 프로퍼티
 */
@Getter
@ConfigurationProperties(prefix = "custom.hikari")
public class HikariProperties {

    private final int maximumPoolSize;
    private final int connectionTimeout;
    private final int maxLifetime;

    public HikariProperties(int maximumPoolSize, int connectionTimeout, int maxLifetime) {
        this.maximumPoolSize = maximumPoolSize;
        this.connectionTimeout = connectionTimeout;
        this.maxLifetime = maxLifetime;
    }

}
