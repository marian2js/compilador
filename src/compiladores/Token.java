package compiladores;

public abstract class Token {
    protected String lexema;
    
    public Token(String lexema) {
        this.lexema = lexema;
    }

    abstract boolean esReservada();

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }
}
