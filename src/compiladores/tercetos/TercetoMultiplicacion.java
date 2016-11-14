package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoMultiplicacion extends Terceto {
    public TercetoMultiplicacion(Objeto operando1, Objeto operando2) {
        super("*", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if (this.getTipo().equals("integer")) {
            return "MOV AX, " + getOperando1().getValor() + "\n" +
                   "MUL AX, " + getOperando2().getValor() + "\n" +
                   "MOV " + this.getValor() + ", AX" + "\n";
        }
        return "FLD " + getOperando1().getValor() + "\n" +
               "FMUL " + getOperando2().getValor() + "\n" +
               "FSTP " + this.getValor() + "\n";
    }
}
