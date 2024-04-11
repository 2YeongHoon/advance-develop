package com.advance.externalinjection.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ExternalInjectionBean {

    private String color;

    public ExternalInjectionBean(@Value("${test}") String color) {
        this.color = color;
    }

}
