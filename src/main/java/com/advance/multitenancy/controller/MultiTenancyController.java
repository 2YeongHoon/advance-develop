package com.advance.multitenancy.controller;

import com.advance.multitenancy.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/multi-tenancy")
public class MultiTenancyController {

    private final TestService testService;

    @GetMapping
    public ResponseEntity<Void> getTenancy(@RequestParam String id) {
        testService.create();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
