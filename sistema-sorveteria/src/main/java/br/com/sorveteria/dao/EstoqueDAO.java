/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sorveteria.dao;

import br.com.sorveteria.model.Funcionario;
import br.com.sorveteria.model.LogEstoque;
import br.com.sorveteria.model.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
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
    
    public List<LogEstoque> listarPorOperacao(String operacao){
        try {
            List<LogEstoque> logs = new ArrayList<>();
            
            String query = "SELECT * FROM log_estoque L "
                    + "INNER JOIN funcionario F on F.id_fun like L.fk_id_fun "
                    + "INNER JOIN produto P on P.id_produto like L.fk_id_produto "
                    + "WHERE L.operacao like ?"
                    + "order by L.data_op desc";
            
            try(PreparedStatement pstm = connection.prepareStatement(query)){
                pstm.setString(1, operacao);
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
    
    public Boolean movimentarEstoque(Produto produto, int quantidadeMovimentada, String operacao, int idFun){
        if (produto.getEstoque() < quantidadeMovimentada) {
            return false;
        }

        try {
            int estoqueAtual = produto.getEstoque() - quantidadeMovimentada;
            String query = "UPDATE produto SET estoque=? WHERE id_produto like ?";

            try ( PreparedStatement pstm = connection.prepareStatement(query)) {
                pstm.setInt(1, estoqueAtual);
                pstm.setInt(2, produto.getId());
                int linhasAfetadas = pstm.executeUpdate();

                if (linhasAfetadas > 0) {

                    query = "INSERT INTO log_estoque(data_op, operacao, quantidade, fk_id_produto, fk_id_fun) VALUES (?,?,?,?,?)";
                    try ( PreparedStatement pst = connection.prepareStatement(query)) {
                        pst.setDate(1, new Date(Calendar.getInstance().getTimeInMillis()));
                        pst.setString(2, operacao);
                        pst.setInt(3, quantidadeMovimentada);
                        pst.setInt(4, produto.getId());
                        pst.setInt(5, idFun);

                        pst.execute();
                    }
                }

            }

            return true;
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


