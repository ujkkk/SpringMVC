<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.repository.MemberRepository" %><%--
  Created by IntelliJ IDEA.
  User: yujin
  Date: 2024/06/21
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // request, response는 자동으로 생성됨, jsp가 서블릿으로 자동 변환돼서 사용돼서
    MemberRepository memberRepository = MemberRepository.getInstance();

    // form에서 넘어온 데이터를 받아서 저장
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    // 저장
    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
