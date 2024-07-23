package hello.login.web.session;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */
    public void createSession(Object value, HttpServletResponse response) {
        // 세션 id를 생성하고, 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        // 쿠키 생성
        Cookie meySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(meySessionCookie);
    }

    /**
     * 세션 조화
     */
    public Object getSession(HttpServletRequest request){

        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie == null){
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }


    public Cookie findCookie(HttpServletRequest  request, String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()  //병렬 처리해도 순서 상관없이 빨리 나온 애
                .orElse(null);  // 찾이못하면 null 리턴
    }

    /**
     * 세션 만료
     */
    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);

        if(sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }
}
