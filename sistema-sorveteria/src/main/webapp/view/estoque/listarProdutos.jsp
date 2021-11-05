<%-- 
    Document   : listarProdutos.jsp
    Created on : 2 de nov de 2021, 18:01:49
    Author     : rodolpho
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../../uteis/header.jsp" />
        <link href="${pageContext.request.contextPath}/css/estoque.css"
              rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estoque</title>
    </head>
    <body>
        <c:import url="../../uteis/navbar.jsp" />
        <main class="d-flex">
            <aside class="menu-lateral">
            </aside>
            <section class="content-section-estoque">
                <section class="busca-section-estoque">aa</section>
                <section class="table-section-estoque">aaa</section>
                <section class="btns-section-estoque">sss</section>
            </section>
        </main>
    </body>
</html>
