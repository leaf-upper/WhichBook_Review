package com.whichbook.review.review;

import com.whichbook.review.aop.TokenNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<?> handleTokenNotValidException(TokenNotValidException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
