package compiladores.sintactico;

import compiladores.TablaSimbolos;
import compiladores.Terceto;
import compiladores.Token;
import compiladores.logger.Logger;
import compiladores.logger.Error;

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

    public static void validarIndices(ParserVal filas, ParserVal columnas) {
        Token f = (Token) filas.obj;
        Token c = (Token) columnas.obj;
        if (f.getValue() != ParserTokens.CTE_ENTERA) {
            Logger.getLog().addMensaje(new Error("Tipo de subindice invalido", filas.ival, "Semantico"));
        }
        if (c.getValue() != ParserTokens.CTE_ENTERA) {
            Logger.getLog().addMensaje(new Error("Tipo de subindice invalido", columnas.ival, "Semantico"));
        }
    }

    public static void agregarBF(Terceto terceto) {
        Terceto bf = new Terceto("BF", terceto, null);
        Parser.saltos.add(bf);
    }

    public static void completarBF() {
	Terceto bf = Parser.saltos.get(Parser.saltos.size()-1);
	Parser.saltos.remove(Parser.saltos.size() - 1);
        Terceto tFinal = new Terceto("BFF", null, null);
        bf.setOperando2(tFinal);
    }

    public static void agregarBI() {
        Terceto bi = new Terceto("BI", null, null);
        Parser.saltos.add(bi);
    }

    public static void completarBI() {
	Terceto bf = Parser.saltos.get(Parser.saltos.size()-1);
	Parser.saltos.remove(Parser.saltos.size() - 1);
        Terceto tFinal = new Terceto("BIF", null, null);
        bf.setOperando2(tFinal);
    }

    public static void eliminarSalto() {
        Parser.saltos.remove(Parser.saltos.size() - 1);
    }

    public static void checkVarRedeclarada(Parser parser, ParserVal ids, int linea) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        for (ParserVal variableVal : ((ArrayList<ParserVal>) ids.obj)) {
            Token variable = ((Token) variableVal.obj);
            String var = "var@"+variable.getLexema();
            if (ts.get(var) != null) {
                Logger.getLog().addMensaje(new Error("Variable " + variable.getLexema() + " ya declarada", linea, "Semantico"));
            }
        }
    }

    public static void checkMatRedeclarada(Parser parser, ParserVal id, int linea) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        Token variable = ((Token) id.obj);
        String mat = "mat@"+variable.getLexema();
        if (ts.get(mat) != null) {
            Logger.getLog().addMensaje(new Error("Matriz " + variable.getLexema() + " ya declarada", linea, "Semantico"));
        }
    }

    public static void checkVarDeclarada(Parser parser, ParserVal id, int linea) {
        Token variable = ((Token) id.obj);
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        String var = "var@"+variable.getLexema();
        String mat = "mat@"+variable.getLexema();
        if ((ts.get(var) == null) && (ts.get(mat) == null)) {
            Logger.getLog().addMensaje(new Error("Variable " + variable.getLexema() + " no declarada", linea, "Semantico"));
        }
    }

}
