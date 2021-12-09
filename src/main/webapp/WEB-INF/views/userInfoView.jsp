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
        <title><fmt:message key="info.title" /></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
    </head>
    <body>
        <jsp:include page="menuView.jsp"></jsp:include>

        <h3><fmt:message key="info" />${loginUser.login}</h3>
        <fmt:message key="info.text" /><b>${loginUser.role}</b>
        <br/>

    </body>
</html>