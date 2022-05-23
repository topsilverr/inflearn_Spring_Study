package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository=new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("지상은");

        repository.save(member);

        Member result= repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
        //Assertions.assertEquals(member,result);
        //System.out.println("result = " + (result==member));
    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setName("지상은");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("박찬석");
        repository.save(member2);

        Member result=repository.findByName("박찬석").get();

        assertThat(result).isEqualTo(member2);
        System.out.println(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("박찬석");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("지상은");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

// test 실행 순서 보장 안됨 즉 순서에 의존관계 없이 test 코드 작성해야함 => findAll 먼저 실행됨 => 다른 객체가 튀어나옴 ==> afterEach 필수