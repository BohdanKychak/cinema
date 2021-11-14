<%@page import="java.sql.ResultSet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session ="true" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="languages/messages"/>

<html lang="${cookie['lang'].value}">
<html>
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="exception" /></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
        <style>
            <%@include file="css/error.css" %>
        </style>
    </head>
    <body>
        <h1>
            <fmt:message key="exception" /> ${statusCode}
        </h1>
        <c:if test="${statusCode ne 500}">
            <h3><fmt:message key="details.error" /></h3>
            <strong><fmt:message key="status.code" /></strong> ${statusCode}<br>
            <strong><fmt:message key="requested.uri" /></strong> ${requestUri}
        </c:if>
        <c:if test="${statusCode eq 500}">
            <h3><fmt:message key="details.exception" /></h3>
            <ul>
                <li><fmt:message key="servlet.name" /> ${servletName}</li>
                <li><fmt:message key="exception.name" /> ${throwableName}</li>
                <li><fmt:message key="requested.uri" /> ${requestUri}</li>
                <li><fmt:message key="exception.message" /> ${throwableMessage}</li>
            </ul>
        </c:if>
        <br><br>
        <a href="${pageContext.request.contextPath}/">
            <fmt:message key="main.title" />
        </a>
    </body>
</html>