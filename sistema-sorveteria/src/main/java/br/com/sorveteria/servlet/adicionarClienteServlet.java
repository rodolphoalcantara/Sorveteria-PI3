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

/**
*
* @author Pedro Henrique Souza Peçanha
*/
//@WebServlet(value="/adicionaCliente", name="adicionaClienteServlet")
//public class adicionarClienteServlet extends HttpServlet {
//	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		List<Cliente> clientes = new ArrayList<>();
//        
//        try{
//            ClienteDAO clienteDAO = new ClienteDAO(ConnectionFactory.getInstance().recuperaConexao());
//            
//            clientes = clienteDAO.buscarTodosClientes();
//
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        
//        request.setAttribute("listaClientes", clientes);
//        request.getRequestDispatcher(Constantes.ADICIONA_CLIENTE).forward(request, response);
//    }
//}
