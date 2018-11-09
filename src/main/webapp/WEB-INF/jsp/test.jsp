<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="add_student_form">
    <form action="${pageContext.request.contextPath}/students" method="POST">
        <input type="text" name="firstName" placeholder="Student's name">

        <input type="text" name="secondName" placeholder="Student's surname">

        <input type="submit" value="OK">
    </form>
</div>
<div class="students_table">
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
</body>
</html>