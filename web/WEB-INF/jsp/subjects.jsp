<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Subjects</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resource/css/style.css">
    <script type="text/javascript" src="/resource/js/subjects.js"></script>
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
        </ul>
    </nav>
    <div class="col-md-9">
        <div class="container content py-5">
            <div class="title text-right">
                <h1>Subjects</h1>
                <a href="/subjectsPage/add" class="btn btn-app-color btn-sm active" role="button">add</a>
            </div>
            <form>
                <div class="row">
                    <div class="col-2">
                        <input type="text" class="form-control" id="sortByName" name="sortByName" placeholder="Subject">
                    </div>
                    <div class="col-2">
                        <input type="text" class="form-control" id="sortByHours" name="sortByHours" placeholder="Hours">
                    </div>
                    <div class="col-2">
                        <input type="text" class="form-control" id="sortByProf" name="sortByProf"
                               placeholder="Professor">
                    </div>
                    <div class="col-2">
                        <input class="btn btn-app-color" type="button" value="Search" onclick="updateCards()">
                    </div>
                </div>
            </form>
            <div id="subjectsTable"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.onload = function () {
        updateCards();
    }
</script>
</body>
</html>