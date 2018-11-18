<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ page language="java" contentType="text/html; charset=ISO-8859-1"--%>
<%--pageEncoding="ISO-8859-1" %>--%>
<%--<%@ page isELIgnored="false" %> PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<!DOCTYPE html>
<html>
<head>
    <title>Groups</title>
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
                <h1>Groups</h1>
                <a href="/groups/add" class="btn btn-sm active" role="button">add</a>
            </div>

            <form action="/groups" method="GET">
                <div class="row">
                    <div class="col-2">
                        <input type="text" class="form-control" id="sortByGroup" name="sortByGroup" placeholder="Group">
                    </div>
                    <div class="col-2">
                        <input class="btn" type="submit" value="Search">
                    </div>
                </div>
            </form>
            <div id="groupsTable"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/groups', true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState != 4) return
        clearTimeout(xhrTimeout);
        if (xhr.status === 200) {
            let data = JSON.parse(xhr.responseText);
            let card = document.getElementById('groupsTable');
            card.insertAdjacentHTML('beforeend', createTable(data));
        } else {
            alert("ERROR\n" + xhr.status + ': ' + xhr.statusText);
        }
    };

    function createTable(data) {
        let cardBody;
        let card = "<div>";
        for (let i = data.length - 1; i >= 0; i--) {
            cardBody =   "<div class='card m-1' style='width: 18em; display: inline-block'>" +
                            "<div class='card-body'>" +
                                "<h5 class='card-title'>Group " + data[i].groupNumber + "</h5>" +
                            "</div>" +
                        "</div>";
            card += cardBody;
        }
        card += "</div>";
        return card;
    }
</script>
</body>
</html>