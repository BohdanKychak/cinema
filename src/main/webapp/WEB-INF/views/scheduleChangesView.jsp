<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>scheduleChanges</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
</head>
<body>

<jsp:include page="menuAvailableToAllView.jsp"></jsp:include>

<h3>Schedule Changes<br/>
    <em><small>You can delete or add a session to your cinema schedule in one attempt.</small></em></h3>


<form method="POST" action="${pageContext.request.contextPath}/scheduleChanges">
    <input type="hidden" name="redirectId" value="${param.redirectId}"/>
    <table border="0">

        <tr>
            <td><p style="color:#c800ff">Add a new movie to the schedule</p>
                You can add a movie to the schedule no later than the day before it is shown.
            </td>
        </tr>
        <tr>
            <form>
                <td>
                    Movie: <select name="movieTitle">
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <c:if test="${not empty list}">
                        <c:forEach items="${list}" var="record">
                            <option>
                                    ${record.movieTitle}
                            </option>
                        </c:forEach>
                    </c:if>
                </select>
                    Date: <input type="date" name="sessionDate">
                    Time: <input type="time" name="sessionTime" value="9:01" min="09:00" max="22:00">
                </td>
            </form>
        </tr>
        <tr>
            <td><p style="color:#c800ff">Cancel movie</p>
                Enter the session number
            </td>
        </tr>
        <tr>
            <td>
                ID: <input type="number" name="id" min="1">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <p><br/></p>
                <input type="submit" value="Submit"/>
                <INPUT TYPE="button" VALUE="Cancel" onClick="history.go(-1);">
            </td>
        </tr>
    </table>
</form>
<i><p style="color: red;">${errorMessage}</p></i>

</body>
</html>
