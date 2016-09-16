/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.logger.Error;
import compiladores.logger.Logger;
import compiladores.lexico.AnalizadorLexico;

/**
 *
 * @author luksbelt
 */
public class AccionSemantica4 extends AccionSemantica{
    //Error de caracter
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        int linea = analizadorLexico.getLinea();
        Error e = new Error("Error de caracter invalido",linea,"Lexico");
        Logger log = Logger.getLog();
        log.addMensaje(e);
        analizadorLexico.consumir();
        analizadorLexico.setBuffer("");
        return null;
    }
    
}
