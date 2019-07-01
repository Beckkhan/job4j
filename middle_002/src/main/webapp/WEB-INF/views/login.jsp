<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 01.07.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color:red">
        <c:out value="${error}"/>
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    Login  : <input type="text" name="login"/><br/>
    Password : <input type="password" name="password"/><br/>
    <input type="submit" value="Sign In"/>
</form>
</body>
</html>