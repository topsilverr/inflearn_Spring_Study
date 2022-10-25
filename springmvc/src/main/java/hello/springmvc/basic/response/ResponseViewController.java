package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data","hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data","hello!");

        return "response/hello"; // view 의 논리적 이름 취;
    }

    // 경로의 이름이 호출하고싶은 뷰의 이름과 동일한 경우!
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello!");
    }
}
