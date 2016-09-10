package compiladores;

public class PalabraReservada extends Token {

    public PalabraReservada(String lexema) {
        super(lexema);
    }

    @Override
    boolean esReservada() {
        return true;
    }
   
}


