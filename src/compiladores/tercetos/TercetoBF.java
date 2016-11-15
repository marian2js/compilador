/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.tercetos;

import compiladores.Objeto;

/**
 *
 * @author luksbelt
 */
public class TercetoBF extends Terceto{
    private static Integer num=0;
    public TercetoBF(String operacion, Objeto operando1, Objeto operando2) {
        super(operacion, operando1, operando2);
    }
    
    public String getAssembler(){
        String salto="";
        String bf ="";
        if(getOperacion() == "BF"){
            switch (((Terceto)getOperando1()).getOperacion()) {
            case (">="):
                salto = "JL";
                break;
            case ("<="):
                salto = "JG";
                break;
            case (">"):
                salto = "JLE";
                break;
            case ("<"):
                salto = "JGE";
                break;
            case ("="):
                salto = "JNE";
                break;
            case ("!="):
                salto = "JE";
                break;
            }
        bf = salto+" _labelBF"+((Terceto)getOperando2()).getOperando1().getValor()+"\n";//ver el get tipo y la generacion de variables
        }
        else{
            bf = "_labelBF"+getOperando1().getValor()+":\n";
        }
        
        return bf;
    }
    
}
