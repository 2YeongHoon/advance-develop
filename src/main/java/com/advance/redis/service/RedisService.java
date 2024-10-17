package com.advance.redis.service;

import com.advance.redis.enums.RedisKeyType;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate template;

    public void saveUser(String userId, String data) {
        template.opsForValue().set(
            addPrefix(RedisKeyType.USER, userId),
            data,
            RedisKeyType.USER.getTtl(),
            TimeUnit.MINUTES);
    }

    private String addPrefix(RedisKeyType type, String key) {
        return String.format("%s:%s", type.getPrefix(), key);
    }

}

