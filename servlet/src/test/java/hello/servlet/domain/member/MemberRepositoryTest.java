package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){ // 한 테스트 이후 repository 초기화하는 코드
        memberRepository.clearStore();
    }

    @Test
    void getInstance() {
    }

    @Test
    void save() {
        //given
        Member member = new Member("sangeun", 24);
        //when
        Member saveMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(saveMember.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
        //given
        Member mem1 = new Member("mem1", 20);
        Member mem2 = new Member("mem2", 31);

        Member saveMem1 = memberRepository.save(mem1);
        Member saveMem2 = memberRepository.save(mem2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(mem1,mem2);
    }

    @Test
    void clearStore() {
    }
}