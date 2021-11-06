package br.com.sorveteria.model;

import java.util.ArrayList;
import java.util.Date;

public class Venda {

    private int id;
    private Date dataVenda;
    private double valorTotal;
    private int fkIdCliente;
    private int fkIdFuncionario;
    private ArrayList<Produto> produtos;

    public Venda(Date dataVenda, double valorTotal, int fkIdCliente, int fkIdFuncionario) {
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.fkIdCliente = fkIdCliente;
        this.fkIdFuncionario = fkIdFuncionario;
    }

    public Venda(int id, Date dataVenda, double valorTotal, int fkIdCliente, int fkIdFuncionario) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.fkIdCliente = fkIdCliente;
        this.fkIdFuncionario = fkIdFuncionario;
    }

    public Venda() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getFkIdCliente() {
        return fkIdCliente;
    }

    public void setFkIdCliente(int fkIdCliente) {
        this.fkIdCliente = fkIdCliente;
    }

    public int getFkIdFuncionario() {
        return fkIdFuncionario;
    }

    public void setFkIdFuncionario(int fkIdFuncionario) {
        this.fkIdFuncionario = fkIdFuncionario;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Venda{" + "id=" + id + ", dataVenda=" + dataVenda + ", valorTotal=" + valorTotal + ", fkIdCliente=" + fkIdCliente + ", fkIdFuncionario=" + fkIdFuncionario + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        hash = 61 * hash + this.fkIdCliente;
        hash = 61 * hash + this.fkIdFuncionario;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.fkIdCliente != other.fkIdCliente) {
            return false;
        }
        if (this.fkIdFuncionario != other.fkIdFuncionario) {
            return false;
        }
        return true;
    }

    public Object setFkIdCliente() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
