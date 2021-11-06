<%-- 
    Document   : listarProdutos.jsp
    Created on : 2 de nov de 2021, 18:01:49
    Author     : rodolpho
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../../uteis/header.jsp" />
        <link href="${pageContext.request.contextPath}/css/estoque.css"
              rel="stylesheet" type="text/css"/>
        <script src="../../js/estoque.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estoque</title>
    </head>
    <body id="flex-outer">
        <c:import url="../../uteis/navbar.jsp" />
        <main class="d-flex flex-grow-1">
            <aside class="menu-lateral">
                Menu
            </aside>
            <section class="content-section-estoque">
                <section class="busca-section-estoque">
                      
                    <form id="divBusca" action="">
                        <img src="../../assets/Lupa.svg" />
                        <input type="text" id="txtBusca" placeholder="Produto..." />
                        <input type="submit" onsubmit="buscarProduto()" id="btnBusca" value="Buscar" />
                    </form>

                </section>
                <section class="table-section-estoque">
                   
                    <table  class="table">
                        <thead>
                        <th scope="col" class="col-1">Cód. Produto</th><th scope="col" class="col-2">Nome Produto</th><th scope="col" class="col-1">Quantidade</th>
                        </thead>
                        <tbody>
                            <c:forEach var="produto" items="${listaProdutos}">
                                <tr>
                                    <th scope="row">${produto.id}</th>
                                    <td>${produto.nome}</td>
                                    <td>${produto.estoque}</td>
                                    <td class="col-1"><button onclick="" class="btn bg-pink">Adicionar</button></td>
                                    <td class="col-1"><button onclick="" class="btn bg-pink">Retirar</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </section>
            </section>
        </main>
    </body>
</html>
