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
public class Logger {
    public static Logger log = null;
    private ArrayList<Error> errors;
    private Logger(){
        this.errors = new ArrayList<Error>();
    }
    
    public static Logger getLog(){
        if(log == null){
            log = new Logger();
        }
        return log;
    }
    
    public void addError (Error e){
        this.errors.add(e);
    }
}
