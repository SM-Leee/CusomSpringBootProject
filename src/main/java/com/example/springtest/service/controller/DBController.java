package com.example.springtest.service.controller;

import com.example.springtest.service.service.iService.INeosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class DBController {
    @Autowired
    INeosService neosService;

    @GetMapping("/comp")
    public List<Object> comp(){
        return neosService.comp();
    }

    @RequestMapping("/pid")
    public List<Object> pid() { return neosService.his(); }
}
