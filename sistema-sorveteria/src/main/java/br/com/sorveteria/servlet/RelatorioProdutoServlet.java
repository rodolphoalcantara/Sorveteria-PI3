/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.servlet;

//import br.com.sorveteria.dao.ProdutoDao;
import br.com.sorveteria.model.Produto;
import static br.com.sorveteria.util.Constantes.COD_PRODUTO;
import static br.com.sorveteria.util.Constantes.RELATORIO_PRODUTO_JSP_URL;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Naga
 */
//@WebServlet(value = "/relatorioProdutos", name = "RelatorioProdutoServlet")
//public class RelatorioProdutoServlet extends HttpServlet {
//    private static final String PRODUTOS_ATTRIBUTE = "produtos";
    
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Produto> produtos = ProdutoDao.pegarTudo();
//        if (req.getParameter(COD_PRODUTO) != null) {
//            String codigo = req.getParameter(COD_PRODUTO).trim();
//            if (!codigo.isEmpty())ss {
//                produtos = ProdutoDao.fsiltar(codigo);
//            }
//        }
//        req.setAttribute(PRODUTOS_ATTRIBUTE, produtos);
//        RequestDispatcher rd = req.getRequestDispatcher(RELATORIO_PRODUTO_JSP_URL);
//        rd.forward(req, resp);
//    }
//}
