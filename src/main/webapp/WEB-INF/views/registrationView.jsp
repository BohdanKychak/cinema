<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>

<jsp:include page="menuAvailableToAllView.jsp"></jsp:include>

<h3>Registration</h3>


<form method="POST" action="${pageContext.request.contextPath}/registration">
    <input type="hidden" name="redirectId" value="${param.redirectId}" />
    <table border="0">
        <tr>
            <td>Login</td>
            <td><input type="text" name="login" value= "${user.login}" /> </td>
        </tr>
        <tr>
            <td>Password</td>
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