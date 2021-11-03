package br.com.sorveteria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	
	int id;
	String nome;
	String descricao;
	String tipo;
	double valorUnitario;
	int estoque;
	
	// Fornecedor fornecedor; implementar assim que for criada a classe

	public Produto(String nome, String descricao, double preco, int estoque, String tipo) {
		this.nome = nome;
		this.descricao = descricao;
		this.valorUnitario = preco;
		this.estoque = estoque;
		this.tipo = tipo;
	}
	
	
	
}
