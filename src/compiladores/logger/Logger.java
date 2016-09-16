/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.logger;

import java.util.ArrayList;

/**
 *
 * @author luksbelt
 */
public class Logger {
    public static Logger log = null;
    private ArrayList<Mensaje> mensajes;
    private Logger(){
        this.mensajes = new ArrayList<>();
    }

    public static Logger getLog(){
        if(log == null){
            log = new Logger();
        }
        return log;
    }

    public void addMensaje (Mensaje e){
        this.mensajes.add(e);
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void borrarMensajes() {
        mensajes.clear();
    }
}
