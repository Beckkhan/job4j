<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 27.06.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    New Name  : <input type="text" name="name" value="${user.name}"/><br/>
    New Login : <input type="text" name="login" value="${user.login}"/><br/>
    New E-Mail: <input type="text" name="email" value="${user.email}"/><br/>
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="submit" value="Update"/>
</form>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="All Users"/>
</form>
</body>
</html>