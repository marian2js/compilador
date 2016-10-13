package compiladores.sintactico;

import compiladores.TablaSimbolos;
import compiladores.Token;
import java.util.ArrayList;

class ParserHelper {

    public static void cargarVars(Parser parser, ParserVal tipo, ParserVal tokens) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        for (ParserVal tokenVal : ((ArrayList<ParserVal>) tokens.obj)) {
            Token token = ((Token) tokenVal.obj);
            token.set("tipo", ((Token) tipo.obj).getLexema());
            token.set("lexema", "var@" + token.getLexema());
            token.set("uso", "Variable");
            Token tokenTabla = ts.get(token.getLexema());
            if (tokenTabla == null) {
                ts.addSimbolo(token);
            }
        }
    }

    public static void cargarMatriz(Parser parser,ParserVal matriz, ParserVal tipo, ParserVal filas, ParserVal columnas,ParserVal anotacion) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        Token token = ((Token) matriz.obj);
        token.set("tipo", ((Token) tipo.obj).getLexema());
        token.set("lexema", "mat@" + token.getLexema());
        token.set("filas",((Token)filas.obj).get("numero"));
        token.set("columnas",((Token)columnas.obj).get("numero"));
        token.set("uso", "Nombre de arreglo");
        if(anotacion != null){
            token.set("anotacion",((Token)anotacion.obj).getLexema());
        }
        Token tokenTabla = ts.get(token.getLexema());
        if (tokenTabla == null) {
            ts.addSimbolo(token);
        }
    }
   public static void cargarMatriz(Parser parser,ParserVal matriz, ParserVal tipo, ParserVal filas, ParserVal columnas){
	cargarMatriz(parser,matriz,tipo,filas,columnas,null);
    }


    public static ArrayList<ParserVal> cargarListaVariables(ParserVal var, ParserVal tokens) {
        if (tokens == null) {
            tokens = new ParserVal(new ArrayList<Token>());
        }
        ((ArrayList<ParserVal>) tokens.obj).add(var);
        return (ArrayList<ParserVal>)tokens.obj;
    }
    
    public static void setNombrePrograma(Parser parser, ParserVal nombre) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        Token token = (Token) nombre.obj;
        token.set("uso", "Nombre del programa");
        ts.addSimbolo(token);
    }

}
