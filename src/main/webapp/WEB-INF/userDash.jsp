<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>Dashboard</title>
  <%--    Bootstrap--%>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
  <h1 class="text-center"> Dashboard</h1>
  <h3 class="text-center">Hey! <c:out value="${user.username}" /></h3>
  <p>
    <a href="/logout" class="btn btn-outline-primary">Logout</a>
  </p>
  <p>
    <a href="/book/new" class="btn btn-outline-primary">Add Book </a>
  </p>
  <table class="table table-striped table-bordered text-center">
    <thead class="table-dark">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Title</th>
      <th scope="col">Author Name </th>
      <th scope="col">Posted By</th>

    </tr>
    </thead>
    <tbody>
<%--    var should be singular and items plurarl --%>
    <c:forEach var="book" items="${books}">
      <tr>
        <td>${book.id}</td>
        <td>
          <a href="/books/${book.id}/view">${book.title}</a></td>
        <td>${book.author}</td>
        <td>${book.submittedBy.username}</td>
      </tr>
    </c:forEach>

    </tbody>
  </table>
</div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>