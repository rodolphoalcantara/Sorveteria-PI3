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
    </head>
    <body>
                <nav class="navbar navbar-dark bg-brown">
            <a class="navbar-brand" href="#">
                <img src="assets/Logo.svg" width="80" height="80" class="d-inline-block align-center" alt="">
                Sorveteria
            </a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <div class="container"><a class="nav-link active" href="#">Logout</a></div>
                </li>
            </ul>
        </nav>
        
        
         <div class="row">
          <div class="col-md-12">
            <table class="table">
              <tr>
                  <th>N.Venda </th><th>Data </th><th>Cliente
                  </th><th>Vendedor </th><th>Valor total </th>
                  
              </tr>
              
              
              <div class="panel panel-default">
  <div class="panel-heading">
      <h2>Relatório Venda por Pedido/Período</h2>
  </div>
  <div class="panel-body">
      <form method="post" action="/Relatorios/RelVendasPorPeriodo"
      id="frmVenda" class="form-horizontal">

          <div class="form-group">
              <label for="dataInicial" class="col-sm-2 control-label">Data Inicial:
              </label>
              <div class="col-sm-10">
                  <input type="date" class="form-control" id="dataInicial"
                  name="dataInicial" required>
              </div>
          </div>
          <div class="form-group">
              <label for="dataFinal" class="col-sm-2 control-label">Data Final
              </label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" id="dataFinal"
                    name="dataFinal" required>
              </div>
          </div>

          <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                  <button type="submit" class="btn btn-default"
                  >Buscar</button>
              </div>
          </div>     
       
        </div>
</div>
        
        
        <div class="form-busca form-busca-off">
                            <form class="form-busca-site" action="">
                                <input class="btn-text-top" type="text" name="txtsearch" placeholder="Buscar">
                                <button class="btn-buscar-top" type="submit"></button>
                            </form>
                        </div>
       
    </body>
</html>
