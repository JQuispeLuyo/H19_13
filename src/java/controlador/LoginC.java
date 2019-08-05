/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.LoginImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import modelo.Persona;
import modelo.Vendedor;
import service.SessionUtils;

/**
 *
 * @author PC23
 */
@Named(value = "loginC")
@SessionScoped
public class LoginC implements Serializable {

    private Persona persona = new Persona();

    public LoginC() {
    }

    //Variables de Logeo
    private String User;
    private String Pass;

    public void startSession() throws Exception {
        LoginImpl dao;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            dao = new LoginImpl();
            persona = dao.startSession(User, Pass);
            if (persona != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", persona);
                switch (persona.getTIPPER()) {
                    case "A":
                        ec.redirect(ec.getRequestContextPath() + "/faces/vistas/persona/persona.xhtml");
                        //FacesContext.getCurrentInstance().getExternalContext().redirect("/Sessions/Vistas/Templates/Administrador.xhtml");
                        break;
                    case "J":
                        ec.redirect(ec.getRequestContextPath() + "/faces/vistas/persona/persona.xhtml");
                        break;
                    case "V":
                        Vendedor vendedor = new Vendedor();
                        vendedor = dao.getVendedor(persona.getIDPER());
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("vendedor", vendedor);
                        ec.redirect(ec.getRequestContextPath() + "/faces/vistas/venta/venta.xhtml");
                        //FacesContext.getCurrentInstance().getExternalContext().redirect("/Sessions/Vistas/Templates/Usuario.xhtml");
                        break;
                }
            } else {
                setPass(null);
                persona = new Persona();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contraseña/Usuario Incorrecto"));
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void finishSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear(); //Cierra la Session
        FacesContext.getCurrentInstance().getExternalContext().redirect("/JEE"); // Mandamos al Login
    }

    public void securityLogin() throws IOException {
        Persona us = SessionUtils.obtenerObjetoSesion();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if (us != null) {
            switch (persona.getTIPPER()) {
                case "A":
                    System.out.println("No solucione");
                    ec.redirect(ec.getRequestContextPath() + "/faces/vistas/persona/persona.xhtml");
                    //FacesContext.getCurrentInstance().getExternalContext().redirect("/Sessions/Vistas/Templates/Administrador.xhtml");
                    break;
                case "V":
                    ec.redirect(ec.getRequestContextPath() + "/faces/vistas/venta/venta.xhtml");
                    //FacesContext.getCurrentInstance().getExternalContext().redirect("/Sessions/Vistas/Templates/Usuario.xhtml");
                    break;
            }
        }
    }

    public void securitySession() throws IOException {
        if (SessionUtils.obtenerObjetoSesion() == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/JEE");
        }
    }

    public void obtenerDatos() {
        System.out.println(SessionUtils.ObtenerCodigoSesion());
        System.out.println(SessionUtils.ObtenerNombreSesion());
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

}
