package hello.servlet.web.frontcontroller.V3.Controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.V3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 논리적인 이름을 넣는다. 공토 이름 다 빼고
        return new ModelView("new-form");
    }
}
