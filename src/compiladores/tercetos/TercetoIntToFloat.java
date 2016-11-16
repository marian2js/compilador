package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoIntToFloat extends Terceto{
    public TercetoIntToFloat(String operacion, Objeto operando1, Objeto operando2) {
        super(operacion, operando1, operando2);
    }

    @Override
    public String getTipo() {
        return "float";
    }

    @Override
    public String getAssembler() {
        return "FILD " + getOperando1().getValor() + "\n" +
               "FSTP " + this.getValor() + "\n";
    }
}
