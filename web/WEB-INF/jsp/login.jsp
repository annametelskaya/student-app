<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 19.11.18
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="/login" method="POST">
    <div class="form-container">
        <input type="text" class="form-control" id="name" name="name" placeholder="Login"
               required>
        <input type="text" class="form-control" id="pass" name="pass" placeholder="Pass"
               required>
        <input class="btn" type="submit" value="OK">
    </div>
</form>
</body>
</html>
