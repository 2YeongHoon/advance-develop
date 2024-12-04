package com.advance.user.service;

import com.advance.redis.aop.RedissonLock;
import com.advance.redis.service.RedisService;
import com.advance.user.controller.dto.CreateUserRequest;
import com.advance.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementService {

    private final RedisService redisService;
    private final UserService userService;

    public void normalSave(CreateUserRequest request){
        final String name = request.name();
        final String age = request.age();

        userService.findByNameAndAgeThrowIfExist(name, age);
        userService.save(User.of(name, age));
    }

    // TODO: 분산락 구현
    @RedissonLock(value = "#key")
    public void redisSave(CreateUserRequest request){
        final String name = request.name();
        final String age = request.age();
        final String key = name + age;

        redisService.saveUser(name, age);
        userService.findByNameAndAgeThrowIfExist(name, age);
        userService.save(User.of(name, age));
    }

}
