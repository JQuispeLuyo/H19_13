/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.EquipoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import modelo.Equipo;
import modelo.Persona;

/**
 *
 * @author PC
 */
@Named(value = "personaaC")
@SessionScoped
public class PersonaC {

    /**
     * Creates a new instance of PersonaaC
     */
    public PersonaC() {
    }
    
    EquipoImpl dao = new EquipoImpl();
    List<EquipoC> listaPersona = new ArrayList();
    Persona persona = new Persona();

    @PostConstruct
    public void onInit() {
        this.listar();
    }

    public void listar() {
        try {
            this.listaPersona = this.dao.listar();
            this.limpiar();
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiar() {
        this.persona = new Persona();
    }

    public List<EquipoC> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<EquipoC> listaPersona) {
        this.listaPersona = listaPersona;
    }
 
    
}
