package com.advance.retry.repository;

import com.advance.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class RetryRepository {

    private static int retrySeq = 0;

    /**
     * 3번 요청에 1번씩 실패하는 메서드
     */
    @Retry
    public HttpStatus threeTimeOneFail() {
        retrySeq++;
        log.info("retry seq = {}", retrySeq);
        if(retrySeq % 3 == 0){
            throw new RuntimeException("예외발생");
        }
        return HttpStatus.OK;
    }

    /**
     * 실패 메서드
     */
    @Retry
    public HttpStatus defaultFail() {
        throw new RuntimeException("예외발생");
    }
}
