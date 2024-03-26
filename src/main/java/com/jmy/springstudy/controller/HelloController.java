package com.jmy.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // Get 메서드, 주소창에 /hello 입력하는 부분
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");// data는 hello.html에 매핑(모델)
        return "hello"; // 템플릿에 있는 hello.html 파일과 매칭
    }

    @GetMapping("hello-mvc")///http://localhost:8080/hello-mvc?name="여기 있는 텍스트 넘겨줌"
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);//모델.애트리뷰트에서 param으로 넘어온 name 받음
        return "hello-template";
    }
    @GetMapping("hello-string")// 리스폰스 바디는 http 헤더부와 바디부중 바디부에 데이터를 직접 넣는다는 뜻
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //html 태그가 없고, 주소창에 적은 문자 그대로 내려감
    }

    @GetMapping("hello-api") // http://localhost:8080/hello-api?name=~~~ 치면 JSON 구조로 만들어줌
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();// ctrl shift enter 누르면 괄호 닫고 자동 완성
        hello.setName(name);
        return hello;
    }//api 방식은 객체를 반환함

    static class Hello { //이렇게 static class로 만들면 이 클래스 안에서 이 클래스를 또 쓸 수 있음
        private String name;//javabin 표준 방식

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        } // 우클릭 > 생성 > getter, setter 선택하면 바로 만들어짐
    }
}




