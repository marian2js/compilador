package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;
import compiladores.sintactico.ParserTokens;

public class AccionSemantica101 extends AccionSemantica {
    // Floats
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        String buffer = analizadorLexico.getBuffer();
        buffer = buffer.substring(2);
        int indexOfE = buffer.indexOf("E");
        float n;
        int e = 1;

        if (indexOfE != -1) {
            // Floats con exponente
            n = new Float(buffer.substring(0, indexOfE));
            e = new Integer(buffer.substring(indexOfE + 1));
        } else {
            // Floats sin exponente
            n = new Float(buffer);
        }

        // Limites
        if (Math.pow(n, e) < -Math.pow(3.40282347, 38)) {
            n = -3.40282347f;
            e = 38;
        } else if (Math.pow(n, e) < 0 && Math.pow(n, e) > -Math.pow(1.17549435, -38)) {
            n = 0;
            e = 1;
        } else if (Math.pow(n, e) > Math.pow(3.40282347, 38)) {
            n = 3.40282347f;
            e = 38;
        } else if (Math.pow(n, e) > 0 && Math.pow(n, e) < Math.pow(1.17549435, -38)) {
            n = 0;
            e = 1;
        }
        
        String val = Float.toString(n);

        // Si el exponente es distinto a 1, lo incluimos
        if (e != 1) {
            val += "E" + Integer.toString(e);
        }
        
        return new Token("_f" + val, ParserTokens.CTE_FLOAT);
    }
}
