/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.dao;

import br.com.sorveteria.model.Produto;
import br.com.sorveteria.util.GerenciadorConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDao {

    public void salvar(Produto entidade) {
    }

    static public List<Produto> pegarTudo() {
        List<Produto> produtos = new ArrayList();
        try ( Connection conexao = GerenciadorConexao.getConnection()) {

            java.sql.Statement st = conexao.createStatement();
            String sql = "select * from produto";
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(result.getInt("id_produto"));
                produto.setNomeprod(result.getString("nome"));
                produto.setValorprod(result.getDouble("valor_unitario"));
                produto.setDescricaoprod(result.getString("descricao"));
                produto.setCategoriaprod(result.getString("tipo"));
                produto.setQtdestoque(result.getInt("estoque"));
                produtos.add(produto);
            }

        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return produtos;
    }

    static public List<Produto> filtar(String codProduto) {
        List<Produto> produtos = new ArrayList();
        String sql = "select * from produto WHERE id_produto = ?";
        try ( PreparedStatement pstm = GerenciadorConexao.getConnection().prepareStatement(sql)) {

            pstm.setString(1, codProduto);

            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(result.getInt("id_produto"));
                produto.setNomeprod(result.getString("nome"));
                produto.setValorprod(result.getDouble("valor_unitario"));
                produto.setDescricaoprod(result.getString("descricao"));
                produto.setCategoriaprod(result.getString("tipo"));
                produto.setQtdestoque(result.getInt("estoque"));
                produtos.add(produto);
            }

        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return produtos;
    }
}
