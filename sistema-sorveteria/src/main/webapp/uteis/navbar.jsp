<%-- 
    Document   : navbar.jsp
    Created on : 2 de nov de 2021, 18:37:58
    Author     : rodolpho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <nav class="navbar navbar-dark bg-brown">
            <a class="navbar-brand" href="#">
                <img src="${pageContext.request.contextPath}/assets/Logo.svg" width="65" height="65" class="d-inline-block align-center" alt="">
                Sorveteria
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <div class="container"><a class="nav-link active" href="#">Logout</a></div>
                </li>
            </ul>
        </nav>
    </body>
</html>
