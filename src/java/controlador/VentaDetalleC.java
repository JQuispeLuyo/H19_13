package controlador;

import dao.EquipoImpl;
import dao.ReportsImpl;
import dao.VentaDetalleImpl;
import dao.VentaImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modelo.Equipo;
import modelo.Vendedor;
import modelo.Venta;
import modelo.VentaDetalle;
import service.Rutas;
import service.SessionUtils;

@Named(value = "ventaDetalleC")
@ViewScoped
public class VentaDetalleC implements Serializable {

    public VentaDetalleC() {
    }

    VentaDetalleImpl dao = new VentaDetalleImpl();
    VentaDetalle ventaDetalle = new VentaDetalle();
    VentaDetalle selectVentaDetalle = new VentaDetalle();
    List<VentaDetalle> ventaDetalleListTemp = new ArrayList();
    int total;

    EquipoImpl daoEquipo = new EquipoImpl();
    List<Equipo> equipoList = new ArrayList();
    Equipo selectEquipo = new Equipo();

    private Venta venta = new Venta();
    private Vendedor vendedor = SessionUtils.obtenerVendedorSesion();

    private VentaImpl daoVenta = new VentaImpl();
    
    private boolean ventaRealizada = false;

    @PostConstruct()
    public void onInit() {

        try {
            this.iniciar();
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.listarEquipo();
        System.out.println("Temrina Post constructor");
    }

    public void iniciar() throws Exception {
        this.dao.conectarVentaDetalle();
        this.dao.crearTemporal();
        Date fechaActual = new Date();
        this.venta.setFECVEN(fechaActual);
        this.listarVentaDetalleTemp();
        this.ventaRealizada = false;
        this.limpiar();
        FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nueva venta", ""));
    }

    public void agregarVentDet() {
        try {

            this.ventaDetalle.setIDEQUI(this.selectEquipo.getIDEQUI());
            this.ventaDetalle.setDESEQUI(this.selectEquipo.getDESEQUI());
            this.ventaDetalle.setPREEQUI(this.selectEquipo.getPREEQUI());

            this.dao.registrarTemp(this.ventaDetalle);
            this.listarVentaDetalleTemp();
            this.limpiar();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto Agregado", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Selecione un producto", ""));
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarVentDet() {
        try {

            this.dao.modificarTemp(this.selectVentaDetalle);
            this.listarVentaDetalleTemp();
            this.limpiar();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto modificado", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "A ocurrido un error", ""));
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarVentDet() {
        try {

            this.dao.eliminarTemp(this.selectVentaDetalle);
            this.listarVentaDetalleTemp();
            this.limpiar();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto modificado", ""));

        } catch (Exception ex) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "A ocurrido un error", ""));
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void vender() throws Exception {
        try {
            this.venta.setIDEMP(vendedor.getIDDETASIG());
            this.ventaDetalle.setIDVENT(this.daoVenta.registrar(venta));
            this.dao.registrarVenta(this.ventaDetalle.getIDVENT());
            this.ventaDetalleListTemp = new ArrayList();
            this.ventaRealizada = true;
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta realizada con exito", ""));
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void generarBoleta() throws Exception{
        this.reportVenta(this.ventaDetalle.getIDVENT());
    }

    public void listarEquipo() {
        try {
            this.equipoList = this.daoEquipo.listar();
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarVentaDetalleTemp() {
        try {
            this.ventaDetalleListTemp = this.dao.listarVentaDetalleTemp();
            this.total = this.dao.getTotal();
            System.out.println("Totla");
            System.out.println(this.total);
        } catch (Exception ex) {
            Logger.getLogger(VentaDetalleC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiar() {
        this.selectEquipo = new Equipo();
        this.ventaDetalle = new VentaDetalle();
    }

    public void reportVenta(int IDVENT) throws Exception {
        ReportsImpl report = new ReportsImpl();
        try {
            HashMap parameters = new HashMap();
            parameters.put("IDVENT", IDVENT);
            report.reportBoleta(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void verVentas() throws IOException {
        Rutas.redirectVenta();
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

    public VentaDetalle getSelectVentaDetalle() {
        return selectVentaDetalle;
    }

    public void setSelectVentaDetalle(VentaDetalle selectVentaDetalle) {
        this.selectVentaDetalle = selectVentaDetalle;
    }

    public boolean isVentaRealizada() {
        return ventaRealizada;
    }

    public void setVentaRealizada(boolean ventaRealizada) {
        this.ventaRealizada = ventaRealizada;
    }
   
}
