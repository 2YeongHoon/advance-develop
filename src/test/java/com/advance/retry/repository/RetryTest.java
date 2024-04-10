package com.advance.retry.repository;

import com.advance.retry.aop.RetryAspect;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(RetryAspect.class)
@SpringBootTest
class RetryTest {

    @Autowired
    RetryRepository repository;

    @Test
    void 재시도_횟수를_초과화면_익셉션을_반환한다() {
        Assertions.assertThatThrownBy(() -> repository.defaultFail())
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 익셉션이_발생하면_재시도한다() {
        final int requestCount = 5;
        for (int count = 1; count <= requestCount; count++) {
            repository.threeTimeOneFail();
        }
    }
}