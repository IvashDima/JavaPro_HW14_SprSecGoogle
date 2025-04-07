<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 23.03.2025
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Client</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <form role="form" class="form-horizontal" action="/client/add" method="post">
            <h3>New client</h3>
            <input class="form-control form-group" type="text" name="name" placeholder="Name">
            <input class="form-control form-group" type="text" name="surname" placeholder="Surname">
            <input class="form-control form-group" type="text" name="phone" placeholder="Phone">
            <input class="form-control form-group" type="text" name="email" placeholder="Email">
            <input type="submit" class="btn btn-primary" value="Add">
        </form>
    </div>

    <script>
        $('.selectpicker').selectpicker();
    </script>
</body>
</html>
