<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>User Info</title>
   </head>
   <body>

     <jsp:include page="menuUserView.jsp"></jsp:include>

      <h3>Hello: ${loginUser.login}</h3>

      User: <b>${loginUser.login}</b>
      <br />


   </body>
</html>