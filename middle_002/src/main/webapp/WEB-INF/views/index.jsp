<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 27.06.2019
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
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.name}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><c:out value="${user.email}"/></td>
        <td><c:out value="${user.createDate}"/></td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" value="Edit">
            </form>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/" method="post">
                <input type="hidden" name="id" value="${user.id}">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
<br/>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type='submit' value='Create New User'/>
</form>
</body>
</html>