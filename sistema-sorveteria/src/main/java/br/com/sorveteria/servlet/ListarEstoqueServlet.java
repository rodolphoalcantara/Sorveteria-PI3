/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.sorveteria.servlet;

import br.com.sorveteria.dao.ProdutoDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Produto;
import br.com.sorveteria.util.Constantes;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private ProdutoDAO produtoDAO;
    
    @Override
    public void init() throws ServletException {
        try {
            produtoDAO = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao());
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ListarEstoqueServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListarEstoqueServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Produto> produtos = new ArrayList<>();
        String nomeProduto = request.getParameter("p");
        
        if(nomeProduto.trim().isBlank() || nomeProduto.trim().isEmpty()){
      
            try {
                produtos = this.produtoDAO.buscarTodosProdutos();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                produtos = this.produtoDAO.filtrarPorNome(nomeProduto);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        request.setAttribute("listaProdutos", produtos);
        request.getRequestDispatcher(Constantes.LISTAR_ESTOQUE).forward(request, response);
    }
}
