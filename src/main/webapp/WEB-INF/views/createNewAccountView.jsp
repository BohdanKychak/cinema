<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>CreateNewAccount</title>
</head>
<body>

<jsp:include page="menuAvailableToAllView.jsp"></jsp:include>

<h3>Create New Account</h3>


<form method="POST" action="${pageContext.request.contextPath}/createNewAccount">
  <input type="hidden" name="redirectId" value="${param.redirectId}" />
  <table border="0">
    <tr>
      Role: <select name="role">
      <option>admin</option>
      <option>user</option>
    </select>
    </tr>
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
