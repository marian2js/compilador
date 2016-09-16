package compiladores.logger;

public class Error extends Mensaje {

    public Error(String mensage, int linea, String tipo) {
        super(mensage, linea, tipo, Mensaje.ERROR_LEVEL);
    }

}
