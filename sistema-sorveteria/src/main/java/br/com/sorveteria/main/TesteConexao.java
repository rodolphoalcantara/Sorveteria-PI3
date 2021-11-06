/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.main;


import br.com.sorveteria.dao.ProdutoDAO;
import br.com.sorveteria.factory.ConnectionFactory;

import br.com.sorveteria.model.Produto;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

public class TesteConexao {

    public static void main(String[] a) throws ClassNotFoundException, SQLException, PropertyVetoException {


    	ProdutoDAO produtoDAO = new ProdutoDAO(ConnectionFactory.getInstance().recuperaConexao());
    	
        List<Produto> pegarTudo = produtoDAO.buscarTodosProdutos();
        
        }
}
