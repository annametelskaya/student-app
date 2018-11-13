<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add student</title>
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
        <div class="container content div-center flex-column">
            <div class="title text-center">
                <h1>Add student</h1>
            </div>
            <form action="/students" method="POST">
                <div class="form-container">
                    <input type="text" class="form-control" id="nameForm" name="firstName" placeholder="First name"
                           required>
                    <input type="text" class="form-control" id="surnameForm" name="secondName" placeholder="Second name"
                           required>
                    <select class="form-control" id="groupForm" name="groupNumber" required pattern="[0-9]+">
                        <c:forEach items="${groups}" var="group">
                            <option value="${group.getId()}">${group.getGroupNumber() }</option>
                        </c:forEach>
                    </select>
                    <input class="btn" type="submit" value="OK">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
</html>
