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
                Objeto f = (Objeto) filas.obj;
                Objeto c = (Objeto) columnas.obj;
                if (!f.getTipo().equals("integer")) {
                    Logger.getLog().addMensaje(new Error("Tipo de subindice invalido", filas.ival, "Semantico"));
                }
                if (!c.getTipo().equals("integer")) {
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

    public static Terceto crearTercetoMatrix(Parser parser, Objeto id,Objeto fila, Objeto columna) {
        Token cte1 = getTokenCte(parser, 1);
        Token cte2 = getTokenCte(parser, 2);
        Terceto muli;
        Terceto matrix = null;
        Terceto columnas = null;
        if((Objeto)id.get("columnas") == null){//la variable no existe
            Token token =  new Token("_i-err" , ParserTokens.CTE_ENTERA); //Utilizo una constante error
            token.set("numero", Double.MIN_VALUE);
            token.set("tipostr", "Constante Entera");
            token.set("tipo", "integer");
            columnas = new TercetoSuma((Objeto)token, cte1);
        }
        else{
            columnas = new TercetoSuma((Objeto)id.get("columnas"), cte1);
        }
        //calcular posicion (i)*(#columnas)+(j)*T
        if (id.get("anotacion") != null && id.get("anotacion").toString().equals("/#@1")) {
            Terceto posfila = new TercetoResta(fila, cte1);
            muli = new TercetoMultiplicacion(posfila, (Objeto)id.get("columnas"));
        } else {
            muli = new TercetoMultiplicacion(fila, columnas);
        }

        Terceto pos = new TercetoSuma(muli, columna);
        Terceto pos2 = new TercetoMultiplicacion(pos, cte2);
        /*Double pos = ((Double)fila.get("numero") - 0) * ((Double)((Objeto)id.get("columnas")).get("numero") - 0 + 1)
                + (Double)columna.get("numero") - 0;
        Double offset = pos * 16;*/

        //generar terceto matrix
        matrix = new TercetoMatrix(id, pos2);
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

    private static Token getTokenCte(Parser parser, int val) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        String valStr = "_i" + val;
        Token token = ts.get(valStr);
        if (token != null) {
            return token;
        }
        token = new Token(valStr, ParserTokens.CTE_ENTERA);
        token.set("numero", val);
        token.set("tipo", "integer");
        token.set("tipostr", "Constante Entera");
        ts.addSimbolo(token);
        return token;
    }

}
