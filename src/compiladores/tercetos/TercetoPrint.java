package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoPrint extends Terceto {


    public TercetoPrint(Objeto operando1) {
        super("print", operando1, null);
    }

    public String getAssembler() {
        return "";
    }
}
