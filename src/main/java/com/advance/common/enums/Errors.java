package com.advance.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 오류 정의
 */
@Getter
@AllArgsConstructor
public enum Errors {
    EXTERNAL_API_ERROR(1, "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다"),

    NOT_FOUND_MAIL_SEND_TYPE(4001, "존재하지 않는 메일 발송 방식입니다."),

    INTERNAL_SERVER_ERROR(9999, "요청을 처리하던 중 예상하지 못한 오류가 발생했습니다");

    private final int code;
    private final String message;

}
