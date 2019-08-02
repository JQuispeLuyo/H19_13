/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.EquipoImpl;
import dao.VentaDetalleImpl;
import dao.VentaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import modelo.Equipo;
import modelo.Vendedor;
import modelo.Venta;
import modelo.VentaDetalle;
import service.SessionUtils;

/**
 *
 * @author PC23
 */
@Named(value = "ventaDetalleC")
@ViewScoped
public class VentaDetalleC implements Serializable {

    /**
     * Creates a new instance of VentaDetalleC
     */
    public VentaDetalleC() {
    }
    
    
    VentaDetalleImpl dao = new VentaDetalleImpl();
    VentaDetalle ventaDetalle = new VentaDetalle();
    List<VentaDetalle> ventaDetalleListTemp = new ArrayList();
    int total;

    EquipoImpl daoEquipo = new EquipoImpl();
    List<Equipo> equipoList = new ArrayList();
    Equipo selectEquipo = new Equipo();

    
    private Venta venta = new Venta();
    private Vendedor vendedor = SessionUtils.obtenerVendedorSesion();
    
    private VentaImpl daoVenta = new VentaImpl();

    @PostConstruct()
    public void onInit() {

        try {
            this.dao.vamos();
            this.dao.crearTemporal();
            Date fechaActual = new Date();
            this.venta.setFECVEN(fechaActual);
            this.listarVetnaDetalleTemp();
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.listarEquipo();
        System.out.println("Temrina Post constructor");
    }

    public void vender() throws Exception{
        try {
            this.venta.setIDEMP(vendedor.getIDDETASIG());
            this.ventaDetalle.setIDVENT(this.daoVenta.registrar(venta));
            this.dao.registrarVenta(this.ventaDetalle.getIDVENT());
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void agregarVentDet() {
        try {

            this.ventaDetalle.setIDEQUI(this.selectEquipo.getIDEQUI());
            this.ventaDetalle.setDESEQUI(this.selectEquipo.getDESEQUI());
            this.ventaDetalle.setPREEQUI(this.selectEquipo.getPREEQUI());

            this.dao.registrarTemp(this.ventaDetalle); 
            this.listarVetnaDetalleTemp();
            System.out.println("Nuevo registor temp");
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    public void listarEquipo() {
        try {
            this.equipoList = this.daoEquipo.listar();
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listarVetnaDetalleTemp(){
        try {
            this.ventaDetalleListTemp = this.dao.listarVentaDetalleTemp();
            this.total = this.dao.getTotal();
            System.out.println("Totla");
            System.out.println(this.total);
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public VentaDetalle getVentaDetalle() {
        return ventaDetalle;
    }

    public void setVentaDetalle(VentaDetalle ventaDetalle) {
        this.ventaDetalle = ventaDetalle;
    }

    public List<VentaDetalle> getVentaDetalleListTemp() {
        return ventaDetalleListTemp;
    }

    public void setVentaDetalleListTemp(List<VentaDetalle> ventaDetalleListTemp) {
        this.ventaDetalleListTemp = ventaDetalleListTemp;
    }

    public List<Equipo> getEquipoList() {
        return equipoList;
    }

    public void setEquipoList(List<Equipo> equipoList) {
        this.equipoList = equipoList;
    }

    public Equipo getSelectEquipo() {
        return selectEquipo;
    }

    public void setSelectEquipo(Equipo selectEquipo) {
        this.selectEquipo = selectEquipo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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
