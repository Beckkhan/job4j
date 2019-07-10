<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 10.07.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table table-striped" align="center" style="border: black" border="1" cellpadding="1" cellspacing="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Password</th>
        <th>E-Mail</th>
        <th>Actual Date</th>
        <th>Role</th>
        <th>City</th>
        <th>Country</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <c:if test="${(user.login == login) || role == 'ADMIN'}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.createDate}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td><c:out value="${user.city}"/></td>
                <td><c:out value="${user.country}"/></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit" method="get">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="submit" value="Edit">
                    </form>
                </td>
                <c:if test="${role == 'ADMIN'}">
                    <td>
                        <form action="${pageContext.servletContext.contextPath}/" method="post">
                            <input type="hidden" name="id" value="${user.id}">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:if>
    </c:forEach>
</table>
<br/>
<c:if test="${role == 'ADMIN'}">
    <form action="${pageContext.servletContext.contextPath}/create" method="get">
        <input type='submit' value='Create New User'/>
    </form>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <input type="submit" value="Sign Out"/>
</form>
</body>
</html>