package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CommonException.class})
    public ResponseEntity<Map<String, Object>> handleCommonException(CommonException exception, WebRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", new Date());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Common Exception");
        error.put("message", exception.getMessage());
        error.put("path", ((ServletWebRequest) request).getRequest().getRequestURI().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
