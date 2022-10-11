package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
// @Controller 을 사용하면 String 반환할 경우 반환된 String 을 뷰 이름으로 인식하여 그 문자열로 이루어진 뷰를 찾고 그 뷰가 랜더링 됨
// @RestController 를 사용하면 String 반환 가능 => String 을 반환하면 그 반환 값으로 뷰를 찾는 것이 아니라 HTTP 메시지 바디에 바로 입력됨
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass()); => @Slf4j 를 사용하면 필요없음!

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name); // name = Spring 만 출력됨 -> 모든 서버에 남음

        // application.properties 에 아래 코드를 작성하여 설정가능 -> 배포할 때 설정 가능!
        // #hello.springmvc 패키지와 그 하위의 로그 레벨 설정
        //logging.level.hello.springmvc=trace
        //기본은 info 부터 출력 logging.level.root = info 가 생략되어있는 것! => debug 로 하면 어마어마한 양의 log 가 찍힘
        log.trace("trace log = {} ", name); // log 다 볼거야 - 로컬서버
        log.debug("debug log = {} ", name); // debug 부터 볼거야 - 개발서버
        // 운영 서버에서는 info 부터
        log.info("info log = {}",name); //2022-10-12 00:52:56.055  INFO 39999 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : info log = Spring 로 출력
        log.warn("warn log = {} ", name);
        log.error("error log = {} ", name);
        // 반드시 위와 같은 형식으로 작성 (+ 연산자 쓰지 말기!)

        return "ok";
    }
}
