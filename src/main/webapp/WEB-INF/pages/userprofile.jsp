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
    <h1>Your login is: ${email}, your roles are:</h1>
    <c:forEach var="s" items="${roles}">
        <h3><c:out value="${s}" /></h3>
    </c:forEach>

    <c:if test="${admin}">
        <p><a href="/admin">Click</a> for admin page</p>
    </c:if>

    <form action="/update" method="POST">
        E-mail:<br/><input type="text" name="name" value="${name}" /><br/>
        Phone:<br/><input type="text" name="phone" value="${phone}" /><br/>
        Address:<br/><input type="text" name="address" value="${address}" /><br/>
        <input type="submit" value="Update"/>
    </form>

    <a href="${pageContext.request.contextPath}/account/client/${clientid}">
        <button type="button">Go to your accounts</button>
    </a>

    <p>Click to logout: <a href="/logout">LOGOUT</a></p>
</div>
</body>
</html>

