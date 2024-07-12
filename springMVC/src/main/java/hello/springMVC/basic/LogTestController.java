package hello.springMVC.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        // log의 레벨을 지정
        log.trace("trace log= {}", name);
        log.debug("trace log= {}", name);
        log.info("trace log= {}", name);
        log.warn("trace log= {}", name);
        log.error("info log = {}", name);

        return "ok";
    }
}
