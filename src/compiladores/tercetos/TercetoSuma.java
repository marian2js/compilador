package compiladores.tercetos;

import compiladores.Objeto;
import compiladores.TablaRegistros;

public class TercetoSuma  extends Terceto {
    public TercetoSuma(Objeto operando1, Objeto operando2) {
        super("+", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        String registro = TablaRegistros.getInstance().getRegistro();
        String codigo = "MOV " + registro + ", " + getOperando1().getValor() + "\n" +
                        "ADD " + registro + ", " + getOperando2().getValor();
        this.set("registro", registro);
        return codigo;
    }
}
