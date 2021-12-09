<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page session ="true" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="languages/messages"/>

<html lang="${cookie['lang'].value}">
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="schedule.changes.title" /></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
    </head>
    <body>
        <jsp:include page="menuView.jsp"></jsp:include>

        <h3><fmt:message key="schedule.changes.title" /><br/>
            <em><small><fmt:message key="schedule.changes.text" /></small></em></h3>


        <form method="POST" action="${pageContext.request.contextPath}/scheduleChanges">
            <input type="hidden" name="redirectId" value="${param.redirectId}"/>
            <table border="0">

                <tr>
                    <td>
                        <p style="color:#c800ff">
                            <fmt:message key="schedule.changes.add" />
                        </p>
                        <fmt:message key="schedule.changes.add.text" />
                    </td>
                </tr>
                <tr>
                    <form>
                        <td>
                            <fmt:message key="input.movie" />
                            <select name="movieTitle">
                                <c:if test="${not empty list}">
                                    <c:forEach items="${list}" var="record">
                                        <option>
                                            ${record.movieTitle}
                                        </option>
                                    </c:forEach>
                                </c:if>
                            </select>
                            <fmt:message key="input.time" /><input type="datetime-local" name="sessionTime">
                        </td>

                    </form>
                </tr>
                <tr>
                    <td><p style="color:#c800ff"><fmt:message key="schedule.changes.delete" /></p>
                        <fmt:message key="schedule.changes.delete.text" />
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
            <c:if test="${message eq 'movie'}">
                <i><p style="color: red;"><fmt:message key="error.movie" /></p></i>
            </c:if>
            <c:if test="${message eq 'id'}">
                <i><p style="color: red;"><fmt:message key="error.id" /></p></i>
            </c:if>
            <c:if test="${message eq 'empty'}">
                <i><p style="color: red;"><fmt:message key="error.empty" /></p></i>
            </c:if>
        </form>

    </body>
</html>
