package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;

public abstract class AccionSemantica {
    
    public abstract Token ejecutar(AnalizadorLexico al, char c);
}
