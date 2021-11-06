package br.com.sorveteria.servlet;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sorveteria.dao.ProdutoDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Produto;
import br.com.sorveteria.util.Constantes;

/**
*
* @author Pedro Henrique Souza Peçanha
*/
@WebServlet(value="/telaProduto", name="ProdutoServlet")
public class ProdutoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Produto> produtos = new ArrayList<>();
        
        try{
            ProdutoDAO produtoDAO = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao());
            
            produtos = produtoDAO.buscarTodosProdutos();

        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        request.setAttribute("listaProdutos", produtos);
        request.getRequestDispatcher(Constantes.TELA_PRODUTO).forward(request, response);
    }
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ope = request.getParameter("ope");
         // Passo 1 - Recuperar os parametros
        String nome = request.getParameter("nomeCliente");
        String descricao = request.getParameter("descricao");
        String tipo = request.getParameter("tipo");
        double valorUnitario = Double.parseDouble(request.getParameter("valor_unitario"));
        int estoque = Integer.parseInt(request.getParameter("estoque"));
        // Passo 2 - Inserir no BD
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setTipo(tipo);
        produto.setValorUnitario(valorUnitario);
        produto.setEstoque(estoque);
        try {
            // ope = 1 => Update
        	ProdutoDAO produtoDAO = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao());
            if ("1".equals(ope)) {
               produtoDAO.atualizarProduto(produto);
            } else {
               produtoDAO.salvar(produto);
            }
            response.sendRedirect(request.getContextPath()+"/uteis/sucesso.jsp");
        } catch(SQLException | ClassNotFoundException | PropertyVetoException ex) {
           response.sendRedirect(request.getContextPath()+"/uteis/erro.jsp");
        }
    }
	  
}
