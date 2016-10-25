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

}
