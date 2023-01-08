package jpabook.japshop.service;

import jpabook.japshop.domain.Member;
import jpabook.japshop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("지상은");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test()
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("상은");

        Member member2 = new Member();
        member2.setName("상은");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2);
        } catch (IllegalStateException e){
            return;
        }


        //then
        fail("예외가 발생한다.");
    }
}