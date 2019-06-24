<%@ page import="ru.job4j.http.User" %>
<%@ page import="ru.job4j.http.ValidateService" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 24.06.2019
  Time: 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
</head>
<body>

<table style="border: black" border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>E-Mail</th>
        <th>Actual Date</th>
    </tr>
    <% for (User user : ValidateService.getInstance().findAll()) { %>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/edit" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="submit" value="Edit">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/list" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <% } %>
</table>
<br/>
<form action="<%=request.getContextPath()%>/create" method="get">
    <input type='submit' value='Create New User'/>
</form>
</body>
</html>