/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Jefe;
import modelo.Persona;
import modelo.Vendedor;

/**
 *
 * @author PC23
 */
public class LoginImpl extends Conexion{
    
    public Persona startSession(String User, String Pass) throws Exception {
        this.conectar();
        
        Persona persona = null;
        try {
            this.conectar();
            String sql = "SELECT * FROM PERSONA.PERSONA WHERE DNIPER = ? AND PSWPER = ? "
                    + " and ESTAPER = 'A'";
            PreparedStatement ps = this.getCn().prepareCall(sql);
            ps.setString(1, User);
            ps.setString(2, Pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                persona = new Persona();
                persona.setIDPER(rs.getInt("IDPER"));
                persona.setNOMPER(rs.getString("NOMPER"));
                persona.setAPEPER(rs.getString("APEPER"));
                persona.setDNIPER(rs.getString("DNIPER"));
                persona.setPSWPER(rs.getString("PSWPER"));
                persona.setTIPPER(rs.getString("TIPPER"));
                persona.setESTAPER(rs.getString("ESTAPER"));
            }
            rs.close();
            ps.close();
            return persona;
        } catch (SQLException e) {
            throw e;
        } finally{
            this.cerrar();
        }
    }
    
    public Vendedor getVendedor(int IDPER) throws Exception{
        Vendedor vendedor = new Vendedor();
        try {
            this.conectar();
            String sql = "SELECT	PDA.IDDETASIG," +
                            "		PDA.IDPEREMP," +
                            "		P.NOMPER," +
                            "		P.APEPER," +
                            "		P.DNIPER, " +
                            "		PAS.IDPERJEF," +
                            "		P1.NOMPER AS NOMPER1," +
                            "		P1.APEPER AS APEPER1," +
                            "		SUC.IDSUC," +
                            "		SUC.NOMSUC," +
                            "		PDA.ESTADETASIG" +
                            "		FROM PERSONA.DETALLE_ASIGNACION AS PDA" +
                            "	INNER JOIN PERSONA.PERSONA AS P" +
                            "		ON P.IDPER = PDA.IDPEREMP" +
                            "	INNER JOIN PERSONA.ASIGNACION AS PAS" +
                            "		ON PAS.IDASIG = PDA.IDASIG" +
                            "	INNER JOIN PERSONA.PERSONA AS P1" +
                            "		ON PAS.IDPERJEF = P1.IDPER" +
                            "	INNER JOIN UBICACION.SUCURSAL AS SUC" +
                            "		ON SUC.IDSUC = PAS.IDSUC" +
                            "   where PDA.ESTADETASIG = 'A' and IDPEREMP = "+ IDPER +"";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                vendedor.setIDDETASIG(rs.getInt("IDDETASIG"));
                vendedor.setIDPERJEF(rs.getString("IDPERJEF"));
                vendedor.setIDPER(rs.getInt("IDPEREMP"));
                vendedor.setNOMPER(rs.getString("NOMPER"));
                vendedor.setIDSUC(rs.getInt("IDSUC"));
                vendedor.setAPEPER(rs.getString("APEPER"));
                vendedor.setDNIPER(rs.getString("DNIPER"));
                
            }
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return vendedor;
    }
    
    public Jefe getJefe(int IDPER) throws Exception{
        Jefe jefe = new Jefe();
        try {
            this.conectar();
            String sql = "SELECT	P.IDPER,\n" +
                        "		P.NOMPER,\n" +
                        "		P.APEPER,\n" +
                        "		P.DNIPER,\n" +
                        "		SUC.IDSUC,\n" +
                        "		SUC.NOMSUC\n" +
                        "  		FROM PERSONA.PERSONA AS P\n" +
                        "  	INNER JOIN PERSONA.ASIGNACION AS PAS\n" +
                        "  		ON PAS.IDPERJEF = P.IDPER\n" +
                        "  	INNER JOIN UBICACION.SUCURSAL AS SUC\n" +
                        "  		ON SUC.IDSUC = PAS.IDSUC\n" +
                        "	WHERE P.IDPER = "+IDPER+" AND P.ESTAPER = 'A' AND PAS.ESTAASIGPER = 'A'";
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                jefe.setIDPER(rs.getInt("IDPER"));
                jefe.setNOMPER(rs.getString("NOMPER"));
                jefe.setAPEPER(rs.getString("APEPER"));
                jefe.setDNIPER(rs.getString("DNIPER"));
                jefe.setIDSUC(rs.getInt("IDSUC"));
                jefe.setNOMSUC(rs.getString("NOMSUC"));  
            }
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return jefe;
    }
}
