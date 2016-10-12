package compiladores.sintactico;

import compiladores.TablaSimbolos;
import compiladores.Token;
import java.util.ArrayList;

class ParserHelper {

    static void cargarVars(Parser parser, ParserVal tipo, ParserVal tokens) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        for (ParserVal tokenVal : ((ArrayList<ParserVal>) tokens.obj)) {
            Token token = ((Token) tokenVal.obj);
            token.set("tipo", ((Token) tipo.obj).getLexema());
            token.set("lexema", "var@" + token.getLexema());
            Token tokenTabla = ts.get(token.getLexema());
            if (tokenTabla == null) {
                ts.addSimbolo(token);
            }
        }
    }

    static void cargarMatriz(Parser parser, ParserVal tipo, ParserVal tokenVal) {
        TablaSimbolos ts = parser.getAnalizadorLexico().getTablaSimbolos();
        Token token = ((Token) tokenVal.obj);
        token.set("tipo", ((Token) tipo.obj).getLexema());
        token.set("lexema", "mat@" + token.getLexema());
        Token tokenTabla = ts.get(token.getLexema());
        if (tokenTabla == null) {
            ts.addSimbolo(token);
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
