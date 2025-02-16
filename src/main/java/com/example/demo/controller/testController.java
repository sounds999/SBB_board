package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello spring.....!!~~~~";
    }

    @GetMapping("/sbb")
    public void simpleWebApplication() {
        System.out.println("안녕하세요 sbb에 오신 것을 환경합니다dddd");
    }
}
