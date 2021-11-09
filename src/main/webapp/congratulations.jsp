<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session ="true" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="languages/messages"/>

<html lang="${cookie['lang'].value}">
<html>
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="congratulations.title" /></title>
        <link rel="icon" href="./img/icon.ico" type="image/x-icon">
        <style>
            <%@include file="WEB-INF/views/css/congratulationsStyle.css" %>
            <%@include file="WEB-INF/views/css/menuStyle.css" %>
        </style>
    </head>
    <body>

        <div class="colored">
            <h1><fmt:message key="congratulations" /></h1>
        </div>
        <h3>
            <fmt:message key="congratulations.continue" />
            =>
            <a href="${pageContext.request.contextPath}/login">
                <fmt:message key="login.title" />
            </a>
        </h3>
        <div style="text-align: right;padding:5px;margin:5px 0px;background:#D7D7D9;">
            <i><fmt:message key="choose.language" /></i> =>&emsp;
            <a href="?cookieLocale=en"><fmt:message key="label.lang.en" /></a>
            //
            <a href="?cookieLocale=ua"><fmt:message key="label.lang.ua" /></a>
        </div>
    </body>
</html>


