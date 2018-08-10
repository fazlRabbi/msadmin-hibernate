<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Patients</title>

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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">MS-App Settings<span class="caret"></span></a>
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
    <button class="btn btn-default" onclick="location.href = '/patients/createPatient'">Create new Patient</button>
    <hr/>
    <table>
        <tr>
            <th>Ssn</th>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Address</th>
            <th>Zipcode</th>
            <th>City</th>
            <th>Gender</th>
            <th>Birthday</th>
            <th>Options</th>
        </tr>
        <c:forEach var="patient" items="${patients}">
            <tr>
                <th>${patient.ssn}</th>
                <th>${patient.firstName}</th>
                <th>${patient.lastName}</th>
                <th>${patient.address}</th>
                <th>${patient.zipCode}</th>
                <th>${patient.city}</th>
                <th>${patient.sex}</th>
                <th><fmt:formatDate pattern="dd-MM-yyyy" value="${patient.birthday}" var="formattedBirthday"/>
                        ${formattedBirthday}</th>
                <td>
                    <a href="/patients/editPatient/${patient.ssn}" id="${patient.ssn}">Edit</a> |
                    <a href="/patients/deletePatient/${patient.ssn}" id="${patient.ssn}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</html>