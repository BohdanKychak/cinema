<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<style>
  <%@include file="css/menuStyle.css" %>
</style>

<a href="${pageContext.request.contextPath}/userMenu">
  User Menu
</a>
||
<a href="${pageContext.request.contextPath}/adminMenu">
  Admin Menu
</a>
||
<a href="${pageContext.request.contextPath}/userInfo">
  Info
</a>
||
<a href="${pageContext.request.contextPath}/schedule">
  Schedule
</a>
||
<a href="${pageContext.request.contextPath}/logout">
  Logout
</a>

&nbsp;
<span style="color:green">[ ${loginUser.login} ]</span>