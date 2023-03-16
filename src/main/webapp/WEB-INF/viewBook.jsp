<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>View Page </title>
  <%--    Bootstrap--%>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="card">
  <div class="card-header title">
      Active User: <c:out value="${user.username}" />
  </div>
    <div class="card-body">
      <h5 class="card-title">
        <c:if test="${user.id == oneBook.submittedBy.id}">
          <td>
            <h2> Hi, ${user.username} you read ${oneBook.title} by ${oneBook.author}</h2>
          </td>
        </c:if>
      </h5>
      <p class="card-text">
      <h3> Below is  your review: </h3>
      <p>
        ${oneBook.myThoughts}
      </p>
      <p>
        <a href="/book/${oneBook.id}/delete" class="btn btn-outline-danger">Delete</a>
      </p>
      <p>
        <a href="/book/${oneBook.id}/update" class="btn btn-outline-primary">Update</a>
      </p>

    </div>
  <div class="card-footer text-muted">
    <small class="text-muted ">
      Back to dashbaord
      <a class="ml-2"href="/dashboard">Dashboard</a>
    </small>
  </div>
</div>
</body>
</html>
