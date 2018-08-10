<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
    <title>Login</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link href="../../resources/css/site.css" rel="stylesheet" type="text/css">
    <script src="../../resources/js/jquery-3.1.1.min.js"></script>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a href="#" class="navbar-brand">Admin Console</a>
        </div>
    </div>
</div>
<div class="container">
    <c:url var="loginUrl" value="/login" />
    <form action="${loginUrl}" class="centerContent" method="post">
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                <p>Invalid email or password.</p>
            </div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert alert-success">
                <p>You have been logged out successfully.</p>
            </div>
        </c:if>

        <div class="row">
            <label for="email">Email</label><br/>
            <input class="text-box single-line" name="email" type="email" id="email"/>
        </div>
        <div class="row">
            <label for="password">Password</label><br/>
            <input class="text-box single-line password" name="password" id="password" required type="password" value=""/>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /></strong>
        <div class="row">
            <button class="btn btn-default btn-block" type="submit">Login</button>
        </div>
    </form>
</div>
</body>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</html>