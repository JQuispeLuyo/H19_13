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
import modelo.Asignacion;

/**
 *
 * @author PC23
 */
public class AsignacionImpl extends Conexion {

    public void registrar(Asignacion modelo) throws Exception {
        System.out.println("Entras");
        try {
            this.conectar();
            String sql = "INSERT INTO PERSONA.ASIGNACION"
                    + "	(IDSUC,IDPERJEF,ESTAASIGPER)"
                    + "	VALUES(?,?,?)";

            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getIDSUC());
            ps.setInt(2, modelo.getIDPERJEF());
            ps.setString(3, "A");

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
    
    public void modificar(Asignacion modelo) throws Exception {
        System.out.println("Entras");
        try {
            this.conectar();
            String sql = "UPDATE PERSONA.ASIGNACION"
                    + "	set IDSUC = ?,IDPERJEF = ?"
                    + "	where IDASIG = ?";

            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getIDSUC());
            ps.setInt(2, modelo.getIDPERJEF());
            ps.setInt(3, modelo.getIDASIG());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
    
    public void eliminar(Asignacion modelo) throws Exception {
        System.out.println("Entras");
        try {
            this.conectar();
            String sql = "UPDATE PERSONA.ASIGNACION"
                    + "	set ESTAASIGPER = 'I'"
                    + "	where IDASIG = ?";

            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, modelo.getIDASIG());

            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
    
    public List<Asignacion> listar () throws Exception{
        
        List<Asignacion> listado = new ArrayList();
        Asignacion asignacion;
        try {
            this.conectar();
            String sql = "SELECT PAS.IDASIG," +
                    "		SUC.IDSUC," +
                    "		SUC.NOMSUC," +
                    "		PAS.IDPERJEF," +
                    "		CONCAT(P.NOMPER,' ',P.APEPER) AS NOMCOMJEF," +
                    "		P.DNIPER AS DNIJEF," +
                    "		PAS.ESTAASIGPER" +
                    "		FROM PERSONA.ASIGNACION AS PAS" +
                    "	INNER JOIN PERSONA.PERSONA AS P\n" +
                    "		ON P.IDPER = PAS.IDPERJEF" +
                    "	INNER JOIN UBICACION.SUCURSAL AS SUC" +
                    "		ON SUC.IDSUC = PAS.IDSUC";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                asignacion = new Asignacion();
                asignacion.setIDASIG(rs.getInt("IDASIG"));
                asignacion.setIDSUC(rs.getInt("IDSUC"));
                asignacion.setNOMSUC(rs.getString("NOMSUC"));
                asignacion.setIDPERJEF(rs.getInt("IDPERJEF"));
                asignacion.setNOMCOMJEF(rs.getString("NOMCOMJEF"));
                asignacion.setDNIJEF(rs.getString("DNIJEF"));
                asignacion.setESTAASIGPER(rs.getString("ESTAASIGPER"));
                
                listado.add(asignacion);
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
