package compiladores.tercetos;

import compiladores.Assembler;
import compiladores.Objeto;
import compiladores.Token;

public class TercetoMatrix extends Terceto {
    private Double offset;

    public TercetoMatrix(Objeto operando1, Double offset) {
        super("matrix", operando1, null);
        this.offset = offset;
    }

    @Override
    public String getAssembler() {
        return "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
               "ADD CX, " + offset.toString().replace(".0", "") + "\n";
    }

    @Override
    public String getValor() {
        return "sword ptr[ECX]";
    }

    @Override
    public String getTipo() {
        return getOperando1().getTipo();
    }
}
