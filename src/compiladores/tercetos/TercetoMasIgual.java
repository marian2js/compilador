package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoMasIgual extends Terceto {
    public TercetoMasIgual(Objeto operando1, Objeto operando2) {
        super(":=", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if (getOperando1().getTipo().equals("integer")) {
            return "MOV BX, " + getOperando2().getValor() + "\n" +
                   "ADD BX, " + getOperando1().getValor() + "\n" +
                   "MOV " + getOperando1().getValor() + ", BX\n";
        }
        return "";
    }
}
