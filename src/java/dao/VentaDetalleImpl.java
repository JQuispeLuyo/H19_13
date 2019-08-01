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
import modelo.VentaDetalle;
import modelo.eq;

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
                    + " (IDEQUI INT,"
                    + " DESEQUI VARCHAR(100),"
                    + " PREEQUI DECIMAL(7,2),"
                    + " CANTEQUI INT)");
            System.out.println("Creado con exito");
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
            System.out.println("Insertado con exito con exito");
        } catch (Exception e) {
            throw e;
        } finally {

        }
    }

    public List<VentaDetalle> listarVentaDetalleTemp() throws Exception {

        List<VentaDetalle> listado;
        VentaDetalle ventaDetalle;
        try {
            String sql = "SELECT IDEQUI, "
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
                ventaDetalle.setIDEQUI(rs.getInt("IDEQUI"));
                ventaDetalle.setDESEQUI(rs.getString("DESEQUI"));
                ventaDetalle.setPREEQUI(rs.getDouble("PREEQUI"));
                ventaDetalle.setCANTEQUI(rs.getInt("CANTEQUI"));
                ventaDetalle.setIMPORT(rs.getDouble("IMPORT"));

                System.out.println(rs.getInt("IDEQUI"));
                System.out.println(rs.getString("DESEQUI"));
                System.out.println(rs.getDouble("PREEQUI"));
                System.out.println(rs.getInt("CANTEQUI"));
                System.out.println(rs.getDouble("IMPORT"));

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

}
