<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 23.03.2025
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Account</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <form role="form" class="form-horizontal" action="/account/add" method="post">
            <h3>New Account</h3>
            <select class="selectpicker form-control form-group" name="client">
                <option value="-1">Default</option>
                <c:forEach items="${clients}" var="client">
                    <option value="${client.id}">${client.name}</option>
                </c:forEach>
            </select>
            <input class="form-control form-group" type="text" name="balance" placeholder="Balance">
            <label for="currency">Currency:</label>
            <select class="selectpicker form-control form-group" id="currency" name="currency">
                <option value="-1">Default</option>
                <c:forEach var="curr" items="${currencies}">
                    <option value="${curr}">${curr}</option>
                </c:forEach>
            </select>
            <input type="submit" class="btn btn-primary" value="Add">
        </form>
    </div>

    <script>
        $('.selectpicker').selectpicker();
    </script>

</body>
</html>
