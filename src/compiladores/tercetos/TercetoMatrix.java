package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoMatrix extends Terceto {

    public TercetoMatrix(Objeto operando1, Objeto operando2) {
        super("matrix", operando1, operando2);
    }

    @Override
    public String getAssembler() {
        if(getOperando1().getTipo().equals("integer")){
            return "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
               "ADD CX, " + getOperando2().getValor() + "\n";
        }
        else{
            return "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
               "ADD CX, " + getOperando2().getValor() + "\n";
        }
    }

    @Override
    public String getValor() {
        if(getOperando1().getTipo().equals("integer")){
            return "sword ptr[ECX]";
        }
        else{
            return "real4 ptr[ECX]";
        }
    }

    @Override
    public String getTipo() {
        return getOperando1().getTipo();
    }

    @Override
    public String getAssemblerInit() {
        return this.getAssembler();
    }
}
