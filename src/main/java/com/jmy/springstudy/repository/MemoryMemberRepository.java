package com.jmy.springstudy.repository;

import com.jmy.springstudy.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 이렇게 공유되는 변수는 concurrent or HashMap (동시성 문제 해결)
    private static long sequence = 0L; //시퀸스는 키 값을 생성, 실무에서는 Long 보다는 동시성 문제 고려해서 Atomic
    @Override
    public Member save(Member member) {
        member.setId(++sequence);//setId할 때 먼저 시퀸스 값을 하나 올림
        store.put(member.getId(), member);//올려 준 다음, 스토어에다 put,
        return member;// 스토어에다가 넣기 전에 멤버에 id값 세팅, 여기 세이브 전 이름은 넘어온 상태
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//아이디 찾기는 스토어에서 꺼내옴, null 반환될 가능성이 있어 옵셔널 감쌈

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()//java의 Lambda 사용, 루프로 돌리는 역할
                .filter(member -> member.getName().equals(name))//멤버에서 getName이 여기 파라미터로 넘어온 Name과 같은지 확인
                .findAny();// 루프를 돌면서 하나 찾으면 걔를 그냥 반환, 없으면 옵셔널 null로 반환
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 위에 있는 멤버들이 다 반환이 됨
    }

    public void clearStore(){ // 스토어 초기화 코드
        store.clear();
    }
}
