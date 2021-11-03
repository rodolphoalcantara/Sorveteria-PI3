<%-- 
    Document   : relatorioEstoque
    Created on : 31 de out de 2021, 23:37:25
    Author     : Naga
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="uteis/header.jsp" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatorio estoque</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css">

        <!-- Optional theme -->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css">

    </head>
    <body>    
        <nav class="navbar navbar-dark bg-brown ">
            <a class="navbar-brand" href="#">
                <img src="assets/Logo.svg" width="80" height="80" class="d-inline-block align-center" alt="">
                Sorveteria
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Logout</a>
                </li>
            </ul>
        </nav>
        <div class="container-fluid">
            <div class="form-busca form-busca-off form-group">
                <form action="/hms/accommodations" method="GET"> 
                    <div class="row">
                        <label class="col-form-label col-md-2">Cod.produto</label>
                        <div class="col-sm-12 col-md-10 ">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search" id="txtSearch"/>
                                <div class="input-group-btn">
                                    <button class="btn btn-primary" type="submit">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="row"></div>
            <div class="col-md-12"></div>
            <table class="table">
                <tr>
                    <th>Cod.Produto </th><th>Nome do produto </th><th>Data
                    </th><th>Quantidade </th><th>Tipo </th>
                    <th>Responsavel </th>

                </tr>    
            </table>
        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
