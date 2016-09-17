/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;
import compiladores.sintactico.ParserTokens;

/**
 *
 * @author luksbelt
 */
public class AccionSemantica3 extends AccionSemantica{
    //Anotacion /#@0 /#@1
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        //analizadorLexico.consumir();
        String buffer = analizadorLexico.getBuffer();
        String anotacion = buffer.substring(0, 4);
        Token token = new Token(anotacion, ParserTokens.ANOTACION);
        return token;
    }
    
}
