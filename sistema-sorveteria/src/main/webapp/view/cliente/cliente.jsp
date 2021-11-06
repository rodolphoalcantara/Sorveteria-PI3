<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../../uteis/header.jsp" />
        <link href="${pageContext.request.contextPath}/css/estoque.css"
              rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
    </head>
    <body id="flex-outer">
        <c:import url="../../uteis/navbar.jsp" />
        <main class="d-flex flex-grow-1">
            <aside class="menu-lateral">
                Menu
            </aside>
            <section class="content-section-estoque">
                <section class="busca-section-estoque align-items-center">
					
					<div>
						<button onclick="" class="btn btnPink mr-3">Adicionar</button>
					</div>
					
                    <div id="divBusca">
                        <input type="text" id="txtBusca" placeholder="Buscar..."/>
                        <img src="../../assets/Lupa.svg" id="btnBusca" alt="Buscar"/>
                    </div>

                </section>
                <section class="table-section-estoque">
                   
                    <table  class="table">
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
                                    <td class="col-1"><button onclick="" class="btn btnPink">Editar</button></td>
                                    <td class="col-1"><button onclick="" class="btn btnPink">Remover</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </section>
            </section>
        </main>
    </body>
</html>