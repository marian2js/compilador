%{
import compiladores.lexico.*;
import compiladores.*;
import compiladores.logger.Logger;
import compiladores.logger.Info;
import compiladores.logger.Error;
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
            tipo lista_de_variables {ParserHelper.cargarVars(this, $1, $2); Logger.getLog().addMensaje(new Info("Lista de declaraciones de variables detectada", yylval.ival, "Sintactico"));}
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
                    tipo MATRIX ID '['CTE_ENTERA']''['CTE_ENTERA']' inicializacion ';' ANOTACION {Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", $1.ival, "Sintactico"));ParserHelper.cargarMatriz(this,$3,$1,$5,$8,$12);}
                    | tipo MATRIX ID '['CTE_ENTERA']''['CTE_ENTERA']' inicializacion ';' {Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", $1.ival, "Sintactico"));ParserHelper.cargarMatriz(this,$3,$1,$5,$8);}
                    | tipo MATRIX ID '['CTE_ENTERA']''['CTE_ENTERA']' ';' ANOTACION {Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", $1.ival, "Sintactico"));ParserHelper.cargarMatriz(this,$3,$1,$5,$8,$11);}
                    | tipo MATRIX ID '['CTE_ENTERA']''['CTE_ENTERA']' ';' {Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", $1.ival, "Sintactico"));ParserHelper.cargarMatriz(this,$3,$1,$5,$8);}
;
inicializacion:
               '{' columna '}';
columna:
        fila
        | columna';' fila;
fila:
     CTE_FLOAT
     | CTE_ENTERA
     | fila ',' CTE_ENTERA
     | fila',' CTE_FLOAT;
declaracion_allow:
                  ALLOW tipo TO tipo;
expresion:
          expresion '+' termino {$$.obj = new Terceto("+", (Objeto) $1.obj, (Objeto) $3.obj);}
          | expresion '-' termino {$$.obj = new Terceto("-", (Objeto) $1.obj, (Objeto) $3.obj);}
          | termino
          | expresion '+' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          | expresion '-' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          |'+' termino {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          |'-' termino {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

termino:
        termino '*' factor {$$.obj = new Terceto("*", (Objeto) $1.obj, (Objeto) $3.obj);}
        | termino '/' factor {$$.obj = new Terceto("/", (Objeto) $1.obj, (Objeto) $3.obj);}
        | factor
        | termino '*' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        | termino '/' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        |'*' factor {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        |'/' factor {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};;
factor:
       ID
       |ID '['expresion']''['expresion']'
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
            ID ASIGNACION expresion {Logger.getLog().addMensaje(new Info("Asignacion detectada", yylval.ival, "Sintactico"));}
            | ID MASIGUAL expresion {Logger.getLog().addMensaje(new Info("Asignacion más-igual detectada", yylval.ival, "Sintactico"));}
            | ID '['expresion']''['expresion']' ASIGNACION expresion {Logger.getLog().addMensaje(new Info("Asignacion detectada", yylval.ival, "Sintactico"));}
            | ID '['expresion']''['expresion']' MASIGUAL expresion; {Logger.getLog().addMensaje(new Info("Asignacion más-igual detectada", yylval.ival, "Sintactico"));}
            | error ASIGNACION expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | error MASIGUAL expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID error expresion {Error e = new Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID ASIGNACION {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID MASIGUAL {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

asignacion_for:
                ID ASIGNACION expresion;
                | error ASIGNACION expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                | ID error expresion {Error e = new Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                | ID ASIGNACION {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

bloque_if:
           condicion_if cuerpo_if;
condicion_if:
              IF '('condicion')'
              |IF error {Error e = new Error("Falta condicion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
cuerpo_if: bloque_de_sentencias ENDIF
           | sentencia ENDIF
           | bloque_de_sentencias ELSE bloque_de_sentencias ENDIF
           | sentencia ELSE sentencia ENDIF
           | bloque_de_sentencias ELSE sentencia ENDIF
           | sentencia ELSE bloque_de_sentencias ENDIF;
condicion:
           expresion comparador expresion
           | comparador expresion {Error e = new Error("Falta termino izquierdo en la comparacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
           | expresion comparador {Error e = new Error("Falta termino derecho en la comparacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

comparador:
            '='
            | '<'
            | '>'
            | COMPARADOR;
bloque_for:
            FOR'('asignacion_for';'condicion';'asignacion')' bloque_de_sentencias
            | FOR'('asignacion_for';'condicion';'asignacion')' sentencia;


bloque_print:
              PRINT'('CADENA')' {$$.obj = new Terceto("print",(Objeto)$3.obj, null);}
              | PRINT CADENA')' {Error e = new Error("Falta (",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
              | PRINT'('CADENA {Error e = new Error("Falta )",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
              | PRINT'('error')' {Error e = new Error("Falta cadena a imprimir",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
%%

/* Parser.java */
private AnalizadorLexico analizadorLexico;
public static ArrayList<Terceto> tercetos = new ArrayList<>();

public Parser(File file) {
    analizadorLexico = new AnalizadorLexico(file);
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


