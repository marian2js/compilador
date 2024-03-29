package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;
import compiladores.logger.Logger;
import compiladores.logger.Warning;
import compiladores.sintactico.ParserTokens;

public class AccionSemantica7 extends AccionSemantica {
    // Integers
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        String buffer = analizadorLexico.getBuffer();
        double n = Double.parseDouble(buffer.substring(2));
        if (n < -32768) {
            n = -32768;
            Warning w = new Warning("Integer fuera de rango. Transformado al mínimo.", analizadorLexico.getLinea(), "Lexico");
            Logger.getLog().addMensaje(w);
        }
        if (n > 32767) {
            n = 32767;
            Warning w = new Warning("Integer fuera de rango. Transformado al máximo.", analizadorLexico.getLinea(), "Lexico");
            Logger.getLog().addMensaje(w);
        }
        Token token = analizadorLexico.getTablaSimbolos().get(buffer);
        if (token != null) {
            return token;
        }
        int pos = Double.toString(n).indexOf(".");
        buffer = Double.toString(n).substring(0, pos);
        token =  new Token("_i" + buffer, ParserTokens.CTE_ENTERA); //Integer
        token.set("numero", n);
        token.set("tipostr", "Constante Entera");
        token.set("tipo", "integer");
        analizadorLexico.getTablaSimbolos().addSimbolo(token);
        return token;
    }
}
