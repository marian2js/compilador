package compiladores;

import java.util.HashMap;

public abstract class Objeto {
    protected HashMap<String, Object> atributos = new HashMap<>();

    public String getLexema() {
        return (String)this.atributos.get("lexema");
    }

    public Object get(String key) {
        return this.atributos.get(key);
    }

    public void set(String key, Object value) {
        this.atributos.put(key, value);
    }

    public abstract String getTipo();
}
