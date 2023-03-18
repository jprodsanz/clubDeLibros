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
    <title>Edit Book  </title>
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<h1 class="display-1 text-center" >Edit Book  </h1>
<div class="container">
    <p>
        <a href="/logout" class="btn btn-outline-primary">Logout</a>
    </p>
</div>

<div class="container">
    <form:form action="/books/${updateBook.id}/update" method="POST" modelAttribute="updateBook">
        <input type="hidden" name="_method" value="PUT"/>

            <form:input type="hidden" path="submittedBy" value="${updateBook.submittedBy.id}" class="form"/>
            <form:input type="hidden" path="id" value="${updateBook.id}" class="form"/>

        <div class="mb-3">
            <form:label for="title" class="form-label" path="title">Title:</form:label>
            <form:input style="width:250px;" type="text" class="form-control" id="title" aria-describedby="title" path="title"/>
            <form:errors path="title" class="text-danger"/>
        </div>
        <div class="mb-3">
            <form:label for="author" class="form-label" path="author">Author:</form:label>
            <form:input style="width:250px;" type="text" class="form-control" id="author" aria-describedby="author" path="author"/>
            <form:errors path="author" class="text-danger"/>
        </div>
        <div class="mb-3">
            <form:label for="myThoughts" class="form-label" path="myThoughts">My Thoughts:</form:label>
            <form:input style="width:250px;" type="text" class="form-control" id="myThoughts" aria-describedby="myThoughts" path="myThoughts"/>
            <form:errors path="myThoughts" class="text-danger"/>
        </div>
        <button type="submit" class="btn btn-outline-primary">Update Book  </button>
    </form:form>
      <div>
        <small class="text-muted ">
          return to dashboard?
          <a class="ml-2"href="/dashboard">Dashboard</a>
        </small>
      </div>
</div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>