package com.jmy.springstudy.repository;

import com.jmy.springstudy.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository() ;

    @AfterEach // 각 테스트 종료 후 repository 클리어
    public void afterEach() {
        repository.clearStore();
    }
        @Test
        public void  save() {
            Member member = new Member();
            member.setName("spring"); // Name에 spring 할당

            repository.save(member); // 세이브

            Member result = repository.findById(member.getId()).get(); // 값이 제대로 저장되나 확인, 옵셔널에서 값을 꺼내올 땐 get으로 가능
            //Assertions.assertEquals(member, result); // 맞게 저장했으면 정상 구동
            assertThat(member).isEqualTo(result);//assertj 사용
        }

        @Test
        public void findByName() { // shift + f6 리팩토링
            Member member1 = new Member();
            member1.setName("spring1"); // Name에 spring1 할당

            repository.save(member1); // 세이브

            Member member2 = new Member();
            member2.setName("spring2"); // Name에 spring2 할당

            repository.save(member2); // 세이브, member1, 2가 회원에 가입 된 것

            Member result = repository.findByName("spring1").get();
            assertThat(result).isEqualTo(member1);
        }

        @Test
        public void findAll() {
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring1");
            repository.save(member2);

            List<Member> result = repository.findAll();

            assertThat(result.size()).isEqualTo(2);//갯수 확인

        }
// 각 테스트 별 순서 보장 안됨, 각자 실행됨 주의, 테스트가 하나 끝나면 데이터를 클리어 해줘야함
}//테스트는 서로 그 순서와 관계없이 설계가 되어야 함, 하나의 테스트가 끝날 때 마다 저장소 지워줘야 함(중요)
