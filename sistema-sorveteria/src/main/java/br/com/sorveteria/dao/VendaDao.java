/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.dao;

import br.com.sorveteria.model.Venda;
import br.com.sorveteria.util.GerenciadorConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Neto-Pc
 */
public class VendaDao {

    static public List<Venda> pegarTudo() {
        List<Venda> vendas = new ArrayList();
        try ( Connection conexao = GerenciadorConexao.getConnection()) {

            java.sql.Statement st = conexao.createStatement();
            String sql = "select * from venda";
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {
                vendas.add(
                        new Venda(
                                result.getInt("id_venda"),
                                result.getDate("data_venda"),
                                result.getDouble("valor_total"),
                                result.getInt("fk_id_cli"),
                                result.getInt("fk_id_fun")
                        )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return vendas;
    }

    static public List<Venda> filtar(String cpfCliente) {
        List<Venda> vendas = new ArrayList();
        String sql = "select * from venda WHERE id_venda = ?";
        try ( PreparedStatement pstm = GerenciadorConexao.getConnection().prepareStatement(sql)) {
            pstm.setString(1, cpfCliente);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Venda produto = new Venda();
                vendas.add(produto);
            }
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return vendas;
    }
    
    static public List<Venda> filtarPorCpf(String cpfCliente){
        //TODO - falta fazer
        return null;
    }
    
    static public List<Venda> filtarPorPeriodo(String dataInicial, String dataFinal){
        //TODO - falta fazer
        return null;
    }
}
