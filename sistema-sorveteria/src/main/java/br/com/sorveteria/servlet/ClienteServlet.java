package br.com.sorveteria.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sorveteria.dao.ClienteDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Cliente;
import br.com.sorveteria.util.Constantes;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

/**
*
* @author Pedro Henrique Souza Peçanha
*/
@WebServlet(urlPatterns = {"/telaCliente", "/insertCliente", "/editarCliente", "/excluirCliente", "/updateCliente", "/buscaCliente"}, name = "ClienteServlet")
public class ClienteServlet extends HttpServlet {
    
    protected ClienteDAO cliDAO;

    @Override
    public void init() throws ServletException {
        
        try{
            cliDAO = new ClienteDAO(ConnectionFactory.getInstance().recuperaConexao());
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String caminho = request.getServletPath();
        
        if(caminho.contains("/telaCliente")){
            listarClientes(request, response);
        }else if(caminho.contains("/excluirCliente")){
            excluirCliente(request, response);
        }else if(caminho.contains("/editarCliente")){
            editarCliente(request, response);
        }else if(caminho.contains("buscaCliente")){
            buscarCliente(request, response);
        }
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String caminho = request.getServletPath();
        if(caminho.contains("/insertCliente")){
            cadastrarCliente(request, response);
        }else if(caminho.contains("/updateCliente")){
            updateCliente(request, response);
        }
    }

    protected void listarClientes(HttpServletRequest request, HttpServletResponse response) {

        List<Cliente> clientes = new ArrayList<>();

        try {
            ClienteDAO clienteDAO = new ClienteDAO(ConnectionFactory.getInstance().recuperaConexao());

            clientes = clienteDAO.buscarTodosClientes();

            request.setAttribute("listaClientes", clientes);
            request.getRequestDispatcher(Constantes.TELA_CLIENTE).forward(request, response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void cadastrarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Cliente cli = new Cliente();

            cli.setCPF(request.getParameter("CPF"));
            cli.setNome(request.getParameter("nome"));
            cli.setEmail(request.getParameter("email"));
            cli.setTelefone(request.getParameter("telefone"));
            cli.setEndereco(request.getParameter("endereco"));
            cli.setCidade(request.getParameter("cidade"));
            cli.setEstado(request.getParameter("estado"));
            cli.setData_nasc(request.getParameter("dataNasc"));
            cli.setSexo(request.getParameter("sexo"));

            cliDAO.salvarCliente(cli);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("/telaCliente");
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            Cliente cli = cliDAO.buscarPorId(id);
            request.setAttribute("cliente", cli);


            RequestDispatcher rd = getServletContext().getRequestDispatcher("/protected/cliente/editCliente.jsp");
            rd.forward(request, response);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void excluirCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            
            Boolean res = cliDAO.deletarCliente(id);
            
            response.getWriter().print(res);
        
        } catch (Exception ex) {
            ex.printStackTrace();
            response.getWriter().print(false);
        }
    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Cliente cli = new Cliente();
            cli.setId(Integer.parseInt(request.getParameter("id")));
            cli.setCPF(request.getParameter("CPF"));
            cli.setNome(request.getParameter("nome"));
            cli.setEmail(request.getParameter("email"));
            cli.setTelefone(request.getParameter("telefone"));
            cli.setEndereco(request.getParameter("endereco"));
            cli.setCidade(request.getParameter("cidade"));
            cli.setEstado(request.getParameter("estado"));
            cli.setData_nasc(request.getParameter("dataNasc"));
            cli.setSexo(request.getParameter("sexo"));

            cliDAO.atualizarCliente(cli);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        response.sendRedirect("/telaCliente");
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response) {
        try{
            String nomeCliente = request.getParameter("nomeCliente");
            List<Cliente> clientes = cliDAO.buscarPorNome(nomeCliente);
            String clientesJson = new Gson().toJson(clientes);
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.write(clientesJson);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
