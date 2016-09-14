package compiladores;

public class Token {
    protected String lexema;
    protected int value;
    private boolean reservada;
    
    public Token(String lexema, int value) {
        this.lexema = lexema;
        this.value = value;
        this.reservada = false;
    }

    public boolean esReservada(){
        return this.reservada;
    };

    public String getLexema() {
        return lexema;
    }

    public int getValue() {
        return value;
    }

}
