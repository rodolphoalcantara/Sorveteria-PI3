/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sorveteria.dao;

import br.com.sorveteria.factory.ConnectionFactory;
import br.com.sorveteria.model.Produto;
import br.com.sorveteria.model.Venda;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paola
 */
public class VendaDAO {

    public static List<Venda> buscarTodosProdutos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Venda> getAll() throws DaoException {

        String sql = "SELECT * FROM venda";

        ArrayList<Venda> vendas = null;

        try (Connection con = ConnectionFactory.getInstance().recuperaConexao()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            vendas = new ArrayList<>();
            while (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("venda_id"));
                venda.setFkIdCliente(rs.getInt("venda_cli"));
                venda.setFkIdFuncionario(rs.getInt("venda_func"));
                venda.setValorTotal(rs.getDouble("venda_val_total"));
                venda.setDataVenda(rs.getDate("venda_data_venda"));

                //Percorre e pega todas os produtos vendidos no id da compra
                ArrayList<Produto> produtos = new ArrayList<>();
                String sqlProd = "SELECT * FROM itens WHERE id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, venda.getId());
                ResultSet rsProd = stmt.executeQuery();

                while (rsProd.next()) {
                    //Cria produto e add na lista
                }
                rsProd.close();
                stmt.close();
                venda.setProdutos(produtos);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO getALL VendaDAO", ex);
        } finally {
            return vendas;
        }

    }

    /**
     * Regista uma venda no Banco de dados
     *
     * @param venda - Venda a ser registrada
     * @return boolean - confirma se a operacao foi realizada
     * @throws DaoException
     */
 
    public boolean create(Venda venda) throws DaoException, PropertyVetoException {
//        Ajustar para a venda
        int vendaId;
        String sql = "INSERT INTO venda (venda_cli_id, venda_func, venda_val_total, venda_data_venda) VALUES (?,?,?,?)";
        try (Connection conn = ConnectionFactory.getInstance().recuperaConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, venda.getFkIdCliente());
                stmt.setInt(2, 1);//venda.getIdFuncionario());
                stmt.setDouble(3, venda.getValorTotal());
                stmt.setDate(4, new java.sql.Date(venda.getDataVenda().getTime()));
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                vendaId = (int) rs.getLong(1);
                for (Produto prod : venda.getProdutos()) {
                    sql = "INSERT INTO itens (it_produto, it_venda, it_valor_prod, it_qtd) VALUES(?,?,?,?)";
                    try (PreparedStatement stmtProd = conn.prepareStatement(sql)) {
                        stmtProd.setInt(1, prod.getId());
                        stmtProd.setInt(2, vendaId);
                        stmtProd.setDouble(3, prod.getValorUnitario());
                        stmtProd.execute();
                        stmtProd.close();
                    }
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO create-con VendaDAO", ex);
        }
    }

  
    public boolean delete(int id) throws DaoException, PropertyVetoException {
        String sql = "DELETE itens.*, venda.* FROM itens, venda WHERE itens.it_venda = ? AND venda.venda_id = ?";
        try (Connection conn = ConnectionFactory.getInstance().recuperaConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, id);
                stmt.setInt(2, id);
                stmt.execute();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public ArrayList get(String nome) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean update(int id, Venda venda) throws DaoException, PropertyVetoException {
        String sql = "UPDATE venda SET venda_func = ?, venda_val_total = ?, venda_cli_id = ? WHERE venda_id = ?";

        try (Connection conn = ConnectionFactory.getInstance().recuperaConexao()) {
            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            conn.setAutoCommit(false);

            stmt.setInt(1, venda.getFkIdCliente());
            stmt.setInt(2, 1);//venda.getIdFuncionario());
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setDate(4, new java.sql.Date(venda.getDataVenda().getTime()));
            stmt.setInt(5, id);

            stmt.executeUpdate();

            sql = "DELETE FROM itens WHERE it_venda = ?";
            try (PreparedStatement stmtProd = conn.prepareStatement(sql)) {
                stmtProd.setInt(1, id);
                stmtProd.execute();
                stmtProd.close();
            }

            for (Produto prod : venda.getProdutos()) {
                sql = "INSERT INTO itens (it_produto, it_venda, it_valor_prod, it_qtd) VALUES(?,?,?,?)";
                try (PreparedStatement stmtProd = conn.prepareStatement(sql)) {
                    stmtProd.setInt(1, prod.getId());
                    stmtProd.setInt(2, id);
                    stmtProd.setDouble(3, prod.getValorUnitario());
                    stmtProd.execute();
                    stmtProd.close();
                }
            }
            conn.commit();
            conn.close();
            return true;
        } catch (SQLException ex) {
            return false;
//           throw new RuntimeException("ERRO getALL VendaDAO", ex);
        }
    }

    
    public Venda read(int id) throws DaoException {
        String sql = "SELECT * FROM venda where id = ?";

        Venda vendas = null;

        try (Connection con = ConnectionFactory.getInstance().recuperaConexao()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            stmt.close();

            if (rs.next()) {
                Venda venda = new Venda();
                venda.setId(rs.getInt("venda_id"));
                venda.setFkIdCliente(rs.getInt("venda_cli"));
                venda.setFkIdFuncionario(rs.getInt("venda_func"));
                venda.setValorTotal(rs.getDouble("venda_val_total"));
                venda.setDataVenda(rs.getDate("venda_data_venda"));

                //Percorre e pega todas os produtos vendidos no id da compra
                ArrayList<Produto> produtos = new ArrayList<>();
                String sqlProd = "SELECT * FROM itens WHERE id = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, venda.getId());
                ResultSet rsProd = stmt.executeQuery();

                while (rsProd.next()) {
                    //Cria produto e add na lista
                }
                rsProd.close();
                stmt.close();
                venda.setProdutos(produtos);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO read VendaDAO", ex);
        } finally {
            return vendas;
        }
    }

    private static class DaoException extends Exception {

        public DaoException() {
        }
    }

}
