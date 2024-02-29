package com.jeans.cosmetic_project.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ErrorController {

    @ResponseBody
    @ExceptionHandler(AbstractException.class)
    public ResponseEntity errorResponse(AbstractException e) {
        HttpStatus status = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
            .code(String.valueOf(status.value()))
            .message(e.getMessage())
            .build();
        return ResponseEntity.status(status).body(body);
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity errorResponse(BadCredentialsException e) {
//        HttpStatus status = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
//            .code()
            .message(e.getMessage())
            .build();
        return ResponseEntity.ok(body);
    }
}
