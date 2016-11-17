package compiladores.tercetos;

import compiladores.Assembler;
import compiladores.Objeto;
import compiladores.Token;

public class TercetoMatrix extends Terceto {
    public static int indexId = 0;
    private Double offset;
    private int index;

    public TercetoMatrix(Objeto operando1, Double offset) {
        super("matrix", operando1, null);
        this.offset = offset;
        index = indexId;
        indexId++;
    }

    @Override
    public String getAssembler() {
        return "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
               "ADD CX, " + offset.toString().replace(".0", "") + "\n" +
               "MOV @index" + index + ", ECX\n";
    }

    @Override
    public String getValor() {
        return "sword ptr[@index" + index + "]";
    }

    @Override
    public String getTipo() {
        return getOperando1().getTipo();
    }
}
