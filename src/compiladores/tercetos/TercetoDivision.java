package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoDivision extends Terceto {
    public TercetoDivision(Objeto operando1, Objeto operando2) {
        super("/", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if (this.getTipo().equals("integer")) {
            return getOperando2().getAssemblerInit() +
                   "MOV BX, " + getOperando2().getValor() + "\n" +
                   "CMP BX, 0\n" +
                   "JE _label_div_cero\n" +
                   "MOV DX, 0\n" +
                   getOperando1().getAssemblerInit() +
                   "MOV AX, " + getOperando1().getValor() + "\n" +
                   "CWD\n" +
                    getOperando2().getAssemblerInit() +
                   "IDIV " + getOperando2().getValor() + "\n" +
                   "MOV " + this.getValor() + ", AX" + "\n";
        }
        return getOperando2().getAssemblerInit() +
               "FLD " + getOperando2().getValor() + "\n" +
               "FTST\n" +
               "FSTSW AX\n" +
               "SAHF\n" +
               "JE _label_div_cero\n" +
                getOperando1().getAssemblerInit() +
               "FLD " + getOperando1().getValor() + "\n" +
               "FDIV ST, ST(1)\n" +
               "FSTP " + this.getValor() + "\n";
    }
}
