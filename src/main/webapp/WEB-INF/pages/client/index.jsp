<%--
  Created by IntelliJ IDEA.
  User: demon
  Date: 22.03.2025
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Main page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"><script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
  </head>
  <body>
    <div class="container">
      <h3><img height="50" width="55" src="<c:url value="/static/logo.png"/>"/><a href="/client/">Client List</a></h3>

      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul id="groupList" class="nav navbar-nav">
              <li><button type="button" id="add_client" class="btn btn-default navbar-btn">Add Client</button></li>
              <li><button type="button" id="delete_client" class="btn btn-default navbar-btn">Delete Client</button></li>
              <li><button type="button" id="reset" class="btn btn-default navbar-btn" onclick="fetch('reset').then(() => alert('Demo data has been reset!'))">Reset Demo Data</button></li>
<%--              <li class="dropdown">--%>
<%--                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Groups <span class="caret"></span></a>--%>
<%--                <ul class="dropdown-menu">--%>
<%--                  <li><a href="/">Default</a></li>--%>
<%--                  <c:forEach items="${groups}" var="group">--%>
<%--                    <li><a href="/group/${group.id}">${group.name}</a></li>--%>
<%--                  </c:forEach>--%>
<%--                </ul>--%>
<%--              </li>--%>
            </ul>
            <form class="navbar-form navbar-left" role="search" action="/client/search" method="post">
              <div class="form-group">
                <input type="text" class="form-control" name="pattern" placeholder="Search by name">
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
          <td><b>Name</b></td>
          <td><b>Surname</b></td>
          <td><b>Phone</b></td>
          <td><b>E-mail</b></td>
          <td><b>Actions</b></td>
        </tr>
        </thead>
        <c:forEach items="${clients}" var="client">
          <tr>
            <td><input type="checkbox" name="toDelete[]" value="${client.id}" id="checkbox_${client.id}"/></td>
            <td>${client.name}</td>
            <td>${client.surname}</td>
            <td>${client.phone}</td>
            <td>${client.email}</td>
              <td>
                  <a href="${pageContext.request.contextPath}/account/client/${client.id}">
                      <button type="button" id="accounts" class="btn btn-default navbar-btn">View Accounts</button>
                  </a>
              </td>
          </tr>
        </c:forEach>
      </table>

      <nav aria-label="Page navigation">
        <ul class="pagination">
          <c:if test="${allPages ne null}">
            <c:forEach var="i" begin="1" end="${allPages}">
              <li><a href="/client/?page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
            </c:forEach>
          </c:if>
        </ul>
      </nav>
    </div>

    <script>
      $('.dropdown-toggle').dropdown();

      $('#add_client').click(function(){
        window.location.href='/client/client_add_page';
      });

      $('#reset').click(function(){
        window.location.href='/client/reset';
      });

      $('#delete_client').click(function(){
          let data = { 'toDelete[]' : []};
          $(":checked").each(function() {
              data['toDelete[]'].push($(this).val());
          });
          $.post("/client/delete", data, function(data, status) {
              window.location.reload();
          });
      });
    </script>
  </body>
</html>
