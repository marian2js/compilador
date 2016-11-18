package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoMasIgual extends Terceto {
    public TercetoMasIgual(Objeto operando1, Objeto operando2) {
        super(":=", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if (getOperando1().getTipo().equals("integer")) {
            return getOperando2().getAssemblerInit() +
                   "MOV BX, " + getOperando2().getValor() + "\n" +
                    getOperando1().getAssemblerInit() +
                   "ADD BX, " + getOperando1().getValor() + "\n" +
                    getOperando1().getAssemblerInit() +
                   "MOV " + getOperando1().getValor() + ", BX\n";
        }
        return "FLD " + getOperando2().getValor() + "\n" +
               "FADD " + getOperando1().getValor() + "\n" +
               "FSTP " + getOperando1().getValor() + "\n";
    }
}
