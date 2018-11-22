<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add professor</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resource/css/style.css">
    <script type="text/javascript" src="/resource/js/professors.js"></script>
</head>
<body>
<div class="row content">
    <nav class="col-md-3">
        <ul class="nav flex-column fixed-bottom nav-items-padding col-md-3">
            <li class="nav-item">
                <a href="/groupsPage" class="nav-link nav-link-text">Groups</a>
            </li>
            <li class="nav-item">
                <a href="/professorsPage" class="nav-link nav-link-text">Professors</a>
            </li>
            <li class="nav-item ">
                <a href="/studentsPage" class="nav-link nav-link-text">Students</a>
            </li>
            <li class="nav-item">
                <a href="/subjectsPage" class="nav-link nav-link-text">Subjects</a>
            </li>
            <li class="nav-item">
                <a href="/marksPage" class="nav-link nav-link-text">Marks</a>
            </li>
            <li class="nav-item">
                <a class="nav-link nav-link-text action-item" onclick="logout()">Log out</a>
            </li>
        </ul>
    </nav>
    <div class="col-md-9">
        <div class="container content div-center flex-column">
            <div class="title text-center">
                <h1>Add professor</h1>
            </div>
            <form action="/professorsPage">
                <div class="form-container">
                    <input type="text" class="form-control" id="nameForm" name="firstName" placeholder="First name"
                           required>
                    <input type="text" class="form-control" id="surnameForm" name="secondName" placeholder="Second name"
                           required>
                    <input class="btn btn-app-color" type="submit" value="OK" onclick="addProfessor()">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
</html>
