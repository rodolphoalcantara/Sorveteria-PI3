<%-- 
    Document   : vendas
    Created on : 05/11/2021, 21:07:57
    Author     : Paola
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/vendaEstilo.css">-->
        <c:import url="${pageContext.request.contextPath}/uteis/header.jsp" />
        <title>Sorveteria</title>
    </head>
    <body>
        <c:import url="${pageContext.request.contextPath}/uteis/navbar.jsp" />
        <header>
        <h1 class="welcome-header">Venda</h1>
        </header>
        <main class="container">
        <form>
            <div class="form-row">
                <div class="col">
                    <label for="nomeCliente">Cliente</label>
                    <input id="nomeCliente" class="form-control" disabled type="text" name="nomeCliente" value="${cliente.nome}"
                           required class="form-control"/>
                </div>
                    <div class="col">
                        <label for="buscarCPF">Buscar por CPF:</label>
                        <div id="divBusca">
                            <input name="buscarCPF" type="text" id="txtBusca" placeholder="Buscar..."/>
                            <img src="${pageContext.request.contextPath}/assets/Lupa.svg" id="btnBuscaCPF" alt="Buscar"/>
                            <button type="button" onclick="buscarClienteCPF()" id="btnBusca">Buscar</button>
                        </div>
                    </div>
            </div>
            <div class="mt-3 form-row">
                <div class="form-group col-6">
                    <label for="selectProduto">Selecione um produto</label>
                    <select class="form-control" id="selectProduto">
                        <c:forEach var="produto" items="${listProds}">
                            <option value="${produto.id}">${produto.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-2">
                    <label for="quantidadeProd">Quantidade</label>
                    <input class="form-control" type="text" id="quantidadeProd" name="quantidadeProd"
                           required class="form-control"/>
                </div>
                <button type="button" onclick="adicionarProdutoCarrinho()" class="btnPink btnOutlineBrown m-3 p-3">Adicionar</button>

            </div>
        </form>     
        <section class="table-section-estoque">

            <table  class="table" id="tableCarrinho">
                <thead>
                <th scope="col" class="col-2">Cód. Produto</th><th scope="col" class="col-4">Nome Produto</th><th scope="col" class="col-3">Quantidade</th>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${carrinho}">
                        <tr>
                            <th id="idList" hidden="true">${item.idLista}</th>
                            <th scope="row">${item.idProd}</th>
                            <td>${item.nomeProd}</td>
                            <td>${item.quantidade}</td>
                            <td class="col-2"><button onclick="retirarCarrinho()" id="btnDelete" class="btn bg-pink">Retirar</button></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>
        <section class="m-3 p-4 d-flex">
            
                <button onclick="finalizarVenda()" class="btnPink btnOutlineBrown">Finalizar</button>
            
        </section>
        </main>
        <div>                  
    </body>
</html>
