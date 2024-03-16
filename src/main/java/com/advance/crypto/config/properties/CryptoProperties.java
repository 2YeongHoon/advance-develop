package com.advance.crypto.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 커스텀 데이터소스 프로퍼티
 */
@Getter
@ConfigurationProperties(prefix = "spring.encrypt")
public class CryptoProperties {

    private final String key;

    public CryptoProperties(String key) {
        this.key = key;
    }

}