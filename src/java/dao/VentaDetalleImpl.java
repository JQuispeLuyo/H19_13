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
import modelo.Inventario;
import modelo.Vendedor;
import modelo.Venta;
import modelo.VentaDetalle;

/**
 *
 * @author PC23
 */
public class VentaDetalleImpl extends Conexion {

    public void conectarVentaDetalle() {
        this.conectar();
    }

    public void crearTemporal() throws Exception {
        try {
            Statement st = this.getCn().createStatement();
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
            st.executeUpdate("CREATE TABLE #venta"
                    + " (IDDETVENT int identity(1,1),"
                    + " IDVENT INT,"
                    + " IDEQUI INT,"
                    + " IDSUC INT,"
                    + " DESEQUI VARCHAR(100),"
                    + " PREEQUI DECIMAL(7,2),"
                    + " CANTEQUI INT)");
            System.out.println("Creado con exito");
            st.close();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void crearTemporalInventario() throws Exception {
        try {
            Statement st = this.getCn().createStatement();
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
            st.executeUpdate("CREATE TABLE #inventarioTemp"
                    + " (IDDETVENT INT identity(1,1),"
                    + " IDEQUI INT,"
                    + " DESEQUI Varchar(100),"
                    + " PREEQUI DECIMAL(7,2),"
                    + " IDSUC INT,"
                    + " CANTINV INT)");
            System.out.println("Creado con exito TEMP ");
            st.close();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void iniciarTemporalInventario(Vendedor modelo) throws Exception {
        try {
            String sql = "INSERT INTO #inventarioTemp"
                    + " (IDEQUI,"
                    + " DESEQUI,"
                    + " PREEQUI,"
                    + " IDSUC," 
                    + " CANTINV)"
                    + " SELECT\n" +
                    "   	EQ.IDEQUI, \n" +
                    "   	EQ.DESEQUI,\n" +
                    "   	EQ.PREEQUI,\n" +
                    "           SUC.IDSUC,\n" +
                    "   	 SUM(\n" +
                    "   		CASE \n" +
                    "   		WHEN I.TIPINV = 'S' THEN I.CANTINV * -1\n" +
                    "   			ELSE I.CANTINV\n" +
                    "   			END) AS CANTINV\n" +
                    "   	FROM PRODUCTO.INVENTARIO AS I\n" +
                    "   	INNER JOIN PRODUCTO.EQUIPO AS EQ\n" +
                    "   			ON I.IDEQUI = EQ.IDEQUI\n" +
                    "		INNER JOIN UBICACION.SUCURSAL AS SUC\n" +
                    "   			ON SUC.IDSUC = I.IDSUC\n" +
                    "		WHERE SUC.IDSUC = ?" +
                    "   		GROUP BY EQ.IDEQUI,EQ.DESEQUI,EQ.PREEQUI,SUC.IDSUC";
            
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1,modelo.getIDSUC());
            
            ps.executeUpdate();
            System.out.println("Normal inser inven");
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public List<Inventario> listarEquiposInventarioTemp(Vendedor modelo) throws Exception {

        List<Inventario> lista = new ArrayList();
        Inventario inventario;
        try {

            String sql = "SELECT" +
                    "   	IDEQUI," +
                    "   	DESEQUI," +
                    "   	PREEQUI," +
                    "           IDSUC," +
                    "   	CANTINV" +
                    "   	FROM #inventarioTemp";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inventario = new Inventario();
                inventario.setIDEQUI(rs.getInt("IDEQUI"));
                inventario.setDESEQUI(rs.getString("DESEQUI"));
                inventario.setPREEQUI(rs.getDouble("PREEQUI"));
                inventario.setCANTINV(rs.getInt("CANTINV"));
                inventario.setIDSUC(rs.getInt("IDSUC"));
                lista.add(inventario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    
    public void registrarTemp(VentaDetalle modelo) throws Exception {

        try {
            String sql = "";
            sql = "";
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
//
//            ps.setInt(1, modelo.getCANTEQUI());
//            ps.setInt(2, modelo.getIDEQUI());
//            ps.setInt(3, modelo.getIDEQUI());
//            ps.executeUpdate();
//            
            
            sql = "IF NOT EXISTS(Select 1 FROM #venta where IDEQUI = ?)"
               + "  INSERT INTO #venta"
               + "     (IDEQUI, DESEQUI, PREEQUI, CANTEQUI, IDSUC)"
               + "	VALUES"
               + "     (?,?,?,?,?)"
               + " ELSE "
               + "     UPDATE #venta"
               + "         set CANTEQUI = CANTEQUI + ?"
               + "     where IDEQUI = ? ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setInt(1, modelo.getIDEQUI());
            ps.setInt(2, modelo.getIDEQUI());
            ps.setString(3, modelo.getDESEQUI());
            ps.setDouble(4, modelo.getPREEQUI());
            ps.setInt(5, modelo.getCANTEQUI());
            ps.setInt(6, modelo.getIDSUC());
            ps.setInt(7, modelo.getCANTEQUI());
            ps.setInt(8, modelo.getIDEQUI());
            
            ps.executeUpdate();
            
            this.actualizarInventarioTemp(modelo, "C");
           
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void actualizarInventarioTemp(VentaDetalle modelo, String tipo) throws Exception {

        try {
            
            String sql = "";
            
            switch (tipo){
                case "C":
                    sql = "UPDATE #inventarioTemp "
                        + "	set CANTINV = CANTINV - ?"
                        + "	Where IDEQUI = ?";
                    break;
                case "U1":
                    sql = "UPDATE #inventarioTemp "
                        + "	set CANTINV = CANTINV + ?"
                        + "	Where IDEQUI = ?";
                    break;
                case "U2":
                    sql = "UPDATE #inventarioTemp "
                        + "	set CANTINV = CANTINV - ?"
                        + "	Where IDEQUI = ?";
                    break;
                case "D":
                    sql = "UPDATE #inventarioTemp "
                        + "	set CANTINV = CANTINV + ?"
                        + "	Where IDEQUI = ?";
                    break;
            }
            
            
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getCANTEQUI());
            ps.setInt(2, modelo.getIDEQUI());
            ps.executeUpdate();
            
            ps.close();
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void modificarTemp(VentaDetalle modelo) throws Exception {

        try {
            int CANTEQUIANT = 0;
            String sql = "";
            sql = "SELECT CANTEQUI "
                    + " FROM #venta"
                    + " where IDEQUI = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getIDEQUI());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CANTEQUIANT = rs.getInt("CANTEQUI");
            }
            
            
            sql = "Update #venta"
                    + "	set CANTEQUI = ?"
                    + " where IDDETVENT = ?";
            ps = this.getCn().prepareStatement(sql);

            ps.setInt(1, modelo.getCANTEQUI());
            ps.setInt(2, modelo.getIDDETVENT());

            ps.executeUpdate();
            
            if(CANTEQUIANT > modelo.getCANTEQUI()){
                modelo.setCANTEQUI(CANTEQUIANT - modelo.getCANTEQUI());
                this.actualizarInventarioTemp(modelo, "U1");
            }if(CANTEQUIANT < modelo.getCANTEQUI()){
                modelo.setCANTEQUI(modelo.getCANTEQUI() - CANTEQUIANT );
                this.actualizarInventarioTemp(modelo, "U2");
            }
            
            ps.close();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void eliminarTemp(VentaDetalle modelo) throws Exception {

        try {
            String sql = "delete #venta"
                    + " where IDDETVENT = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);

            ps.setInt(1, modelo.getIDDETVENT());

            ps.executeUpdate();
            
            this.actualizarInventarioTemp(modelo, "D");
            ps.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public void registrarVenta(int IDVENT) throws Exception {

        try {
            String sql = "Update #venta set IDVENT = ?";
            
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, IDVENT);
            ps.executeUpdate();
            
            
            this.insertarVentasDetalle();     
            this.actualizarInventario();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    public void insertarVentasDetalle() throws Exception {
        try {
            String sql = "INSERT INTO VENTA.DETALLE_VENTA "
                        + "( IDVENT, "
                        + "   IDEQUI, "
                        + "   CANTEQUI ) "
                        + "SELECT "
                        + " IDVENT, "
                        + " IDEQUI, "
                        + "CANTEQUI "
                        + "FROM #venta ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        }
    }

    public void actualizarInventario() throws Exception {
        try {
            String sql = "INSERT INTO PRODUCTO.INVENTARIO\n" +
                        "(   IDEQUI," +
                        "    FECINV," +
                        "    CANTINV," +
                        "    TIPINV," +
                        "    IDSUC ) "
                        + " SELECT "
                        + "     IDEQUI, "
                        + "     DATEADD(HH,-5, GETUTCDATE()) AS FECINV, "
                        + "     CANTEQUI,"
                        + "     'S' AS TIPINV,"
                        + "     IDSUC "
                        + " FROM #venta ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
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
            this.cerrar();
        }
    }
    
    public int getTotalView(Venta modelo) throws Exception {
        int i = 0;
        this.conectar();
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
