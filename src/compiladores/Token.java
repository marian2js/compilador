package compiladores;

public class Token extends Objeto {

    public Token(String lexema, int value) {
        this.atributos.put("lexema", lexema);
        this.atributos.put("value", value);
    }

    public boolean esReservada(){
        return false;
    }

    public int getValue() {
        return (int)this.atributos.get("value");
    }

    @Override
    public String getTipo() {
        if (this.get("tipo") != null) {
            return this.get("tipo").toString();
        } else {
            return "";
        }
    }

    @Override
    public String getValor() {
        return "_"+getLexema();
    }
}
