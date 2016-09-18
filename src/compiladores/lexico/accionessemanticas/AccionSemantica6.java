package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;

public class AccionSemantica6 extends AccionSemantica {

    //No consume el ultimo, simbolos simples
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        String buffer = analizadorLexico.getBuffer();
        char caracter = buffer.charAt(0);
        int ascii = (int) caracter;
        Token simbolo = new Token(buffer, ascii);
        return simbolo;
    }
    
}
