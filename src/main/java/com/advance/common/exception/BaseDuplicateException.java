package com.advance.common.exception;

import com.advance.common.enums.Errors;
import org.springframework.http.HttpStatus;

public class BaseDuplicateException extends BaseRuntimeException{

    public BaseDuplicateException(String message) {
        super(message);
    }

    public BaseDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseDuplicateException(Errors errors) {
        super(errors, HttpStatus.CONFLICT);
    }

}
