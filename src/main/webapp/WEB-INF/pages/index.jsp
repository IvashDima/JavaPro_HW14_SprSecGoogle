<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 01.04.2025
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Wellcome page</title>
</head>
<body>
<div align="center">
    <h1>Wellcome to the system :) </h1>

    <h3>Hi ${email}! Please, choose option:</h3>

    <a href="${pageContext.request.contextPath}/account/client/${clientid}">
        <button type="button">Go to your accounts</button></a>

    <a href="/user_profile"><button type="button">Go to your profile</button></a>

    <a href="/logout"><button type="button">LOGOUT</button></a>
</div>
</body>
</html>

