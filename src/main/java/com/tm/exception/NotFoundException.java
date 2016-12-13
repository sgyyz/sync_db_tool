package com.tm.exception;

import static com.tm.exception.ErrorCode.RESOURCE_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;


public class NotFoundException extends BaseException {
    public NotFoundException() {
        super(NOT_FOUND, RESOURCE_NOT_FOUND);
    }

    public NotFoundException(ErrorCode errorCode) {
        super(NOT_FOUND, errorCode);
    }

    public NotFoundException(String errorMessage) {
        super(NOT_FOUND, RESOURCE_NOT_FOUND, errorMessage);
    }

    public NotFoundException(ErrorCode errorCode, String errorMessage) {
        super(NOT_FOUND, errorCode, errorMessage);
    }
}
