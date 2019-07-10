<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%--
  User: Khan Vyacheslav (mailto: beckkhan@mail.ru)
  Date: 10.07.2019
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
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
                    var countries = JSON.parse(data.responseText);
                    var result = "<option value=\"\">Choose country:</option>";
                    for (var i = 0; i !== countries.length; ++i) {
                        result += "<option value=\""+countries[i]+"\">"+countries[i]+"</option>";
                    }
                    var countriesDiv = document.getElementById("selectCountry");
                    countriesDiv.innerHTML = result;
                }
            })
        );

        function loadCities() {
            $.ajax('./location', {
                type: 'POST',
                dataType: 'json',
                data: {"country":$('#selectCountry').val()},
                complete: function(data) {
                    var cities = JSON.parse(data.responseText);
                    var result = "";
                    for (i = 0; i !== cities.length; ++i) {
                        result += "<option value="+cities[i]+" >"+cities[i]+"</option>";
                    }
                    var citiesDiv = document.getElementById("selectCity");
                    citiesDiv.innerHTML = result;
                }
            })
        }

    </script>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" name="name" id="name" placeholder="Enter name">
    </div>
    <div class="form-group">
        <label for="login">Login:</label>
        <input type="text" class="form-control" name="login" id="login" placeholder="Enter login">
    </div>
    <div class="form-group">
        <label for="password">Password:</label>
        <input type="text" class="form-control" name="password" id="password" placeholder="Enter password">
    </div>
    <div class="form-group">
        <label for="email">E-Mail:</label>
        <input type="text" class="form-control" name="email" id="email" placeholder="Enter e-mail">
    </div>
    <div class="form-group">
        <label for="selectCountry">Country : </label>
        <select class="form-control" name="country" id="selectCountry" onchange="loadCities();">

        </select>
    </div>
    <div class="form-group" id="selectCityDiv">
        <label for="selectCity">City : </label>
        <select class="form-control" name="city" id="selectCity">
        </select>
    </div>
    <input type="submit" value="Create User" onclick="return validate();">
</form>
<br/>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="submit" value="All Users"/>
</form>
</body>
</html>