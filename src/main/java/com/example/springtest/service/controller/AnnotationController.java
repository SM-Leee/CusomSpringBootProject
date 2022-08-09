package com.example.springtest.service.controller;

import com.example.springtest.annotation.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Test
@RestController
public class AnnotationController {

    @RequestMapping("/annotationTest")
    public String annotaionTest(){
        return "AnnotaionTest";
    }
}
