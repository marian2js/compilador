package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.TablaSimbolos;

public abstract class AccionSemantica {
    
    public abstract Token ejecutar(TablaSimbolos ts, String buffer);
}
