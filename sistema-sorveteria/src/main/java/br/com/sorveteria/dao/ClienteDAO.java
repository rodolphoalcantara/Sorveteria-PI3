package br.com.sorveteria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.sorveteria.model.Cliente;
import br.com.sorveteria.util.GerenciadorConexao;

public class ClienteDAO {
	
	private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }
	
	public void salvarCliente(Cliente cliente) {

        try {
            String sql = "INSERT INTO cliente(CPF, nome, email, sexo, data_nasc, telefone, endereco, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try ( PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, cliente.getCPF());
                pstm.setString(2, cliente.getNome());
                pstm.setString(3, cliente.getEmail());
                pstm.setString(4, cliente.getSexo());
                pstm.setString(5, cliente.getData_nasc());
                pstm.setString(6, cliente.getTelefone());
                pstm.setString(7, cliente.getEndereco());
                pstm.setString(8, cliente.getCidade());
                pstm.setString(9, cliente.getEstado());
                
                pstm.execute();

                try ( ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                    	cliente.setId(rst.getInt(1));
                    }
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
	
	public Cliente buscarPorNome(String nome) {
        try {
            Cliente cliente = null;

            String sql = "SELECT * FROM cliente c WHERE c.nome like ?";

            try ( PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, nome);

                pstm.execute();

                try ( ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        cliente = new Cliente(
                        		rst.getString("CPF"),
                        		rst.getString("nome"),
                        		rst.getString("email"),
                        		rst.getString("sexo"),
                        		rst.getString("data_nasc"),
                        		rst.getString("telefone"),
                        		rst.getString("endereco"),
                        		rst.getString("cidade"),
                        		rst.getString("estado"));
                    }
                    return cliente;
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
	
	public List<Cliente> buscarTodosClientes() throws ClassNotFoundException, SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        
        Connection con = GerenciadorConexao.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
            	Cliente cliente = new Cliente();
            	String CPF = rst.getString("CPF");
        		String nome = rst.getString("nome");
        		String email =  rst.getString("email");
        		String sexo = rst.getString("sexo");
        		String data_nasc = rst.getString("data_nasc");
        		String telefone = rst.getString("telefone");
        		String endereco = rst.getString("endereco");
        		String cidade = rst.getString("cidade");
        		String estado = rst.getString("estado");
                cliente.setCPF(CPF);
                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setSexo(sexo);
                cliente.setData_nasc(data_nasc);
                cliente.setTelefone(telefone);
                cliente.setEndereco(endereco);
                cliente.setCidade(cidade);
                cliente.setEstado(estado);
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
        
    }
    		
	
	public boolean deletarCliente(int nome) throws ClassNotFoundException, SQLException {
        boolean ok = true;
        String query = "DELETE FROM cliente where nome=?";
        Connection con = GerenciadorConexao.getConnection();
         try {
             PreparedStatement ps = con.prepareStatement(query);
             ps.setInt(1, nome);
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
             ok = false;
         }
         return ok;
    }
	
	public boolean atualizarCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        boolean ok = true;
        String query = "UPDATE cliente SET CPF=?, nome=?, email=?, sexo=?, data_nasc=?, telefone=?, endereco=?, cidade=?, estado=?  where id_cli=?";
        Connection con = GerenciadorConexao.getConnection();
         try {
             PreparedStatement pstm = con.prepareStatement(query);
             pstm.setString(1, cliente.getCPF());
             pstm.setString(2, cliente.getNome());
             pstm.setString(3, cliente.getEmail());
             pstm.setString(4, cliente.getSexo());
             pstm.setString(5, cliente.getData_nasc());
             pstm.setString(6, cliente.getTelefone());
             pstm.setString(7, cliente.getEndereco());
             pstm.setString(8, cliente.getCidade());
             pstm.setString(9, cliente.getEstado());
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
             ok = false;
         }
         return ok;
    }
	
}
