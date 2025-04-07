<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 23.03.2025
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Accounts</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h3><img height="50" width="55" src="<c:url value="/static/logo.png"/>"/><a href="/account/">Accounts List</a></h3>

        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul id="groupList" class="nav navbar-nav">
                        <li><button type="button" id="add_account" class="btn btn-default navbar-btn">Open Account</button></li>
                        <li><button type="button" id="reset" class="btn btn-default navbar-btn" onclick="fetch('reset').then(() => alert('Demo data has been reset!'))">Reset Demo Data</button></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Clients <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/account/">Default</a></li>
                                <c:forEach items="${clients}" var="client">
                                    <li><a href="/account/client/${client.id}">${client.name}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search" action="/account/search" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" name="pattern" placeholder="Search by currency">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <table class="table table-striped">
            <thead>
            <tr>
                <td></td>
                <td><b>Id</b></td>
                <td><b>Client</b></td>
                <td><b>Balance</b></td>
                <td><b>Currency</b></td>
                <td><b>Client details</b></td>
                <td><b>Actions</b></td>
            </tr>
            </thead>
            <c:forEach items="${accounts}" var="account">
                <tr>
                    <td><input type="checkbox" name="toDelete[]" value="${account.id}" id="checkbox_${account.id}"/></td>
                    <td>${account.id}</td>
                    <c:choose>
                        <c:when test="${account.client ne null}">
                            <td>${account.client.name}</td>
                        </c:when>
                        <c:otherwise>
                            <td>Default</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${account.balance}</td>
                    <td>${account.currency}</td>
                    <td>${account.client}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/transaction/account/${account.id}">
                            <button type="button" id="transactions" class="btn btn-default navbar-btn">View Transactions</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${allPages ne null}">
                    <c:forEach var="i" begin="1" end="${allPages}">
                        <li><a href="/account/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
                <c:if test="${byClientPages ne null}">
                    <c:forEach var="i" begin="1" end="${byClientPages}">
                        <li><a href="/account/client/${clientId}?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>
    </div>
    <script>
        $('.dropdown-toggle').dropdown();

        $('#add_account').click(function(){
            window.location.href='/account/account_add_page';
        });
        $('#reset').click(function(){
            window.location.href='/account/reset';
        });
    </script>

</body>
</html>
