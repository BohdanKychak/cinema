<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
   <head>
      <meta charset="UTF-8">
      <title>User Info</title>
       <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
   </head>
   <body>

     <jsp:include page="menuUserView.jsp"></jsp:include>

      <h3>Hello: ${loginUser.login}</h3>

      User: <b>${loginUser.login}</b>
      <br />


   </body>
</html>