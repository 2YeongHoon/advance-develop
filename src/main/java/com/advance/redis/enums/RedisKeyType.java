package com.advance.redis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RedisKeyType {
    REFRESH_TOKEN("refresh_token", 5),
    USER("user", 10)
    ;

    private final String prefix;
    private final int ttl;

}
