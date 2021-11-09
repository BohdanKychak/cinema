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
        <title><fmt:message key="admin.title" /></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
    </head>
    <body>
        <jsp:include page="menuView.jsp"></jsp:include>

        <h3><fmt:message key="admin.title" /></h3>
        <fmt:message key="admin.text" />


        <p>
            <a href="${pageContext.request.contextPath}/createAccount">
                <span><fmt:message key="create.account.title" /></span>
            </a>
        </p>
        <p>
            <a href="${pageContext.request.contextPath}/scheduleChanges">
                <span><fmt:message key="schedule.changes.title" /></span>
            </a>
        </p>
    </body>
</html>