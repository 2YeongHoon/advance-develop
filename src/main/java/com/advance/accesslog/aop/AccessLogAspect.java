package com.advance.accesslog.aop;

import com.advance.accesslog.application.AccessLogService;
import com.advance.accesslog.application.dto.AccessLogDto;
import com.advance.accesslog.context.RequestLogContext;
import java.io.UnsupportedEncodingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessLogAspect {

    private static final String EMPTY_BODY = "empty";
    private static final String MULTI_PART_TYPE = "multipart";
    private final AccessLogService accessLogService;

    @Pointcut("(within(@org.springframework.stereotype.Controller *) || within(@org.springframework.web.bind.annotation.RestController *)) "
        + "&& !within(springfox..*)") // Swagger
    public void controllerMethods() {}

    @AfterReturning(pointcut = "controllerMethods()", returning = "response")
    public void afterController(Object response) {
        AccessLogDto dto = RequestLogContext.get();
        dto.setParams(getBodyFromWrappedRequest());
        accessLogService.saveAccessLog(dto);
    }

    private String getBodyFromWrappedRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) return EMPTY_BODY;

        HttpServletRequest request = attrs.getRequest();
        if (!(request instanceof ContentCachingRequestWrapper)) {
            return EMPTY_BODY;
        }

        String contentType = request.getContentType();
        if (contentType != null && contentType.toLowerCase().startsWith(MULTI_PART_TYPE)) {
            return MULTI_PART_TYPE;
        }

        ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
        byte[] buf = wrapper.getContentAsByteArray();

        if (buf.length == 0) return EMPTY_BODY;

        try {
            return new String(buf, wrapper.getCharacterEncoding());
        } catch (UnsupportedEncodingException e) {
            return "[unknown encoding]";
        }
    }

}