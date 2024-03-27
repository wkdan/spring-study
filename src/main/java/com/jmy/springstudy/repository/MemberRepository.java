package com.jmy.springstudy.repository;

import com.jmy.springstudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // null 처리 대신 그 null을 옵셔널로 감쌈
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
