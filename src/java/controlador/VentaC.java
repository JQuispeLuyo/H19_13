/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import modelo.Persona;
import modelo.Venta;
import service.SessionUtils;

/**
 *
 * @author PC23
 */
@Named(value = "ventaC")
@SessionScoped
public class VentaC implements Serializable {

    private Venta venta = new Venta();
    private Persona usuario = SessionUtils.obtenerObjetoSesion();
    
    public VentaC() {
        this.venta.setIDEMP(usuario.getIDPER());
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    
    
    
}
