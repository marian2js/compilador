package compiladores.tercetos;

import compiladores.Objeto;
import compiladores.Terceto;

public class TercetoPrint extends Terceto {


    public TercetoPrint(Objeto operando1) {
        super("print", operando1, null);
    }

    public String getAssembler() {
        return "";
    }
}
