<%-- 
    Document   : vendas
    Created on : 05/11/2021, 21:07:57
    Author     : Paola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/vendaEstilo.css">

        <title>Sorveteria</title>
    </head>
    <body>
        <h1>Venda</h1>

        <div>
            <label for="">ID Produto: </label>
            <input type="text" name="produtoId" value="${prodIdAtr}">
            <c:if test="${erroProdId != null}">
                <div class="msgErro">
                    <c:out value="${erroProdId}"/>
                </div>
            </c:if>
            <div>
                <label for="">Nome Produto: </label>
                <input disabled type="text" name="produto" value="${prodNomeAtr}">
            </div>  
        </div>     
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

</body>
</html>
