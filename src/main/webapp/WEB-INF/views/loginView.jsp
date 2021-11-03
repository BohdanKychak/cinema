<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
      <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
   </head>
   <body>

      <jsp:include page="menuUnknownUserView.jsp"></jsp:include>

      <h3>Login Page</h3>


      <form method="POST" action="${pageContext.request.contextPath}/login">
         <input type="hidden" name="redirectId" value="${param.redirectId}" />
         <table border="0">
            <tr>
               <td>Login:</td>
               <td><input type="text" name="login" value= "${user.login}" /> </td>
            </tr>
            <tr>
               <td>Password:</td>
               <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>

            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <INPUT TYPE="button" VALUE="Cancel" onClick="history.go(-1);">
               </td>
            </tr>
         </table>
      </form>
      <i><p style="color: red;">${errorMessage}</p></i>

   </body>
</html>
