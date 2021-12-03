/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.sorveteria.servlet;

import br.com.sorveteria.dao.ClienteDAO;
import br.com.sorveteria.dao.ItemVendaDAO;
import br.com.sorveteria.dao.ProdutoDAO;
import br.com.sorveteria.dao.VendaDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Cliente;
import br.com.sorveteria.model.Funcionario;
import br.com.sorveteria.model.ItemCarrinho;
import br.com.sorveteria.model.ItemVenda;
import br.com.sorveteria.model.Produto;
import br.com.sorveteria.model.Venda;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodolpho
 */
@WebServlet(name = "VendaServlet", urlPatterns = {"/insertVenda", "/buscarClienteVenda", "/listarProdsDisp", "/atualizarCarrinho"})
public class VendaServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String caminho = request.getServletPath();
        
        if(caminho.contains("/atualizarCarrinho")){
           atualizarCarrinho(request,response);
        }else if(caminho.contains("/buscarClienteVenda")){
            buscarClienteVenda(request, response);
        }else if(caminho.contains("/listarProdsDisp")){
            listarProdutosDisponiveis(request,response);
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        cadastrarVenda(request, response);
    }

    
    private void atualizarCarrinho(HttpServletRequest request, HttpServletResponse response) {
        try {
            
            HttpSession sessao = request.getSession();
            List<ItemCarrinho> carrinho;
            
            String idLista = request.getParameter("idLista");
            if (idLista != null) {
                int id = Integer.parseInt(idLista);
                carrinho = (List<ItemCarrinho>) sessao.getAttribute("carrinho");
                for (ItemCarrinho item : carrinho) {
                    if(item.getIdLista() == id)
                        carrinho.remove(item);
                }

            } else {

                int idProd = Integer.parseInt(request.getParameter("id"));
                String nomeProd = request.getParameter("nome");
                int quantidade = Integer.parseInt(request.getParameter("quant"));

                if (sessao.getAttribute("carrinho") == null) {
                    carrinho = new ArrayList<>();
                } else {
                    carrinho = new ArrayList<>();
                    carrinho = (List<ItemCarrinho>) sessao.getAttribute("carrinho");
                }
                int count = carrinho.size() + 1;
                ItemCarrinho item = new ItemCarrinho(count, idProd, nomeProd, quantidade);

                if (!carrinho.contains(item)) {
                    carrinho.add(item);
                }
            }
            sessao.setAttribute("carrinho", carrinho);
            response.sendRedirect("/listarProdsDisp");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void cadastrarVenda(HttpServletRequest request, HttpServletResponse response) {
        try{
            HttpSession sessao = request.getSession();
            List<ItemCarrinho> carrinho = (List<ItemCarrinho>) sessao.getAttribute("carrinho");
            Cliente cliente = (Cliente) sessao.getAttribute("cliente");
            Funcionario user = (Funcionario) sessao.getAttribute("user");
            
            if(carrinho != null && cliente != null && user != null){
                
                Venda venda = new Venda();
                venda.setDataVenda(Date.from(Instant.now()));
                venda.setFkIdCliente(cliente.getId());
                venda.setFkIdFuncionario(user.getId());
                
                VendaDAO.save(venda);
                
                ArrayList<ItemVenda> itens = new ArrayList<>();
                Double valorTotal = 0.0;
                for (ItemCarrinho itemCarrinho : carrinho) {
                    
                    Double valorUnit = ProdutoDAO.getValorProd(itemCarrinho.getIdProd());
                    Double subtotal = (valorUnit * itemCarrinho.getQuantidade());
                    ItemVenda item = new ItemVenda();
                    item.setIdVenda(venda.getId());
                    item.setQuantidade(itemCarrinho.getQuantidade());
                    item.setId_produto(itemCarrinho.getIdProd());
                    item.setValorSubtotal(subtotal);
                    itens.add(item);
                    valorTotal += subtotal;
                    ItemVendaDAO.save(item);                    
                }
                VendaDAO.salvaValor(venda.getId(), valorTotal);
                venda.setItens(itens);
                sessao.setAttribute("cliente", new Cliente());
                sessao.setAttribute("carrinho", new ArrayList<ItemCarrinho>());
                response.getWriter().print("Venda realizada !");
            }else{
                response.getWriter().print("Verifique, novamente, se os campos foram preenchidos corretamente.");
            }   
            
        }catch(Exception ex){
            ex.printStackTrace();
        }   
    }

    private void buscarClienteVenda(HttpServletRequest request, HttpServletResponse response) {

        String cpf = request.getParameter("cpf");
        HttpSession sessao = request.getSession();
        Cliente cliente;

        try {
            cliente = new ClienteDAO(ConnectionFactory.getInstance().recuperaConexao()).buscarPorCPF(cpf);

            sessao.setAttribute("cliente", cliente);
            response.getWriter().print(cliente.getNome());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void listarProdutosDisponiveis(HttpServletRequest request, HttpServletResponse response) {

        List<Produto> produtos = new ArrayList<>();

        try {
            produtos = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao()).buscarTodosProdutos();
            request.setAttribute("listProds", produtos);
            request.getRequestDispatcher("/protected/venda/venda.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
  

}
