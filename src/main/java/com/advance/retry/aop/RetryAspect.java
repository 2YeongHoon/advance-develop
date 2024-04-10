package com.advance.retry.aop;

import com.advance.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {

    @Around("@annotation(retry)")
    public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        final int retryMaxCount = retry.value();
        Exception exceptionHolder = null;

        for (int retryCount = 1; retryCount <= retryMaxCount; retryCount++) {
            try {
                log.info("[Retry] try count = {}/{}", retryCount, retryMaxCount);
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
            }
        }

        throw exceptionHolder;
    }
}
