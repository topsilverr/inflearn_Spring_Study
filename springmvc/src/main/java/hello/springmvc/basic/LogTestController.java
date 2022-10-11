package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @Controller 을 사용하면 String 반환 못함 View 를 반환해야함
// @RestController 를 사용하면 String 반환 가능 => String 을 반환해도 String 그대로 반환됨
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name); // name = Spring 만 출력됨 -> 모든 서버에 남음
        // application.properties 에 아래 코드를 작성하여 설정가능 -> 배포할 때 설정 가능!
        // #hello.springmvc 패키지와 그 하위의 로그 레벨 설정
        //logging.level.hello.springmvc=trace
        log.trace("trace log = {} ", name); // log 다 볼거야 - 로컬서버
        log.debug("debug log = {} ", name); // debug 부터 볼거야 - 개발서버
        // 운영 서버에서는 info 부터
        log.info("info log = {}",name); //2022-10-12 00:52:56.055  INFO 39999 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : info log = Spring 로 출력
        log.warn("warn log = {} ", name);
        log.error("error log = {} ", name);
        return "ok";
    }
}
