<%-- 
    Document   : index.jsp
    Created on : 31 de out de 2021, 16:47:49
    Author     : rodolpho
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="uteis/header.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sorveteria</title>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-brown">
            <a class="navbar-brand" href="#">
                <img src="assets/Logo.svg" width="80" height="80" class="d-inline-block align-center" alt="">
                Sorveteria
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <div class="container"><a class="nav-link active" href="#">Logout</a></div>
                </li>
            </ul>
        </nav>
        <header class="welcome-header">
            <h1>Bem vindo,</h1>
            <h2>Naga Freitas</h2> <!--Mudar-->
        </header>
        <section class="relevant-section d-flex justify-content-around align-items-center">
            <div class="relevant-card flex-column justify-content-between">
                <div class="container relevant-card-content">
                    <div class="container d-flex justify-content-end" style="font-size: 64px">10</div>
                    <div class="container d-flex justify-content-end" style="font-size: 24px">Clientes Cadastrados</div>
                </div>
                <div class="container d-flex justify-content-between align-items-center relevant-card-footer">
                    <div style="font-size: 24px">Gerenciar Clientes</div>
                    <a href="#"><img src="assets/Seta.svg" width="30" height="30" alt="alt"/></a>
                </div>
            </div>

            <div class="relevant-card flex-column justify-content-between">
                <div class="container relevant-card-content">
                    <div class="container d-flex justify-content-end" style="font-size: 48px">R$ 00.000,00</div>
                    <div class="container d-flex justify-content-end" style="font-size: 24px">Faturamento</div>
                </div>
                <div class="container d-flex justify-content-between align-items-center relevant-card-footer">
                    <div style="font-size: 24px;">Relatório de Vendas</div>
                    <a href="#"><img src="assets/Seta.svg" width="30" height="30" alt="alt"/></a>
                </div>
            </div>
            <div class="relevant-card flex-column justify-content-between">
                <div class="container relevant-card-content">
                    <div class="container d-flex justify-content-end" style="font-size: 64px">10</div>
                    <div class="container d-flex justify-content-end" style="font-size: 20px">Fornecedores em Sistema</div>
                </div>
                <div class="container d-flex justify-content-between align-items-center relevant-card-footer">
                    <div style="font-size: 20px">Gerenciar Fornecedores</div>
                    <a href="#"><img src="assets/Seta.svg" width="30" height="30" alt="alt"/></a>
                </div>
            </div>


        </section>

    </body>
</html>
