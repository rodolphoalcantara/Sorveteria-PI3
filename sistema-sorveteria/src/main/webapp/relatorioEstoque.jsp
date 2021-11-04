<%-- 
    Document   : relatorioEstoque
    Created on : 31 de out de 2021, 23:37:25
    Author     : Naga
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js" integrity="sha384-THVO/sM0mFD9h7dfSndI6TS0PgAGavwKvB5hAxRRvc0o9cPLohB0wb/PTA7LdUHs" crossorigin="anonymous"></script>

    </head>
    <body class="custom-body">    
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
            <div class="form-busca form-busca-off form-group">
                <form action="relatorioEstoque" method="GET"> 
                    <div class="row">
                        <label class="col-form-label col-sm-12 col-md-1">Cod.produto</label>
                        <div class="col-sm-12 col-md-11 ">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Buscar código produto..." id="txtSearch" name="codProduto"/>
                                <input type="hidden" class="form-control" name="acao" value="filtrar"/>
                                <div class="input-group-btn">
                                    <button class="btn btn-defaul" type="submit">
                                        <span class="glyphicon glyphicon-search"></span>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <div id="dvData">
                <table class="table table-striped" style="background-color: white;" >
                    <thead>
                        <tr>
                            <th scope="col">Cod.Produto</th>
                            <th scope="col">Nome do produto</th>
                            <th scope="col">Data</th>
                            <th scope="col">Quantidade</th>
                            <th scope="col">Tipo </th>
                            <th scope="col">Responsavel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="log" items="${requestScope.logs}">
                            <tr>
                                <th scope="row">${log.id}</th>
                                <td>${log.produto.nome}</td>
                                <td>${ log.dataOperacao }</td>
                                <td>${log.quantidadeMovimentada}</td>
                                <td>${log.produto.tipo}</td>
                                <td>${ log.funcionario.nome }</td> 
                            <tr>
                            </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="container-centralizado">
                <button type="button" class="btn btn-primary float-right" id="btnExport">EMITIR RELATORIO</button>
            </div>
        </div>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="https://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script>
            $('#btnExport').click(function () {
                var specialElementHandlers = {
                    '#editor': function (element, renderer) {
                        return true;
                    }
                };

                var doc = new jsPDF();
                doc.fromHTML($('#dvData').html(), 15, 15, {
                    'width': 170, 'elementHandlers': specialElementHandlers
                });
                doc.save('sample-file.pdf');

            });
        </script>

    </body>
</html>
