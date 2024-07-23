package hello.login.web.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 호출시점이 완전히 분리되어있다. 후에 지정한 값을 postHandle, afterCompletion에서 쓰기 위해선 어딘가에 담어두어야한다.
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        request.setAttribute(LOG_ID, uuid);

        if (handler instanceof HandlerMethod){
            HandlerMethod hm = (HandlerMethod) handler;// 호출할 컨트롤러 메서드의 모든 정보가 포함되너 있다.

        }
        // 핸들러 어댑터가 호출안되고, 무슨 핸드러가 호출될지는 알려준다
        log.info("REQUEST, [{}][{}][{}]", uuid, requestURI, handler);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // uuid 넘기기
        String requestURI = request.getRequestURI();
        Object logId = request.getAttribute(LOG_ID);

        log.info("RESPONSE, [{}][{}][{}]", logId, requestURI, handler);

        if(ex != null){
            log.error("afterCompletion!!", ex);
        }
    }
}
