<%--
  Created by IntelliJ IDEA.
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 22.06.2019
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ru.job4j.http.User" %>
<%@ page import="ru.job4j.http.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's list</title>
</head>
<body>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th>
            Id
        </th>
        <th>
            Name
        </th>
        <th>
            Login
        </th>
        <th>
            Email
        </th>
        <th>
            Create Date
        </th>
    </tr>
    <% for (User user : ValidateService.getInstance().findAll()) { %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td><form action="<%=request.getContextPath()%>/edit.jsp">
            <input type="hidden" name="id" value="<%=user.getId()%>">
            <input type="submit" value="edit">
        </form></td>
        <td><form action="<%=request.getContextPath()%>/list" method="post">
            <input type="hidden" name="id" value="<%=user.getId()%>">
            <input type="submit" value="delete">
        </form></td>
    </tr>
    <% } %>
</table>
<br/>
<form action="<%=request.getContextPath()%>/create.jsp">
    <input type="submit" value="Add user">
</form>
</body>
</html>