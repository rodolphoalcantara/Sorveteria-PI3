<%-- 
    Document   : login
    Created on : 28 de nov de 2021, 14:55:00
    Author     : rodolpho
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="${pageContext.request.contextPath}/uteis/header.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <c:import url="${pageContext.request.contextPath}/uteis/navbar.jsp" />
        <main class="containerLogin">
            <section class="">
                <img src="${pageContext.request.contextPath}/assets/icecream_login.svg" width="580" height="440" alt="login_icecream"/>
            </section>
            <section class="form-login d-flex flex-column">
                <h1>Log In</h1>
                <c:if test="${param.invalid != null}">
                    <div class="alert alert-danger" role="alert">
                        Usuário e/ou Senha inválidos.
                    </div>
                </c:if>
                <form id="formLogin" action="LoginServlet" method="POST">
                    <input class="form-control" type="text" id="loginUsuario" placeholder="Login">
                    <input class="form-control" type="password" id="senhaUsuario" placeholder="Senha">
                    <button class="btnPink btnOutlineBrown" type="" onclick="enviarRequisicao()" >Log In</button>
                </form>
            </section>
        </main>
    </body>
    <script src="${pageContext.request.contextPath}/js/login.js" type="text/javascript"></script>
</html>
