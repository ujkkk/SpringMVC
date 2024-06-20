package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // form에서 넘어온 데이터를 받아서 저장
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));

        // 저장
        Member member = new Member(username, age);
        memberRepository.save(member);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>회원 저장 완료</h1>\n" +
                "    <ul>\n" +
                "        <li>username=" + member.getUsername() + "</li>\n" +
                "        <li>age=" + member.getAge() + "</li>\n" +
                "    </ul>\n" +
                "    <a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>"
        );
    }
}
