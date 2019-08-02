/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.EquipoImpl;
import dao.SucursalImpl;
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
import modelo.Equipo;
import modelo.Sucursal;

/**
 *
 * @author PC23
 */
@Named(value = "sucursalC")
@SessionScoped
public class SucursalC implements Serializable {

    SucursalImpl dao = new SucursalImpl();
    List<Sucursal> listaSucursal = new ArrayList();
    Sucursal sucursal = new Sucursal();
    Sucursal selectSucursal = new Sucursal();
    
    public SucursalC() {
    }
    

    @PostConstruct
    public void onInit() {
        this.listar();
    }

    public void registrar(){
        
        try {
            
            this.dao.registrar(sucursal);
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
            
            this.dao.modificar(selectSucursal);
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
            this.dao.eliminar(selectSucursal);
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
            this.listaSucursal = this.dao.listar();
            this.limpiar();
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiar (){
        this.sucursal = new Sucursal();
    }

    public SucursalImpl getDao() {
        return dao;
    }

    public void setDao(SucursalImpl dao) {
        this.dao = dao;
    }

    public List<Sucursal> getListaSucursal() {
        return listaSucursal;
    }

    public void setListaSucursal(List<Sucursal> listaSucursal) {
        this.listaSucursal = listaSucursal;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Sucursal getSelectSucursal() {
        return selectSucursal;
    }

    public void setSelectSucursal(Sucursal selectSucursal) {
        this.selectSucursal = selectSucursal;
    }
    
    
}
