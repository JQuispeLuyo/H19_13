/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ReportsImpl;
import dao.VentaDetalleImpl;
import dao.VentaImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import modelo.Persona;
import modelo.Vendedor;
import modelo.Venta;
import modelo.VentaDetalle;
import service.Rutas;
import service.SessionUtils;

/**
 *
 * @author PC23
 */
@Named(value = "ventaC")
@ViewScoped
public class VentaC implements Serializable {


    private VentaImpl dao = new VentaImpl();
    private List<Venta> listaVenta = new ArrayList();
    private List<VentaDetalle> ventaDetalleList = new ArrayList();
   
    private Venta selectVenta = new Venta();
    
    private VentaDetalleImpl daoVentaDetalle = new VentaDetalleImpl();
    private int total = 0;
     
    @PostConstruct()
    public void onInit (){
        try {
            this.listar();
        } catch (Exception ex) {
            Logger.getLogger(VentaC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listar() throws Exception{
        this.listaVenta = this.dao.listar();
    }
    
    public void verVenta() throws Exception{
        System.out.println("entra");
        this.ventaDetalleList = this.daoVentaDetalle.listarVentaDetalle(selectVenta);
        this.total = this.daoVentaDetalle.getTotalView(selectVenta);
    }

    
    public void reportVenta() throws Exception{
        ReportsImpl report = new ReportsImpl();
        try {
            HashMap parameters = new HashMap();
            parameters.put("IDVENT", selectVenta.getIDVENT());
            report.reportBoleta(parameters);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void ventaDetalleRedirect() throws IOException{
        Rutas.redirectVentaDetalle();
    }
    
    public List<Venta> getListaVenta() {
        return listaVenta;
    }

    public void setListaVenta(List<Venta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    public Venta getSelectVenta() {
        return selectVenta;
    }

    public void setSelectVenta(Venta selectVenta) {
        this.selectVenta = selectVenta;
    }

    public List<VentaDetalle> getVentaDetalleList() {
        return ventaDetalleList;
    }

    public void setVentaDetalleList(List<VentaDetalle> ventaDetalleList) {
        this.ventaDetalleList = ventaDetalleList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
