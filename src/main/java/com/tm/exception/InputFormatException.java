package com.tm.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by justin.li on 12/8/16.
 */
public class InputFormatException extends BaseException {


    public InputFormatException(HttpStatus status, ErrorCode errorCode, String errorMessage) {
        super(status, errorCode, errorMessage);
    }
}
