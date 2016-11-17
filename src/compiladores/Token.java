package compiladores;

import compiladores.sintactico.ParserTokens;

public class Token extends Objeto {
    public static int cteId = 0;

    public Token(String lexema, int value) {
        this.atributos.put("lexema", lexema);
        this.atributos.put("value", value);
    }

    public boolean esReservada(){
        return false;
    }

    public int getValue() {
        return (int)this.atributos.get("value");
    }

    @Override
    public String getTipo() {
        if (this.get("tipo") != null) {
            return this.get("tipo").toString();
        } else {
            return "";
        }
    }

    @Override
    public String getValor() {
        switch (this.getValue()) {
            case ParserTokens.ID:
                return "_" + getLexema();
            case ParserTokens.CTE_ENTERA:
            case ParserTokens.CTE_FLOAT:
                String cte = (String) this.atributos.get("cte");
                if (cte == null) {
                    cte = "_cte" + cteId;
                    this.atributos.put("cte", cte);
                    cteId++;
                }
                return cte;
            default:
                return getLexema();
        }
    }

    public String getAssemblerInit() {
        return "";
    }
}
