package com.example.springtest.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정할때, Configuration 어노테이션을 사용한다.
//프로그램이 실행될때 설정을 하게된다.
@Configuration
public class TestBean {
    @Bean
    public String testBean1(){
        System.out.println("TestBran 테스트중");
        return "TestBean";
    }
}
