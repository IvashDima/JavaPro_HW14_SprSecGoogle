<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 25.03.2025
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h3><img height="50" width="55" src="<c:url value="/static/logo.png"/>"/><a href="/account/transaction/">
            Account # ${id}. Transactions List:</a></h3>

        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul id="groupList" class="nav navbar-nav">
                        <li><button type="button" id="deposit" class="btn btn-default navbar-btn">
                            <a href="${pageContext.request.contextPath}/transaction/deposit_page/${accountId}">Deposit to ${accountId}</a></button></li>
                        <li><button type="button" id="transfer" class="btn btn-default navbar-btn">
                            <a href="${pageContext.request.contextPath}/transaction/transfer_page/${accountId}">Transfer from ${accountId}</a></button></li>
                        <li><button type="button" id="reset" class="btn btn-default navbar-btn" onclick="fetch('reset').then(() => alert('Demo data has been reset!'))">Reset Demo Data</button></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Accounts <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="/transaction/">Default</a></li>
                                <c:forEach items="${accounts}" var="account">
                                    <li><a href="/transaction/account/${account.id}">${account.client.name}+${account.currency}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search" action="/transaction/search" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" name="pattern" placeholder="Search by amount">
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
                <td><b>From</b></td>
                <td><b>To</b></td>
                <td><b>Amount</b></td>
                <td><b>Type</b></td>
            </tr>
            </thead>
            <c:forEach items="${transactions}" var="transaction">
                <tr>
                    <td><input type="checkbox" name="toDelete[]" value="${transaction.id}" id="checkbox_${transaction.id}"/></td>
                    <td>${transaction.id}</td>
                    <c:choose>
                        <c:when test="${transaction.sender ne null}">
                            <td>${transaction.sender}</td>
                        </c:when>
                        <c:otherwise>
                            <td>Default</td>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${transaction.receiver ne null}">
                            <td>${transaction.receiver}</td>
                        </c:when>
                        <c:otherwise>
                            <td>Default</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${transaction.amount}</td>
                    <td>${transaction.type}</td>
                </tr>
            </c:forEach>
        </table>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${allPages ne null}">
                    <c:forEach var="i" begin="1" end="${allPages}">
                        <li><a href="/transaction/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
                <c:if test="${byAccountPages ne null}">
                    <c:forEach var="i" begin="1" end="${byAccountPages}">
                        <li><a href="/transaction/client/${clientId}?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                    </c:forEach>
                </c:if>
            </ul>
        </nav>
    </div>
    <script>
        $('.dropdown-toggle').dropdown();

        $('#deposit').click(function(){
            window.location.href='/transaction/deposit_page';
        });
        $('#reset').click(function(){
            window.location.href='/transaction/reset';
        });
    </script>
</body>
</html>
