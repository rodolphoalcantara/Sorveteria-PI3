/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.dao;

import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Cliente;
import br.com.sorveteria.model.ItemVenda;
import br.com.sorveteria.model.Produto;
import br.com.sorveteria.model.Venda;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paola, Rodolpho
 */
public class VendaDAO {

    /**
     * lista todas as vendas
     *
     * @return uma lista de vendas, cada uma preenchida com uma lista de itens
     * relacionados
     * @throws ClassNotFoundException Caso não encontre o Driver de conexão com
     * o MySQL
     */
    public static List<Venda> listarVendasComItens() throws ClassNotFoundException, PropertyVetoException {
        try {
            Venda anterior = null;
            List<Venda> vendas = new ArrayList<>();
            String query = "select v.id_venda, v.valor_total, v.data_venda, v.fk_id_cli, v.fk_id_fun, "
                    + "i.id_item, i.quantidade, i.valor_subtotal, "
                    + "p.id_produto, p.nome, p.valor_unitario, p.estoque, p.descricao, p.tipo "
                    + "from venda v "
                    + "inner join cliente c on c.id_cli = v.fk_id_cli "
                    + "inner join funcionario f on f.id_fun=v.fk_id_fun "
                    + "inner join item_venda i on v.id_venda = i.fk_id_venda "
                    + "inner join produto p on p.id_produto = i.fk_id_produto "
                    + "order by v.data_venda desc";

            try ( PreparedStatement pstm = ConnectionFactory.getInstance().recuperaConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstm.execute();

                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (anterior == null || !(anterior.getId() == rst.getInt(1))) {
                            Venda venda = new Venda(rst.getInt("v.id_venda"),rst.getDate("v.data_venda"), rst.getInt("v.fk_id_cli"), rst.getInt("v.fk_id_fun"), rst.getDouble("v.valor_total"));
                            vendas.add(venda);
                            anterior = venda;
                            anterior.setId(rst.getInt(1));
                        }
                        Produto produto = new Produto(rst.getInt("p.id_produto"), rst.getString("p.nome"),rst.getString("p.descricao"),rst.getString("p.tipo"),rst.getDouble("p.valor_unitario"), rst.getInt("p.estoque"));
                        ItemVenda item = new ItemVenda(rst.getInt("i.id_item"),rst.getInt("i.quantidade"), rst.getDouble("i.valor_subtotal"),rst.getInt("v.id_venda"), rst.getInt("p.id_produto"));
                        anterior.getItens().add(item);
                    }
                }
                return vendas;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * lista todas as vendas em um certo periodo de tempo
     *
     * @return uma lista de vendas em um certo periodo de venda, cada uma
     * preenchida com uma lista de itens relacionados
     * @throws ClassNotFoundException Caso não encontre o Driver de conexão com
     * o MySQL
     */
    public static List<Venda> listarVendasComItensEmUmPeriodo(Date dataInicial, Date dataFinal) throws ClassNotFoundException, PropertyVetoException {
        try {
            Venda anterior = null;
            List<Venda> vendas = new ArrayList<Venda>();
            String query = "select v.id_venda, v.data_venda, "
                    + "c.id_cli, c.nome, c.CPF, c.sexo, c.data_nasc, c.email, c.estado_civil, c.telefone, c.fk_id_endereco "
                    + "i.id_item, i.quantidade, i.valor_total, "
                    + "p.id_produto, p.nome, p.valor_unitario, p.estoque, p.descricao, p.tipo "
                    + "from venda v "
                    + "inner join cliente c on c.id_cli = v.fk_id_cli "
                    + "inner join item_venda i on v.id_venda = i.fk_id_venda "
                    + "inner join produto p on p.id_produto = i.fk_id_produto "
                    + "where v.data_venda between ? and ? "
                    + "order by v.data_venda desc ";

            try ( PreparedStatement pstm = ConnectionFactory.getInstance().recuperaConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDate(1, dataInicial);
                pstm.setDate(2, dataFinal);

                pstm.execute();

                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (anterior == null || !(anterior.getId() == rst.getInt(1))) {
                            Venda venda = new Venda(rst.getInt("v.id_venda"),rst.getDate("v.data_venda"), rst.getInt("v.fk_id_cli"), rst.getInt("v.fk_id_fun"));
                            vendas.add(venda);
                            anterior = venda;
                            anterior.setId(rst.getInt(1));
                        }
                        Produto produto = new Produto(rst.getInt("p.id_produto"), rst.getString("p.nome"),rst.getString("p.descricao"),rst.getString("p.tipo"),rst.getDouble("p.valor_unitario"), rst.getInt("p.estoque"));
                        ItemVenda item = new ItemVenda(rst.getInt("i.id_item"),rst.getInt("i.quantidade"), rst.getDouble("i.valor_subtotal"),rst.getInt("v.id_venda"), rst.getInt("p.id_produto"));
                        anterior.getItens().add(item);
                    }
                }
                return vendas;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * lista todas as vendas em um certo periodo de tempo de um determinado
     * cliente
     *
     * @return uma lista de vendas, em um certo periodo de venda de um
     * determinado cliente, cada uma preenchida com uma lista de itens
     * relacionados
     * @throws ClassNotFoundException Caso não encontre o Driver de conexão com
     * o MySQL
     */
    public static List<Venda> listarVendasComItensEmUmPeriodoPorCliente(Date dataInicial, Date dataFinal, String nomeCliente) throws ClassNotFoundException, PropertyVetoException {
        try {
            Venda anterior = null;
            List<Venda> vendas = new ArrayList<Venda>();
            String query = "select v.id_venda, v.data_venda, "
                    + "c.id_cli, c.nome, c.CPF, c.sexo, c.data_nasc, c.email, c.estado_civil, c.telefone, c.fk_id_endereco "
                    + "i.id_item, i.quantidade, i.valor_total, "
                    + "p.id_produto, p.nome, p.valor_unitario, p.estoque, p.descricao, p.tipo "
                    + "from venda v "
                    + "inner join cliente c on c.id_cli = v.fk_id_cli "
                    + "inner join item_venda i on v.id_venda = i.fk_id_venda "
                    + "inner join produto p on p.id_produto = i.fk_id_produto "
                    + "where c.nome like ? and v.data_venda between ? and ? "
                    + "order by v.data_venda desc ";

            try ( PreparedStatement pstm = ConnectionFactory.getInstance().recuperaConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, "%" + nomeCliente + "%");
                pstm.setDate(2, dataInicial);
                pstm.setDate(3, dataFinal);

                pstm.execute();

                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (anterior == null || !(anterior.getId() == rst.getInt(1))) {
                            Venda venda = new Venda(rst.getInt("v.id_venda"),rst.getDate("v.data_venda"), rst.getInt("v.fk_id_cli"), rst.getInt("v.fk_id_fun"));
                            vendas.add(venda);
                            anterior = venda;
                            anterior.setId(rst.getInt(1));
                        }
                        Produto produto = new Produto(rst.getInt("p.id_produto"), rst.getString("p.nome"),rst.getString("p.descricao"),rst.getString("p.tipo"),rst.getDouble("p.valor_unitario"), rst.getInt("p.estoque"));
                        ItemVenda item = new ItemVenda(rst.getInt("i.id_item"),rst.getInt("i.quantidade"), rst.getDouble("i.valor_subtotal"),rst.getInt("v.id_venda"), rst.getInt("p.id_produto"));
                        anterior.getItens().add(item);
                    }
                }
                return vendas;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * lista todas as vendas de um determinado cliente
     *
     * @return uma lista de vendas de um determinado cliente, cada uma
     * preenchida com uma lista de itens relacionados
     * @throws ClassNotFoundException Caso não encontre o Driver de conexão com
     * o MySQL
     */
    public static List<Venda> listarVendasComItensPorCliente(String nomeCliente) throws ClassNotFoundException, PropertyVetoException {
        try {
            Venda anterior = null;
            List<Venda> vendas = new ArrayList<Venda>();
            String query = "select v.id_venda, v.data_venda, "
                    + "c.id_cli, c.nome, c.CPF, c.sexo, c.data_nasc, c.email, c.estado_civil, c.telefone, c.fk_id_endereco "
                    + "i.id_item, i.quantidade, i.valor_total, "
                    + "p.id_produto, p.nome, p.valor_unitario, p.estoque, p.descricao, p.tipo "
                    + "from venda v "
                    + "inner join cliente c on c.id_cli = v.fk_id_cli "
                    + "inner join item_venda i on v.id_venda = i.fk_id_venda "
                    + "inner join produto p on p.id_produto = i.fk_id_produto "
                    + "where c.nome like ?"
                    + "order by v.data_venda desc ";

            try ( PreparedStatement pstm = ConnectionFactory.getInstance().recuperaConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, nomeCliente);

                pstm.execute();

                try ( ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (anterior == null || !(anterior.getId() == rst.getInt(1))) {
                            Venda venda = new Venda(rst.getInt("v.id_venda"),rst.getDate("v.data_venda"), rst.getInt("v.fk_id_cli"), rst.getInt("v.fk_id_fun"));
                            vendas.add(venda);
                            anterior = venda;
                            anterior.setId(rst.getInt(1));
                        }
                        Produto produto = new Produto(rst.getInt("p.id_produto"), rst.getString("p.nome"),rst.getString("p.descricao"),rst.getString("p.tipo"),rst.getDouble("p.valor_unitario"), rst.getInt("p.estoque"));
                        ItemVenda item = new ItemVenda(rst.getInt("i.id_item"),rst.getInt("i.quantidade"), rst.getDouble("i.valor_subtotal"),rst.getInt("v.id_venda"), rst.getInt("p.id_produto"));
                        anterior.getItens().add(item);
                    }
                }
                return vendas;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Salva uma venda no banco de dados
     *
     * @param venda obj da classe Venda devidamente preenchido
     * @return Obj Venda com id preenchido
     * @throws ClassNotFoundException Caso não encontre o Driver de conexão com
     * o MySQL
     */
    public static Venda save(Venda venda) throws ClassNotFoundException, PropertyVetoException {

        try {
            String query = "insert into venda (data_venda,  fk_id_cli, fk_id_fun) values (?,?,?)";

            try ( PreparedStatement pstm = ConnectionFactory.getInstance().recuperaConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDate(1, new Date(venda.getDataVenda().getTime()));
                pstm.setInt(2, venda.getFkIdCliente());
                pstm.setInt(3, venda.getFkIdFuncionario());
                pstm.execute();

                try ( ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        venda.setId(rst.getInt(1));
                    }
                }
                return venda;
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    public static void salvaValor(int id, double valorTotal) throws ClassNotFoundException, PropertyVetoException {

        try {
            String query = "update venda set valor_total=?";

            try ( PreparedStatement pstm = ConnectionFactory.getInstance().recuperaConexao().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setDouble(1, valorTotal);
                pstm.executeUpdate();

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

}
