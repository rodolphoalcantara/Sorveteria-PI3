/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.sorveteria.servlet;

import br.com.sorveteria.dao.ProdutoDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Produto;
import br.com.sorveteria.util.Constantes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rodolpho
 */

public class ListarEstoqueServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Produto> produtos = new ArrayList<>();
        
        try{
            ProdutoDAO produtoDAO = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao());
            
            produtos = produtoDAO.buscarTodosProdutos();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        request.setAttribute("listaProdutos", produtos);
        request.getRequestDispatcher(Constantes.LISTAR_ESTOQUE).forward(request, response);
    }
}
