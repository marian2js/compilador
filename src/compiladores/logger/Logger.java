/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        // Ordena los mensajes por numero de linea
        Collections.sort(this.mensajes, new Comparator<Mensaje>() {
            @Override
            public int compare(Mensaje m1, Mensaje m2)
            {
                // Ordenar por nivel
                if (m1.getLevel().equals(Mensaje.INFO_LEVEL) && !m2.getLevel().equals(Mensaje.INFO_LEVEL)) {
                    return -1;
                }
                if (!m1.getLevel().equals(Mensaje.INFO_LEVEL) && m2.getLevel().equals(Mensaje.INFO_LEVEL)) {
                    return 1;
                }
                if (m1.getLevel().equals(Mensaje.WARNING_LEVEL) && m2.getLevel().equals(Mensaje.ERROR_LEVEL)) {
                    return -1;
                }
                if (m1.getLevel().equals(Mensaje.ERROR_LEVEL) && m2.getLevel().equals(Mensaje.WARNING_LEVEL)) {
                    return 1;
                }

                // Ordenar por lexico y sintactico
                if (m1.getTipo().equals("Lexico") && m2.getTipo().equals("Sintactico")) {
                    return -1;
                }
                if (m1.getTipo().equals("Sintactico") && m2.getTipo().equals("Lexico")) {
                    return 1;
                }

                if (m1.getLinea() > m2.getLinea()) {
                    return 1;
                } else if (m1.getLinea() < m2.getLinea()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void borrarMensajes() {
        mensajes.clear();
    }
}
