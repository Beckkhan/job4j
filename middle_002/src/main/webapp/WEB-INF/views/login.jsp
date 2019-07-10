<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 10.07.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<script>
    function validateLogin() {
        var message = '';
        var result = (($('#login').val() != '') && ($('#password').val() != ''));
        message += (($('#login').val() == '') ? "Field \"Login\" is not filled.\n" : "");
        message += (($('#password').val() == '') ? "Field \"Password\" is not filled.\n" : "");
        if (!result) {
            alert(message);
        }
        return result;
    }
</script>
<body>
<div class="container">
    <h3>Enter Login & Password:</h3>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" name="login" id="login" placeholder="Enter login">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="Enter password">
        </div>
        <button type="submit" class="btn btn-default" onclick="return validateLogin();">Sign In</button>
    </form>
    <div class="form-group">
        <c:if test="${error != ''}">
            <div style="background-color:red">
                <c:out value="${error}"/>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>