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
import modelo.AsignacionDetalle;

/**
 *
 * @author jq_00
 */
public class AsignacionDetalleImpl extends Conexion {

    public void registrar() {

    }

    public List<AsignacionDetalle> listar() throws Exception {

        List<AsignacionDetalle> listado = new ArrayList();
        AsignacionDetalle asignacionDetalle;
        try {
            this.conectar();
            String sql = "SELECT	PDA.IDDETASIG,\n" +
                        "		PAS.IDASIG,\n" +
                        "		SUC.IDSUC,\n" +
                        "		SUC.NOMSUC,\n" +
                        "		PAS.IDPERJEF,\n" +
                        "		CONCAT(P1.NOMPER,' ',P1.APEPER) AS NOMCOMJEF,\n" +
                        "		PDA.IDPEREMP,\n" +
                        "		CONCAT(P.NOMPER,' ',P.APEPER) AS NOMCOMEMP,\n" +
                        "		P.DNIPER AS DNIEMP, \n" +
                        "		PDA.ESTADETASIG\n" +
                        "		FROM PERSONA.DETALLE_ASIGNACION AS PDA\n" +
                        "	INNER JOIN PERSONA.PERSONA AS P\n" +
                        "		ON P.IDPER = PDA.IDPEREMP\n" +
                        "	INNER JOIN PERSONA.ASIGNACION AS PAS\n" +
                        "		ON PAS.IDASIG = PDA.IDASIG\n" +
                        "	INNER JOIN PERSONA.PERSONA AS P1\n" +
                        "		ON PAS.IDPERJEF = P1.IDPER\n" +
                        "	INNER JOIN UBICACION.SUCURSAL AS SUC\n" +
                        "		ON SUC.IDSUC = PAS.IDSUC\n";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                asignacionDetalle = new AsignacionDetalle();
                asignacionDetalle.setIDDETASIG(rs.getInt("IDDETASIG"));
                asignacionDetalle.setIDASIG(rs.getInt("IDASIG"));
                asignacionDetalle.setIDSUC(rs.getInt("IDSUC"));
                asignacionDetalle.setNOMSUC(rs.getString("NOMSUC"));
                asignacionDetalle.setIDPERJEF(rs.getInt("IDPERJEF"));
                asignacionDetalle.setNOMCOMJEF(rs.getString("NOMCOMJEF"));
                asignacionDetalle.setIDPEREMP(rs.getInt("IDPEREMP"));
                asignacionDetalle.setNOMCOMEMP(rs.getString("NOMCOMEMP"));
                asignacionDetalle.setDNIEMP(rs.getString("DNIEMP"));
                asignacionDetalle.setESTADETASIG(rs.getString("ESTADETASIG"));
                
                listado.add(asignacionDetalle);
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
