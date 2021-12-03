<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="${pageContext.request.contextPath}/uteis/header.jsp" />
        <link href="${pageContext.request.contextPath}/css/estoque.css"
              rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
    </head>
    <body id="flex-outer">
        <c:import url="${pageContext.request.contextPath}/uteis/navbar.jsp" />
        <main class="d-flex flex-grow-1">
            <section class="content-section-estoque">
                <section class="busca-section-estoque align-items-center">
                    <button id="btnAddProduto" onclick="" class="btn btnPink mr-3">Adicionar</button>
                    <form id="divBusca" action="">
                        <img src="${pageContext.request.contextPath}/assets/Lupa.svg" />
                        <input type="text" id="txtBusca" placeholder="Buscar..." />
                        <input type="submit" onsubmit="" id="btnBusca" value="Buscar" />
                    </form>

                </section>
                <section class="table-section-estoque">
                   
                    <table id="tabelaClientes" class="table">
                        <thead>
                        <th scope="col" class="col-1">Cód. Cliente</th><th scope="col" class="col-2">Nome</th><th scope="col" class="col-2">CPF</th><th scope="col" class="col-4">Email</th><th scope="col" class="col-1">Data de Nasc.</th><th scope="col" class="col-1">cidade</th><th scope="col" class="col-1">sexo</th>
                        </thead>
                        <tbody>
                            <c:forEach var="cliente" items="${listaClientes}">
                                <tr>
                                    <th scope="row">${cliente.id}</th>
                                    <td>${cliente.nome}</td>
                                    <td>${cliente.CPF}</td>
                                    <td>${cliente.email}</td>
                                    <td>${cliente.data_nasc}</td>
                                    <td>${cliente.cidade}</td>
                                    <td>${cliente.sexo}</td>
                                    <td class="col-1"><button onclick="window.location.href='/editarCliente?id=${cliente.id}'" class="btn btnPink">Editar</button></td>
                                    <td class="col-1"><button onclick="mostrarModalExclusaoCliente(${cliente.id}, '${cliente.nome}')" class="btn btnPink">Remover</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </section>
            </section>
        </main>
                    
        <div class="modal fade" id="modalExclusaoCliente" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">Confirmar Exclusão</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                    Confirmar exclusão do cliente  <label id="nomeCliente"></label> ?
                    <input id="idCliente" hidden="true" value="" />
                 
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                  <button type="button" class="btn btn-primary" onclick="excluirCliente()">Confirmar</button>
                </div>
              </div>
            </div>
          </div>
        <script src="${pageContext.request.contextPath}/js/estoque.js" type="text/javascript"></script>
    </body>
</html>