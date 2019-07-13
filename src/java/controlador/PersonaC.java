
package controlador;

import dao.EquipoImpl;
import dao.PersonaImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Equipo;
import modelo.Persona;


@Named(value = "personaC")
@SessionScoped
public class PersonaC {

    PersonaImpl dao = new PersonaImpl();
    List<Persona> listaPersona = new ArrayList();
    Persona persona = new Persona();
    
    public PersonaC() {
    }
    
    public void registrar(){
        
        try {
            
            this.dao.registrar(persona);
            this.listar();
            this.limpiar();
            System.out.println("Entro y rgsitro :D");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro :D", "Detalle"));
            
        } catch (Exception ex) {
            Logger.getLogger(EquipoC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
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
 
    
    
    
}
