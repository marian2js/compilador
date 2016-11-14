package compiladores.tercetos;

import compiladores.Objeto;
import compiladores.TablaRegistros;

public class TercetoSuma  extends Terceto {
    public TercetoSuma(Objeto operando1, Objeto operando2) {
        super("+", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        String codigo = "MOV BX, " + getOperando1().getValor() + "\n" +
                        "ADD BX, " + getOperando2().getValor() + "\n" +
                        "MOV " + this.getValor() + ", BX" + "\n";
        return codigo;
    }
}
