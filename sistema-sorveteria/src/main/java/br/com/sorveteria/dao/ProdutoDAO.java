package br.com.sorveteria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.sorveteria.model.Produto;
import br.com.sorveteria.util.GerenciadorConexao;

/**
 * @see Produtos
 * @author Pedro Peçanha
 */
public class ProdutoDAO {
    
    private Connection connection;

    public ProdutoDAO() {}
    
    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }
        
    public void salvar(Produto produto) {

        try {
            String sql = "INSERT INTO produto(nome, descricao, valor_unitario , estoque, tipo) VALUES (?, ?, ?, ?, ?)";

            try ( PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, produto.getNome());
                pstm.setString(2, produto.getDescricao());
                pstm.setDouble(3, produto.getValorUnitario());
                pstm.setInt(4, produto.getEstoque());
                pstm.setString(5, produto.getTipo());
                
                pstm.execute();

                try ( ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto.setId(rst.getInt(1));
                    }
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    
    public Produto buscarPorNome(String nome) {
        try {
            Produto produto = null;

            String sql = "SELECT * FROM produto p WHERE p.nome like ?";

            try ( PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, nome);

                pstm.execute();

                try ( ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        produto = new Produto(
                                rst.getInt("id_produto"),
                                rst.getString("nome"),
                                rst.getString("descricao"),
                                rst.getString("tipo"),
                                rst.getDouble("valor_unitario"),
                                rst.getInt("estoque"));
                    }
                    return produto;
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public List<Produto> buscarTodosProdutos() throws ClassNotFoundException, SQLException {
        List<Produto> produtos = new ArrayList<>();
        String query = "SELECT * FROM produto";
        
        Connection con = GerenciadorConexao.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	Produto produto = new Produto();
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                String tipo = rs.getString("tipo");
                double valorUnitario = rs.getDouble("valor_unitario");
                int estoque = rs.getInt("estoque");
                produto.setNome(nome);
                produto.setDescricao(descricao);
                produto.setTipo(tipo);
                produto.setValorUnitario(valorUnitario);
                produto.setEstoque(estoque);
                produtos.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
        
    }
    
    public boolean deletarProduto(int id) throws ClassNotFoundException, SQLException {
        boolean ok = true;
        String query = "DELETE FROM produto where id_produto=?";
        Connection con = GerenciadorConexao.getConnection();
         try {
             PreparedStatement ps = con.prepareStatement(query);
             ps.setInt(1, id);
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
             ok = false;
         }
         return ok;
    }
    
    public boolean atualizarProduto(Produto produto) throws ClassNotFoundException, SQLException {
        boolean ok = true;
        String query = "UPDATE produto SET nome=?,descricao=?, tipo=?, valor_unitario=?, estoque=?  where id_produto=?";
        Connection con = GerenciadorConexao.getConnection();
         try {
             PreparedStatement ps = con.prepareStatement(query);
             ps.setString(1, produto.getNome());
             ps.setString(2, produto.getDescricao());
             ps.setString(3, produto.getTipo());
             ps.setDouble(4, produto.getValorUnitario());
             ps.setInt(5, produto.getEstoque());
             ps.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
             ok = false;
         }
         return ok;
    }
    
}

