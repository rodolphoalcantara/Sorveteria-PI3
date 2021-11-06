/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.servlet;

import br.com.sorveteria.dao.ProdutoDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Produto;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuscaProduto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Produto> produtos = new ArrayList<>();
        String produtosJson = "";
        try {
            ProdutoDAO prodDAO = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao());

            String nomeCliente = request.getParameter("nomeCliente");
            produtos = prodDAO.filtrarPorNome(nomeCliente);
            produtosJson = new Gson().toJson(produtos);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.write(produtosJson);
    }

}
