package com.advance.hello.controller;

import com.advance.multitenancy.service.TestService
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloController(private val testService: TestService) {

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, World!"
    }

}
