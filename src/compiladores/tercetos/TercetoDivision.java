package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoDivision extends Terceto {
    public TercetoDivision(Objeto operando1, Objeto operando2) {
        super("/", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if (this.getTipo().equals("integer")) {
            return "MOV DX, 0\n" +
                   "MOV AX, " + getOperando1().getValor() + "\n" +
                   "CWD\n" +
                   "IDIV " + getOperando2().getValor() + "\n" +
                   "MOV " + this.getValor() + ", AX" + "\n";
        }
        return "FLD " + getOperando1().getValor() + "\n" +
               "FDIV " + getOperando2().getValor() + "\n" +
               "FSTP " + this.getValor() + "\n";
    }
}
