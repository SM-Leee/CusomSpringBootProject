package com.example.springtest.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Excep")
public class ExceptionController {

    @RequestMapping("/interceptor")
    public void ExceptionInterceptor() throws Exception {
        throw new Exception();
    }
}
