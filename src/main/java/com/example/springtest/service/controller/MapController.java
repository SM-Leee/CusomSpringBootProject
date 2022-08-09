package com.example.springtest.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController(value="/map")
public class MapController {

    @RequestMapping("/test1")
    public void MapTest(){

        HashMap<String, Object> items = new HashMap<>();
    }
}
