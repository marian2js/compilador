package compiladores;

import compiladores.lexico.AnalizadorLexico;

public class Compiladores {

    public static void main(String[] args) {
        AnalizadorLexico aLexico = new AnalizadorLexico();
        //Agregar Palabras Reservadas
        Token prIf = new PalabraReservada("if");
        Token prElse = new PalabraReservada("else");
        Token prEndif = new PalabraReservada("endif");
        Token prPrint = new PalabraReservada("print");
        Token prInteger = new PalabraReservada("integer");
        Token prMatrix = new PalabraReservada("matrix");
        Token prFor = new PalabraReservada("for");
        aLexico.getTablaSimbolos().addSimbolo(prIf);
        aLexico.getTablaSimbolos().addSimbolo(prElse);
        aLexico.getTablaSimbolos().addSimbolo(prEndif);
        aLexico.getTablaSimbolos().addSimbolo(prPrint);
        aLexico.getTablaSimbolos().addSimbolo(prInteger);
        aLexico.getTablaSimbolos().addSimbolo(prMatrix);
        aLexico.getTablaSimbolos().addSimbolo(prFor);
        aLexico.ejecutar("");
    }
    
}
