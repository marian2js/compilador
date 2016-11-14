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
                    declaracion += token.getValor() +" DW "+ token.get("numero")+"\n";
                    break;
                case ParserTokens.ID :
                    if("integer".equals(token.getTipo())){
                        declaracion += token.getValor();
                        declaracion+=" DW ?\n";
                    }
                    else if ("float".equals(token.getTipo())){
                        declaracion += token.getValor();
                        declaracion+=" DD ?\n";
                    }
                    break;
                default:
                    declaracion += "";
            }
          }     
        }
               
        return declaracion;
    }
    
    public String generarInstrucciones(ArrayList<Terceto> tercetos){
        String instrucciones = "";
        instrucciones += ".code\n";
        instrucciones += "start:\n";
        for(Terceto terceto : tercetos){
            instrucciones += terceto.getAssembler();
        }
        instrucciones += "invoke ExitProcess, 0\n";
        instrucciones += "end start";
        return instrucciones;
    }
}
