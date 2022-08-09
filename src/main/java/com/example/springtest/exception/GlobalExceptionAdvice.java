package com.example.springtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestControllerAdvice
@EnableWebMvc
public class GlobalExceptionAdvice { // Exception Interceptor

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> runtimeException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Excpetion test");
        HashMap<String ,String> result = new HashMap<>();
        result.put("code", "-1");
        result.put("msg", e.getMessage() );

        return result;
    }
}
