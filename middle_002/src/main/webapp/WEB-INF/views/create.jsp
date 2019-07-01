<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 27.06.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    Name   : <input type="text" name="name"/><br/>
    Login  : <input type="text" name="login"/><br/>
    Password  : <input type="text" name="password"/><br/>
    E-Mail : <input type="text" name="email"/><br/>
    <input type="submit" value="Create User"/>
</form>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="All Users"/>
</form>
</body>
</html>