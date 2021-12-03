<%-- 
    Document   : navbar.jsp
    Created on : 2 de nov de 2021, 18:37:58
    Author     : rodolpho
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <nav class="navbar navbar-dark bg-brown">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/assets/Logo.svg" width="65" height="65" class="d-inline-block align-center" alt="">
                Sorveteria
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <c:if test="${fn:contains(pageContext.request.requestURI,'/protected/')}" >
                        <div class="container"><a class="nav-link active" href="${pageContext.request.contextPath}/LoginServlet">Logout</a></div>
                    </c:if>
                </li>
            </ul>
        </nav>
    </body>
</html>
