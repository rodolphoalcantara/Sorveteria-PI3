
import br.com.sorveteria.dao.EstoqueDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.LogEstoque;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rodolpho
 */
public class Main {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        
        EstoqueDAO estoqueDAO = new EstoqueDAO(ConnectionFactory.getInstance().recuperaConexao());
        
        List<LogEstoque> logs = estoqueDAO.listarTodos();
        
        for (LogEstoque log : logs) {
            System.out.println(log.toString());
        }
        
        
    }
}
