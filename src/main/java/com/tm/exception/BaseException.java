package com.tm.exception;

import org.springframework.http.HttpStatus;

import static com.tm.exception.ErrorCode.UNKNOWN;

public class BaseException extends RuntimeException {
    private final HttpStatus status;
    private final ErrorCode errorCode;
    private final String errorMessage;

    protected BaseException(HttpStatus status) {
        this(status, UNKNOWN, "unknown error");
    }

    protected BaseException(HttpStatus status, ErrorCode errorCode) {
        this(status, errorCode, errorCode.toString().toLowerCase());
    }

    protected BaseException(HttpStatus status, ErrorCode errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
