package compiladores.tercetos;

import compiladores.Objeto;
import compiladores.sintactico.Parser;

public class TercetoAsignacion extends Terceto {
    private Parser parser;

    public TercetoAsignacion(Objeto operando1, Objeto operando2) {
        super(":=", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if (getOperando1().getTipo().equals("integer")) {
            return "MOV BX, " + getOperando2().getValor() + "\n" +
                   "MOV " + getOperando1().getValor() + ", BX\n";
        }
        return "FLD " + getOperando2().getValor() + "\n" +
               "FSTP " + getOperando1().getValor() + "\n";
    }
}
