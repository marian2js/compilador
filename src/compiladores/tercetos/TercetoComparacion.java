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
public class TercetoComparacion extends Terceto{
    
    public TercetoComparacion(String operacion, Objeto operando1, Objeto operando2) {
        super(operacion, operando1, operando2);
    }
    
    public String getAssembler(){
        String comparacion = "";
        if (getOperando1().getTipo().equals("integer")) {
            comparacion += getOperando1().getAssemblerInit();
            comparacion += "MOV BX, " +getOperando1().getValor()+"\n";
            comparacion += getOperando2().getAssemblerInit();
            comparacion += "CMP BX,"+getOperando2().getValor()+"\n";
        } else {
            comparacion = "FLD " + getOperando1().getValor() + "\n";
            comparacion += "FCOM " + getOperando2().getValor() + "\n";
            comparacion += "FSTSW AX \n";
            comparacion += "SAHF \n";
        }

        
        return comparacion;
    }
}
