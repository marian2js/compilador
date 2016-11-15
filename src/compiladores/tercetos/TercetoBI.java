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
public class TercetoBI extends Terceto{
    
    public TercetoBI(String operacion, Objeto operando1, Objeto operando2) {
        super(operacion, operando1, operando2);
    }
    
    public String getAssembler(){
        String bi="";
        if(getOperacion() == "BI"){
            bi = "JMP _labelBI"+((Terceto)getOperando1()).getOperando1().getValor()+"\n";
        }
        else{
            bi = "_labelBI"+getOperando1().getValor()+":\n";;
        }
        return bi;
    }
    
}
