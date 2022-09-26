package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository { //static 으로 구성 -> new MemberRepository가 많이 호출 되어도 store과 sequence는 하나로 사용됨.

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; // id가 하나씩 증가하는 sequence로 사용

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){ // 외부에서 MemberRepository가 필요한 경우에는 getInstance로 하나의 동일한 memberRepository 사용
        return instance;
    }
    private MemberRepository(){ // 싱글톤으로 생성하기 위해 생성자를 private 으로 선언

    }

    public Member save(Member member){
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
