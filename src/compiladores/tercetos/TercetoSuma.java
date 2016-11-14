package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoSuma  extends Terceto {
    public TercetoSuma(Objeto operando1, Objeto operando2) {
        super("+", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if (this.getTipo().equals("integer")) {
            return "MOV BX, " + getOperando1().getValor() + "\n" +
                   "ADD BX, " + getOperando2().getValor() + "\n" +
                   "MOV " + this.getValor() + ", BX" + "\n";
        }
        return "FLD " + getOperando1().getValor() + "\n" +
               "FADD " + getOperando2().getValor() + "\n" +
               "FSTP " + this.getValor() + "\n";
    }
}