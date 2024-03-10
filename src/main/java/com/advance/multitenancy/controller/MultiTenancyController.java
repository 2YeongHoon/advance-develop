package com.advance.multitenancy.controller;

import com.advance.multitenancy.entity.Test;
import com.advance.multitenancy.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MultiTenancyController {

    private final TestRepository testRepository;

    @GetMapping("/test")
    @Transactional
    public ResponseEntity<Void> test() {
        Test test = new Test("name");
        testRepository.save(test);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
