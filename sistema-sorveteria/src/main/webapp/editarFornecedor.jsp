<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="pt-br">
        <c:import url="uteis/header.jsp"/>
        <meta charset="utf-8">
        <title>Editar Fornecedores</title>
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
    </head>
    <body>
        <nav class="navbar navbar-dark bg-brown ">
            <a class="navbar-brand" href="index.jsp">
                <img src="assets/Logo.svg" width="80" height="80" class="d-inline-block align-center" alt="">
                Sorveteria
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="http://pudim.com.br">Logout</a>
                </li>
            </ul>
        </nav>
        <h1>Editar Fornecedor</h1>
        <form name="formFornecedor" action="updateFornecedor" method="post">
            <table class="input-group flex-nowrap" >
                <tr>
                    <td><input type="text" name="idFor" readonly value="<%out.print(request.getAttribute("idFor"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="cnpj" placeholder="CNPJ" value="<%out.print(request.getAttribute("cnpj"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="nome" placeholder="Nome" value="<%out.print(request.getAttribute("nome"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="email" placeholder="E-mail" value="<%out.print(request.getAttribute("email"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="telefone" placeholder="Telefone" value="<%out.print(request.getAttribute("telefone"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="endereco" placeholder="Endereço" value="<%out.print(request.getAttribute("endereco"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="cidade" placeholder="Cidade" value="<%out.print(request.getAttribute("cidade"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="estado" placeholder="Estado" value="<%out.print(request.getAttribute("estado"));%>"></td>
                </tr>
                <tr>
                    <td><input type="text" name="segmento" placeholder="Segmento" value="<%out.print(request.getAttribute("segmento"));%>"></td>
                </tr>
            </table>
            <input type="button" value="Salvar" onclick="validar()">
        </form>
        <script src="scripts/validador.js"></script>
    </body>
</html>