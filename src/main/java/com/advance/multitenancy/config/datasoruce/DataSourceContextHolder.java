package com.advance.multitenancy.config.datasoruce;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSourceContextHolder {

    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void set(String schema) {
        context.set(schema);
    }

    public static String get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}