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
        comparacion += "MOV CX, " +getOperando1().getValor()+"\n";
        comparacion += "CMP CX,"+getOperando2().getValor()+"\n";
        
        
        return comparacion;
    }
}
