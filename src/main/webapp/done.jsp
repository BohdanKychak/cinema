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
        <title><fmt:message key="done" /></title>
        <link rel="icon" href="./img/icon.ico" type="image/x-icon">
    </head>
    <body>
        <h3>
            <fmt:message key="done" />
        </h3>
        <div>
            <a href = "javascript:history.back()">
                <fmt:message key="input.back" />
            </a>
        </div>

    </body>
</html>