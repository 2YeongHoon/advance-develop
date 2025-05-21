package com.advance.accesslog.filter;

import com.advance.accesslog.application.dto.AccessLogDto;
import com.advance.accesslog.context.RequestLogContext;
import java.io.IOException;
import java.time.LocalDateTime;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Component
@RequiredArgsConstructor
public class AccessLogFilter extends OncePerRequestFilter {

    private static final String ANONYMOUS_USER_ID = "anonymous";
    // TODO: 토큰 파싱 서비스
//    private final AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

        try {
            AccessLogDto dto = buildAccessLogDto(wrappedRequest);
            RequestLogContext.set(dto);
            filterChain.doFilter(wrappedRequest, response);
        } finally {
            RequestLogContext.clear();
        }
    }

    private AccessLogDto buildAccessLogDto(ContentCachingRequestWrapper request) {
        return AccessLogDto.builder()
            .ip(request.getRemoteAddr())
            .queryString(request.getQueryString())
            .uri(request.getRequestURI().toString())
            .method(request.getMethod())
//            .userId(authenticationService.findDisAssembleUserId().orElse(ANONYMOUS_USER_ID))
            .accessedAt(LocalDateTime.now())
            .build();
    }

}