package com.advance.common.exception;

import com.advance.common.enums.Errors;
import org.springframework.http.HttpStatus;

public class BaseRuntimeException extends RuntimeException{

    private static final Errors DEFAULT_ERROR = Errors.INTERNAL_SERVER_ERROR;
    private static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    private final int errorCode;
    private final String errorMessage;
    private final int errorStatus;

    public BaseRuntimeException(String message) {
        super(message);

        this.errorCode = DEFAULT_ERROR.getCode();
        this.errorMessage = DEFAULT_ERROR.getMessage();
        this.errorStatus = DEFAULT_HTTP_STATUS.value();
    }
    public BaseRuntimeException(Errors errors, HttpStatus httpStatus) {
        super(errors.getMessage());

        this.errorCode = errors.getCode();
        this.errorMessage = errors.getMessage();
        this.errorStatus = httpStatus.value();
    }
    public BaseRuntimeException(Errors errors) {
        super(errors.getMessage());

        this.errorCode = errors.getCode();
        this.errorMessage = errors.getMessage();
        this.errorStatus = DEFAULT_HTTP_STATUS.value();
    }

}
