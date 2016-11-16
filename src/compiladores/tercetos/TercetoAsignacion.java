package compiladores.tercetos;

import compiladores.Objeto;
import compiladores.sintactico.Parser;

public class TercetoAsignacion extends Terceto {
    private Parser parser;

    public TercetoAsignacion(Objeto operando1, Objeto operando2, Parser p) {
        super(":=", operando1, operando2);
        this.parser = p;
    }

    @Override
    public String getAssembler() {
        if (getOperando1().getTipo().equals("integer")) {
            if (getOperando2().getTipo().equals("float") && parser.conversionAllowPermitida()) {
                Terceto t = new TercetoFloatToInt("ftoi", getOperando2(), null);
                this.setOperando2(t);
            }
            return "MOV BX, " + getOperando2().getValor() + "\n" +
                   "MOV " + getOperando1().getValor() + ", BX\n";
        }
        return "FLD " + getOperando2().getValor() + "\n" +
               "FSTP " + getOperando1().getValor() + "\n";
    }
}
