/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sorveteria.dao;

import br.com.sorveteria.model.Funcionario;
import br.com.sorveteria.model.LogEstoque;
import br.com.sorveteria.model.Produto;
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
 * @author rodolpho
 */
public class EstoqueDAO {
    
    private Connection connection;

    public EstoqueDAO(Connection connection) {
        this.connection = connection;
    }
   
    public List<LogEstoque> listarTodos() {
        try {
            List<LogEstoque> logs = new ArrayList<>();
            
            String query = "SELECT * FROM log_estoque L "
                    + "INNER JOIN funcionario F on F.id_fun like L.fk_id_fun "
                    + "INNER JOIN produto P on P.id_produto like L.fk_id_produto "
                    + "order by L.data_op desc";
            
            try(PreparedStatement pstm = connection.prepareStatement(query)){
                pstm.execute();
                try(ResultSet rs = pstm.getResultSet()){
                    while(rs.next()){
                        LogEstoque log = new LogEstoque(rs.getInt("L.id_log"), 
                                rs.getDate("L.data_op"), 
                                rs.getString("L.operacao"), 
                                rs.getInt("L.quantidade"), 
                                new Produto(rs.getInt("P.id_produto"), 
                                        rs.getString("P.nome"),
                                        rs.getString("P.descricao"),
                                        rs.getString("P.tipo"), 
                                        rs.getDouble("P.valor_unitario"), 
                                        rs.getInt("P.estoque")), 
                                new Funcionario(rs.getInt("F.id_fun"), rs.getString("F.CPF"), rs.getString("F.nome")));
                        
                        logs.add(log);
                    }
                }
            }
            
            return logs;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<LogEstoque> filtrar(String codigo) {
        List<LogEstoque> logs = new ArrayList();
        String sql =  "SELECT * FROM log_estoque L "
                    + "INNER JOIN funcionario F on F.id_fun like L.fk_id_fun "
                    + "INNER JOIN produto P on P.id_produto like L.fk_id_produto "
                + "WHERE L.id_log = ?";
        
        try ( PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, codigo);
            pstm.execute();
            ResultSet rs = pstm.getResultSet();

            while (rs.next()) {
                LogEstoque estoque = new LogEstoque(rs.getInt("L.id_log"), 
                                rs.getDate("L.data_op"), 
                                rs.getString("L.operacao"), 
                                rs.getInt("L.quantidade"), 
                                new Produto(rs.getInt("P.id_produto"), 
                                        rs.getString("P.nome"),
                                        rs.getString("P.descricao"),
                                        rs.getString("P.tipo"), 
                                        rs.getDouble("P.valor_unitario"), 
                                        rs.getInt("P.estoque")), 
                                new Funcionario(rs.getInt("F.id_fun"), rs.getString("F.CPF"), rs.getString("F.nome")));
                logs.add(estoque);
            }

        } catch (SQLException e) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return logs;
    }

}
