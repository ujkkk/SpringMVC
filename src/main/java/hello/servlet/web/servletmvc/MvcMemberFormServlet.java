package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

    @WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
    public class MvcMemberFormServlet extends HttpServlet {
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String viewPath = "/WEB-INF/views/new-form.jsp";
            // Controller에서 View로 이동할 때 사용
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
            // jsp를 찾아서 넘어가서 호출
            requestDispatcher.forward(request, response);
        }
    }
