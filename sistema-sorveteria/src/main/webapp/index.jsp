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
        <c:import url="uteis/navbar.jsp" />
        <header class="welcome-header">
            <div>Bem vindo,</div>
            <div>Usuário</div> <!--Mudar-->
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
        <section class="options-section">
            <div class="option-card d-flex justify-content-center align-items-center text-center">
                <a class="flex-column" href="#">
                    <img src="assets/Sacola.svg" width="80" height="80" class="align-self-end" alt="" />
                    <div class="option-link">Realizar Vendas</div>
                </a>
            </div>
            <div class="option-card d-flex justify-content-center align-items-center text-center">
                <a class="flex-column" href="#">
                    <img src="assets/Produto.svg" width="80" height="80" class="align-self-end" alt="" />
                    <div class="option-link">Produtos</div>
                </a>
            </div>
            <div class="option-card d-flex justify-content-center align-items-center text-center">
                <a class="flex-column" href="#">
                    <img src="assets/Funcionario.svg" width="80" height="80" class="align-self-end" alt="" />
                    <div class="option-link">Funcionarios</div>
                </a>
            </div>
            <div class="option-card d-flex justify-content-center align-items-center text-center">
                <a class="flex-column" href="#">
                    <img src="assets/Caixa.svg" width="80" height="80" class="align-self-end" alt="" />
                    <div class="option-link">Estoque</div>
                </a>
            </div>
        </section>
    </body>
</html>
