package com.example.springtest.service.controller;

import com.example.springtest.annotation.SMStringAnno;
import com.example.springtest.service.mapper.NeosMapper;
import com.example.springtest.service.service.iService.INeosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class NewController {

    @Autowired
    INeosService neosService;

    @SMStringAnno(value = "이성민2")
    String testString;

    @RequestMapping("/")
    public String hello(){
        System.out.println("NewController Connection");
        return "Hello World";
    }

    //@Scheduled(fixedRate = 1000)
    /*@Scheduled(cron = "0/1 * * * * *")
    public void scheduler() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Date now = new Date();
      String strDate = sdf.format(now);
      System.out.println("스케쥴러 동작 : "+strDate);
    }*/
}
