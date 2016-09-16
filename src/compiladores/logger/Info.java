package compiladores.logger;

public class Info extends Mensaje {

    public Info(String mensage, int linea, String tipo) {
        super(mensage, linea, tipo, Mensaje.INFO_LEVEL);
    }

}