package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoResta extends Terceto {
    public TercetoResta(Objeto operando1, Objeto operando2) {
        super("-", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if (this.getTipo().equals("integer")) {
            return getOperando1().getAssemblerInit() +
                   "MOV BX, " + getOperando1().getValor() + "\n" +
                   getOperando2().getAssemblerInit() +
                   "SUB BX, " + getOperando2().getValor() + "\n" +
                   "MOV " + this.getValor() + ", BX" + "\n";
        }
        return getOperando1().getAssemblerInit() +
               "FLD " + getOperando1().getValor() + "\n" +
                getOperando2().getAssemblerInit() +
               "FSUB " + getOperando2().getValor() + "\n" +
               "FSTP " + this.getValor() + "\n";
    }
}
