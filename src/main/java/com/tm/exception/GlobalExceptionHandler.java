package com.tm.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<?> handleSQLSyntaxErrorException(BaseException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex), ex.getStatus());
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleException(BaseException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex), ex.getStatus());
    }
}
