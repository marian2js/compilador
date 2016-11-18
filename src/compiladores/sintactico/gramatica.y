%{
import compiladores.lexico.*;
import compiladores.*;
import compiladores.logger.Logger;
import compiladores.logger.Info;
import compiladores.logger.Error;
import compiladores.tercetos.*;
import java.io.File;
import java.util.ArrayList;
%}

%token ID CTE_ENTERA CTE_FLOAT IF ELSE ENDIF FOR PRINT INTEGER FLOAT MATRIX CADENA ANOTACION ALLOW TO ASIGNACION MASIGUAL COMPARADOR
%left '-'
%left '+'
%left '*'
%left '/'

%start inicio_programa

/* Gramatica */
%%
inicio_programa: ID programa; {ParserHelper.setNombrePrograma(this, $1);}
programa: grupo_declaraciones bloque_de_sentencias;
grupo_declaraciones:
                    declaracion';'
                    | declaracion';'grupo_declaraciones
                    | declaracion_matrix
                    | declaracion_matrix grupo_declaraciones;
declaracion:
            tipo lista_de_variables {ParserHelper.checkVarRedeclarada(this, $2, yylval.ival); ParserHelper.cargarVars(this, $1, $2); Logger.getLog().addMensaje(new Info("Lista de declaraciones de variables detectada", yylval.ival, "Sintactico"));}
            | declaracion_allow {Logger.getLog().addMensaje(new Info("Declaración de tipo allow detectada", yylval.ival, "Sintactico"));}
            | error lista_de_variables {Error e = new Error("Error de tipo invalido",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | tipo error; {Error e = new Error("Falta declarar variables",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}

lista_de_variables:
                   ID {$$.obj = ParserHelper.cargarListaVariables($1, null);}
                   | ID ',' lista_de_variables {$$.obj = ParserHelper.cargarListaVariables($1, $3);}
                   | ID ',' error {Error e = new Error("Falta declarar variables luego de ,",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
tipo:
     INTEGER
     | FLOAT;

declaracion_matrix:
                      tipo MATRIX ID '['expresion']''['expresion']' inicializacion ';' ANOTACION {ParserHelper.checkMatRedeclarada(this, $3, yylval.ival); Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", $1.ival, "Sintactico"));
                                                                                                  ParserHelper.cargarMatriz(this,$3,$1,$5,$8,$10,$12);
                                                                                                  ParserHelper.validarIndices($3, $5, $8);}
                    | tipo MATRIX ID '['expresion']''['expresion']' inicializacion ';' {ParserHelper.checkMatRedeclarada(this, $3, yylval.ival); Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", $1.ival, "Sintactico"));
                                                                                        ParserHelper.cargarMatriz(this,$3,$1,$5,$8,$10,null);
                                                                                        ParserHelper.validarIndices($3, $5, $8);}
                    | tipo MATRIX ID '['expresion']''['expresion']' ';' ANOTACION {ParserHelper.checkMatRedeclarada(this, $3, yylval.ival); Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", $1.ival, "Sintactico"));
                                                                                   ParserHelper.cargarMatriz(this,$3,$1,$5,$8,null,$11);
                                                                                   ParserHelper.validarIndices($3, $5, $8);}
                    | tipo MATRIX ID '['expresion']''['expresion']' ';' {ParserHelper.checkMatRedeclarada(this, $3, yylval.ival); Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", $1.ival, "Sintactico"));
                                                                         ParserHelper.cargarMatriz(this,$3,$1,$5,$8);
                                                                         ParserHelper.validarIndices($3, $5, $8);}
;
inicializacion:
               '{' columna '}'; { $$.obj = $2.obj; }
columna:
        fila { ArrayList<Token> tokens = new ArrayList<>(); tokens.addAll((ArrayList<Token>)$1.obj); $$.obj = tokens; }
        | columna';' fila; { ArrayList<Token> tokens = new ArrayList<>(); tokens.addAll((ArrayList<Token>)$1.obj); tokens.addAll((ArrayList<Token>)$3.obj); $$.obj = tokens; }
fila:
     CTE_FLOAT { ArrayList<Token> tokens = new ArrayList<>(); tokens.add((Token)$1.obj); $$.obj = tokens; }
     | CTE_ENTERA { ArrayList<Token> tokens = new ArrayList<>(); tokens.add((Token)$1.obj); $$.obj = tokens; }
     | fila ',' CTE_ENTERA { ArrayList<Token> tokens = (ArrayList<Token>)$$.obj; tokens.add((Token)$3.obj); }
     | fila',' CTE_FLOAT; { ArrayList<Token> tokens = (ArrayList<Token>)$$.obj; tokens.add((Token)$3.obj); }
declaracion_allow:
                  ALLOW tipo TO tipo; {this.setAllow($2, $4);}
expresion:
          expresion '+' termino {$$.obj = new TercetoSuma((Objeto) $1.obj, (Objeto) $3.obj);}
          | expresion '-' termino {$$.obj = new TercetoResta((Objeto) $1.obj, (Objeto) $3.obj);}
          | termino
          | expresion '+' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          | expresion '-' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          |'+' termino {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          |'-' termino {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

termino:
        termino '*' factor {$$.obj = new TercetoMultiplicacion((Objeto) $1.obj, (Objeto) $3.obj);}
        | termino '/' factor {$$.obj = new TercetoDivision((Objeto) $1.obj, (Objeto) $3.obj);}
        | factor
        | termino '*' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        | termino '/' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        |'*' factor {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        |'/' factor {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};;
factor:
       ID {ParserHelper.checkVarDeclarada(this, $1, yylval.ival); ParserHelper.validarIndices($1, null, null);}
       |ID '['expresion']''['expresion']' {ParserHelper.checkVarDeclarada(this, $1, yylval.ival); ParserHelper.validarIndices($1, $3, $6); $$.obj = ParserHelper.crearTercetoMatrix(this, (Objeto)$1.obj,(Objeto)$3.obj,(Objeto)$6.obj);}
       | CTE_ENTERA
       | CTE_FLOAT;

bloque_de_sentencias:
                      '{' grupo_sentencias '}';
grupo_sentencias:
                  sentencia
                  | sentencia grupo_sentencias;
sentencia:
           asignacion';'
           | bloque_if';' {Logger.getLog().addMensaje(new Info("Bloque 'if' detectado", $1.ival, "Sintactico"));}
           | bloque_for {Logger.getLog().addMensaje(new Info("Bloque 'for' detectado", $1.ival, "Sintactico"));}
           | bloque_print';'; {Logger.getLog().addMensaje(new Info("Bloque 'print' detectado",yylval.ival,"Sintactico"));}
asignacion:
            ID ASIGNACION expresion {ParserHelper.checkVarDeclarada(this, $1, yylval.ival); $$.obj = new TercetoAsignacion((Objeto)$1.obj, (Objeto)$3.obj); ParserHelper.checkAllowAsignacion(this, $1, $3, yylval.ival); Logger.getLog().addMensaje(new Info("Asignacion detectada", yylval.ival, "Sintactico"));
                                                                    ParserHelper.validarIndices($1, null, null);}
            | ID MASIGUAL expresion {ParserHelper.checkVarDeclarada(this, $1, yylval.ival);$$.obj = new TercetoMasIgual((Objeto)$1.obj, (Objeto)$3.obj); ParserHelper.checkAllowAsignacion(this, $1, $3, yylval.ival); Logger.getLog().addMensaje(new Info("Asignacion más-igual detectada", yylval.ival, "Sintactico"));
                                                                    ParserHelper.validarIndices($1 ,null, null);}
            | ID '['expresion']''['expresion']' ASIGNACION expresion {ParserHelper.checkVarDeclarada(this, $1, yylval.ival);$$.obj = new TercetoAsignacion(ParserHelper.crearTercetoMatrix(this, (Objeto)$1.obj, (Objeto)$3.obj, (Objeto)$6.obj),(Objeto)$9.obj);Logger.getLog().addMensaje(new Info("Asignacion detectada", yylval.ival, "Sintactico"));
                                                                      ParserHelper.validarIndices($1, $3, $6); ParserHelper.checkAllowAsignacion(this, $1, $9, yylval.ival);}
            | ID '['expresion']''['expresion']' MASIGUAL expresion {ParserHelper.checkVarDeclarada(this, $1, yylval.ival);$$.obj = new Terceto(((Objeto)$8.obj).getLexema(),ParserHelper.crearTercetoMatrix(this, (Objeto)$1.obj, (Objeto)$3.obj, (Objeto)$6.obj),(Objeto)$9.obj);
                                                                     Logger.getLog().addMensaje(new Info("Asignacion más-igual detectada", yylval.ival, "Sintactico"));
                                                                     ParserHelper.validarIndices($1, $3, $6); ParserHelper.checkAllowAsignacion(this, $1, $9, yylval.ival);}
            | error ASIGNACION expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | error MASIGUAL expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID error expresion {Error e = new Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID ASIGNACION {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID MASIGUAL {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

asignacion_for:
                ID ASIGNACION expresion {ParserHelper.checkVarDeclarada(this, $1, yylval.ival); $$.obj = new TercetoAsignacion((Objeto)$1.obj, (Objeto)$3.obj); ParserHelper.checkAllowAsignacion(this, $1, $3, yylval.ival);ParserHelper.agregarBIF();}
                | error ASIGNACION expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                | ID error expresion {Error e = new Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                | ID ASIGNACION {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

bloque_if:
           condicion_if cuerpo_if ENDIF
          |condicion_if cuerpo_if_else ELSE cuerpo_else ENDIF
          |condicion_if error{Error e = new Error("Falta palabra reservada ENDIF",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          |condicion_if cuerpo_if_else ELSE error{Error e = new Error("Falta palabra reservada ENDIF",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
	//TODO AGREGAR ERRORES
	//TODO tampoco esta tirando error cuando no se cierra la llave del bloque de sentencias y aparece el ENDIF
;
condicion_if:
              IF '('condicion')' {$$.obj = new TercetoBF("BF",(Objeto)$3.obj,null);
				saltos.add((Terceto)$$.obj);}
              |IF error {Error e = new Error("Falta condicion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

cuerpo_if: bloque_de_sentencias {ParserHelper.completarBF();}
           | sentencia {ParserHelper.completarBF();}
;
cuerpo_if_else: bloque_de_sentencias {ParserHelper.agregarBI();/*agregarBI tambien completa BF*/}
           | sentencia {ParserHelper.agregarBI();/*agregarBI tambien completa BF*/}
;

cuerpo_else: bloque_de_sentencias {ParserHelper.completarBI();}
           | sentencia {ParserHelper.completarBI();}
;


condicion:
           expresion comparador expresion {$$.obj = new TercetoComparacion(((Objeto)$2.obj).getLexema(),(Objeto)$1.obj,(Objeto)$3.obj);}
           | comparador expresion {Error e = new Error("Falta termino izquierdo en la comparacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
           | expresion comparador {Error e = new Error("Falta termino derecho en la comparacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

comparador:
            '='
            | '<'
            | '>'
            | COMPARADOR;
bloque_for:
            FOR'('asignacion_for';'condicion_for';'asignacion')' cuerpo_for {ParserHelper.checkTipoVarAsignacion(this, $7, yylval.ival);
                                                                             }
;

condicion_for:
               condicion {$$.obj = new TercetoBF("BF", (Objeto)$1.obj, null);
                          saltos.add((Terceto)$$.obj);}
;

cuerpo_for:
            bloque_de_sentencias {ParserHelper.completarBIF();/*Tambien completa BF*/}
            | sentencia {ParserHelper.completarBIF();}
;

bloque_print:
              PRINT'('CADENA')' {$$.obj = new TercetoPrint((Objeto)$3.obj);}
              | PRINT CADENA')' {Error e = new Error("Falta (",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
              | PRINT'('CADENA {Error e = new Error("Falta )",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
              | PRINT'('error')' {Error e = new Error("Falta cadena a imprimir",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
%%

/* Parser.java */
private AnalizadorLexico analizadorLexico;
public static ArrayList<Terceto> tercetos;
public static ArrayList<Terceto> saltos;
public static boolean allowFloatToInt = false;

public Parser(File file) {
    analizadorLexico = new AnalizadorLexico(file);
    tercetos = new ArrayList<>();
    saltos = new ArrayList<>();
    yydebug = true;
}

private int yylex() {
    Token token = analizadorLexico.yylex();
    if (token == null) {
        return 0;
    }
    int val = token.getValue();
    yylval = new ParserVal(token);
    yylval.ival = analizadorLexico.getLinea();
    System.out.println("=== Value: " + val + " ===");
    System.out.println("=== Lexema: " + token.getLexema() + " ===");
    return val;
}


private void yyerror(String error) {
    System.out.println("Error: " + error);
}

public AnalizadorLexico getAnalizadorLexico() {
    return analizadorLexico;
}

public void setAnalizadorLexico(AnalizadorLexico analizadorLexico) {
    this.analizadorLexico = analizadorLexico;
}

public void setAllow(ParserVal tipo1, ParserVal tipo2){
    if (((Token)(tipo1.obj)).getLexema().equals("float") && ((Token)(tipo2.obj)).getLexema().equals("integer")) {
        this.allowFloatToInt = true;
    }
}

public boolean conversionAllowPermitida() {
    return this.allowFloatToInt;
}


