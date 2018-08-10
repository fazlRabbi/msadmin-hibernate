<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Settings</title>

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
    <form:form action="/settings/updatePreperationStart" class="centerContent" method="post" modelAttribute="settings">
        <h4>Number of weeks before appointment preperation opens</h4>
        <span class="showing">
            <p>${settings.appointmentPreperationMinStart}</p>
            <a href="#" id="numWeeksEditLink" onclick="editPrepStart()">Edit</a>
        </span>
        <span class="hiding input-group" id="numWeeksInput">
            <form:input class="text-box single-line" path="appointmentPreperationMinStart" type="number" value=""/>
            <span class="input-group-btn">
                <button class="btn btn-default numWeeksButton" type="submit">Save</button>
            </span>
        </span>
    </form:form>
</div>
</body>
<script>
    $(document)
            .ready(function () {
                $(".hiding").css('display', 'none');
                $(".showing").css('display', 'inline-flex');
            });
    function editPrepStart() {
        console.log("edit");
        $(".hiding").css('display', 'inline-flex');
        $(".showing").css('display', 'none');
    }
    ;
</script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</html>