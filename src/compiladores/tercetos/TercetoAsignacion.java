package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoAsignacion extends Terceto {
    public TercetoAsignacion(Objeto operando1, Objeto operando2) {
        super(":=", operando1, operando2);
    }
}
