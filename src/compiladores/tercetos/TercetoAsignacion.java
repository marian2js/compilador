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
            return getOperando2().getAssemblerInit() +
                   "MOV BX, " + getOperando2().getValor() + "\n" +
                    getOperando1().getAssemblerInit() +
                   "MOV " + getOperando1().getValor() + ", BX\n";
        }
        return getOperando2().getAssemblerInit() +
               "FLD " + getOperando2().getValor() + "\n" +
               getOperando1().getAssemblerInit() +
               "FSTP " + getOperando1().getValor() + "\n";
    }
}
