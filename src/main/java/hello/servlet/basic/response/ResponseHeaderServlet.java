package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setStatus(HttpServletResponse.SC_OK);
       resp.setHeader("Content-Type", "text/plain");
       resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
       resp.setHeader("Pragma", "no-cache");
       resp.setHeader("my-header", "hello");

       cookie(resp);
       PrintWriter writer = resp.getWriter();
       writer.println("ok");
    }

    private void cookie(HttpServletResponse resp) {
        Cookie cokie = new Cookie("myCookie", "good");
        cokie.setMaxAge(600);
        resp.addCookie(cokie);
    }

    protected void content(HttpServletResponse resp)  {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
    }
}
