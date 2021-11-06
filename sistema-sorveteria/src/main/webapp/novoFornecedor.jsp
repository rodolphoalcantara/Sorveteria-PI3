<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="pt-br">
        <c:import url="uteis/header.jsp"/>
        <meta charset="utf-8">
        <title>Fornecedores</title>
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
        <h1>Cadastrar novo Fornecedor</h1>
        <form name="formFornecedor" action="insertFornecedor" method="post">
            <table class="input-group flex-nowrap" >
                <tr>
                    <td><input type="text" name="cnpj" placeholder="CNPJ"></td>
                </tr>
                <tr>
                    <td><input type="text" name="nome" placeholder="Nome"></td>
                </tr>
                <tr>
                    <td><input type="text" name="email" placeholder="E-mail"></td>
                </tr>
                <tr>
                    <td><input type="text" name="telefone" placeholder="Telefone"></td>
                </tr>
                <tr>
                    <td><input type="text" name="endereco" placeholder="Endereço"></td>
                </tr>
                <tr>
                    <td><input type="text" name="cidade" placeholder="Cidade"></td>
                </tr>
                <tr>
                    <td><input type="text" name="estado" placeholder="Estado"></td>
                </tr>
                <tr>
                    <td><input type="text" name="segmento" placeholder="Segmento"></td>
                </tr>
            </table>
            <input type="button" value="Cadastrar" onclick="validar()">
        </form>
        <script src="scripts/validador.js"></script>
    </body>
</html>