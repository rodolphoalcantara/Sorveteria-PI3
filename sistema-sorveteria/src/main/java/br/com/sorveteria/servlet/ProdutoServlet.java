package br.com.sorveteria.servlet;

import java.io.IOException;
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
import javax.servlet.RequestDispatcher;

/**
*
* @author Pedro Henrique Souza Peçanha
*/
@WebServlet(urlPatterns = {"/telaProduto", "/insertProduto", "/editarProduto", "/excluirProduto", "/updateProduto", "/buscarProduto"}, name = "ProdutoServlet")
public class ProdutoServlet extends HttpServlet {

    protected ProdutoDAO prodDAO;

    @Override
    public void init() throws ServletException {

        try {
            prodDAO = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String caminho = request.getServletPath();

        if (caminho.contains("/telaProduto")) {
            listarProdutos(request, response);
        } else if (caminho.contains("/excluirProduto")) {
            excluirProduto(request, response);
        } else if (caminho.contains("/editarProduto")) {
            editarProduto(request, response);
        } else if (caminho.contains("/buscarProduto")) {
            buscarProduto(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String caminho = request.getServletPath();

        if (caminho.equals("/insertProduto")) {
            adicionarProduto(request, response);
        }else if(caminho.contains("/updateProduto")){
            updateProduto(request, response);
        }

    }

    protected void listarProdutos(HttpServletRequest request, HttpServletResponse response) {
        List<Produto> produtos = new ArrayList<>();

        try {
            ProdutoDAO produtoDAO = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao());

            produtos = produtoDAO.buscarTodosProdutos();

            request.setAttribute("listaProdutos", produtos);
            request.getRequestDispatcher(Constantes.TELA_PRODUTO).forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    
    
    protected void adicionarProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Passo 1 - Recuperar os parametros
            String nome = request.getParameter("nomeProduto");
            String descricao = request.getParameter("descProduto");
            String tipo = request.getParameter("tipoProduto");
            double valorUnitario = Double.parseDouble(request.getParameter("valorProduto").replace(",", "."));
            int estoque = Integer.parseInt(request.getParameter("estoqueProduto"));
            // Passo 2 - Inserir no BD
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setTipo(tipo);
            produto.setValorUnitario(valorUnitario);
            produto.setEstoque(estoque);

            // ope = 1 => Update
            prodDAO.salvar(produto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/telaProduto");
    }

    private void excluirProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            
            Boolean res = prodDAO.deletarProduto(id);
            
            response.getWriter().print(res);
        
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().print(false);
        }
    }

    private void editarProduto(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Produto prod = prodDAO.buscarPorId(id);
            request.setAttribute("produto", prod);

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/protected/admin/produto/editProduto.jsp");
            rd.forward(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void buscarProduto(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updateProduto(HttpServletRequest request, HttpServletResponse response) throws IOException {
         try {
            Produto prod = new Produto();
            prod.setId(Integer.parseInt(request.getParameter("id_produto")));
            prod.setNome(request.getParameter("nomeProduto"));
            prod.setDescricao(request.getParameter("descProduto"));
            prod.setTipo(request.getParameter("tipoProduto"));
            prod.setValorUnitario(Double.parseDouble(request.getParameter("valorProduto").replace(",", ".")));
            prod.setEstoque(Integer.parseInt(request.getParameter("estoqueProduto")));
            prodDAO.atualizarProduto(prod);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("/telaProduto");
    }
    
	  
}
