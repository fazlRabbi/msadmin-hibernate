<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Edit patient</title>

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
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Admin Console</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/appointments">Appointments</a></li>
                <li><a href="/patients">Patients</a></li>
                <li><a href="/doctors">Doctors</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">MS-App Settings<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/settings">General</a></li>
                        <li><a href="/settings/symptoms">Default symptoms</a></li>
                        <li><a href="/settings/subjects">Default other subjects</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="<c:url value="/logout" />">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="container">

    <form:form action="/patients/updatePatient" class="centerContent" method="post" modelAttribute="patient">
        <input value="${patient.uuid}" id="uuid" name="uuid" type="hidden" />
        <input value="${patient.ssn}" id="ssn" name="ssn" type="hidden" />
        <div class="row">
            <label>Ssn</label><br/>
            <h5>${patient.ssn}</h5>
        </div>
        <div class="row">
            <label for="firstName">Firstname</label><br/>
            <input class="text-box single-line" id="firstName" name="firstName" type="text" value="${patient.firstName}"/>
        </div>
        <div class="row">
            <label for="lastName">Lastname</label><br/>
            <input class="text-box single-line" id="lastName" name="lastName" type="text" value="${patient.lastName}"/>
        </div>
        <div class="row">
            <fmt:formatDate pattern="yyyy-MM-dd" value="${patient.birthday}" var="formattedBirthday"/>
            <label for="birthday">Birthday</label><br/>
            <input class="text-box single-line" id="birthday" name="birthday" type="date" value="${formattedBirthday}"/>
        </div>
        <div class="row">
            <label for="address">Address</label><br/>
            <input class="text-box single-line" id="address" name="address" type="text" value="${patient.address}"/>
        </div>
        <div class="row">
            <label for="zipCode">Zipcode</label><br/>
            <input class="text-box single-line" id="zipCode" name="zipCode" maxlength="4" type="number" value="${patient.zipCode}"/>
        </div>
        <div class="row">
            <label for="city">City</label><br/>
            <input class="text-box single-line" id="city" name="city" type="text" value="${patient.city}"/>
        </div>
        <div class="row">
            <label for="sex">Gender</label><br/>
            <form:select path="sex" items="${genders}" />
        </div>
        <div class="row">
            <button class="btn btn-default btn-block" type="submit">Update</button>
        </div>
    </form:form>
</div>
</body>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</html>