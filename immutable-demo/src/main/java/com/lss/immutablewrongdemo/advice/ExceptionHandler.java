package com.lss.immutablewrongdemo.advice;

import com.lss.immutablewrongdemo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(IllegalStateException ex) {
        ErrorResponse errorBody = ErrorResponse.of(ex.getMessage());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(Exception ex) {
        ErrorResponse errorBody = ErrorResponse.of("Unexpected server error");
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
