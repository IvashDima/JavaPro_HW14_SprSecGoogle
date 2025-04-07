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
    <title>Prog.kiev.ua</title>
</head>
<body>
<div align="center">
    <form action="/j_spring_security_check" method="POST">
        Login:<br/><input type="text" name="j_login"><br/>
        Password:<br/><input type="password" name="j_password"><br/>
        <input type="submit" />

        <p><a href="/register">Register new user</a></p>

        <c:if test="${param.error != null}">
            <p>Wrong login or password!</p>
        </c:if>

        <c:if test="${param.logout ne null}">
            <p>Chao!</p>
        </c:if>
    </form>
</div>
</body>
</html>

