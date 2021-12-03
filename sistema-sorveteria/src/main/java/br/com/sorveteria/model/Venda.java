package br.com.sorveteria.model;

import java.util.ArrayList;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    private int id;
    private Date dataVenda;
    private int fkIdCliente;
    private int fkIdFuncionario;
    private ArrayList<ItemVenda> itens;
    private Double valorTotal;

    public Venda(int id, Date dataVenda, int fkIdCliente, int fkIdFuncionario) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.fkIdCliente = fkIdCliente;
        this.fkIdFuncionario = fkIdFuncionario;
        
        this.itens = new ArrayList<ItemVenda>();
    }
    public Venda(int id, Date dataVenda, int fkIdCliente, int fkIdFuncionario, Double valor) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.fkIdCliente = fkIdCliente;
        this.fkIdFuncionario = fkIdFuncionario;
        this.valorTotal = valor;
        this.itens = new ArrayList<ItemVenda>();
    }

    
    
    
}
