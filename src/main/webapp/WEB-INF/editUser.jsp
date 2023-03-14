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
    <title>Edit User </title>
    <%--    Bootstrap--%>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<h1 class="display-1 text-center" >Edit Username</h1>
    <div class="container">
            <form action="/user/${user.id}/edit" method="POST" modelAttribute="user">
                <input type="hidden" name="_method" value="PUT"/>
                <div class="mb-3">
<%--                    <input type="hidden" path="id" value="${user.id}" class="form"/>--%>
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label" path="username">Username:</label>
                    <input style="width:250px;" type="text" class="form-control" id="username" aria-describedby="username" value="${user.username}" name="username"/>
<%--                    <form:errors path="username" class="text-danger"/>--%>
                </div>
            <button type="submit" class="btn btn-outline-primary">Update</button>
        </form>
    </div>
</body>
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</html>