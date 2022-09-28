package hello.servlet.web.frontcontroller.V3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.V3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username=paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelView mv = new ModelView("save-result"); // view 의 논리 이름 설정
        mv.getModel().put("member",member); // 가져온 model 에다가 저장할 member 정보 넣기

        return mv; // view 의 논리 이름을 설정한 것과, 저장할 model 이 들어있는 modelview 반환
    }
}
