package com.jmy.springstudy.controller;

import com.jmy.springstudy.domain.Member;
import com.jmy.springstudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 컴포넌트 스캔 방식, 자바 코드 방식에서도 쓰임
public class MemberController {

    private final MemberService memberService;

    @Autowired//의존관계 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    } // 스프링 컨테이너에 넣어줌, 멤버 서비스를 가져다가 연결 시켜줌
    //필드 방식

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMembersForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();// 컨트롤 알트 v
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
