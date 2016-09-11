/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;

/**
 *
 * @author luksbelt
 */
public class AccionSemantica2 extends AccionSemantica{
    //Limpia buffer
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        analizadorLexico.consumir();
        analizadorLexico.setBuffer("");
        return null;
    }
    
}
