<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>User Info</title>
   </head>
   <body>

     <jsp:include page="menu2.jsp"></jsp:include>

      <h3>Hello: ${loginedUser.userName}</h3>

      User Name: <b>${loginedUser.userName}</b>
      <br />


   </body>
</html>