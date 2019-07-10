<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 10.07.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>

        function validate() {
            var message = '';
            var result = (($('#name').val() != '')
                && ($('#login').val() != '')
                && ($('#password').val() != '')
                && ($('#email').val() != '')
                && ($('#country').val() != '')
                && ($('#city').val() != ''));
            message += (($('#name').val() == '') ? "Field \"Name\" is not filled.\n" : "");
            message += (($('#login').val() == '') ? "Field \"Login\" is not filled.\n" : "");
            message += (($('#password').val() == '') ? "Field \"Password\" is not filled.\n" : "");
            message += (($('#email').val() == '') ? "Field \"E-Mail\" is not filled.\n" : "");
            message += (($('#country').val() == '') ? "Field \"Country\" is not filled.\n" : "");
            message += (($('#city').val() == '') ? "Field \"City\" is not filled.\n" : "");
            if (!result) {
                alert(message);
            }
            return result;
        }

        $(
            $.ajax('./location', {
                type: 'GET',
                complete: function(data) {
                    var userCountry = '${user.country}';
                    var countries = JSON.parse(data.responseText);
                    console.log(countries);
                    var result = "<option>Select country:</option>";
                    for (var i = 0; i !== countries.length; ++i) {
                        result += "<option";
                        if (countries[i] === userCountry) {
                            result += " selected";
                        }
                        result += " value=\""+countries[i]+"\">"+countries[i]+"</option>";
                    }
                    var countriesDiv = document.getElementById("country");
                    countriesDiv.innerHTML = result;
                    loadCities();
                }
            })
        );

        function loadCities() {
            $.ajax('./location', {
                type: 'POST',
                dataType: 'json',
                data: {"country":$('#country').val()},
                complete: function(data) {
                    var userCity = '${user.city}';
                    var cities = JSON.parse(data.responseText);
                    var result = "";
                    for (i = 0; i !== cities.length; ++i) {
                        result += "<option";
                        if(cities[i] === userCity) {
                            result += " selected";
                        }
                        result += " value=\"" + cities[i] + "\">"+cities[i]+"</option>";
                    }
                    var citiesDiv = document.getElementById("city");
                    citiesDiv.innerHTML = result;
                }
            })
        }

    </script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <input type="hidden" name="oldLogin" value="${user.login}">
    <input type="hidden" name="oldPassword" value="${user.password}">
    <c:if test="${role != 'ADMIN'}">
        <input type="hidden" name="role" value="${user.role}"/>
    </c:if>
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" name="name" id="name" value="${user.name}" placeholder="Enter name">
    </div>
    <div class="form-group">
        <label for="login">Login:</label>
        <input type="text" class="form-control" name="login" id="login" value="${user.login}" placeholder="Enter login">
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="text" class="form-control" name="password" id="password" value="${user.password}" placeholder="Enter password">
    </div>
    <div class="form-group">
        <label for="email">E-Mail:</label>
        <input type="text" class="form-control" name="email" id="email" value="${user.email}" placeholder="Enter e-mail">
    </div>
    <c:if test="${role == 'ADMIN'}">
        New Role :
        <select name="role">
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
        </select>
    </c:if>
    <br/>
    <div class="form-group">
        <label for="country">Country : </label>
        <select class="form-control" name="country" id="country" value="${user.country}" onchange="loadCities();"></select>
    </div>
    <div class="form-group">
        <label for="city">City : </label>
        <select class="form-control" name="city" id="city" value="${user.city}"></select>
    </div>
    <br/>
    <input type="submit" value="Update" onclick="return validate();">
</form>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="All Users"/>
</form>
</body>
</html>