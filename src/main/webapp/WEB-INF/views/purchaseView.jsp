<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session ="true" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="languages/messages"/>

<html lang="${cookie['lang'].value}">
<html>
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="purchase.title" /></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
    </head>
    <body>
        <jsp:include page="menuView.jsp"></jsp:include>

        <h3><fmt:message key="purchase.title" /></h3>

        <form method="POST" action="${pageContext.request.contextPath}/purchase">
            <input type="hidden" name="redirectId" value="${param.redirectId}"/>
            <table border="0">

                <tr>
                    <td>
                        <i>
                            <fmt:message key="purchase.text" />
                        </i>
                    </td>
                </tr>
                <tr>
                    <td>
                        ID: <input type="number" name="id" min="1">
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="input.place" /> <input type="number" name="place" min="1" max="40">
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
