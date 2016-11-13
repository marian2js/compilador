package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;
import compiladores.sintactico.ParserTokens;

public class AccionSemantica5 extends AccionSemantica {

    // Consume el ultimo
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        analizadorLexico.addBuffer(c);
        analizadorLexico.consumir();
        String buffer = analizadorLexico.getBuffer();
        Token simbolo;
        if (buffer.length() == 1) {
            char caracter = buffer.charAt(0);
            int ascii = (int) caracter;
            simbolo = new Token(buffer, ascii); //Simbolo simple

        }
        else if (c == '\'') {
            Token token = analizadorLexico.getTablaSimbolos().get(buffer);
            if (token != null) {
                return token;
            }
            simbolo = new Token(buffer, ParserTokens.CADENA); //Cadena
            simbolo.set("tipostr", "Constante de caracteres");
            simbolo.set("ID",new String ("C"+(new Integer(analizadorLexico.getLinea())).toString()));
            analizadorLexico.getTablaSimbolos().addSimbolo(simbolo);
            return simbolo;
        }
        else if (buffer.equals("+=")) {
            simbolo = new Token(buffer, ParserTokens.MASIGUAL); //Asignacion +=
        }
        else if (buffer.equals(":=")) {
            simbolo = new Token(buffer, ParserTokens.ASIGNACION); //Asignacion
        }
        else {
            simbolo = new Token(buffer, ParserTokens.COMPARADOR);  //Comparacion
        }
        return simbolo;
    }
    
}
