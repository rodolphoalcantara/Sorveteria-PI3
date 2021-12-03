<%--
    Author     : Pedro.pecanha
    --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="${pageContext.request.contextPath}/uteis/header.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produtos</title>
    </head>
    <body>
        <c:import url="${pageContext.request.contextPath}/uteis/navbar.jsp"/>
        <h1 class="welcome-header">Cadastro de Clientes</h1>
        <section class="container">
        <form class="" action="/insertCliente" method="POST">
            <div class="form-group">
                <input type="hidden" name="id_cli" value="${cliente.id}"
                       required class="form-control"/><br/>
            </div>
            <div  class="form-group">
                <label>CPF</label>
                <input type="text" name="CPF" value="${cliente.CPF}"
                       required class="form-control"/><br/> 
            </div>
            <div class="form-group">
                <label>Nome</label>
                <input type="text" name="nome" value="${cliente.nome}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>Email</label>
                <input type="text" name="email" value="${cliente.email}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <legend class="col-form-label col-sm-2 pt-0 pl-0">Sexo</legend>
                <div class="form-check form-check-inline">
                    <label class="form-check-label mr-2" for="masculino">Masculino </label>
                    <input class="form-check-input" type="radio" id="masculino" name="sexo" value="masculino"
                           required class="form-control"/>
                </div>
                <div class="form-check form-check-inline">
                    <label class="form-check-label mr-2" for="masculino">Feminino </label>
                    <input class="form-check-input" type="radio" id="feminino" name="sexo" value="feminino"
                           required class="form-control"/>
                </div>
                <br/>
                <br/>
            </div>
            <div class="form-group">
                <label>Data de Nascimento</label>
                <input type="date" name="dataNasc" value="${cliente.data_nasc}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>Telefone</label>
                <input type="text" name="telefone" value="${cliente.telefone}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>Endereço</label>
                <input type="text" name="endereco" value="${cliente.endereco}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>Cidade</label>
                <input type="text" name="cidade" value="${cliente.cidade}"
                       required class="form-control"/><br/>
            </div>
            <div class="form-group">
                <label>Estado</label>
                <input type="text" name="estado" value="${cliente.estado}"
                       required class="form-control"/><br/>
            </div>
            <br/>
            <br/>
            <button type="submit" class="btn bg-pink btnOutlineBrown ml-0 w-100">Enviar</button>
        </form>
        </section>
    </body>
</html>