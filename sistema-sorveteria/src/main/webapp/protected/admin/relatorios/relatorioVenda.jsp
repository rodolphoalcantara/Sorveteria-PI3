<%-- 
    Document   : relatorioVenda
    Created on : 31 de out de 2021, 22:30:53
    Author     : Naga
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="uteis/header.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatorio de vendas</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">
    </head>
    <body>
        <nav class="navbar navbar-dark bg-brown navbar-custom">
            <div class="container-fluid">
                <a class="navbar-brand navbar-brand-custom" href="index.jsp">
                    <img src="assets/Logo.svg" width="80" height="80" class="d-inline-block align-center" alt="">
                    Sorveteria
                </a>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <div class=""><a class="nav-link active" href="#">Logout</a></div>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container-fluid">
            <div class="panel panel-default">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-heading">
                            <h2>Relatório Venda por Período</h2>
                        </div>
                        <div class="panel-body">
                            <form method="post" action="#" id="frmVenda" class="form-horizontal">
                                <div class="form-group row">
                                    <label for="dataInicial" class="col-sm-2 col-form-label">Data Inicial:</label>
                                    <div class="col-sm-3">
                                        <input type="date" class="form-control" id="dataInicial" name="dataInicial" required> 
                                    </div>

                                    <label for="dataFinal" class="col-sm-2 col-form-label">Data Final</label>
                                    <div class="col-sm-3">
                                        <input type="date" class="form-control" id="dataFinal"
                                               name="dataFinal" required>
                                    </div>

                                    <div class="col-sm-2">
                                        <button type="submit" class="btn btn-default">Buscar</button>
                                    </div>
                                </div>  
                            </form>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 panel-body">
                        <div class="col-sm-3 form-busca form-busca-off float-right">
                            <form class="form-busca-site" action="">              
                                <div class="col-sm-12">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Buscar por CPF..." id="txtSearch" name="codProduto"/>
                                        <input type="hidden" class="form-control" name="acao" value="filtrar"/>
                                        <div class="input-group-btn">
                                            <button class="btn btn-defaul" type="submit">
                                                <span class="glyphicon glyphicon-search"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <a href="relatorioEstoque" class="btn btn-lg btn-block bg-brown text-white">Relatório Estoque</a>

            </div>

            <table class="table table-striped" >
                <thead>
                    <tr>
                        <th scope="col">N.Venda</th>
                        <th scope="col">Data</th>
                        <th scope="col">Cliente</th>
                        <th scope="col">Vendedor</th>
                        <th scope="col">Valor total </th>
                    </tr>
                </thead>
                 <tbody>
                    <c:forEach var="venda" items="${requestScope.vendas}">
                        <tr>
                            <th scope="row">${venda.id}</th>
                            <td>${venda.dataVenda}</td>
                            <td>${venda.fkIdCliente}</td>
                            <td>${venda.fkIdFuncionario}</td>
                            <td>${venda.valorTotal}</td>
                        <tr>
                        </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
