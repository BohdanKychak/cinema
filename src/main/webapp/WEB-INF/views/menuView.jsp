<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session ="true" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="languages/messages"/>

<html lang="${cookie['lang'].value}">
<style>
    <%@include file="css/menuStyle.css" %>
</style>

<c:if test="${loginUser.role eq 'admin'}">
    <a href="${pageContext.request.contextPath}/adminMenu">
        <fmt:message key="menu.admin" />
    </a>
    ||
</c:if>
<c:if test="${not empty loginUser.role}">
    <a href="${pageContext.request.contextPath}/userMenu">
        <fmt:message key="menu.user" />
    </a>
    ||
    <a href="${pageContext.request.contextPath}/userInfo">
        <fmt:message key="info.title" />
    </a>
    ||
</c:if>
<a href="${pageContext.request.contextPath}/schedule">
  <fmt:message key="schedule" />
</a>
<c:if test="${empty loginUser.role}">
    ||
    <a href="${pageContext.request.contextPath}/login">
        <fmt:message key="login.title" />
    </a>
    ||
    <a href="${pageContext.request.contextPath}/registration">
        <fmt:message key="registration.title" />
    </a>
</c:if>
<c:if test="${not empty loginUser.role}">
    ||
    <a href="${pageContext.request.contextPath}/logout">
        <fmt:message key="menu.logout" />
    </a>
    &nbsp;
    <span style="color:green">[ ${loginUser.login} ]</span>
</c:if>
<div style="text-align: right;padding:5px;margin:5px 0px;background:#D7D7D9;">
    <i><fmt:message key="choose.language" /></i> =>&emsp;
    <a href="?cookieLocale=en"><fmt:message key="label.lang.en" /></a>
    //
    <a href="?cookieLocale=ua"><fmt:message key="label.lang.ua" /></a>
</div>

