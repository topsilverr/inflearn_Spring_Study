package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); // 저장소에 회원 정보 저장
    Optional<Member> findById(Long id); //Null 반환 시 Optional 로 감싸서 반환
    Optional<Member> findByNane(String name);
    List<Member> findAll();
}
