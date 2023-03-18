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

<div class="content">

<%--  if match = true --%>
  <c:if test="${user.id == oneBook.submittedBy.id}">
    <td>
      <h3> You read ${oneBook.title} by ${oneBook.author}</h3>
      <h4 >Here's your thoughts:</h4>
    </td>
  </c:if>

  <!-- NOT a match -->
  <c:if test="${user.id != oneBook.submittedBy.id}">
    <td>
      <h3> ${oneBook.submittedBy.username} read  ${oneBook.title} by ${oneBook.author}</h3>
      <h4 >Here's ${oneBook.submittedBy.username} thoughts:</h4>
    </td>
  </c:if>

  <hr/>
  <p> ${oneBook.myThoughts}</p>
  <hr/>

  <!-- user and submittedBy match -->
  <c:if test="${user.id == oneBook.submittedBy.id}">
    <div class="flexControl">
      <h3><a href="/books/${oneBook.id}/update" class="btn btn-outline-primary">Update</a></h3>
      <h3><a href="/books/${oneBook.id}/delete" class="btn btn-outline-danger">Delete</a></h3>
    </div>
  </c:if>
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
