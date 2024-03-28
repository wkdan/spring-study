package com.jmy.springstudy.service;

import com.jmy.springstudy.domain.Member;
import com.jmy.springstudy.repository.MemoryMemberRepository;
import com.jmy.springstudy.repository.MemoryMemberRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    } // 이렇게 하면 테스트 실행할 때 마다 각각 생성 해줌, 각 테스트를 실행하기 전에
    //메모리 멤버 리포지토리 생성 > 여기에 넣고, 멤버 서비스에서 멤버 서비스로 넣어줌
    //이렇게 하면 같은 리포지토리로 됨

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 익셉션 메세지 검증

//밑의 트라이 캐치보다 위의 방식이 더 좋음
/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        }*/


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}