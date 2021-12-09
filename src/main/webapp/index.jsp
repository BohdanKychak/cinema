<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session ="true" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="languages/messages"/>

<html lang="${cookie['lang'].value}">
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="main.title" /></title>
        <link rel="icon" href="./img/icon.ico" type="image/x-icon">
        <style>
            <%@include file="WEB-INF/views/css/indexStyle.css" %>
        </style>
    </head>
    <body>
        <jsp:include page="WEB-INF/views/menuView.jsp"></jsp:include>

        <div class="colored">
            <h1>
                <fmt:message key="main.welcome" />
            </h1>
        </div>
        <p style="color: #4206a2;">
            <fmt:message key="main.text" />
        </p>

    </body>
</html>