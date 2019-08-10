/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipo;
import modelo.Inventario;

/**
 *
 * @author PC31
 */
public class InventarioImpl extends Conexion {


    public void registrar(Inventario modelo) throws Exception {

        try {
            this.conectar();

            String sql = "INSERT INTO PRODUCTO.INVENTARIO"
                    + "   (IDEQUI,"
                    + "    FECINV,"
                    + "    CANTINV,"
                    + "    TIPINV)"
                    + " VALUES"
                    + " (?,(DATEADD(HH,-5,GETUTCDATE())),?,?)";

            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getIDEQUI());
            ps.setInt(2, modelo.getCANTINV());
            System.out.println(modelo.getIDEQUI());
            System.out.println(modelo.getCANTINV());
            ps.setString(3, "I");

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        }

    }


    public void modificar(Inventario modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void eliminar(Inventario modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public List<Inventario> listarHistorial(int IDEQUI) throws Exception {

        List<Inventario> lista = new ArrayList();
        Inventario inventario;
        try {
            this.conectar();

            String sql = "SELECT\n" +
                        "	I.IDINV,\n" +
                        "	I.FECINV,\n" +
                        "	EQ.IDEQUI,\n" +
                        "	EQ.DESEQUI,\n" +
                        "	I.CANTINV,\n" +
                        "	I.TIPINV\n" +
                        "	FROM PRODUCTO.INVENTARIO AS I\n" +
                        "	INNER JOIN PRODUCTO.EQUIPO AS EQ\n" +
                        "		ON I.IDEQUI = EQ.IDEQUI\n";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inventario = new Inventario();
                inventario.setIDINV(rs.getInt("IDINV"));
                inventario.setIDEQUI(rs.getInt("IDEQUI"));
                inventario.setFECINV(rs.getDate("FECINV"));
                inventario.setDESEQUI(rs.getString("DESEQUI"));
                inventario.setCANTINV(rs.getInt("CANTINV"));
                inventario.setTIPINV(rs.getString("TIPINV"));
                lista.add(inventario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    public List<Inventario> listarResumen() throws Exception {

        List<Inventario> lista = new ArrayList();
        Inventario inventario;
        try {
            this.conectar();

            String sql = "SELECT" +
                            "	EQ.IDEQUI," +
                            "	EQ.DESEQUI," +
                            "	SUM(" +
                            "       CASE " +
                            "           WHEN I.TIPINV = 'S' THEN I.CANTINV * -1" +
                            "           ELSE I.CANTINV" +
                            "       END) AS CANTINV" +
                            "	FROM PRODUCTO.INVENTARIO AS I" +
                            "	INNER JOIN PRODUCTO.EQUIPO AS EQ" +
                            "		ON I.IDEQUI = EQ.IDEQUI" +
                            "	GROUP BY EQ.IDEQUI,EQ.DESEQUI";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inventario = new Inventario();
                inventario.setIDEQUI(rs.getInt("IDEQUI"));
                inventario.setDESEQUI(rs.getString("DESEQUI"));
                inventario.setCANTINV(rs.getInt("CANTINV"));
                lista.add(inventario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    public List<Equipo> listarEquiposInventario() throws Exception {

        List<Equipo> lista = new ArrayList();
        Equipo inventario;
        try {
            this.conectar();

            String sql = "SELECT\n" +
                        "	EQ.IDEQUI, \n" +
                        "	EQ.DESEQUI,\n" +
                        "	EQ.PREEQUI,\n" +
                        "	 SUM(\n" +
                        "		CASE \n" +
                        "		WHEN I.TIPINV = 'S' THEN I.CANTINV * -1\n" +
                        "			ELSE I.CANTINV\n" +
                        "			END) AS CANTINV\n" +
                        "		FROM PRODUCTO.INVENTARIO AS I\n" +
                        "		INNER JOIN PRODUCTO.EQUIPO AS EQ\n" +
                        "			ON I.IDEQUI = EQ.IDEQUI\n" +
                        "		GROUP BY EQ.IDEQUI,EQ.DESEQUI,EQ.PREEQUI";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inventario = new Equipo();
                inventario.setIDEQUI(rs.getInt("IDEQUI"));
                inventario.setDESEQUI(rs.getString("DESEQUI"));
                inventario.setPREEQUI(rs.getDouble("PREEQUI"));
                inventario.setCANTINV(rs.getInt("CANTINV"));
                lista.add(inventario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
