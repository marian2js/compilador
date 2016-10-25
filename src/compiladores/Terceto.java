package compiladores;

public class Terceto extends Objeto {
    public String getOperando() {
        return (String)this.atributos.get("operando");
    }

    public Objeto getOperacion1() {
        return (Objeto)this.atributos.get("operacion1");
    }

    public Objeto getOperacion2() {
        return (Objeto)this.atributos.get("operacion2");
    }
}
