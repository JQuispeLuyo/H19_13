/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AsignacionDetalleImpl;
import dao.AsignacionImpl;
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
import modelo.Asignacion;
import modelo.AsignacionDetalle;
import modelo.Persona;

/**
 *
 * @author jq_00
 */
@Named(value = "asignacionDetalleC")
@SessionScoped
public class AsignacionDetalleC implements Serializable {

    AsignacionDetalle asignacionDet = new AsignacionDetalle();
    AsignacionDetalle selectAsignacionDet = new AsignacionDetalle();
    List<AsignacionDetalle> listaAsignacionDet = new ArrayList();
    List<Asignacion> listaAsignacion = new ArrayList();
    List<Persona> listaVendedores = new ArrayList();
    
    AsignacionDetalleImpl dao = new AsignacionDetalleImpl();
    AsignacionImpl daoAsignacion = new AsignacionImpl();
    PersonaImpl daoPersona = new PersonaImpl();

    
    public AsignacionDetalleC() {
    }
    
    @PostConstruct
    public void onInit(){
        try {
            this.listar();
            this.listarCombo();
        } catch (Exception ex) {
            Logger.getLogger(AsignacionC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registrar() throws Exception{
        this.dao.registrar(asignacionDet);
        this.listar();
        this.limpiar();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completo"));
    }
    
    public void modificar() throws Exception{
        this.dao.modificar(selectAsignacionDet);
        this.listar();
        this.limpiar();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Completo"));
    }
    
    public void eliminar() throws Exception{
        this.dao.eliminar(selectAsignacionDet);
        this.listar();
        this.limpiar();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar", "Completo"));
    }
    
    public void listar () throws Exception{       
        this.listaAsignacionDet = this.dao.listar(); 
    }
    
    public void listarCombo() throws Exception{
        this.listaAsignacion = this.daoAsignacion.listar();
        this.listaVendedores = this.daoPersona.listarVendedor();
    }

    public void limpiar(){
        asignacionDet = new AsignacionDetalle();
    }

    public AsignacionDetalle getAsignacionDet() {
        return asignacionDet;
    }

    public void setAsignacionDet(AsignacionDetalle asignacionDet) {
        this.asignacionDet = asignacionDet;
    }

    public AsignacionDetalle getSelectAsignacionDet() {
        return selectAsignacionDet;
    }

    public void setSelectAsignacionDet(AsignacionDetalle selectAsignacionDet) {
        this.selectAsignacionDet = selectAsignacionDet;
    }

    public List<AsignacionDetalle> getListaAsignacionDet() {
        return listaAsignacionDet;
    }

    public void setListaAsignacionDet(List<AsignacionDetalle> listaAsignacionDet) {
        this.listaAsignacionDet = listaAsignacionDet;
    }

    public List<Asignacion> getListaAsignacion() {
        return listaAsignacion;
    }

    public void setListaAsignacion(List<Asignacion> listaAsignacion) {
        this.listaAsignacion = listaAsignacion;
    }

    public List<Persona> getListaVendedores() {
        return listaVendedores;
    }

    public void setListaVendedores(List<Persona> listaVendedores) {
        this.listaVendedores = listaVendedores;
    }
    
}
