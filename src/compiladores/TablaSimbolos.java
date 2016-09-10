package compiladores;

import java.util.ArrayList;

public class TablaSimbolos {
    private ArrayList<Token> tokens = new ArrayList<>();
    
    public boolean esPalabraReservada(String lexema) {
        for(Token t : tokens) {
            if (t.esReservada() && t.getLexema().equals(lexema)) {
                return true;
            }
        }
        return false;
    }
    
    public Token get(String lexema) {
        for(Token t : tokens) {
            if (t.getLexema().equals(lexema)) {
                return t;
            }
        }
        return null;
    }
    
    public void addSimbolo(Token t) {
        tokens.add(t);
    }
}
