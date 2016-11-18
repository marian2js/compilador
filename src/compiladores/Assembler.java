/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import compiladores.sintactico.ParserTokens;
import compiladores.tercetos.Terceto;
import java.util.ArrayList;

/**
 *
 * @author luksbelt
 */
public class Assembler {
    public static String listaAuxiliares = "";
    
    public String generarAssembler(ArrayList<Terceto> tercetos,TablaSimbolos ts){
        String assembler ="";
        String instrucciones = this.generarInstrucciones(tercetos);
        assembler += this.generarEncabezado();
        assembler += this.generarDeclaraciones(ts);
        assembler += listaAuxiliares;
        assembler += instrucciones;

        // Reinicia los valores
        listaAuxiliares = "";
        Terceto.auxId = 0;
        Token.cteId = 0;

        return assembler;
    }
    
    private String generarEncabezado(){
        String encabezado = ".386\n" +
            ".model flat, stdcall\n" +
            "option casemap :none\n" +
            "include \\masm32\\include\\windows.inc\n" +
            "include \\masm32\\include\\kernel32.inc\n" +
            "include \\masm32\\include\\user32.inc\n" +
            "includelib \\masm32\\lib\\kernel32.lib\n" +
            "includelib \\masm32\\lib\\user32.lib\n";
        return encabezado;
    }
    
    private String generarDeclaraciones(TablaSimbolos ts){
        String declaracion = ".data\n";
        declaracion += "_errDivCero DB 'Error: Division por cero', 0\n";
        declaracion += "_errPerdidaConv DB 'Error: Perdida en conversion', 0\n";
        declaracion += "_errIndiceInv DB 'Error: Acceso a indice fuera de rango', 0\n";
        for(Token token : ts.getTokens()){
            if(!token.esReservada()){
            switch (token.getValue()){
                case ParserTokens.CADENA :
                    declaracion += token.get("ID")+ " DB " + token.getLexema() + ", 0\n";
                    break;
                case ParserTokens.CTE_FLOAT :
                    declaracion += token.getValor()+" DD "+ token.get("numero") +"\n";
                    break;
                case ParserTokens.CTE_ENTERA :
                    declaracion += token.getValor() +" DW "+ token.get("numero").toString().replace(".0", "") + "\n";
                    break;
                case ParserTokens.ID :
                    if (token.get("filas") != null && token.get("columnas") != null) {
                        declaracion += token.getValor() + getTipoId(token);
                        double lim = 0;
                        double liminf = 0;
                        double nroFilas = (Double)((Token)token.get("filas")).get("numero");
                        double nroCols = (Double)((Token)token.get("columnas")).get("numero");
                        if (token.get("anotacion") != null && token.get("anotacion").equals("/#@1")) {
                            lim = nroFilas * nroCols;
                        } else {
                            lim = (nroFilas + 1) * (nroCols + 1);
                        }
                        if (token.get("inicializacion") != null) {
                            int i = 0;
                            ArrayList<Token> tokens = (ArrayList<Token>) token.get("inicializacion");
                            for (Token t : tokens) {
                                String num = "" + t.get("numero");
                                if (token.getTipo().equals("integer")) {
                                    num = num.replace(".0", "");
                                }
                                declaracion += num;
                                i++;
                                if (i < tokens.size()) {
                                    declaracion += ",";
                                }
                            }
                            declaracion += "\n";
                        } else {
                            declaracion += Double.toString(lim).replace(".0", "") + " DUP(?)\n";
                        }
                        if ("integer".equals(token.getTipo())) {
                            declaracion += "_lim" + token.getValor() + " DW " + Double.toString(lim*2).replace(".0", "") + "\n" ;
                        } else {
                            declaracion += "_lim" + token.getValor() + " DW " + Double.toString(lim * 4).replace(".0", "") + "\n";
                        }
                    } else {
                        declaracion += token.getValor();
                        declaracion += getTipoId(token) + "?\n";
                    }
                    break;
                default:
                    declaracion += "";
            }
          }     
        }
               
        return declaracion;
    }

    public String getTipoId(Token token) {
        if ("integer".equals(token.getTipo())) {
            return " DW ";
        } else
            return " DD ";
    }


    private String generarLabelDivCero() {
        return "_label_div_cero:\n" +
                "invoke MessageBox, NULL, addr _errDivCero, addr _errDivCero, MB_OK\n" +
                "invoke ExitProcess, 0\n";
    }

    private String generarLabelPerdidaConv() {
        return "_label_perdida_conversion:\n" +
                "invoke MessageBox, NULL, addr _errPerdidaConv, addr _errPerdidaConv, MB_OK\n" +
                "invoke ExitProcess, 0\n";
    }

    private String generarLabelErrorIndice() {
        return "_label_error_indice:\n" +
                "invoke MessageBox, NULL, addr _errIndiceInv, addr _errIndiceInv, MB_OK\n" +
                "invoke ExitProcess, 0\n";
    }

    public String generarInstrucciones(ArrayList<Terceto> tercetos){
        String instrucciones = "";
        instrucciones += ".code\n";
        instrucciones += generarLabelDivCero();
        instrucciones += generarLabelPerdidaConv();
        instrucciones += generarLabelErrorIndice();
        instrucciones += "start:\n";
        for(Terceto terceto : tercetos){
            instrucciones += terceto.getAssembler();
        }
        instrucciones += "invoke ExitProcess, 0\n";
        instrucciones += "end start";
        return instrucciones;
    }
}
