package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member에 sequence를 id 로 저장
        store.put(member.getId(), member); // HashMap 에 저장 member의 id, memberd의 name
        return member; //member 객체 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //id 에 해당하는 value(name) return
    }

    @Override
    public Optional<Member> findByNane(String name) {
        return store.values().stream()
                .filter((member -> member.getName().equals(name)))
                .findAny(); // 위 조건에 부합하는 요소 중 제일 먼저 찾은 요소 리턴

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
