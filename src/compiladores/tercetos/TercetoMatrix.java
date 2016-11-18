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
        if(getOperando1().getTipo().equals("integer")){
            String code = "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
                    "MOV BX, " + getOperando2().getValor() + "\n" +
                    "CMP BX, _lim" + getOperando1().getValor() + "\n" +
                    "JG _label_error_indice\n";
            if (getOperando1().get("anotacion") != null && getOperando1().get("anotacion").equals("/#@1")) {
                code += "MOV BX, " + fila.getValor() + "\n" +
                        "CMP BX, 0\n" +
                        "JLE _label_error_indice\n" +
                        "MOV BX, " + columna.getValor() + "\n" +
                        "CMP BX, 0\n" +
                        "JLE _label_error_indice\n";
            }
               code += "ADD CX, " + getOperando2().getValor() + "\n";
            return code;
        }
        else{
            return "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
                    "FLD " + getOperando2().getValor() + "\n" +
                    "FLD _lim" + getOperando1().getValor() + "\n" +
                    "FCOMP\n" +
                    "FSTSW AX\n" +
                    "SAHF\n" +
                    "JG _label_error_indice\n" +
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
        if(getOperando1().getTipo().equals("integer")){
            return "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
                    "ADD CX, " + getOperando2().getValor() + "\n";
        }
        else{
            return "MOV ECX, OFFSET " + getOperando1().getValor() + "\n" +
                    "ADD CX, " + getOperando2().getValor() + "\n";
        }
    }
}
