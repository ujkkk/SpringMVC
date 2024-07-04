package hello.servlet.web.frontcontroller.V5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.V3.Controller.MemberControllerV3;
import hello.servlet.web.frontcontroller.V3.Controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.V3.Controller.MemberListController;
import hello.servlet.web.frontcontroller.V3.ControllerV3;
import hello.servlet.web.frontcontroller.V5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerSeveletV5 extends HttpServlet {

    private Map<String, Object> handlerMappingMap = new HashMap<>();
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();


    public void FrontControllerServleteV5() {
        initHandlerMappingMap();
        initHandlerAdapter();
    }

    private void initHandlerAdapter() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter adapter = getHandlerAdapter(request, response, handler);

        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();
        // 뷰 반환
        MyView myView = viewResolver(viewName);

        myView.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        for (MyHandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
