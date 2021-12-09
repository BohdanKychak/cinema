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
      <title><fmt:message key="unavailable" /></title>
      <link rel="icon" href="./img/icon.ico" type="image/x-icon">
   </head>
   <body>

      <jsp:include page="WEB-INF/views/menuView.jsp"></jsp:include>

      <br/><br/>

      <h3 style="color:red;"><fmt:message key="unavailable" />!</h3>

   </body>
</html>