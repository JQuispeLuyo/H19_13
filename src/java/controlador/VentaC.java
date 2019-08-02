/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.VentaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import modelo.Persona;
import modelo.Vendedor;
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
    private Vendedor vendedor = SessionUtils.obtenerVendedorSesion();
    
    private VentaImpl dao = new VentaImpl();

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    
    
}
