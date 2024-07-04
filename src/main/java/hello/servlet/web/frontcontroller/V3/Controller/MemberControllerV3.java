package hello.servlet.web.frontcontroller.V3.Controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.repository.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.V3.ControllerV3;

import java.util.Map;

public class MemberControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 생성할 떄는 이름을 넘겨주어서 생성하고, model은 나중에 추가한다.
        // 방식이 통일되어야 할 것 같은데 나누는 이유는?
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // front 에서 필요한 데이터를 따로 model에 담아서 보낸다.
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv;
    }
}
