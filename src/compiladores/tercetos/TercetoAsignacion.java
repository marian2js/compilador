package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoAsignacion extends Terceto {
    public TercetoAsignacion(Objeto operando1, Objeto operando2) {
        super(":=", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        String codigo = "MOV BX, " + getOperando2().getValor() + "\n" +
                        "MOV " + getOperando1().getValor() + ", BX\n";
        return codigo;
    }
}
