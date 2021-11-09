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
        <title><fmt:message key="user.title" /></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
    </head>
    <body>

        <jsp:include page="menuView.jsp"></jsp:include>

        <h3><fmt:message key="user.title" /></h3>
        <p>
            <fmt:message key="info" />${loginUser.login}. <fmt:message key="user.text" />
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/purchase">
                <span><fmt:message key="purchase" /></span>
            </a>
        </p>

    </body>
</html>