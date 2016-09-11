package compiladores.lexico.accionessemanticas;

import compiladores.Simbolo;
import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;

public class AccionSemantica40 extends AccionSemantica {

    // Consume el ultimo
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        analizadorLexico.addBuffer(c);
        analizadorLexico.consumir();
        String buffer = analizadorLexico.getBuffer();
        Token simbolo = new Simbolo(buffer);
        return simbolo;
    }
    
}
