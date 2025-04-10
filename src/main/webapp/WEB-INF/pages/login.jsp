<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 01.04.2025
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Internet Banking</title>
</head>
<body>
<div align="center">
    <form action="/j_spring_security_check" method="POST">
        Login:<br/><input type="text" name="j_login" placeholder="Enter email"><br/>
        Password:<br/><input type="password" name="j_password" placeholder="Enter password"><br/>
        <input type="submit" />

        <p><a href="/register">Register new user</a></p>

        <c:if test="${param.error != null}">
            <p>Wrong login or password!</p>
        </c:if>

        <c:if test="${param.logout ne null}">
            <p>Chao!</p>
        </c:if>
    </form>

    <button type="button" class="btn btn-primary btn-lg" onclick="goToURL('oauth2/authorization/google');">Login via Google</button>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
</body>
</html>

