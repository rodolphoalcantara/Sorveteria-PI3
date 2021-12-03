/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sorveteria.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author rodolpho
 */
public class CryptoUtil {
    
    public static String gerarHashSenha(String senha){
        return BCrypt.withDefaults().hashToString(8, senha.toCharArray());
    }
    
    public static Boolean validarSenha(String senhaInput, String senhaHash){
        BCrypt.Result res = BCrypt.verifyer().verify(senhaInput.toCharArray(), senhaHash);
        return res.verified;
    }
}
