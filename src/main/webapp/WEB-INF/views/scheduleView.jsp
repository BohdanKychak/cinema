<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>
<head>
    <meta charset="UTF-8">
    <title>Schedule</title>
    <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
</head>

<form name="schedule">

    <jsp:include page="menuAvailableToAllView.jsp"></jsp:include>

     <TABLE BORDER="1">
        <TR>
            <TH>-ID-</TH>
            <TH>movie title</TH>
            <TH>&emsp;age&emsp;</TH>
            <TH>poster</TH>
            <TH>&emsp;date&emsp;</TH>
            <TH>time</TH>
            <TH>price</TH>
            <TH>free places</TH>
            <TH>hall<br>number</TH>
        </TR>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty list}">
    <Е>
        <c:forEach items="${list}" var="record">
            <tr>
                <td>${record.sessionId}</td>
                <td>${record.movieTitle}</td>
                <td>${record.age}</td>
                <td>${record.poster}</td>
                <td>${record.sessionDate}</td>
                <td>${record.sessionTime}</td>
                <td>${record.price}</td>
                <td>${record.freePlaces}</td>
                <td>${record.hallId}</td>
            </tr>
        </c:forEach>
     </TABLE>
</c:if>

 </form>