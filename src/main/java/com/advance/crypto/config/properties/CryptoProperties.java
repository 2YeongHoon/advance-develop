package com.advance.crypto.config.properties;

import com.advance.crypto.enums.CryptoMode;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 커스텀 데이터소스 프로퍼티
 */
@Getter
@ConfigurationProperties(prefix = "spring.encrypt")
public class CryptoProperties {

    private final String dbKey;
    private final String clientKey;

    public CryptoProperties(String dbKey, String clientKey) {
        this.dbKey = dbKey;
        this.clientKey = clientKey;
    }

    public String getKey(CryptoMode mode) {
        return CryptoMode.DB.equals(mode) ? dbKey : clientKey;
    }

}