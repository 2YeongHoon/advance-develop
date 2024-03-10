package com.advance.common.config;

import com.advance.multitenancy.config.datasoruce.DataSourceRouteInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc 설정
 */
@EnableWebMvc
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final DataSourceRouteInterceptor dataSourceRouteInterceptor;

    /**
     * 인터셉터 설정
     *
     * @param registry 레지스트리
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataSourceRouteInterceptor).addPathPatterns("/**");
    }

}
