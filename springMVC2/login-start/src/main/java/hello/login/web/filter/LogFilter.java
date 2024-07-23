package hello.login.web.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("log filter doFilter");

        //ServletRequest를 HttpServletRequest롤 타입 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try{
            // 사용자가 요청한 uuid와 URI 생성
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            // 댜음 필터가 없어도 이걸로 컨트롤러까지 호출하기 때문에 무조건 있어야한다
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e){
            throw e;
        } finally {
            log.info("RESPONSE [{}] [{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
