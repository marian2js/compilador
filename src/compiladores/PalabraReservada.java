package compiladores;

public class PalabraReservada extends Token {

    public PalabraReservada(String lexema, int valor) {
        super(lexema, valor);
    }

    @Override
    public boolean esReservada() {
        return true;
    }
   
}


