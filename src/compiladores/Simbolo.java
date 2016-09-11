package compiladores;

public class Simbolo extends Token {
    private String tipo;
    
    public Simbolo(String lexema) {
        super(lexema);
    }

    @Override
    boolean esReservada() {
        return false;
    }
    
}
