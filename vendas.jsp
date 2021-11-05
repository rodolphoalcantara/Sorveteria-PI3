<%-- 
    Document   : vendas
    Created on : 04/11/2021, 20:24:04
    Author     : Paola
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>SORVETERIA PI3</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/vendaEstilo.css">
        <!--<script src="selecionaLinha.js"></script>--> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body id="duas-col">
        <header>
            <nav>
                <div class="center">
                    <ul>
                        <button type="button" value="Voltar" onClick="history.go(-1)">Voltar</button>
                        <li><a href="/restrito/clientes">Gestao de Cliente</a></li>
                        <li><a href="/restrito/produtos">Gestao de Produtos</a></li>
                        <li><a href="/restrito/vendas.jsp">Vendas</a></li>
                    </ul>
                </div><!--center-->
            </nav>
        </header>
        <div id="espacador">
            <br>
        </div><!--espacador-->
        <section class="center">
            <div class="nome-aba">Vendas</div>
            <div id="primeira">

                <form id="form1" method="post" action="${pageContext.request.contextPath}/restrito/vendas" novalidate>
                    <div class="conteudo">
                        <label for="">Filial: </label>
                        <input disabled type="text" name="filial" value="${filialAtr}">
                    </div><!--conteudo-->
                    <div>
                        <label for="">Cliente CPF: </label>
                        <input type="text" name="cliente" value="${clienteAtr}">
                        <c:if test="${erroCliente != null}">
                            <div class="msgErro">
                                <c:out value="${erroCliente}"/>
                            </div>
                        </c:if>
                    </div>
                    <div>
                        <label for="">ID Produto: </label>
                        <input type="text" name="produtoId" value="${prodIdAtr}">
                        <c:if test="${erroProdId != null}">
                            <div class="msgErro">
                                <c:out value="${erroProdId}"/>
                            </div>
                        </c:if>
                    </div>     
                    <div>
                        <label for="">Nome Produto: </label>
                        <input disabled type="text" name="produto" value="${prodNomeAtr}">
                    </div>     
                    <div>
                        <label for="">Quantidade: </label>
                        <input type="text" name="qtd" value="${qtdAtr}">
                        <c:if test="${erroQtd != null}">
                            <div class="msgErro">
                                <c:out value="${erroQtd}"/>
                            </div>
                        </c:if>
                    </div>     
                    <div>
                        <label for="">Valor: </label>
                        <input disabled type="text" name="valor" value="${valorAtr}">
                    </div> 
                    <input id="linhaSelec" type="text" name="linhaSelec" value="-1" hidden="true"></input>
                </form>
                <div class="botoes">
                    <button form="form1" type="submit" name="finalizarBtt">Finalizar Venda</button>
                    <button form="form1" type="submit" name="cancelarBtt"> Cancelar Venda</button>
                    <button formaction="${pageContext.request.contextPath}/cliente" type="submit">
                        Cadastrar Cliente</button>
                </div><!--botoes-->
            </div><!--primeira-->
            <div id="segunda">
                <div class="conteudo">
                    <div class="carrinho">
                        <div class="titulo">
                            Carrinho
                        </div><!--titulo-->
                        <table id="tabela">
                            <thead>
                            <th class="prod">Produto</th>
                            <th class="qtd">Quantidade</th>
                            <th class="preco">Valor</th>
                            </thead>
                            <c:forEach var="produto" items="${listaProd}">
                                <tr id="${produto.id}" class="hover">
                                    <td>${produto.nome}</td>
                                    <td class="qtd">${produto.quantidade}</td>
                                    <td class="preco">${produto.valor} R$</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div><!--carrinho-->
                    <div class="botoes">
                        <button form="form1" type="submit" name="addProdBtt">Add Produto</button>
                        <button form="form1" type="submit" name="removeBtt">Excluir Produto</button>
                    </div>
                    <div class="valor-total">
                        <div>Valor Total</div>
                        <input disabled type="text" name="total" value="${totalAtr}">
                    </div>
                </div><!--conteudo-->
            </div><!--segunda-->
            <div style="clear: both;"></div>
        </section><!--center-->

        <footer>
            <div>
                Felipe Ferreira Henriques, Luciana Alves, Matheus Makoto e Rogerio Lucon. 
            </div>
        </footer>
        <c:if test="${erroAlert != null}">
            <script>
                var r = alert("${erroAlert}");
            </script>
        </c:if>
        <script>
            var tabela = document.getElementById("tabela");
            var linhas = tabela.getElementsByTagName("tr");
            var valor = document.getElementById("linhaSelec");

            for (var i = 0; i < linhas.length; i++) {
                var linha = linhas[i];
                linha.addEventListener("click", function () {
                    selLinha(this, false);
                });
            }

            function selLinha(linha, multiplos) {
                if (!multiplos) {
                    var linhas = linha.parentElement.getElementsByTagName("tr");
                    for (var i = 0; i < linhas.length; i++) {
                        var linha_ = linhas[i];
                        linha_.classList.remove("selecionado");
                    }
                }
                linha.classList.toggle("selecionado");
                valor.value = linha.id;
            }
        </script>
    </body>
</html>
