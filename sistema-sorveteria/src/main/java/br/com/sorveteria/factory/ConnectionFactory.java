
package br.com.sorveteria.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe para criação e recuperação de conexões em uma pool de conexões
 * @author rodolpho
 */
public class ConnectionFactory {
    
    private static ConnectionFactory conFact;
    public ComboPooledDataSource cpds;
    
    public ConnectionFactory() throws PropertyVetoException{
        
        cpds = new ComboPooledDataSource();
        
        //seta configurações da conexão com o mysql
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost/sorveteriadb?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC");
        cpds.setUser("root");
        cpds.setPassword("root");
        
        //seta algumas configurações da pool
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        
    }

    public static ConnectionFactory getInstance() throws PropertyVetoException{
        if(conFact == null){
            conFact = new ConnectionFactory();
        }
        return conFact;
    }
    
    public Connection recuperaConexao() throws SQLException{
        return this.cpds.getConnection();
    }
}
