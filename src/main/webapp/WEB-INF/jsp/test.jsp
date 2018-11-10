<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Students</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="row content">
    <nav class="col-md-3">
        <ul class="nav flex-column fixed-bottom nav-items-padding">
            <li class="nav-item ">
                <a href="/" class="nav-link nav-link-text">Home</a>
            </li>
            <li class="nav-item ">
                <a href="/students" class="nav-link nav-link-text">Students</a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link nav-link-text">Groups</a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link nav-link-text">Marks</a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link nav-link-text">Professor</a>
            </li>
            <li class="nav-item">
                <a href="#" class="nav-link nav-link-text">Subjects</a>
            </li>
        </ul>
    </nav>
    <div class="col-md-9">
        <div class="add-student-form">
            <div class="title text-right">
                <h1>Students</h1>
            </div>
            <form action="${pageContext.request.contextPath}/students" method="POST">
                <div class="input-container">
                    <input type="text" id="student-name-form" name="firstName" placeholder="Name" required>
                    <input type="text" id="student-surname-form" name="secondName" placeholder="Surname" required>
                    <input type="text" id="student-group-form" name="groupNumber" placeholder="Group" required
                           pattern="[0-9]+">
                    <input type="submit" value="OK">
                </div>
            </form>
            <table>
                <thead>
                <tr>
                    <th>First name</th>
                    <th>Second name</th>
                    <th>Group</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${students}" var="student">
                    <tr>
                        <td>${student.getFirstName() }</td>
                        <td>${student.getSecondName() }</td>
                        <td>${student.getGroupNumber()}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>