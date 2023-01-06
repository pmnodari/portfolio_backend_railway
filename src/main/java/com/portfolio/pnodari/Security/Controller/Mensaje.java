
package com.portfolio.pnodari.Security.Controller;

/**
 *
 * Va a controlar los mensaje que se utiliza en el AuthController
 */
public class Mensaje {
    private String mensaje;
    
    //Constructores
    public Mensaje() {
    }

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
