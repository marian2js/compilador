package compiladores.tercetos;

import compiladores.Objeto;
import compiladores.sintactico.Parser;

public class Terceto extends Objeto {

    public Terceto(String operacion, Objeto operando1, Objeto operando2) {
        this.atributos.put("operacion", operacion);
        this.atributos.put("operando1", operando1);
        this.atributos.put("operando2", operando2);
        Parser.tercetos.add(this);
    }

    public String getOperacion() {
        return (String)this.atributos.get("operacion");
    }

    public Objeto getOperando1() {
        return (Objeto)this.atributos.get("operando1");
    }

    public Objeto getOperando2() {
        return (Objeto)this.atributos.get("operando2");
    }
    
    public void setOperando2(Objeto operando2) {
        this.atributos.put("operando2",operando2);
    }

    @Override
    public String getTipo() {
        String tipoOp1 = this.getOperando1().getTipo();
        String tipoOp2 = this.getOperando2().getTipo();
        if (tipoOp2 == null) {
            return tipoOp1;
        } else if (tipoOp1.equals("integer") && tipoOp2.equals("integer")) {
            return "integer";
        } else {
            return "float";
        }
    }

    public String getAssembler() {
        return "";
    };

    @Override
    public String getValor() {
        return "";
    }
}
