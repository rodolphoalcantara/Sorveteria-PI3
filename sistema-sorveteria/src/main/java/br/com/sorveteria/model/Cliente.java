package br.com.sorveteria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	
	int id;
    String CPF;
    String nome;
    String email;
    String sexo;
    String data_nasc;
    String telefone;
    String endereco;
    String cidade;
    String estado;
    
	public Cliente(String cPF, String nome, String email, String sexo, String data_nasc, String telefone,
			String endereco, String cidade, String estado) {
		CPF = cPF;
		this.nome = nome;
		this.email = email;
		this.sexo = sexo;
		this.data_nasc = data_nasc;
		this.telefone = telefone;
		this.endereco = endereco;
		this.cidade = cidade;
		this.estado = estado;
	}
	
    
    
}
