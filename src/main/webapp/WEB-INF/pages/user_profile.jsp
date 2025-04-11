<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 03.04.2025
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>User page</title>
</head>
<body>
    <div align="center">
        <h1>Your login is: ${email}</h1>
        <c:forEach var="s" items="${roles}">
            <h3>Your roles are: <c:out value="${s}" /></h3>
        </c:forEach>

        <c:if test="${admin}">
            <p><a href="/admin">Click</a> for admin page</p>
        </c:if>

        <form action="/update" method="POST">
            E-mail:<br/><input type="text" name="email" value="${email}" /><br/>
            Phone:<br/><input type="text" name="phone" value="${phone}" /><br/>
            Address:<br/><input type="text" name="address" value="${address}" /><br/>
            <input type="submit" value="update"/>
        </form>

        <a href="${pageContext.request.contextPath}/account/client/${clientid}">
            <button type="button">Go to your accounts</button>
        </a>

        <p>Click to logout: <a href="/logout">LOGOUT</a></p>
    </div>
</body>
</html>
