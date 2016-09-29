%{
import compiladores.lexico.*;
import compiladores.*;
import compiladores.logger.Logger;
import compiladores.logger.Info;
import compiladores.logger.Error;
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
            tipo lista_de_variables {ParserHelper.setTipo($1, $2); Logger.getLog().addMensaje(new Info("Lista de declaraciones de variables detectada", yylval.ival, "Sintactico"));}
            | declaracion_matrix {Logger.getLog().addMensaje(new Info("Declaración de matriz detectada", yylval.ival, "Sintactico"));}
            | declaracion_allow; {Logger.getLog().addMensaje(new Info("Declaración de tipo allow detectada", yylval.ival, "Sintactico"));}
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
          | expresion '+' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          | expresion '-' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          |'+' termino {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
          |'-' termino {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};

termino:
        termino '*' factor
        | termino '/' factor
        | factor
        | termino '*' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        | termino '/' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        |'*' factor {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
        |'/' factor {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};;
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
                  | expresion_entera '+' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                  | expresion_entera '-' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                  |'+' termino_entero {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                  |'-' termino_entero {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
termino_entero:
                termino_entero '*' factor_entero
                | termino_entero '/' factor_entero
                | factor_entero
                | termino_entero '*' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                | termino_entero '/' {Error e = new Error("Falta operador derecho",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                |'*' factor_entero {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                |'/' factor_entero {Error e = new Error("Falta operador izquierdo",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
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
           | bloque_if';' {Logger.getLog().addMensaje(new Info("Bloque 'if' detectado", $1.ival, "Sintactico"));}
           | bloque_for {Logger.getLog().addMensaje(new Info("Bloque 'for' detectado", $1.ival, "Sintactico"));}
           | bloque_print';'; {Logger.getLog().addMensaje(new Info("Bloque 'print' detectado",yylval.ival,"Sintactico"));}
asignacion:
            ID ASIGNACION expresion {Logger.getLog().addMensaje(new Info("Asignacion detectada", yylval.ival, "Sintactico"));}
            | ID MASIGUAL expresion {Logger.getLog().addMensaje(new Info("Asignacion más-igual detectada", yylval.ival, "Sintactico"));}
            | ID '['expresion_entera']''['expresion_entera']' ASIGNACION expresion {Logger.getLog().addMensaje(new Info("Asignacion detectada", yylval.ival, "Sintactico"));}
            | ID '['expresion_entera']''['expresion_entera']' MASIGUAL expresion; {Logger.getLog().addMensaje(new Info("Asignacion más-igual detectada", yylval.ival, "Sintactico"));}
            | error ASIGNACION expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | error MASIGUAL expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID error expresion {Error e = new Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID ASIGNACION {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
            | ID MASIGUAL {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
asignacion_for:
                ID ASIGNACION expresion_entera;
                | error ASIGNACION expresion {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                | ID error expresion_entera {Error e = new Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                | ID ASIGNACION {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
bloque_if:
           condicion_if cuerpo_if;
condicion_if:
              IF '('condicion')'
              |IF error {Error e = new Error("Falta condicion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
cuerpo_if: bloque_de_sentencias ENDIF
           | bloque_de_sentencias ELSE bloque_de_sentencias ENDIF;
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
            FOR'('asignacion_for';'condicion';'actualizacion_de_vble_control')' bloque_de_sentencias;
actualizacion_de_vble_control:
                               ID MASIGUAL factor_entero
                               | error MASIGUAL factor_entero {Error e = new Error("Falta variable a izquierda de la asigancion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                               | ID error factor_entero {Error e = new Error("Falta operador de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
                               | ID MASIGUAL {Error e = new Error("Falta termino derecho de la asignacion",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
bloque_print:
              PRINT'('CADENA')'
              | PRINT CADENA')' {Error e = new Error("Falta (",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
              | PRINT'('CADENA {Error e = new Error("Falta )",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);}
              | PRINT'('error')' {Error e = new Error("Falta cadena a imprimir",yylval.ival,"Sintactico");Logger.getLog().addMensaje(e);};
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
    yylval = new ParserVal(token);
    yylval.ival = analizadorLexico.getLinea();
    System.out.println("=== Value: " + val + " ===");
    System.out.println("=== Lexema: " + token.getLexema() + " ===");
    return val;
}


private void yyerror(String error) {
    System.out.println("Error: " + error);
}