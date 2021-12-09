<%@page import="java.sql.ResultSet"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session ="true" %>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="languages/messages"/>

<html lang="${cookie['lang'].value}">
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="schedule" /></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
    </head>
    <body>
        <jsp:include page="menuView.jsp"></jsp:include>
        <form name="schedule">
            <TABLE BORDER="1">
                <TR>
                    <TH>-ID-</TH>
                    <TH><fmt:message key="schedule.movie" /></TH>
                    <TH><fmt:message key="schedule.age" /></TH>
                    <TH><fmt:message key="schedule.time" /></TH>
                    <TH><fmt:message key="schedule.price" /></TH>
                    <TH><fmt:message key="schedule.places.br" /></TH>
                    <TH><fmt:message key="schedule.hall" /></TH>
                </TR>

                <c:if test="${not empty schedulePage.movies}">
                    <c:forEach items="${schedulePage.movies}" var="record">
                        <tr>
                            <td>${record.sessionId}</td>
                            <td>${record.movieTitle}</td>
                            <td>${record.age}</td>
                            <td>${record.sessionTime}</td>
                            <td>${record.price}</td>
                            <td>${record.freePlaces}</td>
                            <td>${record.hallId}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </TABLE>

            <tr>
                <fmt:message key="schedule.page.size" />
                <select name="pageSize">
                    <option <c:if test="${schedulePage.page.pageSize eq 5}">selected="selected"</c:if>>5</option>
                    <option <c:if test="${schedulePage.page.pageSize eq 10}">selected="selected"</c:if>>10</option>
                    <option <c:if test="${schedulePage.page.pageSize eq 20}">selected="selected"</c:if>>20</option>
                </select>
                <input type="submit" value= "Submit" />
            </tr>

            <c:set var="sortA" value="schedule?pageSize=${schedulePage.page.pageSize}&sort="/>
            <c:set var="sortB" value="schedule?pageSize=${schedulePage.page.pageSize}&sort=${schedulePage.sortBy.sort}&sortOrder="/>
            <div>
                <fmt:message key="schedule.sort.by" />
                &emsp;
                <a href="${sortA}m.movieTitle"><fmt:message key="schedule.movie" /></a>
                &emsp;--
                <a href="${sortA}s.sessionTime"><fmt:message key="schedule.time" /></a>
                --&emsp;
                <a href="${sortA}s.freePlaces"><fmt:message key="schedule.places" /></a>
                &emsp;
                ||
                &emsp;
                <fmt:message key="schedule.sort.order" />
                &emsp;
                <a href="${sortB}ASC"><fmt:message key="schedule.ascending" /></a>
                //
                <a href="${sortB}DESC"><fmt:message key="schedule.descending" /></a>
            </div>

            <c:set var="filter" value="schedule?pageSize=${schedulePage.page.pageSize}&sort=${schedulePage.sortBy.sort}&sortOrder=${schedulePage.sortBy.sortOrder}&filterAge="/>
            <div>
                <fmt:message key="schedule.filter.by" />
                &emsp;
                <a href="${filter}"><fmt:message key="schedule.filter.all" /></a>
                --
                <a href="${filter}6">6+</a>
                --
                <a href="${filter}12">12+</a>
                --
                <a href="${filter}16">16+</a>
            </div>

            <br>
            <div class="pagination">

                <c:set var="url" value="schedule?pageSize=${schedulePage.page.pageSize}&sort=${schedulePage.sortBy.sort}&sortOrder=${schedulePage.sortBy.sortOrder}&filterAge=${schedulePage.sortBy.filterAge}&position="/>
                <c:set var="a" value="${schedulePage.page.pageSize * 2}"/>
                <c:set var="b" value="${schedulePage.page.total - schedulePage.page.pageSize}"/>
                <c:set var="d" value="${schedulePage.page.total - schedulePage.page.pageSize - a}"/>
                <c:set var="e" value="${schedulePage.page.numberOfPages * schedulePage.page.pageSize}"/>
                <c:set var="f" value="${schedulePage.page.total - a}"/>
                <fmt:formatNumber var="c" value="${schedulePage.page.position / schedulePage.page.pageSize}" pattern="#"/>

                <c:if test="${schedulePage.page.position > 0}">
                    <a href="${url}${schedulePage.page.position - schedulePage.page.pageSize}">&laquo;</a>
                </c:if>

                <c:if test="${schedulePage.page.position > schedulePage.page.pageSize}">
                    <a href="${url}0">1</a>
                </c:if>

                <c:if test="${schedulePage.page.position > a}">
                    <a>...</a>
                </c:if>

                <c:if test="${schedulePage.page.position > 0}">
                    <a href="${url}${schedulePage.page.position - schedulePage.page.pageSize}">
                        ${c}
                    </a>
                </c:if>

                <a>${c + 1}</a>

                <c:if test="${schedulePage.page.position < b}">
                    <a href="${url}${schedulePage.page.position + schedulePage.page.pageSize}">
                        ${c + 2}
                    </a>
                </c:if>

                <c:if test="${schedulePage.page.position < d}">
                    <a>...</a>
                </c:if>

                <c:if test="${schedulePage.page.position < f}">
                    <a href="${url}${e - schedulePage.page.pageSize}">${schedulePage.page.numberOfPages}</a>
                </c:if>

                <c:if test="${schedulePage.page.position < b}">
                    <a href="${url}${schedulePage.page.position + schedulePage.page.pageSize}">&raquo;</a>
                </c:if>

            </div>

        </form>

    </body>
</html>

