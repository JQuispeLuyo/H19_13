
package controlador;

import dao.AsignacionImpl;
import dao.PersonaImpl;
import dao.SucursalImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.behavior.FacesBehavior;
import javax.faces.context.FacesContext;
import modelo.Asignacion;
import modelo.Persona;
import modelo.Sucursal;

@Named(value = "asignacionC")
@SessionScoped
public class AsignacionC implements Serializable{

    Asignacion asignacion = new Asignacion();
    Asignacion selectAsignacion = new Asignacion();
    List<Asignacion> listaAsignacion = new ArrayList();
    List<Sucursal> listaSucursal = new ArrayList();
    List<Persona> listaJefe = new ArrayList();
    AsignacionImpl dao = new AsignacionImpl();
    SucursalImpl daoSucursal = new SucursalImpl();
    PersonaImpl daoPersona = new PersonaImpl();
    
    public AsignacionC() {
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
        this.dao.registrar(asignacion);
        this.listar();
        this.limpiar();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Completo"));
    }
    
    public void modificar() throws Exception{
        this.dao.modificar(selectAsignacion);
        this.listar();
        this.limpiar();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Completo"));
    }
    
    public void eliminar() throws Exception{
        this.dao.eliminar(selectAsignacion);
        this.listar();
        this.limpiar();
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminar", "Completo"));
    }
    
    public void listar () throws Exception{       
        this.listaAsignacion = this.dao.listar(); 
    }
    
    public void listarCombo() throws Exception{
        this.listaSucursal = this.daoSucursal.listar();
        this.listaJefe = this.daoPersona.listarJefe();
    }

    public void limpiar(){
        asignacion = new Asignacion();
    }
    
    
    
    public Asignacion getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Asignacion asignacion) {
        this.asignacion = asignacion;
    }

    public List<Asignacion> getListaAsignacion() {
        return listaAsignacion;
    }

    public void setListaAsignacion(List<Asignacion> listaAsignacion) {
        this.listaAsignacion = listaAsignacion;
    }

    public Asignacion getSelectAsignacion() {
        return selectAsignacion;
    }

    public void setSelectAsignacion(Asignacion selectAsignacion) {
        this.selectAsignacion = selectAsignacion;
    }

    public List<Sucursal> getListaSucursal() {
        return listaSucursal;
    }

    public void setListaSucursal(List<Sucursal> listaSucursal) {
        this.listaSucursal = listaSucursal;
    }

    public List<Persona> getListaJefe() {
        return listaJefe;
    }

    public void setListaJefe(List<Persona> listaJefe) {
        this.listaJefe = listaJefe;
    } 
   
}
