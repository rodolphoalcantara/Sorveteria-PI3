/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sorveteria.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *Classe modelo que representa um funcionario
 * @author rodolpho
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
    private int id;
    private String CPF;
    private String nome;
    private String sexo;
    private Date dataNascimento;
    private String login;
    private String senha;
    private Boolean isAdmin;
    
    public Funcionario(String CPF, String nome, String sexo, Date dataNascimento, String login, String senha, Boolean isAdmin) {
        this.CPF = CPF;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.senha = senha;
        this.isAdmin = isAdmin;
    }

    public Funcionario(int id, String CPF, String nome) {
        this.id = id;
        this.CPF = CPF;
        this.nome = nome;
    }

    public Funcionario(int id, String CPF, String nome, String sexo, Date dataNascimento, String login, Boolean isAdmin) {
        this.id = id;
        this.CPF = CPF;
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.login = login;
        this.isAdmin = isAdmin;
    }
    
    
}
