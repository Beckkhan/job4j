<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 01.07.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="hidden" name="oldLogin" value="${user.login}">
    <input type="hidden" name="oldPassword" value="${user.password}">
    <c:if test="${role != 'ADMIN'}">
        <input type="hidden" name="role" value="${user.role}"/>
    </c:if>
    New Name  :   <input type="text" name="name" value="${user.name}"/><br/>
    New Login :   <input type="text" name="login" value="${user.login}"/><br/>
    New Password: <input type="text" name="password" value="${user.password}"/><br/>
    New E-Mail :  <input type="text" name="email" value="${user.email}"/><br/>
    <c:if test="${role == 'ADMIN'}">
        New Role :
        <select name="role">
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
        </select>
    </c:if>
    <br/>
    <input type="submit" value="Update"/>
</form>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="All Users"/>
</form>
</body>
</html>