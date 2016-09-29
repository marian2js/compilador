package compiladores.sintactico;

import compiladores.Token;
import java.util.ArrayList;

class ParserHelper {

    static void setTipo(ParserVal tipo, ParserVal tokens) {
        for (ParserVal token : ((ArrayList<ParserVal>) tokens.obj)) {
            ((Token) token.obj).set("tipo", ((Token) tipo.obj).getLexema());
        }
    }

    static ArrayList<ParserVal> cargarListaVariables(ParserVal var, ParserVal tokens) {
        if (tokens == null) {
            tokens = new ParserVal(new ArrayList<Token>());
        }
        ((ArrayList<ParserVal>) tokens.obj).add(var);
        return (ArrayList<ParserVal>)tokens.obj;
    }

}
