package compiladores.tercetos;

import compiladores.Assembler;
import compiladores.Objeto;
import compiladores.Token;

public class TercetoMatrix extends Terceto {

    public TercetoMatrix(Objeto operando1, Objeto operando2) {
        super("matrix", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        return "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
               "ADD CX, " + getOperando2().getValor() + "\n";
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
