package compiladores;

import java.util.HashMap;

public class Token {
    private HashMap<String, Object> atributos = new HashMap<>();

    public Token(String lexema, int value) {
        this.atributos.put("lexema", lexema);
        this.atributos.put("value", value);
    }

    public boolean esReservada(){
        return false;
    }

    public String getLexema() {
        return (String)this.atributos.get("lexema");
    }

    public int getValue() {
        return (int)this.atributos.get("value");
    }

    public Object get(String key) {
        return this.atributos.get(key);
    }

    public void set(String key, Object value) {
        this.atributos.put(key, value);
    }

}
