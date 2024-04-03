package com.jmy.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 도메인 호스트 첫번째 주소 (메인 화면)
    public String home() {
        return "home";
    }
}
