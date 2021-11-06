package br.com.sorveteria.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import br.com.sorveteria.model.Fornecedor;

public class FornecedorDAO {
    // Variaveis de conexão
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/sorveteriadb?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "root";

    // Método de conexão
    private Connection conectarFuncionario() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /** CRUD CRIAR FORNECEDOR */
    public void inserirFornecedor(Fornecedor fornecedor) {
        String insert = "insert into fornecedor(CNPJ, nome, email, telefone, endereco, cidade, estado, segmento) values (?,?,?,?,?,?,?,?)";
        try {
            Connection con = conectarFuncionario();
            PreparedStatement pst = con.prepareStatement(insert);

            pst.setString(1, fornecedor.getCnpj());
            pst.setString(2, fornecedor.getNome());
            pst.setString(3, fornecedor.getEmail());
            pst.setString(4, fornecedor.getTelefone());
            pst.setString(5, fornecedor.getEndereco());
            pst.setString(6, fornecedor.getCidade());
            pst.setString(7, fornecedor.getEstado());
            pst.setString(8, fornecedor.getSegmento());

            pst.executeUpdate();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** CRUD LISTAR FORNECEDOR */
    public ArrayList<Fornecedor> listarFornecedor() {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        String select = "select * from fornecedor order by id_for";
        try {
            Connection con = conectarFuncionario();
            PreparedStatement pst = con.prepareStatement(select);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String idFor = rs.getString(1);
                String cnpj = rs.getString(2);
                String nome = rs.getString(3);
                String email = rs.getString(4);
                String telefone = rs.getString(5);
                String endereco = rs.getString(6);
                String cidade = rs.getString(7);
                String estado = rs.getString(8);
                String segmento = rs.getString(9);

                fornecedores
                        .add(new Fornecedor(idFor, cnpj, nome, email, telefone, endereco, cidade, estado, segmento));
            }
            con.close();
            return fornecedores;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /** CRUD EDITAR */
    // Primeiro, selecionar!
    public void selecionarFornecedor(Fornecedor fornecedor) {
        String selecionar = "select * from fornecedor where id_for = ?";
        try {
            Connection con = conectarFuncionario();
            PreparedStatement pst = con.prepareStatement(selecionar);
            pst.setString(1, fornecedor.getIdFor());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                fornecedor.setIdFor(rs.getString(1));
                fornecedor.setCnpj(rs.getString(2));
                fornecedor.setNome(rs.getString(3));
                fornecedor.setEmail(rs.getString(4));
                fornecedor.setTelefone(rs.getString(5));
                fornecedor.setEndereco(rs.getString(6));
                fornecedor.setCidade(rs.getString(7));
                fornecedor.setEstado(rs.getString(8));
                fornecedor.setSegmento(rs.getString(9));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Agora, update!
    public void alterarFornecedor(Fornecedor fornecedor) {
        String update = "update fornecedor set CNPJ=?, nome=?, email=?, telefone=?, endereco=?, cidade=?, estado=?, segmento=? where id_for=?";
        try {
            Connection con = conectarFuncionario();
            PreparedStatement pst = con.prepareStatement(update);
            pst.setString(1, fornecedor.getCnpj());
            pst.setString(2, fornecedor.getNome());
            pst.setString(3, fornecedor.getEmail());
            pst.setString(4, fornecedor.getTelefone());
            pst.setString(5, fornecedor.getEndereco());
            pst.setString(6, fornecedor.getCidade());
            pst.setString(7, fornecedor.getEstado());
            pst.setString(8, fornecedor.getSegmento());
            pst.setString(9, fornecedor.getIdFor());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /** CRUD DELETE */
    public void deleteFornecedor(Fornecedor fornecedor) {
        String delete = "delete from fornecedor where id_for=?";
        try {
            Connection con = conectarFuncionario();
            PreparedStatement pst = con.prepareStatement(delete);
            pst.setString(1, fornecedor.getIdFor());
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
