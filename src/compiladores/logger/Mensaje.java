package compiladores.logger;

public abstract class Mensaje {
    public static String INFO_LEVEL = "INFO";
    public static String WARNING_LEVEL = "WARNING";
    public static String ERROR_LEVEL = "ERROR";

    private String mensaje;
    private int linea;
    private String tipo;
    private String level;

    public Mensaje(String mensage, int linea, String tipo, String level) {
        this.mensaje = mensage;
        this.linea = linea;
        this.tipo = tipo;
        this.level = level;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
