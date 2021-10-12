<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="UTF-8"%>

<form name="movies">

    <jsp:include page="menuAvailableToAllView.jsp"></jsp:include>

     <TABLE BORDER="1">
        <TR>
            <TH>movie title</TH>
            <TH>access</TH>
            <TH>time</TH>
            <TH>&emsp;date&emsp;</TH>
            <TH>price</TH>
            <TH>free places</TH>
            <TH>hall<br/>number</TH>
        </TR>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty list}">
    <Е>
        <c:forEach items="${list}" var="record">
            <tr>
                <td>${record.movieTitle}</td>
                <td>${record.access}</td>
                <td>${record.movieTime}</td>
                <td>${record.movieDate}</td>
                <td>${record.price}</td>
                <td>${record.freePlaces}</td>
                <td>${record.hallId}</td>
            </tr>
        </c:forEach>
     </TABLE>
</c:if>

 </form>