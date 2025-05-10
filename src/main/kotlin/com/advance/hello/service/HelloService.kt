package com.advance.hello.service

import org.springframework.stereotype.Service

@Service
class HelloService {

    fun hello(): String {
        return "Hello world!";
    }

}