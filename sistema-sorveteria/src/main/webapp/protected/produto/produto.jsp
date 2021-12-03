<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="${pageContext.request.contextPath}/uteis/header.jsp" />
        <link href="${pageContext.request.contextPath}/css/estoque.css"
              rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produto</title>
    </head>
    <body id="flex-outer">
        <c:import url="${pageContext.request.contextPath}/uteis/navbar.jsp" />
        <main class="d-flex flex-grow-1">
            <aside class="menu-lateral">
                Menu
            </aside>
            <section class="content-section-estoque">
                <section class="busca-section-estoque align-items-center">
                    <button class="btn btnPink mr-3" onclick="window.location.href='${pageContext.request.contextPath}/protected/admin/produto/formProduto.jsp'">Adicionar</button>
                    
                    <div id="divBusca">
                        <input type="text" id="txtBusca" placeholder="Buscar..."/>
                        <img src="${pageContext.request.contextPath}/assets/Lupa.svg" id="btnBusca" alt="Buscar"/>
                    </div>

                </section>
                <section class="table-section-estoque">
                   
                    <table id="tabelaProdutos" class="table">
                        <thead>
                        <th scope="col" class="col-1">Cód. Produto</th><th scope="col" class="col-4">Nome Produto</th><th scope="col" class="col-4">Descrição</th><th scope="col" class="col-2">Tipo</th><th scope="col" class="col-1">Preço</th>
                        </thead>
                        <tbody>
                            <c:forEach var="produto" items="${listaProdutos}">
                                <tr>
                                    <th scope="row">${produto.id}</th>
                                    <td>${produto.nome}</td>
                                    <td>${produto.descricao}</td>
                                    <td>${produto.tipo}</td>
                                    <td>${produto.valorUnitario}</td>
                                    <td class="col-1"><button onclick="window.location.href='/editarProduto?id=${produto.id}'" class="btn btnPink">Editar</button></td>
                                    <td class="col-1"><button onclick="mostrarModalExclusaoProduto(${produto.id}, '${produto.nome}')" class="btn btnPink">Remover</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </section>
            </section>
        </main>
        <div class="modal fade" id="modalExclusaoProduto" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Confirmar Exclusão</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Confirmar exclusão do produto  <label id="nomeProduto"></label> ?
                        <input id="idProduto" hidden="true" value="" />

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" onclick="excluirProduto()">Confirmar</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/estoque.js" type="text/javascript"></script>
    </body>
</html>