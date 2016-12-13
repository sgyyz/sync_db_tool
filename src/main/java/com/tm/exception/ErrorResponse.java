package com.tm.exception;

import java.util.Date;

public class ErrorResponse {
    private long timestamp;
    private int status;
    private String error;
    private ErrorCode code;
    private String message;

    public ErrorResponse(BaseException ex) {
        this.timestamp = new Date().getTime();
        this.status = ex.getStatus().value();
        this.error = ex.getStatus().getReasonPhrase();
        this.code = ex.getErrorCode();
        this.message = ex.getErrorMessage();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ErrorCode getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }
}
