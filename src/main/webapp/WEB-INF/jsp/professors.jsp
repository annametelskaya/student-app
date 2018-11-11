<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Professors</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="row content">
    <nav class="col-md-3">
        <ul class="nav flex-column fixed-bottom nav-items-padding col-md-3">
            <li class="nav-item ">
                <a href="/" class="nav-link nav-link-text">Home</a>
            </li>
            <li class="nav-item ">
                <a href="/students" class="nav-link nav-link-text">Students</a>
            </li>
            <li class="nav-item">
                <a href="/groups" class="nav-link nav-link-text">Groups</a>
            </li>
            <li class="nav-item">
                <a href="/marks" class="nav-link nav-link-text">Marks</a>
            </li>
            <li class="nav-item">
                <a href="/professors" class="nav-link nav-link-text">Professors</a>
            </li>
            <li class="nav-item">
                <a href="/subjects" class="nav-link nav-link-text">Subjects</a>
            </li>
        </ul>
    </nav>
    <div class="col-md-9">
        <div class="container content py-5">
            <div class="title text-right">
                <h1>Professors</h1>
                <a href="/professors/add">add</a>
            </div>
            <div class="table">
                <div class="row">
                    <div class="col-3 table-header">Name</div>
                    <div class="col-3 table-header">Surname</div>
                    <div class="col-3 table-header">Fathers name</div>
                    <div class="col-3 table-header">Date of birth</div>
                </div>
                <c:forEach items="${professors}" var="professor">
                    <div class="row">
                        <div class="col-3 table-item">${professor.getFirstName() }</div>
                        <div class="col-3 table-item">${professor.getSecondName() }</div>
                        <div class="col-3 table-item">${professor.getFatherName()}</div>
                        <div class="col-3 table-item">${professor.getBirthDate()}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>