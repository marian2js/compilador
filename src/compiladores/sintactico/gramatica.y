%{
import compiladores.lexico.*;
import compiladores.logger.*;
import compiladores.*;
import java.io.File;
%}

%token ID CTE_ENTERA CTE_FLOAT IF ELSE ENDIF FOR PRINT INTEGER FLOAT MATRIX CADENA ANOTACION ALLOW TO ASIGNACION MASIGUAL COMPARADOR
%left '-'
%left '+'
%left '*'
%left '/'

%start inicio_programa

/* Gramatica */
%%
inicio_programa: ID programa;
programa: grupo_declaraciones bloque_de_sentencias;
grupo_declaraciones:
                    declaracion';'
                    | declaracion';'grupo_declaraciones;
declaracion:
            tipo lista_de_variables
            | declaracion_matrix
            | declaracion_allow;
            | error lista_de_variables {compiladores.logger.Error e = new compiladores.logger.Error("Error de tipo invalido",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
            | tipo error; {compiladores.logger.Error e = new compiladores.logger.Error("Falta declarar variables",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
lista_de_variables:
                   ID
                   | ID ',' lista_de_variables
                   | ID ',' error {compiladores.logger.Error e = new compiladores.logger.Error("Falta declarar variables luego de ,",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};
tipo:
     INTEGER
     | FLOAT;
declaracion_matrix:
                    tipo MATRIX ID '['CTE_ENTERA']''['CTE_ENTERA']' inicializacion ANOTACION
                    | tipo MATRIX ID '['CTE_ENTERA']''['CTE_ENTERA']' ANOTACION;
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
          expresion '+' termino
          | expresion '-' termino
          | termino
          | '('expresion')'
          | expresion '+' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador derecho",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
          | expresion '-' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador derecho",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
          |'+' termino {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
          |'-' termino {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};

termino:
        termino '*' factor
        | termino '/' factor
        | factor
        | termino '*' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador derecho",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
        | termino '/' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador derecho",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
        |'*' factor {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
        |'/' factor {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};;
factor:
       ID
       |ID '['expresion_entera']''['expresion_entera']'
       | CTE_ENTERA
       | CTE_FLOAT;

expresion_entera:
                  expresion_entera '+' termino_entero
                  | expresion_entera '-' termino_entero
                  | termino_entero
                  | '('expresion_entera')'
                  | expresion_entera '+' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador derecho",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                  | expresion_entera '-' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador derecho",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                  |'+' termino_entero {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                  |'-' termino_entero {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};
termino_entero:
                termino_entero '*' factor_entero
                | termino_entero '/' factor_entero
                | factor_entero
                | termino_entero '*' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador derecho",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                | termino_entero '/' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador derecho",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                |'*' factor_entero {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                |'/' factor_entero {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};
factor_entero:
               ID
               | CTE_ENTERA;

bloque_de_sentencias:
                      '{' grupo_sentencias '}';
grupo_sentencias:
                  sentencia
                  | sentencia grupo_sentencias;
sentencia:
           asignacion';'
           | bloque_if';'
           | bloque_for
           | bloque_print';';
asignacion:
            ID ASIGNACION expresion
            | ID MASIGUAL expresion
            | ID '['expresion_entera']''['expresion_entera']' ASIGNACION expresion
            | ID '['expresion_entera']''['expresion_entera']' MASIGUAL expresion;
            | error ASIGNACION expresion {compiladores.logger.Error e = new compiladores.logger.Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
            | error MASIGUAL expresion {compiladores.logger.Error e = new compiladores.logger.Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
            | ID error expresion {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
            | ID ASIGNACION {compiladores.logger.Error e = new compiladores.logger.Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
            | ID MASIGUAL {compiladores.logger.Error e = new compiladores.logger.Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};
asignacion_for:
                ID ASIGNACION expresion_entera;
                | error ASIGNACION expresion {compiladores.logger.Error e = new compiladores.logger.Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                | ID error expresion_entera {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                | ID ASIGNACION {compiladores.logger.Error e = new compiladores.logger.Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};
bloque_if:
           condicion_if cuerpo_if;
condicion_if:
              IF '('condicion')'
              |IF error {compiladores.logger.Error e = new compiladores.logger.Error("Falta condicion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};
cuerpo_if: bloque_de_sentencias ENDIF
           | bloque_de_sentencias ELSE bloque_de_sentencias ENDIF;
condicion:
           expresion comparador expresion
           | comparador expresion {compiladores.logger.Error e = new compiladores.logger.Error("Falta termino izquierdo en la comparacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
           | expresion comparador {compiladores.logger.Error e = new compiladores.logger.Error("Falta termino derecho en la comparacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};

comparador:
            '='
            | '<'
            | '>'
            | COMPARADOR;
bloque_for:
            FOR'('asignacion_for';'condicion';'actualizacion_de_vble_control')' bloque_de_sentencias;
actualizacion_de_vble_control:
                               ID MASIGUAL factor_entero
                               | error MASIGUAL factor_entero {compiladores.logger.Error e = new compiladores.logger.Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                               | ID error factor_entero {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
                               | ID MASIGUAL {compiladores.logger.Error e = new compiladores.logger.Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};
bloque_print:
              PRINT'('CADENA')'
              | PRINT CADENA')' {compiladores.logger.Error e = new compiladores.logger.Error("Falta (",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
              | PRINT'('CADENA {compiladores.logger.Error e = new compiladores.logger.Error("Falta )",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);}
              | PRINT'('error')' {compiladores.logger.Error e = new compiladores.logger.Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger log = Logger.getLog();log.addMensaje(e);};
%%

/* Parser.java */
private AnalizadorLexico analizadorLexico;

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
    yylval = new ParserVal(token.getLexema());
    yylval.ival = analizadorLexico.getLinea();
    System.out.println("=== Value: " + val + " ===");
    System.out.println("=== Lexema: " + token.getLexema() + " ===");
    return val;
}


private void yyerror(String error) {
    System.out.println("Error: " + error);
}
