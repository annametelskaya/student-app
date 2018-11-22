<%--
  Created by IntelliJ IDEA.
  User: anna
  Date: 21.11.18
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resource/css/style.css">
    <script type="text/javascript" src="/resource/js/groups.js"></script>
</head>
<body>
<div class="container content div-center flex-column">
    <div class="title text-center">
        <h1>Add group</h1>
    </div>
    <form action="/groupsPage">
        <div class="form-container">
            <input type="text" class="form-control" id="groupNumberForm" name="groupNumber"
                   placeholder="Group number" required pattern="[0-9]+">
            <input class="btn btn-app-color" type="submit" value="OK" onclick="update()">
        </div>
    </form>
</div>
</div>
</body>
</html>
