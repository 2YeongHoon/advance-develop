package com.advance.crypto.aspect;

import com.advance.crypto.controller.dto.CryptoRequest;
import com.advance.crypto.enums.CryptoMode;
import com.advance.crypto.utils.Aes256Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class ClientCryptoAspect {

    private final Aes256Utils aes256Utils;

    // 암호화 변경 AOP
    @Before("execution(* com.advance.crypto.controller..*(..))")
    public void doLog(JoinPoint joinPoint) throws Throwable {
        Object[] objs = joinPoint.getArgs();
        for (Object obj : objs) {
            if (obj instanceof CryptoRequest) {
                CryptoRequest request = CryptoRequest.class.cast(obj);
                String name = decrypt(request.getName());
                request.setName(name);
            }
        }
    }

    private String decrypt(String txt) {
        return aes256Utils.decrypt(CryptoMode.CLIENT, txt);
    }

}
