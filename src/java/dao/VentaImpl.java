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
import modelo.Venta;

/**
 *
 * @author PC
 */
public class VentaImpl extends Conexion{


    public int registrar(Venta modelo) throws Exception {
        int IDVENT = 0;
        System.out.println("Entras");
        try {
            this.conectar();
            String sql = "INSERT INTO VENTA.VENTA"
                    + "	(IDEMP,FECVEN)"
                    + "	VALUES(?,?)";

            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getIDEMP());
            ps.setDate(2, new java.sql.Date(modelo.getFECVEN().getTime()));
            
            ps.executeUpdate();
            System.out.println("Pasa el conver fecha");
      
            
            String sql1 = "select TOP 1 * from VENTA.VENTA" +
                  "	where IDEMP = ?" +
                  "	ORDER BY IDVENT DESC";
            ps = this.getCn().prepareStatement(sql1);
            ps.setInt(1, modelo.getIDEMP());
            
//            Statement st = this.getCn().createStatement();
//            ResultSet rs = st.executeQuery(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {  
                IDVENT = rs.getInt("IDVENT");
            }
            System.out.println("salio del listado");
            return IDVENT;
        } catch (Exception e) {
            throw e;
        }finally{
            
        }
    }

    public List<Venta> listar() throws Exception {

        List<Venta> listado;
        Venta venta;
        try {
            this.conectar();
            String sql = "select \n" +
                        "	VT.IDVENT,\n" +
                        "	VT.FECVEN,\n" +
                        "	VT.NOMCOMEMP,\n" +
                        "	SUM (VT.CANTEQUI * VT.PREEQUI) AS TOTAL\n" +
                        "from\n" +
                        "(SELECT	V.IDVENT,\n" +
                        "		V.IDEMP,\n" +
                        "		V.FECVEN,\n" +
                        "		VD.CANTEQUI,\n" +
                        "		EQ.PREEQUI,\n" +
                        "		CONCAT(P.NOMPER,' ',P.APEPER) AS NOMCOMEMP\n" +
                        "		FROM VENTA.VENTA as V\n" +
                        "		inner join VENTA.DETALLE_VENTA AS VD\n" +
                        "			ON V.IDVENT = VD.IDVENT\n" +
                        "		inner join PRODUCTO.EQUIPO EQ\n" +
                        "			ON VD.IDEQUI = EQ.IDEQUI\n" +
                        "		inner join PERSONA.DETALLE_ASIGNACION AS DTA\n" +
                        "			ON V.IDEMP = DTA.IDDETASIG\n" +
                        "		inner join PERSONA.PERSONA AS P\n" +
                        "			ON P.IDPER = DTA.IDPEREMP\n" +
                        ") AS VT\n" +
                        "GROUP BY VT.FECVEN,VT.IDVENT,VT.NOMCOMEMP";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                venta = new Venta();
                venta.setIDVENT(rs.getInt("IDVENT"));
                venta.setNOMCOMEMP(rs.getString("NOMCOMEMP"));
                venta.setFECVEN(rs.getDate("FECVEN"));
                venta.setTOTAL(rs.getInt("TOTAL"));

                listado.add(venta);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return listado;

    }

 
}
