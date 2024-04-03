package com.jmy.springstudy;


import com.jmy.springstudy.repository.MemberRepository;
import com.jmy.springstudy.repository.MemoryMemberRepository;
import com.jmy.springstudy.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//컴포넌트 스캔 방식을 사용하지 않고 자바코드로 직접 연결하기 위한 클래스
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());//스프링 컨테이너 의존관계 설정
    } // 스프링 빈에 등록 (멤버서비스)

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository(); // 멤버 레포지토리는 인터페이스, 메모리 멤버 인터페이스는 구현체, 따라서 메모리~ 넣어줌
    }//스프링 빈에 등록 (컨트롤 p 하면 의존관계 보임)

}
