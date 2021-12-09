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
        <title><fmt:message key="registration.title" /></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
    </head>
    <body>
        <jsp:include page="menuView.jsp"></jsp:include>

        <h3><fmt:message key="registration.title" /></h3>

        <form method="POST" action="${pageContext.request.contextPath}/registration">
            <input type="hidden" name="redirectId" value="${param.redirectId}" />
            <table border="0">
                <p style="color: chocolate">
                    <fmt:message key="registration.password" />
                </p>
                <tr>
                    <td><fmt:message key="input.login" /></td>
                    <td><input type="text" name="login" value= "${user.login}" /> </td>
                </tr>
                <tr>
                    <td><fmt:message key="input.password" /></td>
                    <td><input type="password" name="password" value= "${user.password}" /> </td>
                </tr>
                <tr>
                    <td><fmt:message key="input.password" /></td>
                    <td><input type="password" name="password2" /> </td>
                </tr>
                <tr>
                    <td><fmt:message key="input.bank.account" /></td>
                    <td><input type="text" name="bankAccount" value= "${user.bankAccount}" /> </td>
                </tr>
                <tr>
                    <td colspan ="2">
                        <input type="submit" value= "Submit" />
                        <INPUT TYPE="button" VALUE="Cancel" onClick="history.go(-1);">
                    </td>
                </tr>
            </table>
            <c:if test="${message eq 'password'}">
                <i><p style="color: red;"><fmt:message key="error.password" /></p></i>
            </c:if>
            <c:if test="${message eq 'registration'}">
                <i><p style="color: red;"><fmt:message key="error.registration" /></p></i>
            </c:if>
        </form>

    </body>
</html>
