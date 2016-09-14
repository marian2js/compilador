package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;
import compiladores.sintactico.ParserTokens;

public class AccionSemantica100 extends AccionSemantica {
    // Integers
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        String buffer = analizadorLexico.getBuffer();
        int n = Integer.parseInt(buffer.substring(2));
        if (n < -32768) {
            n = -32768;
        }
        if (n > 32767) {
            n = 32767;
        }
        Token number = new Token(Integer.toString(n), ParserTokens.CTE_ENTERA); //Integer
        //number.setTipo("int");
        return number;
    }
}
