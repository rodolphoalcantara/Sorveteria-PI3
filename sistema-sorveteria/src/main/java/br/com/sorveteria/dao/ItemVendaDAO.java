/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sorveteria.dao;

import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.ItemVenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author rodolpho
 */
public class ItemVendaDAO {
    /**
     * Salva um ItemVenda no banco de dados
     * @param item Objeto da classe ItemVenda
     * @throws ClassNotFoundException caso não encontre o Drive de conexao com o mySQL
     */
    public static void save(ItemVenda item) throws ClassNotFoundException {
        try {
            String query = "insert into item_venda (quantidade, valor_subtotal, fk_id_venda, fk_id_produto) values (?,?,?,?)";

            try ( PreparedStatement pstm = ConnectionFactory.getInstance().recuperaConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, item.getQuantidade());
                pstm.setDouble(2, item.getValorSubtotal());
                pstm.setInt(3, item.getIdVenda());
                pstm.setInt(4, item.getId_produto());

                pstm.execute();

                try ( ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        item.setId(rst.getInt(1));
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
