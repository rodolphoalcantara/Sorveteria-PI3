package br.com.sorveteria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    public static Iterable<Produto> getProdutos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	int id;
	String nome;
	String descricao;
	String tipo;
	double valorUnitario;
	int estoque;
	
	public Produto(String nome, String descricao, double preco, int estoque, String tipo) {
		this.nome = nome;
		this.descricao = descricao;
		this.valorUnitario = preco;
		this.estoque = estoque;
		this.tipo = tipo;
	}
}
