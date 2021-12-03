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
public class ItemVenda {
    
    private int id;
    private int quantidade;
    private Double valorSubtotal;
    private int idVenda;
    private int id_produto;
      
}
