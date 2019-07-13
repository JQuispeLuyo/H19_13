
package controlador;

import dao.EquipoImpl;
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

@Named(value = "equipoC")
@SessionScoped
public class EquipoC implements Serializable {

    EquipoImpl dao;
    List<Equipo> listaEquipo = new ArrayList();
    Equipo equipo = new Equipo();
    Equipo selectEquipo = new Equipo();
    
    public EquipoC() {
        dao = new EquipoImpl();
    }
    
    
    
    
    

    @PostConstruct
    public void onInit() {
        this.listar();
    }

    public void registrar(){
        
        try {
            
            this.dao.registrar(equipo);
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
            
            this.dao.modificar(selectEquipo);
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
            this.dao.eliminar(selectEquipo);
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
            this.listaEquipo = this.dao.listar();
            this.limpiar();
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiar (){
        this.equipo = new Equipo();
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Equipo getSelectEquipo() {
        return selectEquipo;
    }

    public void setSelectEquipo(Equipo selectEquipo) {
        System.out.println("Cambia valor");
        this.selectEquipo = selectEquipo;
    }

    
    
    
}
