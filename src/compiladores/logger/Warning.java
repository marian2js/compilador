package compiladores.logger;

public class Warning extends Mensaje {

    public Warning(String mensage, int linea, String tipo) {
        super(mensage, linea, tipo, Mensaje.WARNING_LEVEL);
    }

}
