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
    <title>New Transfer</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <form role="form" class="form-horizontal" action="/transaction/transfer" method="post">
            <h3>New Transfer from account # ${account.id} (${account.client.name})</h3>
            <h4>Current balance is ${account.balance} ${account.currency}.</h4>
            <input type="hidden" name="fromaccount" value="${account.id}">
            <p></p>
            <label for="toaccount">Account of receiver:</label>
            <select id="toaccount" class="selectpicker form-control form-group" name="toaccount">
<%--                <option value="${account}">${account.id}+${account.currency} (${account.client.name})</option>--%>
                <option value="-1" disabled selected>Choose account</option>
                <c:forEach items="${accounts}" var="toaccount">
                    <option value="${toaccount.id}">${toaccount.id}+${toaccount.currency} (${toaccount.client.name})</option>
                </c:forEach>
            </select>
            <label for="amount">Amount of transfer:</label>
            <input id="amount" class="form-control form-group" type="text" name="amount" placeholder="Enter amount">
            <input type="submit" class="btn btn-primary" value="Confirm">
        </form>
    </div>

    <script>
        $('.selectpicker').selectpicker();
    </script>
</body>
</html>
