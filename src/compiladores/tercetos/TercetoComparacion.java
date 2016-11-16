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
            comparacion += "MOV CX, " +getOperando1().getValor()+"\n";
            comparacion += "CMP CX,"+getOperando2().getValor()+"\n";
        } else {
            comparacion = "FLD " + getOperando1().getValor() + "\n";
            comparacion += "FLD " + getOperando2().getValor() + "\n";
            comparacion += "FCOM \n";
            comparacion += "FSTSW AX \n";//paso los valores del copro al proc
            comparacion += "SAHF \n";//cargo los valores
        }

        
        return comparacion;
    }
}
