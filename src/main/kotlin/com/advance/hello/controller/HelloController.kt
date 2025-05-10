package com.advance.hello.controller;

import com.advance.hello.service.HelloService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val helloService: HelloService) {

    @GetMapping("/hello")
    fun hello(): String {
        return helloService.hello()
    }

}
