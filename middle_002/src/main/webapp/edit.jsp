<%--
  Created by IntelliJ IDEA.
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 22.06.2019
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.job4j.http.User" %>
<%@ page import="ru.job4j.http.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <% User user = ValidateService.getInstance().findById(new User(request.getParameter("id"))); %>
    Name : <input type="test" name="name" value="<%=user.getName()%>"><br>
    Login : <input type="test" name="login" value="<%=user.getLogin()%>"><br>
    Email : <input type="test" name="email" value="<%=user.getEmail()%>"><br>
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <input type="submit">
</form>
<br>
<form action="<%=request.getContextPath()%>/index.jsp">
    <input type="submit" value="User's list">
</form>
</body>
</html>