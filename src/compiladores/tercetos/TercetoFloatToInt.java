package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoFloatToInt extends Terceto{

    public TercetoFloatToInt(String operacion, Objeto operando1, Objeto operando2) {
        super(operacion, operando1, operando2);
    }

    @Override
    public String getTipo() {
        return "integer";
    }

    @Override
    public String getAssembler() {
        return "FLD " + this.getOperando1().getValor() + "\n" +
               "FIST " + this.getValor() + "\n" +
               "FILD " + this.getValor() + "\n" +
               "FCOMP\n" +
               "FSTSW AX\n" +
               "SAHF\n" +
               "JNE _label_perdida_conversion\n";
    }
}
