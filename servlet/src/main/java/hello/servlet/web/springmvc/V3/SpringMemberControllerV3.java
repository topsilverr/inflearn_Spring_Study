package hello.servlet.web.springmvc.V3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm(){ // String 으로 반환해도 반환되는 값을 view 의 이름으로 인식하여 진행시킴
        return "new-form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username, // 요청 파라미터를 처리하는 애노테이션 (== request.getParameter 와 거의 비슷)
            @RequestParam("age") int age,
            Model model) // 스프링이 제공하는 모델 사용 가능
    {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);

        return "save-result";
    }

    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members",members);

        return "members";
    }
}
