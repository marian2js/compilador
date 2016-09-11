/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

/**
 *
 * @author luksbelt
 */
public class Anotacion extends Token{

    public Anotacion(String lexema) {
        super(lexema);
    }

    @Override
    boolean esReservada() {
        return false;
    }
    
}
