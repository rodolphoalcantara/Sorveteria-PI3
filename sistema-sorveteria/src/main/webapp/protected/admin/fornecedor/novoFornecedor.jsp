<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="pt-br">
        <c:import url="${pageContext.request.contextPath}/uteis/header.jsp"/>
        <meta charset="utf-8">
        <title>Fornecedores</title>
    </head>
    <body>
        <c:import url="${pageContext.request.contextPath}/uteis/navbar.jsp" />
        <h1>Cadastrar novo Fornecedor</h1>
        <form name="formFornecedor" action="/insertFornecedor" method="post">
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
            <input type="button" value="Cadastrar" onclick="validarFornecedor()">
        </form>
        <script src="${pageContext.request.contextPath}/js/validador.js"></script>
    </body>
</html>