package compiladores.lexico.accionessemanticas;

import compiladores.Token;
import compiladores.lexico.AnalizadorLexico;
import compiladores.logger.Logger;
import compiladores.logger.Warning;
import compiladores.sintactico.ParserTokens;

public class AccionSemantica8 extends AccionSemantica {
    // Floats
    
    @Override
    public Token ejecutar(AnalizadorLexico analizadorLexico, char c) {
        String buffer = analizadorLexico.getBuffer();
        buffer = buffer.substring(2);
        int indexOfE = buffer.indexOf("E");
        float n;
        double e = 1;

        if (indexOfE != -1) {
            // Floats con exponente
            n = new Float(buffer.substring(0, indexOfE));
            e = new Double(buffer.substring(indexOfE + 1));
        } else {
            // Floats sin exponente
            n = new Float(buffer);
        }

        // Limites
        if (n * Math.pow(10, e) < -3.40282347 * Math.pow(10, 38)) {
            n = -3.40282347f;
            e = 38;
            Warning w = new Warning("Float fuera de rango. Transformado al mínimo.", analizadorLexico.getLinea(), "Lexico");
            Logger.getLog().addMensaje(w);
        } else if (n * Math.pow(10, e) < 0 && n * Math.pow(10, e) > -1.17549435 * Math.pow(10, -38)) {
            n = 0;
            e = 1;
            Warning w = new Warning("Float demasiado chico. Transformado a cero.", analizadorLexico.getLinea(), "Lexico");
            Logger.getLog().addMensaje(w);
        } else if (n * Math.pow(10, e) > 3.40282347 * Math.pow(10, 38)) {
            n = 3.40282347f;
            e = 38;
            Warning w = new Warning("Float fuera de rango. Transformado al máximo.", analizadorLexico.getLinea(), "Lexico");
            Logger.getLog().addMensaje(w);
        } else if (n * Math.pow(10, e) > 0 && n * Math.pow(10, e) < 1.17549435 * Math.pow(10, -38)) {
            n = 0;
            e = 1;
            Warning w = new Warning("Float demasiado chico. Transformado a cero.", analizadorLexico.getLinea(), "Lexico");
            Logger.getLog().addMensaje(w);
        }
        
        String val = Float.toString(n);

        // Si el exponente es distinto a 1, lo incluimos
        if (e != 1) {
            val += "E" + Double.toString(e);
        }
        
        return new Token("_f" + val, ParserTokens.CTE_FLOAT);
    }
}
