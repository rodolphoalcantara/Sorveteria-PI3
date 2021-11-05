/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sorveteria.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author rodolpho
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogEstoque {
    
    private int id;
    private Date dataOperacao;
    private String operacao;
    private int quantidadeMovimentada;
    private Produto produto;
    private Funcionario funcionario;
    
}
