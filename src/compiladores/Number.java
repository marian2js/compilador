package compiladores;

public class Number extends Token {
    private String tipo;

    public Number(String lexema) {
        super(lexema);
    }
    
    @Override
    boolean esReservada() {
        return false;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
