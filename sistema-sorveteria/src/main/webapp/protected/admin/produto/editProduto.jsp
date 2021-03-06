<%--
    Author     : Pedro.pecanha
    --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="${pageContext.request.contextPath}/uteis/header.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
    </head>
    <body>
        <c:import url="${pageContext.request.contextPath}/uteis/navbar.jsp"/>
        <h1 class="welcome-header">Formulário de Produtos</h1>
        <section class="container">
        <form action="/updateProduto" method="POST">
            <div class="form-group">
                <input type="hidden" name="id_produto" value="${produto.id}"
                       required class="form-control"/><br/>
            </div>
            <div  class="form-group">
                <label>Nome</label>
                <input type="text" name="nomeProduto" value="${produto.nome}"
                       required class="form-control"/><br/> 
            </div>
            <div class="form-group">
                <label>Descrição</label>
                <input type="text" name="descProduto" value="${produto.descricao}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>Tipo</label>
                <input type="text" name="tipoProduto" value="${produto.tipo}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>Valor</label>
                <input type="text" name="valorProduto" value="${produto.valorUnitario}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>Estoque</label>
                <input type="text" name="estoqueProduto" value="${produto.estoque}"
                       required class="form-control"/><br/>
            </div>
            <br/>
            <br/>
            <button type="submit" class="btn bg-pink btnOutlineBrown ml-0 w-100">Enviar</button>
        </form>
        </section>>
    </body>
</html>