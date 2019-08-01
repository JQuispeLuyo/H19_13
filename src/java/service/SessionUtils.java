/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.faces.context.FacesContext;
import modelo.Persona;

/**
 *
 * @author PC23
 */
public class SessionUtils {
    
    
    public static Persona obtenerObjetoSesion() {
        return (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
    }

    public static String ObtenerNombreSesion() {
        Persona us = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        return us != null ? us.getNOMPER(): null;
    }

    public static String ObtenerCodigoSesion() {
        Persona us = (Persona) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        return us != null ? String.valueOf(us.getIDPER()): null;
    }
    
    
}
