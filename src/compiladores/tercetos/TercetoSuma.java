package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoSuma  extends Terceto {
    public TercetoSuma(Objeto operando1, Objeto operando2) {
        super("+", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        String asm = "";
        if (this.getTipo().equals("integer")) {
            asm += getOperando1().getAssemblerInit() +
                   "MOV BX, " + getOperando1().getValor() + "\n" +
                   getOperando2().getAssemblerInit() +
                   "ADD BX, " + getOperando2().getValor() + "\n" +
                   "MOV " + this.getValor() + ", BX" + "\n";
        } else {
            asm += "FLD " + getOperando1().getValor() + "\n" +
                   "FADD " + getOperando2().getValor() + "\n" +
                   "FSTP " + this.getValor() + "\n";
        }
        return asm;
    }
}
