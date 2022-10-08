package hello.servlet.web.springmvc.V1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// 스프링이 자동으로 스프링 빈으로 등록 -> controller 애노테이션 내부에 component 애노테이션이 들어있어서 컴포넌트 스캔 대상이 됨
// 스프링 MVC 에서 애노테이션 기반 컨트롤러로 인식 == RequestMappingHandlerMapping 이 매핑 정보로 인식할 수 있음
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    // 요청 정보를 매핑
    // 해당 url 이 호출되면 아래 메서드가 호출되고, 애노테이션 기반으로 동작하기 때문에 메서드의 이름은 자유롭게!
    public ModelAndView process(){
        //ModelAndView 는 모델과 뷰의 정보를 담아 반환하는 것
        return new ModelAndView("new-form");
    }
}
