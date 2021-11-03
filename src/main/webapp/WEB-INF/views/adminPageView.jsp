<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
</head>

<body>

<jsp:include page="menuUserView.jsp"></jsp:include>

<h3>Admin Page</h3>

Hello! Here's what you can do:

<p>
    <a href="${pageContext.request.contextPath}/createAccount">
        <span>Create Account</span>
    </a>
</p>
<p>
    <a href="${pageContext.request.contextPath}/scheduleChanges">
        <span>Schedule Changes</span>
    </a>
</p>


</body>
</html>