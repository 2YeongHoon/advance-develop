package com.advance.multitenancy.service;

import com.advance.multitenancy.entity.Test;
import com.advance.multitenancy.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestRepository testRepository;

    @Transactional
    public void create() {
        Test test = new Test("name");
        testRepository.save(test);
    }

}
