/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.InventarioImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;


import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modelo.Inventario;

/**
 *
 * @author PC31
 */
@Named(value = "inventarioC")
@ViewScoped
public class InventarioC implements Serializable{
    InventarioImpl dao = new InventarioImpl();;
    List<Inventario> listaInventario = new ArrayList();
    List<Inventario> listaInventarioHistorial = new ArrayList();
    Inventario Inventario = new Inventario();
    Inventario selectInventario = new Inventario();
    
    public InventarioC() {
    }

    @PostConstruct
    public void onInit() {
        this.listar();
    }

    public void registrar(){
        
        try {
            
            this.dao.registrar(Inventario);
            this.listar();
            this.limpiar();
            System.out.println("Entro y rgsitro :D");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro :D", "Detalle"));
            
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void modificar(){
        
        try {
            
            this.dao.modificar(selectInventario);
            this.listar();
            this.limpiar();
            System.out.println("Entro y Eliminar :D");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificar :D", "Detalle"));
            
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void eliminar(){
        
        try {
            this.dao.eliminar(selectInventario);
            this.listar();
            this.limpiar();
            System.out.println("Enotr y Eliminar :D");
            
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar :D", "Detalle"));
            
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    public void listar() {
        try {
            this.listaInventario = this.dao.listarResumen();
            this.limpiar();
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarHistorial(int IDEQUI) {
        try {
            this.listaInventario = this.dao.listarResumen();
            this.limpiar();
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiar (){
        this.Inventario = new Inventario();
    }

    public List<Inventario> getListaInventario() {
        return listaInventario;
    }

    public void setListaInventario(List<Inventario> listaInventario) {
        this.listaInventario = listaInventario;
    }

    public Inventario getInventario() {
        return Inventario;
    }

    public void setInventario(Inventario Inventario) {
        this.Inventario = Inventario;
    }

    public Inventario getSelectInventario() {
        return selectInventario;
    }

    public void setSelectInventario(Inventario selectInventario) {
        this.selectInventario = selectInventario;
    }

    public List<Inventario> getListaInventarioHistorial() {
        return listaInventarioHistorial;
    }

    public void setListaInventarioHistorial(List<Inventario> listaInventarioHistorial) {
        this.listaInventarioHistorial = listaInventarioHistorial;
    }
    
}
