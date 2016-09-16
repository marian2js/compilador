package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;
import compiladores.logger.Logger;
import compiladores.logger.Warning;
import compiladores.sintactico.ParserTokens;

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
            Warning w = new Warning("String truncado a 20 caracteres", analizadorLexico.getLinea(), "Lexico");
            Logger.getLog().addMensaje(w);
            buffer = buffer.substring(20);
        }
        Token token = analizadorLexico.getTablaSimbolos().get(buffer);
        if (token != null) {
            return token;
        }
        token = new Token(buffer, ParserTokens.ID);
        analizadorLexico.getTablaSimbolos().addSimbolo(token);
        return token;
    }
    
}
