<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 24.06.2019
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    Name   : <input type="text" name="name"/><br/>
    Login  : <input type="text" name="login"/><br/>
    E-Mail : <input type="text" name="email"/><br/>
    <input type="submit" value="Create User"/>
</form>
<br/>
<form action="<%=request.getContextPath()%>/list" method="get">
    <input type="submit" value="All Users"/>
</form>
</body>
</html>