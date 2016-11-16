package compiladores.tercetos;

import compiladores.Assembler;
import compiladores.Objeto;
import compiladores.Token;

public class TercetoMatrix extends Terceto {
    public static int matId = 0;

    public TercetoMatrix(Objeto operando1, Objeto operando2) {
        super("matrix", operando1, operando2);
    }

   /* public TercetoMatrix(Objeto operando1, Objeto fila, Objeto columna) {
        super("matrix", operando1, null);
        this.fila = fila;
        this.columna = columna;
    }*/

    @Override
    public String getAssembler() {
//        int f = (int)((Token)fila.get("fila")).get("numero");
//        int c = (int)((Token)fila.get("columna")).get("numero");
//        int offset = 0;
        //if (getOperando1().getTipo().equals("integer")) {
            return "MOV BX" + ", OFFSET " + getOperando2().getValor() + "\n" +
                   "ADD BX, " + getOperando1().getValor() + "\n";
        //}
        //return "";
    }

    @Override
    public String getValor() {
        String mat = (String) this.atributos.get("mat");
        if (mat == null) {
            mat = "@mat" + matId;
            this.atributos.put("mat", mat);
            matId++;
            Assembler.listaAuxiliares += mat + " DB ?\n";
        }
        return "OFFSET " + mat;
    }

    @Override
    public String getTipo() {
        return getOperando1().getTipo();
    }
}
