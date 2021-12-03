/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sorveteria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rodolpho
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinho {
    
    private int idLista;
    private int idProd;
    private String nomeProd;
    private int quantidade;
}
