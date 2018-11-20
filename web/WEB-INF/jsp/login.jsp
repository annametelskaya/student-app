<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css"  href="/resource/css/style.css">
</head>
<body class="login-bg">
<div class="align-self-center justify-content-center w-100">
    <div class="text-center text-light">
        <h1>LOGIN</h1>
    </div>
    <form action="/loginPage" method="POST">
        <div class="form-container padd">
            <input type="text" class="form-control" id="name" name="name" placeholder="Login"
                   required>
            <input type="text" class="form-control" id="pass" name="pass" placeholder="Pass"
                   required>
            <input class="btn btn-login-color" type="submit" value="OK">
        </div>
    </form>
</div>
</body>
</html>
