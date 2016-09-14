%{
import compiladores.lexico.*;
import compiladores.*;
import java.io.File;
%}

%token ID CTE CTE_ENTERA IF ELSE ENDIF FOR PRINT INTEGER FLOAT MATRIX CADENA ANOTACION ALLOW TO ASIGNACION MASIGUAL COMPARADOR
%left '-'
%left '+'
%left '*'
%left '/'

%start programa

/* Gramatica */
%%
programa: grupo_declaraciones bloque_de_sentencias;
grupo_declaraciones:
                    declaracion';'
                    | declaracion';'grupo_declaraciones;
declaracion:
            tipo lista_de_variables
            | declaracion_matrix
            | declaracion_allow;
lista_de_variables:
                   ID
                   | ID','lista_de_variables;
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
     CTE
     | fila',' CTE;
declaracion_allow:
                  ALLOW tipo TO tipo;

expresion:
          expresion '+' termino
          | expresion '-' termino
          | termino
          | '('expresion')';
termino:
        termino '*' factor
        | termino '/' factor
        | factor;
factor:
       ID
       | CTE;

expresion_entera:
                  expresion_entera '+' termino_entero
                  | expresion_entera '-' termino_entero
                  | termino_entero
                  | '('expresion_entera')';
termino_entero:
                termino_entero '*' factor_entero
                | termino_entero '/' factor_entero
                | factor_entero;
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
           | bloque_if
           | bloque_for
           | bloque_print;
asignacion:
            ID ASIGNACION expresion
            | ID MASIGUAL expresion;
asignacion_for:
                ID ASIGNACION expresion_entera;

bloque_if:
           IF'('condicion')' bloque_de_sentencias ENDIF
           | IF'('condicion')' bloque_de_sentencias ELSE bloque_de_sentencias ENDIF;
condicion:
           expresion COMPARADOR expresion;
bloque_for:
            FOR'('asignacion_for';'condicion';'actualizacion_de_vble_control')' bloque_de_sentencias;
actualizacion_de_vble_control:
                               ID MASIGUAL factor_entero;
bloque_print:
              PRINT'('CADENA')';
%%
private AnalizadorLexico analizadorLexico;
/* Parser.java */
public Parser(File file) {
    analizadorLexico = new AnalizadorLexico(file);
    analizadorLexico.yylex();
}

private int yylex() {
    Token token = analizadorLexico.yylex();
    
    return 0;
}


private void yyerror(String error) {
    // TODO
}