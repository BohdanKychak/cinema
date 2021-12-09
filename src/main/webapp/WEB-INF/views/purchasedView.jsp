<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="languages/messages"/>

<html lang="${cookie['lang'].value}">
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="purchased.title"/></title>
        <link rel="icon" href="${pageContext.request.contextPath}/img/icon.ico" type="image/x-icon">
        <style>
            <%@include file="css/copy.css" %>
        </style>
        <script>
            jQuery(document).ready(function ($) {
                $('.text_copy_link').click(function () {
                    var $text_copy = $(this);
                    var $temp = $("<input>");
                    $("body").append($temp);
                    $temp.val($text_copy.text()).select();
                    document.execCommand("copy");
                    $temp.remove();
                    $('.copy_link_mess').fadeIn(400);
                    setTimeout(function () {
                        $('.copy_link_mess').fadeOut(400);
                    }, 5000);
                });
            });
        </script>
    </head>
    <body>
        <h3><p style="color: green;"><fmt:message key="purchased.check"/></p></h3>
        <div class="text_copy_link">
            <h3><p style="color: green;">${message}</p></h3>
        </div>
        <h4><p style="color: #004f80;"><i><fmt:message key="copy"/></i></p></h4>

        <div class="copy_link_mess"><fmt:message key="copy.save"/></div>

        <div>
            <a href="javascript:history.back()">
                <fmt:message key="input.back"/>
            </a>
        </div>

    </body>
</html>
