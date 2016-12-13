package com.tm.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends BaseException {
    protected BadRequestException() {
        super(BAD_REQUEST);
    }

    protected BadRequestException(ErrorCode errorCode) {
        super(BAD_REQUEST, errorCode);
    }

    protected BadRequestException(ErrorCode errorCode, String errorMessage) {
        super(BAD_REQUEST, errorCode, errorMessage);
    }
}
