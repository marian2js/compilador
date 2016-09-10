package compiladores;

public class Identificador extends Token {
    private String tipo;
    private Object valor;

    public Identificador(String lexema) {
        super(lexema);
    }
    
    @Override
    boolean esReservada() {
        return false;
    }
}
