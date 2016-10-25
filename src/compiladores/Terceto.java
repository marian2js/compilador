package compiladores;

public class Terceto extends Objeto {

    public Terceto(String operacion, Objeto operando1, Objeto operando2) {
        this.atributos.put("operacion", operacion);
        this.atributos.put("operando1", operando1);
        this.atributos.put("operando2", operando2);
    }

    public String getOperacion() {
        return (String)this.atributos.get("operacion");
    }

    public Objeto getOperando1() {
        return (Objeto)this.atributos.get("operando1");
    }

    public Objeto getOperando2() {
        return (Objeto)this.atributos.get("operando2");
    }
}
