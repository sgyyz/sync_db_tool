package com.tm.exception;

public enum ErrorCode {
    // 1 ~ 100, system level error
    SYSTEM_ERROR(1),
    DATABASE_ERROR(2),
    PARAMETER_ERROR(3),

    // 1000 ~ 2000, external service error


    // 2000 ~ 3000, business related generic error
    RESOURCE_NOT_FOUND(2000),

    // 3000 ~ 4000, question related error
    QUESTION_NOT_FOUND(3000),


    // 4000 ~ 5000, answer related error


    // 5000 ~ 6000,


    // unknown error
    UNKNOWN(0);

    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
