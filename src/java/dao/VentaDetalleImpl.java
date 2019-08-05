/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import modelo.Equipo;
import modelo.Venta;
import modelo.VentaDetalle;
import modelo.eq;
import service.SessionUtils;

/**
 *
 * @author PC23
 */
public class VentaDetalleImpl extends Conexion {

    public void vamos() {
        this.conectar();
    }

    public void crearTemporal() throws Exception {
        try {
            Statement st = this.getCn().createStatement();
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
            st.executeUpdate("CREATE TABLE #venta"
                    + " (IDDETVENT int identity(1,1),"
                    + " IDEQUI INT,"
                    + " DESEQUI VARCHAR(100),"
                    + " PREEQUI DECIMAL(7,2),"
                    + " CANTEQUI INT)");
            System.out.println("Creado con exito");
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    public void registrarTemp(VentaDetalle modelo) throws Exception {

        try {
            String sql = "INSERT INTO #venta"
                    + "	(IDEQUI, DESEQUI, PREEQUI, CANTEQUI)"
                    + "	VALUES"
                    + "	(?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);

            ps.setInt(1, modelo.getIDEQUI());
            ps.setString(2, modelo.getDESEQUI());
            ps.setDouble(3, modelo.getPREEQUI());
            ps.setInt(4, modelo.getCANTEQUI());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }
    
    public void modificarTemp(VentaDetalle modelo) throws Exception {

        try {
            String sql = "Update #venta"
                    + "	set CANTEQUI = ?"
                    + " where IDDETVENT = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);

            ps.setInt(1, modelo.getCANTEQUI());
            ps.setInt(2, modelo.getIDDETVENT());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }
    
    public void eliminarTemp(VentaDetalle modelo) throws Exception {

        try {
            String sql = "delete #venta"
                    + " where IDDETVENT = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);

            ps.setInt(1, modelo.getIDDETVENT());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    public void registrarVenta(int IDVENT) throws Exception {

        List<VentaDetalle> listado;
        VentaDetalle ventaDetalle;
        try {
            String sql = "SELECT IDEQUI, "
                    + "CANTEQUI "
                    + "FROM #venta";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ventaDetalle = new VentaDetalle();
                ventaDetalle.setIDEQUI(rs.getInt("IDEQUI"));
                ventaDetalle.setCANTEQUI(rs.getInt("CANTEQUI"));
                ventaDetalle.setIDVENT(IDVENT);
                listado.add(ventaDetalle);
            }

            for( VentaDetalle detalle : listado ){
                this.insertarVentasDetalle(detalle);
            }
            
            System.out.println("salio del listado");

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void insertarVentasDetalle(VentaDetalle ventaDetalle) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO VENTA.DETALLE_VENTA "
                    + "(IDVENT,IDEQUI,CANTEQUI) "
                    + "VALUES "
                    + "(?,?,?) ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);

            ps.setInt(1, ventaDetalle.getIDVENT());
            ps.setInt(2, ventaDetalle.getIDEQUI());
            ps.setInt(3, ventaDetalle.getCANTEQUI());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public List<VentaDetalle> listarVentaDetalleTemp() throws Exception {

        List<VentaDetalle> listado;
        VentaDetalle ventaDetalle;
        try {
            String sql = "SELECT IDDETVENT,"
                    + "IDEQUI, "
                    + "DESEQUI,"
                    + "CANTEQUI,"
                    + "PREEQUI,"
                    + "(CANTEQUI * PREEQUI) AS IMPORT "
                    + "FROM #venta";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ventaDetalle = new VentaDetalle();
                ventaDetalle.setIDDETVENT(rs.getInt("IDDETVENT"));
                ventaDetalle.setIDEQUI(rs.getInt("IDEQUI"));
                ventaDetalle.setDESEQUI(rs.getString("DESEQUI"));
                ventaDetalle.setPREEQUI(rs.getDouble("PREEQUI"));
                ventaDetalle.setCANTEQUI(rs.getInt("CANTEQUI"));
                ventaDetalle.setIMPORT(rs.getDouble("IMPORT"));

                listado.add(ventaDetalle);
            }
            System.out.println("salio del listado");
            return listado;
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    public int getTotal() throws Exception {
        int i = 0;
        try {
            String sql = "SELECT SUM(CANTEQUI * PREEQUI) AS TOTAL"
                    + " FROM #venta";

            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                i = rs.getInt("TOTAL");
                System.out.println("salio del TOTAL");
            }

            return i;
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }
    
    public List<VentaDetalle> listarVentaDetalle(Venta modelo) throws Exception {
        this.conectar();
        List<VentaDetalle> listado;
        VentaDetalle ventaDetalle;
        try {
            String sql = "SELECT	V.IDVENT,\n" +
                        "		VD.CANTEQUI,\n" +
                        "		EQ.DESEQUI,\n" +
                        "		EQ.PREEQUI,\n" +
                        "		(EQ.PREEQUI * VD.CANTEQUI) AS IMPORT\n" +
                        "		FROM VENTA.VENTA as V\n" +
                        "		inner join VENTA.DETALLE_VENTA AS VD\n" +
                        "			ON V.IDVENT = VD.IDVENT\n" +
                        "		inner join PRODUCTO.EQUIPO EQ\n" +
                        "			ON VD.IDEQUI = EQ.IDEQUI\n" +
                        "	where V.IDVENT = ?";
            listado = new ArrayList();
            PreparedStatement ps = this.getCn().prepareStatement(sql);      
            ps.setInt(1, modelo.getIDVENT());       
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ventaDetalle = new VentaDetalle();
                ventaDetalle.setIDVENT(rs.getInt("IDVENT"));
                ventaDetalle.setCANTEQUI(rs.getInt("CANTEQUI"));
                ventaDetalle.setDESEQUI(rs.getString("DESEQUI"));
                ventaDetalle.setPREEQUI(rs.getDouble("PREEQUI"));
                ventaDetalle.setIMPORT(rs.getDouble("IMPORT"));

                listado.add(ventaDetalle);
            }
            System.out.println("salio del listado");
            return listado;
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }
    
    public int getTotalView(Venta modelo) throws Exception {
        int i = 0;
        try {
            String sql = "SELECT	SUM(EQ.PREEQUI * VD.CANTEQUI) AS TOTAL\n" +
                        "		FROM VENTA.VENTA as V\n" +
                        "		inner join VENTA.DETALLE_VENTA AS VD\n" +
                        "			ON V.IDVENT = VD.IDVENT\n" +
                        "		inner join PRODUCTO.EQUIPO EQ\n" +
                        "			ON VD.IDEQUI = EQ.IDEQUI\n" +
                        "	where V.IDVENT = ?";

            PreparedStatement ps = this.getCn().prepareStatement(sql);      
            ps.setInt(1, modelo.getIDVENT());       
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i = rs.getInt("TOTAL");
                System.out.println("salio del TOTAL");
            }

            return i;
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

}
