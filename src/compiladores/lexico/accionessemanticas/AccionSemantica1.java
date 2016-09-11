package compiladores.lexico.accionessemanticas;

import compiladores.Identificador;
import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;

public class AccionSemantica1 extends AccionSemantica {
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico,
            char c) {
        String buffer = analizadorLexico.getBuffer();
        
        // Palabra reservada
        if (analizadorLexico.getTablaSimbolos().esPalabraReservada(buffer)) {
            return analizadorLexico.getTablaSimbolos().get(buffer);
        }
        
        // Identificador
        if (buffer.length() > 20) {
           buffer = buffer.substring(20);
        }
        Token token = analizadorLexico.getTablaSimbolos().get(buffer);
        if (token != null) {
            return token;
        }
        token = new Identificador(buffer);
        analizadorLexico.getTablaSimbolos().addSimbolo(token);
        return token;
    }
    
}
