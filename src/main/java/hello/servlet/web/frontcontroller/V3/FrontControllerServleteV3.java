package hello.servlet.web.frontcontroller.V3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.V3.Controller.MemberControllerV3;
import hello.servlet.web.frontcontroller.V3.Controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.V3.Controller.MemberListController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServleteV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServleteV3 extends HttpServlet {
    private final Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServleteV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("FrontControllerServleteV3.service");

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 위의 로직과 비교했을 때 너무 구체적인 로직이라서 따로 뺌, 계층 맞추기
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();

        MyView myView = viewResolver(viewName);
        myView.render(mv.getModel(), request, response);
    }


    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }



}

