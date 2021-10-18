<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>User Page</title>
      <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
   </head>
   <body>

      <jsp:include page="menuUserView.jsp"></jsp:include>

      <h3>User Page</h3>

      Hello, ${loginUser.login}. You can find information about movies in our cinema in the "schedule" tab!

   </body>
</html>