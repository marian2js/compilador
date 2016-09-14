package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;

public class AccionSemantica0 extends AccionSemantica {
    //Consume caracter
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
       analizadorLexico.addBuffer(c);
       analizadorLexico.consumir();
       return null;
    }
}
