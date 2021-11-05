/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.servlet;

//import br.com.sorveteria.dao.VendaDao;
import br.com.sorveteria.model.Venda;
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

@WebServlet(value="/relatorioVenda", name="RelatorioVendaServlet")
public class RelatorioVendaServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Venda> vendas = VendaDao.pegarTudo();
        req.setAttribute("vendas", vendas);
        RequestDispatcher rd = req.getRequestDispatcher("/relatorioVenda.jsp");
        rd.forward(req, resp);
    }
}
*/