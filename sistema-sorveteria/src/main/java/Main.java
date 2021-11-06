
import br.com.sorveteria.dao.EstoqueDAO;
import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.LogEstoque;
import br.com.sorveteria.model.Produto;
import br.com.sorveteria.util.Constantes;
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
        
        Boolean res = estoqueDAO.movimentarEstoque(new Produto(1, "Sorvete", "Sorvete delicioso", "Picolé", 1.00, 100), 50, Constantes.SAIDA_OPERACAO, 1);
        
        System.out.println(res);
    }
}
