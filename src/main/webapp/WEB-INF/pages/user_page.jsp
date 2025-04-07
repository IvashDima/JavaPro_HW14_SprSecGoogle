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
        <form action="/update" method="POST">
            E-mail:<br/><input type="text" name="email" value="${email}" /><br/>
            Phone:<br/><input type="text" name="phone" value="${phone}" /><br/>
            Address:<br/><input type="text" name="address" value="${address}" /><br/>
            <input type="submit" value="update"/>
            <%--        <button type="submit" value="update">Submit</button>--%>
        </form>
    </div>
</body>
</html>
