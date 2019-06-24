<%@ page import="ru.job4j.http.User" %>
<%@ page import="ru.job4j.http.ValidateService" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 24.06.2019
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<%String id = request.getParameter("id");%>
<%ValidateService logic = ValidateService.getInstance();%>
<%User user = logic.findById(new User(id));%>
<form action="<%=request.getContextPath()%>/edit" method="post">
    New Name  : <input type="text" name="name" value="<%=user.getName()%>"/><br/>
    New Login : <input type="text" name="login" value="<%=user.getLogin()%>"/><br/>
    New E-Mail: <input type="text" name="email" value="<%=user.getEmail()%>"/><br/>
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>"/>
    <input type="submit" value="Update"/>
</form>
<br/>
<form action="<%=request.getContextPath()%>/list" method="get">
    <input type="submit" value="All Users"/>
</form>
</body>
</html>