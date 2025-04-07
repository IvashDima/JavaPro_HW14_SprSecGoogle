<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 01.04.2025
  Time: 21:30
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
    <form action="newuser" method="POST">
        Login:<br/><input type="text" name="login" value="${login}"><br/>
        Password:<br/><input type="password" name="password"><br/>
        First name:<br/><input type="name" name="name"><br/>
        Surname:<br/><input type="surname" name="surname"><br/>
        E-mail:<br/><input type="text" name="email"><br/>
        Phone:<br/><input type="text" name="phone"><br/>
        Address:<br/><input type="text" name="address"><br/>
        <input type="submit" />

        <c:if test="${exists ne null}">
            <p>User already exists!</p>
        </c:if>
    </form>
</div>
</body>
</html>
