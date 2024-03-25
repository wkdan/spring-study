package com.jmy.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello") // Get 메서드, 주소창에 /hello 입력하는 부분
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");// data는 hello.html에 매핑(모델)
        return "hello"; // 템플릿에 있는 hello.html 파일과 매칭
    }
}


