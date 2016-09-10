package compiladores.lexico.accionessemanticas;

import compiladores.Identificador;
import compiladores.Token;
import compiladores.TablaSimbolos;

public class AccionSemantica1 extends AccionSemantica {

    @Override
    public Token ejecutar(TablaSimbolos ts, String buffer) {        
        // Palabra reservada
        if (ts.esPalabraReservada(buffer)) {
            return ts.get(buffer);
        }
        
        // Identificador
        if (buffer.length() > 20) {
           buffer = buffer.substring(20);
        }
        Token token = ts.get(buffer);
        if (token != null) {
            return token;
        }
        token = new Identificador(buffer);
        ts.addSimbolo(token);
        return token;
    }
    
}
