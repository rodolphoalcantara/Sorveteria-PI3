<%-- 
    Document   : fornecedor
    Created on : 02 de nov de 2021, 23:02:17
    Author     : Renan Cardoso
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.sorveteria.model.Fornecedor"%>
<%@page import="java.util.ArrayList"%>
<%ArrayList<Fornecedor> lista = (ArrayList<Fornecedor>) request.getAttribute("fornecedores");%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <c:import url="${pageContext.request.contextPath}/uteis/header.jsp" />
        <link href="${pageContext.request.contextPath}/css/estoque.css"
              rel="stylesheet" type="text/css"/>
        <meta charset="utf-8">
        <title>Fornecedores</title>        
    </head>
    <body>
        <c:import url="${pageContext.request.contextPath}/uteis/navbar.jsp" />
        <section>
            <h1 class="welcome-header">Fornecedores</h1>
            <button class="btn btnPink btnOutlineBrown" onclick="window.location.href='view/fornecedor/novoFornecedor.jsp'">Novo Fornecedor</button>
        </section>
        <section class="table-section-estoque">
            <table class="table">
            <thead>
            <th scope="col" class="col-0">ID</th>
                <th scope="col" class="col-2">CNPJ</th>
                <th scope="col" class="col-1">NOME</th>
                <th scope="col" class="col-1">E-MAIL</th>
                <th scope="col" class="col-1">TELEFONE</th>
                <th scope="col" class="col-2">ENDEREÇO</th>
                <th scope="col" class="col-2">CIDADE</th>
                <th scope="col" class="col-1">ESTADO</th>
                <th scope="col" class="col-1">SEGMENTO</th>
            </thead>
            <tbody>
                <%for(int i=0;i<lista.size();i++){%>
                    <tr>
                        <td><%=lista.get(i).getIdFor()%></td>
                        <td><%=lista.get(i).getCnpj().length() > 13 ? lista.get(i).getCnpj().substring(0,13) + "..." : lista.get(i).getCnpj()%></td>
                        <td><%=lista.get(i).getNome().length() > 9 ? lista.get(i).getNome().substring(0,8) + "..." : lista.get(i).getNome()%></td>
                        <td><%=lista.get(i).getEmail().length() > 20 ? lista.get(i).getEmail().substring(0,20) + "..." : lista.get(i).getEmail()%></td>
                        <td><%=lista.get(i).getTelefone()%></td>
                        <td><%=lista.get(i).getEndereco().length() > 18 ? lista.get(i).getEndereco().substring(0,18) + "..." : lista.get(i).getEndereco()%></td>
                        <td><%=lista.get(i).getCidade().length() > 10 ? lista.get(i).getCidade().substring(0,10) + "..." : lista.get(i).getCidade()%></td>
                        <td><%=lista.get(i).getEstado()%></td>
                        <td><%=lista.get(i).getSegmento()%></td>
                        <td class="col-1"><a href="editarF?idFor=<%=lista.get(i).getIdFor()%>">Editar</a></td>
                        
                        <!--   Não é possível excluir fornecedor por conta da relação com a tabela Produtos.
                        <td class="col-1"><a href="javascript: confirmar(<%=lista.get(i).getIdFor()%>)">Excluir</a>
                        -->
                        </td>
                    </tr>
                <%}%>
            </tbody>
        </table>
        </section>
        <script src="${pageContext.request.contextPath}/js/confirmar.js"></script>
    </body>
</html>
