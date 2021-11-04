/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.servlet;

import br.com.sorveteria.dao.EstoqueDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.LogEstoque;
import br.com.sorveteria.model.Produto;
import static br.com.sorveteria.util.Constantes.COD_PRODUTO;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static br.com.sorveteria.util.Constantes.RELATORIO_ESTOQUE_JSP_URL;

/**
 *
 * @author Naga
 */
@WebServlet(value = "/relatorioEstoque", name = "RelatorioEstoqueServlet")
public class RelatorioEstoqueServlet extends HttpServlet {
    private static final String LOGS_ATTRIBUTE = "logs";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            EstoqueDAO dao = new EstoqueDAO(ConnectionFactory.getInstance().recuperaConexao());
            List<LogEstoque> logs = dao.listarTodos();
             
            if (req.getParameter(COD_PRODUTO) != null) {
            String codigo = req.getParameter(COD_PRODUTO).trim();
            if (!codigo.isEmpty()) {
                logs = dao.filtrar(codigo);
            }
        }
        req.setAttribute(LOGS_ATTRIBUTE, logs);
        RequestDispatcher rd = req.getRequestDispatcher(RELATORIO_ESTOQUE_JSP_URL);
        rd.forward(req, resp);
            
        } catch (PropertyVetoException | SQLException ex) {
            Logger.getLogger(RelatorioEstoqueServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
