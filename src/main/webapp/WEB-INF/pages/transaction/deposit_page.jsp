<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 27.03.2025
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Deposit</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <form role="form" class="form-horizontal" action="/transaction/deposit" method="post">
            <h3>New Deposit for account # ${account.id} in ${account.currency} (${account.client.name})</h3>
            <select class="selectpicker form-control form-group" name="account">
<%--                <option value="${account}">${account.id}+${account.currency} (${account.client.name})</option>--%>
                <option value="-1">Default</option>
                <c:forEach items="${accounts}" var="fromaccount">
                    <option value="${fromaccount.id}">${fromaccount.id}+${fromaccount.currency} (${fromaccount.client.name})</option>
                </c:forEach>
            </select>
            <input class="form-control form-group" type="text" name="amount" placeholder="Amount">
            <input type="submit" class="btn btn-primary" value="Confirm">
        </form>
    </div>

    <script>
        $('.selectpicker').selectpicker();
    </script>
</body>
</html>
