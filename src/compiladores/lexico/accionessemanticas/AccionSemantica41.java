package compiladores.lexico.accionessemanticas;

import compiladores.Simbolo;
import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;

public class AccionSemantica41 extends AccionSemantica {

    //No consume el ultimo
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        String buffer = analizadorLexico.getBuffer();
        Token simbolo = new Simbolo(buffer);
        return simbolo;
    }
    
}
