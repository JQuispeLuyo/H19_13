/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PersonaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Persona;

/**
 *
 * @author PC31
 */
@Named(value = "personaC")
@SessionScoped
public class PersonaC implements Serializable {

    private PersonaImpl dao = new PersonaImpl();
    private List<Persona> listaPersona = new ArrayList();
    private Persona persona;
    private Persona selectPersona = new Persona();

    public PersonaC() {
        persona = new Persona();
    }

    @PostConstruct
    public void onInit() {
        this.listar();
    }

    public void registrar() {

        try {
            System.out.println("sss");
            this.dao.registrar(persona);
            this.listar();
            this.limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro :D", "Detalle"));

        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listar() {
        try {
            this.listaPersona = this.dao.listar();
            this.limpiar();
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificar() {
        try {
            System.out.println("modificar");
            this.dao.modificar(selectPersona);
            this.listar();
            this.limpiar();
            System.out.println("normal");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar :D", "Detalle"));
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarCredencial() {
        try {
            System.out.println("modificar");
            this.dao.modificarCredencial(selectPersona);
            this.listar();
            this.limpiar();
            System.out.println("normal");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar :D", "Detalle"));
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar() {
        try {
            this.dao.eliminar(selectPersona);
            this.listar();
            this.limpiar();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar :D", "Detalle"));
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiar() {
        this.persona = new Persona();
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public Persona getSelectPersona() {
        return selectPersona;
    }

    public void setSelectPersona(Persona selectPersona) {
        this.selectPersona = selectPersona;
    }
}
