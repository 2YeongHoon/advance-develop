package com.advance.accesslog.context;

import com.advance.accesslog.application.dto.AccessLogDto;

public class RequestLogContext {

    private static final ThreadLocal<AccessLogDto> context = new ThreadLocal<>();

    public static void set(AccessLogDto dto) {
        context.set(dto);
    }

    public static AccessLogDto get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }

}
