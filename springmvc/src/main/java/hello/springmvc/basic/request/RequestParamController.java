package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParam(HttpServletRequest request, HttpServletResponse response)throws IOException{
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={} , age={}",username,age);

        response.getWriter().write("ok");
    }

    @ResponseBody // 반환되는 값이 view 이름으로 인식되어 view 조회를 하는 것이 아닌, 반환되는 값을 http 메세지 바디에 넣어서 반환
                  // -> @RestController 와 같은 기능
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username={}, age={}",memberName,memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username, // 파라미터 이름과 변수명이 같으면 파라미터 이름 생략 가능
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
}