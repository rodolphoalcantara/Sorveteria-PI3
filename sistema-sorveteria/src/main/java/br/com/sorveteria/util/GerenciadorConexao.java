package br.com.sorveteria.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gerencia as conexoes ao <b>banco de dados</b>
 * @see com.mysql.cj.jdbc.Driver
 * @author rodolpho
 */
public class GerenciadorConexao {
    public static String STATUS = "Não conectado";
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String SERVER = "localhost";
    public static String DATABASE = "sistema_floricultura";
    public static String LOGIN = "root";
    public static String SENHA = "root"; //senha da minha conexao com mysql. Favor alterar
    public static String URL = "";
    public static Connection CONEXAO;

    public GerenciadorConexao() {
    }

    /**
     * Método utilizado para receber uma conexão
     * @return um obj Connection para ser utilizado pelas classes DAO's
     * @throws ClassNotFoundException Caso não encontre o Driver de conexao com o MySQL
     * @throws SQLException Caso haja algum erro ao tentar estabelecer conexao
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        URL = "jdbc:mysql://" + SERVER + ":3306/" + DATABASE + "useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC";

        if (CONEXAO == null) {

            try {
                Class.forName(DRIVER);

                CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);

                if (CONEXAO != null) {
                    STATUS = "Conexão bem sucedida";
                } else {
                    STATUS = "Não foi possível realizar a conexão";
                }

            } catch (ClassNotFoundException e) {
                throw new ClassNotFoundException("O driver expecificado nao foi encontrado.");
            } catch (SQLException e) {
                throw new SQLException("Erro ao estabelecer a conexão (Ex: login ou senha incorretos).");
            }

        } else {
            try {
                if (CONEXAO.isClosed()) {
                    CONEXAO = DriverManager.getConnection(URL, LOGIN, SENHA);
                }
            } catch (SQLException ex) {
                throw new SQLException("Falha ao fechar a conexão.");
            }
        }
        return CONEXAO;
    }

    /**
     * Método utilizado fechar uma conexão
     * @return <b>boolean</b> True: Sucesso ao fechar a conexao , False: Não foi possível fechar conexão
     * @throws SQLException Caso haja algum erro ao tentar fechar a conexao
     */
    public static Boolean closeConnection() throws SQLException {
        Boolean success = false;

        try {
            if (CONEXAO != null) {
                if (!CONEXAO.isClosed()) {
                    CONEXAO.close();
                }
            }

            STATUS = "Não conectado";
            success = true;

        } catch (SQLException e) {
            success = false;
        }

        return success;
    }
}
