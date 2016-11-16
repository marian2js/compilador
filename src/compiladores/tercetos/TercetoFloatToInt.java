package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoFloatToInt extends Terceto{

    public TercetoFloatToInt(String operacion, Objeto operando1, Objeto operando2) {
        super(operacion, operando1, operando2);
    }

    @Override
    public String getAssembler() {
        return "FIST " + getOperando1().getValor() + "\n" +
               "FSTP " + this.getValor();
    }
}
