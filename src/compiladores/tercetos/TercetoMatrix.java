package compiladores.tercetos;

import compiladores.Objeto;

public class TercetoMatrix extends Terceto {
    private Objeto fila;
    private Objeto columna;

    public TercetoMatrix(Objeto operando1, Objeto operando2, Objeto fila, Objeto columna) {
        super("matrix", operando1, operando2);
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public String getAssembler() {
        boolean lim0 = getOperando1().get("anotacion") != null && getOperando1().get("anotacion").equals("/#@1");
        String comp = lim0 ? "JLE" : "JL";
        return
                "MOV CX, " + getOperando2().getValor() + "\n" +
                "CMP CX, _lim" + getOperando1().getValor() + "\n" +
                "JG _label_error_indice\n" +
                "MOV CX, " + fila.getValor() + "\n" +
                "CMP CX, 0\n" +
                comp + " _label_error_indice\n" +
                "MOV CX, " + columna.getValor() + "\n" +
                "CMP CX, 0\n" +
                comp + " _label_error_indice\n" +
                "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
                "ADD CX, " + getOperando2().getValor() + "\n";
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
