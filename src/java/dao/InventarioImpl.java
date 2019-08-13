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
import modelo.Vendedor;

public class InventarioImpl extends Conexion {

    public void registrar(Inventario modelo) throws Exception {

        try {
            this.conectar();

            String sql = "INSERT INTO PRODUCTO.INVENTARIO"
                    + "   (IDEQUI,"
                    + "    FECINV,"
                    + "    CANTINV,"
                    + "    TIPINV,"
                    + "    IDSUC)"
                    + " VALUES"
                    + " (?,(DATEADD(HH,-5,GETUTCDATE())),?,?,?)";

            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getIDEQUI());
            ps.setInt(2, modelo.getCANTINV());
            ps.setString(3, "I");
            ps.setInt(4, modelo.getIDSUC());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        }

    }


    public List<Inventario> listarHistorial(Inventario modelo) throws Exception {

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
                        "	I.TIPINV,\n" +
                        "	SUC.NOMSUC\n" +
                        "	FROM PRODUCTO.INVENTARIO AS I\n" +
                        "	INNER JOIN PRODUCTO.EQUIPO AS EQ\n" +
                        "		ON I.IDEQUI = EQ.IDEQUI\n" +
                        "	INNER JOIN UBICACION.SUCURSAL AS SUC\n" +
                        "		ON I.IDSUC = SUC.IDSUC\n" +
                        "	WHERE SUC.IDSUC = ? AND EQ.IDEQUI = ?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getIDSUC());
            System.out.println(modelo.getIDSUC());
            ps.setInt(2, modelo.getIDEQUI());
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
                System.out.println("resultado");
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    public List<Inventario> listarResumen(int IDSUC) throws Exception {

        List<Inventario> lista = new ArrayList();
        Inventario inventario;
        try {
            this.conectar();
            String where = "";
            
            if(IDSUC != 0){
                where = "WHERE SUC.IDSUC = "+IDSUC;
            }
            
            String sql = "SELECT\n" +
                        "  EQ.IDEQUI,\n" +
                        "  EQ.DESEQUI,\n" +
                        "  SUC.IDSUC,\n" +
                        "  SUC.NOMSUC,\n" +
                        "   SUM(\n" +
                        "		CASE\n" +
                        "			WHEN I.TIPINV = 'S' THEN I.CANTINV * -1\n" +
                        "			ELSE I.CANTINV\n" +
                        "        END) AS CANTINV\n" +
                        "    FROM PRODUCTO.INVENTARIO AS I\n" +
                        "    INNER JOIN PRODUCTO.EQUIPO AS EQ\n" +
                        "    	ON I.IDEQUI = EQ.IDEQUI\n" +
                        "	INNER JOIN	UBICACION.SUCURSAL SUC\n" +
                        "		ON SUC.IDSUC = I.IDSUC\n" +
                        "	"+ where +" " +
                        "    GROUP BY EQ.IDEQUI,EQ.DESEQUI,SUC.IDSUC,SUC.NOMSUC";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inventario = new Inventario();
                inventario.setIDEQUI(rs.getInt("IDEQUI"));
                inventario.setIDSUC(rs.getInt("IDSUC"));
                inventario.setNOMSUC(rs.getString("NOMSUC"));
                inventario.setDESEQUI(rs.getString("DESEQUI"));
                inventario.setCANTINV(rs.getInt("CANTINV"));
                lista.add(inventario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    public List<Inventario> listarEquiposInventario(Vendedor modelo) throws Exception {

        List<Inventario> lista = new ArrayList();
        Inventario inventario;
        try {
            this.conectar();

            String sql = "SELECT\n" +
                    "   	EQ.IDEQUI, \n" +
                    "   	EQ.DESEQUI,\n" +
                    "   	EQ.PREEQUI,\n" +
                    "           SUC.IDSUC,\n" +
                    "           SUC.NOMSUC,\n" +
                    "   	 SUM(\n" +
                    "   		CASE \n" +
                    "   		WHEN I.TIPINV = 'S' THEN I.CANTINV * -1\n" +
                    "   			ELSE I.CANTINV\n" +
                    "   			END) AS CANTINV\n" +
                    "   		FROM PRODUCTO.INVENTARIO AS I\n" +
                    "   		INNER JOIN PRODUCTO.EQUIPO AS EQ\n" +
                    "   			ON I.IDEQUI = EQ.IDEQUI\n" +
                    "		INNER JOIN UBICACION.SUCURSAL AS SUC\n" +
                    "   			ON SUC.IDSUC = I.IDSUC\n" +
                    "		WHERE SUC.IDSUC = ?" +
                    "   		GROUP BY EQ.IDEQUI,EQ.DESEQUI,EQ.PREEQUI,SUC.IDSUC,SUC.NOMSUC";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1,modelo.getIDSUC());
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

}
