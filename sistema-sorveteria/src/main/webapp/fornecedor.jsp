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
        <c:import url="uteis/header.jsp" />
        <meta charset="utf-8">
        <title>Fornecedores</title>
        
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/style.css">
        
    </head>
    <body>
        <nav class="navbar navbar-dark bg-brown ">
            <a class="navbar-brand" href="index.jsp">
                <img src="assets/Logo.svg" width="80" height="80" class="d-inline-block align-center" alt="">
                Sorveteria
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="http://pudim.com.br">Logout</a>
                </li>
            </ul>
        </nav>
        <h1>Fornecedores</h1>
        <a href="novoFornecedor.jsp">Novo Fornecedor  </a>
        <table id="tabelaFornecedor">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>CNPJ</th>
                    <th>NOME</th>
                    <th>E-MAIL</th>
                    <th>TELEFONE</th>
                    <th>ENDEREÇO</th>
                    <th>CIDADE</th>
                    <th>ESTADO</th>
                    <th>SEGMENTO</th>
                    <th>OPÇÕES</th>
                </tr>
            </thead>
            <tbody>
                <%for(int i=0;i<lista.size();i++){%>
                    <tr>
                        <td><%=lista.get(i).getIdFor()%></td>
                        <td><%=lista.get(i).getCnpj()%></td>
                        <td><%=lista.get(i).getNome()%></td>
                        <td><%=lista.get(i).getEmail()%></td>
                        <td><%=lista.get(i).getTelefone()%></td>
                        <td><%=lista.get(i).getEndereco()%></td>
                        <td><%=lista.get(i).getCidade()%></td>
                        <td><%=lista.get(i).getEstado()%></td>
                        <td><%=lista.get(i).getSegmento()%></td>
                        <td><a href="editarF?idFor=<%=lista.get(i).getIdFor()%>">Editar</a>
                        <a href="javascript: confirmar(<%=lista.get(i).getIdFor()%>)">Excluir</a>
                        </td>
                    </tr>
                <%}%>
            </tbody>
        </table>
        <script src="scripts/confirmar.js"></script>
    </body>
</html>
