package com.jmy.springstudy.service;

import com.jmy.springstudy.domain.Member;
import com.jmy.springstudy.repository.MemberRepository;
import com.jmy.springstudy.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//@Service // 스프링에서 호출할 수 있게 써줌
public class MemberService { // 컨트롤 쉬프트 T > 테스트 만들기

    private final MemberRepository memberRepository; //constructor 사용, 기존의 new ~~ 지정하는 방식은 DB를 2개 만들 위험이 있음

    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }//멤버 리포지토리를 직접 내가 new로 생성하는게 아니라 외부에서 넣을 수 있게 바꿈


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //중복 회원 제외
        validateDuplicateMember(member);// 컨트롤 알트 m으로 메서드 변환, 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }



    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 컨트롤 알트 v = 리턴 바로 해줌
            .ifPresent(m -> {        //ifPresent > 만약에 값이 있으면
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /** 전체 회원 조회
     */
    public List<Member> findMembers() {
       return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
