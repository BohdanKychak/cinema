<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ticket purchase</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
</head>
<body>

<jsp:include page="menuAvailableToAllView.jsp"></jsp:include>

<h3>Ticket purchase</h3>

<form method="POST" action="${pageContext.request.contextPath}/purchase">
    <input type="hidden" name="redirectId" value="${param.redirectId}"/>
    <table border="0">

        <tr>
            <td>
                <i>You can view the occupied seats by entering only the session id.<br/>
                               To purchase a ticket, enter the session ID and seat number.<br/></i>
            </td>
        </tr>
        <tr>
            <td>
                ID: <input type="number" name="id" min="1">
            </td>
        </tr>
        <tr>
            <td>
                Seat number: <input type="number" name="place" min="1" max="40">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <INPUT TYPE="button" VALUE="Cancel" onClick="history.go(-1);">
            </td>
        </tr>
    </table>
</form>
<i><p style="color: red;">${message}</p></i>
<p>
<br/>
<img src="${pageContext.request.contextPath}/img/hall.png" width="800" height="450" alt="Hall">
</p>

</body>
</html>
