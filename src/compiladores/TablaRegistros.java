/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores;

import java.util.ArrayList;

/**
 *
 * @author luksbelt
 */
public class TablaRegistros {
    public class Registro{
        public String nombre;
        public Boolean ocupado;
    }
    private static TablaRegistros instance;
    private ArrayList<Registro> registros = new ArrayList<>();
    
    private TablaRegistros() {
        
    }

    public static TablaRegistros getInstance() {
        if (instance == null) {
            instance = new TablaRegistros();
        }
        return instance;
    }

    public String getRegistro(){
        return "EBX";
    }
}
