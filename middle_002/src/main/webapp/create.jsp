<%--
  Created by IntelliJ IDEA.
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 22.06.2019
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create user</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    Name : <input type="text" name="name"><br>
    Login : <input type="text" name="login"><br>
    Email : <input type="text" name="email"><br>
    <input type="submit">
</form>
<br>
<form action="<%=request.getContextPath()%>/index.jsp">
    <input type="submit" value="User's list">
</form>
</body>
</html>