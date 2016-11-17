package compiladores.sintactico;

import compiladores.Objeto;
import compiladores.TablaSimbolos;
import compiladores.tercetos.*;
import compiladores.Token;
import compiladores.logger.Logger;
import compiladores.logger.Error;

import java.util.ArrayList;

class ParserHelper {

    public static void cargarVars(Parser parser, ParserVal tipo, ParserVal tokens) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        for (ParserVal tokenVal : ((ArrayList<ParserVal>) tokens.obj)) {
            Token token = ((Token) tokenVal.obj);
            Token tokenTabla = ts.get(token.getLexema());
            if (tokenTabla == null) {
                token.set("tipo", ((Token) tipo.obj).getLexema());
                token.set("lexema", "var@" + token.getLexema());
                token.set("uso", "Variable");
                ts.addSimbolo(token);
            }
        }
    }

    public static void cargarMatriz(Parser parser,ParserVal matriz, ParserVal tipo, ParserVal filas, ParserVal columnas,ParserVal anotacion) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        Token token = ((Token) matriz.obj);
        Token tokenTabla = ts.get(token.getLexema());
        if (tokenTabla == null) {
            token.set("tipo", ((Token) tipo.obj).getLexema());
            token.set("lexema", "mat@" + token.getLexema());
            token.set("filas",(Token)filas.obj);
            token.set("columnas",(Token)columnas.obj);
            token.set("uso", "Nombre de arreglo");
            if(anotacion != null){
                token.set("anotacion",((Token)anotacion.obj).getLexema());
            }
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

    public static void validarIndices(ParserVal var, ParserVal filas, ParserVal columnas) {
        Token id = (Token) var.obj;
        if (id.getLexema() != null && id.getLexema().contains("mat@")){
            if (filas != null && columnas != null){
                Token f = (Token) filas.obj;
                Token c = (Token) columnas.obj;
                if (f.getValue() != ParserTokens.CTE_ENTERA) {
                    Logger.getLog().addMensaje(new Error("Tipo de subindice invalido", filas.ival, "Semantico"));
                }
                if (c.getValue() != ParserTokens.CTE_ENTERA) {
                    Logger.getLog().addMensaje(new Error("Tipo de subindice invalido", columnas.ival, "Semantico"));
                }
            } else {
                Logger.getLog().addMensaje(new Error("No hay limites declarados en la matriz", var.ival, "Semantico"));
            }
        }

    }

    public static void agregarBF(Terceto terceto) {
        Terceto bf = new Terceto("BF", terceto, null);
        Parser.saltos.add(bf);
    }

    public static void completarBF() {
	Terceto bf = Parser.saltos.get(Parser.saltos.size()-1);
	Parser.saltos.remove(Parser.saltos.size() - 1);
        Terceto tFinal = new TercetoBF("BFF", null, null);
        String label = Integer.toString(Parser.tercetos.size());
        Token tklabel = new Token(label,0);
        tFinal.setOperando1(tklabel);
        bf.setOperando2(tFinal);
    }

    public static void agregarBI() {
        Terceto bi = new TercetoBI("BI", null, null);
        completarBF();
        Parser.saltos.add(bi);
    }

    public static void completarBI() {
	Terceto bf = Parser.saltos.get(Parser.saltos.size()-1);
	Parser.saltos.remove(Parser.saltos.size() - 1);
        Terceto tFinal = new TercetoBI("BIF", null, null);
        String label = Integer.toString(Parser.tercetos.size());
        Token tklabel = new Token(label,0);
        tFinal.setOperando1(tklabel);
        bf.setOperando1(tFinal);
    }
    
    public static void agregarBIF(){//Para sentencia FOR
        Terceto tFinal = new TercetoBI("BIF", null, null);
        String label = Integer.toString(Parser.tercetos.size());
        Token tklabel = new Token(label,0);
        tFinal.setOperando1(tklabel);
        Parser.saltos.add(tFinal);
    }
    
    public static void completarBIF(){
        Terceto bi = new TercetoBI("BI",null, null); 
        completarBF();
        Terceto bif = Parser.saltos.get(Parser.saltos.size()-1);
        Parser.saltos.remove(Parser.saltos.size() - 1);
        bi.setOperando1(bif);
    }

    public static void eliminarSalto() {
        Parser.saltos.remove(Parser.saltos.size() - 1);
    }

    public static Terceto crearTercetoMatrix(Objeto id,Objeto fila, Objeto columna){
        //calcular posicion (i)*(#columnas)+(j)*T
//        Terceto muli = new TercetoMultiplicacion(fila, (Objeto)id.get("filas"));
//        Terceto mulj = new TercetoMultiplicacion(columna, (Objeto)id.get("columnas"));
//        Terceto pos = new TercetoSuma(muli, mulj);
//        Terceto mem = new TercetoMultiplicacion(pos;
        Double posFila = (Double)fila.get("numero") * (Double)((Objeto)id.get("filas")).get("numero");
        Double pos = posFila + (Double)columna.get("numero");
        Double offset = pos * 16;

        //generar terceto matrix
        Terceto matrix = new TercetoMatrix(id, offset);
        return matrix;
    }
    public static void checkVarRedeclarada(Parser parser, ParserVal ids, int linea) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        for (ParserVal variableVal : ((ArrayList<ParserVal>) ids.obj)) {
            Token variable = ((Token) variableVal.obj);
            if (ts.get(variable.getLexema()) != null) {
                Logger.getLog().addMensaje(new Error("Variable " + variable.getLexema() + " ya declarada", linea, "Semantico"));
            }
        }
    }

    public static void checkMatRedeclarada(Parser parser, ParserVal id, int linea) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        Token variable = ((Token) id.obj);
        if (ts.get(variable.getLexema()) != null) {
            Logger.getLog().addMensaje(new Error("Matriz " + variable.getLexema() + " ya declarada", linea, "Semantico"));
        }
    }

    public static void checkVarDeclarada(Parser parser, ParserVal id, int linea) {
        Token variable = ((Token) id.obj);
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        if (ts.get(variable.getLexema()) == null) {
            Logger.getLog().addMensaje(new Error("Variable " + variable.getLexema() + " no declarada", linea, "Semantico"));
        }
    }

    public static void checkTipoVarAsignacion(Parser parser,ParserVal objeto, int linea) {
        Objeto t = ((Objeto) objeto.obj);
        if(!t.getTipo().equals("integer")) {
            Logger.getLog().addMensaje(new Error("Tipo incorrecto en limite de sentencia for", linea, "Semantico"));
        }
    }

    public static void checkAllowAsignacion(Parser parser, ParserVal terminoizq, ParserVal terminoder, int linea){
        if (!((Token) terminoizq.obj).getTipo().equals("float") && !((Objeto) terminoder.obj).getTipo().equals("integer")) {
            if (!parser.conversionAllowPermitida()) {
                Logger.getLog().addMensaje(new Error("Asignacion no permitida", linea, "Semantico"));
            }
        }
    }

}
