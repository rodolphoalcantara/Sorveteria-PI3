/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sorveteria.dao;

import br.com.sorveteria.model.Funcionario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de acesso ao banco de dados que transforma variaveis ou objetos Funcionario em registros.
 * @see Funcionario
 * @author rodolpho
 */
public class FuncionarioDAO {
    
    private Connection connection;

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }
        
    public void salvar(Funcionario funcionario) {

        try {
            String sql = "INSERT INTO funcionario(CPF, nome, sexo, data_nasc, login, senha, isAdmin) SELECT ?, ?, ?, ?, ?, ?, ? WHERE NOT (SELECT * FROM funcionario WHERE login=?)";

            try ( PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, funcionario.getCPF());
                pstm.setString(2, funcionario.getNome());
                pstm.setString(3, funcionario.getSexo());
                pstm.setDate(4, new Date(funcionario.getDataNascimento().getTime()));
                pstm.setString(5, funcionario.getLogin());
                pstm.setString(6, funcionario.getSenha());
                pstm.setString(7, funcionario.getIsAdmin() ? "S" : "N");
                pstm.setString(8, funcionario.getLogin());

                try ( ResultSet rst = pstm.executeQuery()) {
                    while (rst.next()) {
                        funcionario.setId(rst.getInt(1));
                    }
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    
    public Funcionario buscarPorLogin(String login) {
        try {
            Funcionario funcionario = null;

            String sql = "SELECT * FROM funcionario f WHERE f.login like ?";

            try ( PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, login);

                pstm.execute();

                try ( ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        funcionario = new Funcionario(
                                rst.getInt("id_fun"),
                                rst.getString("CPF"),
                                rst.getString("nome"),
                                rst.getString("sexo"),
                                rst.getDate("data_nasc"),
                                rst.getString("login"),
                                rst.getString("senha"),
                                (rst.getString("isAdmin") == "S" ? true : false)
                        );
                    }
                    return funcionario;
                }

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Funcionario getFuncionario(String login){
        try {
            Funcionario funcionario = null;

            String sql = "SELECT * FROM funcionario WHERE login=?";

            try ( PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, login);

                try ( ResultSet rst = pstm.executeQuery()) {
                    if (rst.next()) {
                        String isAdmin = rst.getString("isAdmin");
                        String senhaHash = rst.getString("senha");
                        
                        funcionario = new Funcionario(
                                rst.getInt("id_fun"),
                                rst.getString("CPF"),
                                rst.getString("nome"),
                                rst.getString("sexo"),
                                rst.getDate("data_nasc"),
                                rst.getString("login"),
                                senhaHash,
                                isAdmin.contains("S") ? true : false
                        );
                    }
                    return funcionario;
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    
    
    
}
