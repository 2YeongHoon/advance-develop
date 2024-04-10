package com.advance.retry.controller;

import com.advance.retry.repository.RetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/retry")
public class RetryController {

    private final RetryRepository repository;

    @GetMapping
    public ResponseEntity<Void> retryTest() {
        HttpStatus status = repository.threeTimeOneFail();
        return ResponseEntity.status(status).build();
    }
}
