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
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="row content">
    <nav class="col-md-3">
        <ul class="nav flex-column fixed-bottom nav-items-padding col-md-3">
            <li class="nav-item ">
                <a href="/" class="nav-link nav-link-text">Home</a>
            </li>
            <li class="nav-item">
                <a href="/groups" class="nav-link nav-link-text">Groups</a>
            </li>
            <li class="nav-item">
                <a href="/professors" class="nav-link nav-link-text">Professors</a>
            </li>
            <li class="nav-item ">
                <a href="/students" class="nav-link nav-link-text">Students</a>
            </li>
            <li class="nav-item">
                <a href="/subjects" class="nav-link nav-link-text">Subjects</a>
            </li>
            <li class="nav-item">
                <a href="/marks" class="nav-link nav-link-text">Marks</a>
            </li>
        </ul>
    </nav>
    <div class="col-md-9">
        <div class="container content py-5">
            <div class="title text-right">
                <h1>Subjects</h1>
                <a href="/subjects/add" class="btn btn-sm active" role="button">add</a>
            </div>
            <c:forEach items="${subjects}" var="subject">
                <div class="card m-1" style="width: 18em; display: inline-block">
                    <div class="card-body">
                        <h5 class="card-title">${subject.getName() }</h5>
                        <p class="card-text">Professor: ${subject.getProfessorName() }<br>
                            Hours: ${subject.getHours() }</p>
                            <%--<a href="#" class="btn btn-primary">Go somewhere</a>--%>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>