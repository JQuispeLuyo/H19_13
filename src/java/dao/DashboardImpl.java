/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.RankingVendedor;
import modelo.VentaSucursal;

/**
 *
 * @author jq_00
 */
public class DashboardImpl extends Conexion{
    
    
    public List<VentaSucursal> listarVentaSucursal() throws Exception {
        List<VentaSucursal> lista = new ArrayList();
        VentaSucursal ventaSucursal;
        try {
            this.conectar();

            String sql = "select \n" +
                        "	TOP 3\n" +
                        "	YEAR (VT.FECVEN ) AS FEC,\n" +
                        "	VT.NOMSUC,\n" +
                        "	COUNT(VT.NOMSUC) AS CONT,\n" +
                        "	SUM(VT.PREEQUI * VT.CANTEQUI ) AS TOTAL\n" +
                        "from\n" +
                        "(SELECT	V.IDVENT,\n" +
                        "		V.IDEMP,\n" +
                        "		V.FECVEN,\n" +
                        "		VD.CANTEQUI,\n" +
                        "		EQ.PREEQUI,\n" +
                        "		PAS.IDSUC,\n" +
                        "		SUC.NOMSUC\n" +
                        "		FROM VENTA.VENTA as V\n" +
                        "		inner join VENTA.DETALLE_VENTA AS VD\n" +
                        "			ON V.IDVENT = VD.IDVENT\n" +
                        "		inner join PRODUCTO.EQUIPO EQ\n" +
                        "			ON VD.IDEQUI = EQ.IDEQUI\n" +
                        "		inner join PERSONA.DETALLE_ASIGNACION AS DTA\n" +
                        "			ON V.IDEMP = DTA.IDDETASIG\n" +
                        "		inner join PERSONA.ASIGNACION AS PAS\n" +
                        "			ON PAS.IDASIG = DTA.IDASIG\n" +
                        "		inner join UBICACION.SUCURSAL AS SUC\n" +
                        "			ON PAS.IDSUC = SUC.IDSUC\n" +
                        ") AS VT\n" +
                        "GROUP BY VT.NOMSUC,\n" +
                        "		YEAR (VT.FECVEN )\n" +
                        "ORDER BY VT.NOMSUC DESC\n";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ventaSucursal = new VentaSucursal();
                ventaSucursal.setNOMSUC(rs.getString("NOMSUC"));
                ventaSucursal.setFEC(rs.getString("FEC"));
                ventaSucursal.setCONT(rs.getInt("CONT"));
                ventaSucursal.setTOTAL(rs.getDouble("TOTAL"));
                lista.add(ventaSucursal);
                System.out.println("resultado");
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    public int getMaxVentaSucursal() throws Exception {
        try {
            this.conectar();

            String sql = "SELECT	TOP 1\n" +
                        "		YEAR (V.FECVEN) AS AÑO,\n" +
                        "		SUM((VD.CANTEQUI * EQ.PREEQUI)) AS TOTAL,\n" +
                        "		PAS.IDSUC\n" +
                        "		FROM VENTA.VENTA as V\n" +
                        "		inner join VENTA.DETALLE_VENTA AS VD\n" +
                        "			ON V.IDVENT = VD.IDVENT\n" +
                        "		inner join PRODUCTO.EQUIPO EQ\n" +
                        "			ON VD.IDEQUI = EQ.IDEQUI\n" +
                        "		inner join PERSONA.DETALLE_ASIGNACION AS DTA\n" +
                        "			ON V.IDEMP = DTA.IDDETASIG\n" +
                        "		inner join PERSONA.ASIGNACION AS PAS\n" +
                        "			ON PAS.IDASIG = DTA.IDASIG\n" +
                        "		inner join UBICACION.SUCURSAL AS SUC\n" +
                        "			ON PAS.IDSUC = SUC.IDSUC\n" +
                        "		GROUP BY \n" +
                        "			PAS.IDSUC,\n" +
                        "			YEAR (V.FECVEN)\n" +
                        "		ORDER BY TOTAL DESC\n";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                return rs.getInt("TOTAL");
                
            }
        } catch (Exception e) {
            throw e;
        }
        return 0 ;
    }
    
    
    public List<RankingVendedor> listarRankingVendedor() throws Exception {
        List<RankingVendedor> lista = new ArrayList();
        RankingVendedor rankingVendedor;
        try {
            this.conectar();

            String sql = "SELECT	TOP 3\n" +
                        "		P.IDPER,\n" +
                        "		CONCAT(P.NOMPER,' ',P.APEPER) AS NOMPERCOM,\n" +
                        "		SUM(DV.CANTEQUI) AS TOTAL\n" +
                        "	FROM VENTA.VENTA AS V\n" +
                        "		INNER JOIN VENTA.DETALLE_VENTA AS DV\n" +
                        "			ON V.IDVENT = DV.IDVENT\n" +
                        "		INNER JOIN PERSONA.DETALLE_ASIGNACION DTA\n" +
                        "			ON DTA.IDDETASIG = V.IDEMP \n" +
                        "		INNER JOIN PERSONA.PERSONA AS P\n" +
                        "			ON DTA.IDPEREMP = P.IDPER\n" +
                        "	WHERE DTA.ESTADETASIG = 'A' AND P.ESTAPER = 'A'\n" +
                        "	GROUP BY P.IDPER, CONCAT(P.NOMPER,' ',P.APEPER)\n" +
                        "	\n";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                rankingVendedor = new RankingVendedor();
                rankingVendedor.setIDPER(rs.getInt("IDPER"));
                rankingVendedor.setNOMPERCOM(rs.getString("NOMPERCOM"));
                rankingVendedor.setTOTAL(rs.getInt("TOTAL"));
                lista.add(rankingVendedor);
 
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }
    
    public int getMaxRankingVendedor() throws Exception {
        try {
            this.conectar();

            String sql = "SELECT	TOP 1\n" +
                        "		P.IDPER,\n" +
                        "		CONCAT(P.NOMPER,' ',P.APEPER) AS NOMPERCOM,\n" +
                        "		SUM(DV.CANTEQUI) AS TOTAL\n" +
                        "	FROM VENTA.VENTA AS V\n" +
                        "		INNER JOIN VENTA.DETALLE_VENTA AS DV\n" +
                        "			ON V.IDVENT = DV.IDVENT\n" +
                        "		INNER JOIN PERSONA.DETALLE_ASIGNACION DTA\n" +
                        "			ON DTA.IDDETASIG = V.IDEMP \n" +
                        "		INNER JOIN PERSONA.PERSONA AS P\n" +
                        "			ON DTA.IDPEREMP = P.IDPER\n" +
                        "	WHERE DTA.ESTADETASIG = 'A' AND P.ESTAPER = 'A'\n" +
                        "	GROUP BY P.IDPER, CONCAT(P.NOMPER,' ',P.APEPER)\n" +
                        "	ORDER BY TOTAL DESC\n";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                return rs.getInt("TOTAL");
                
            }
        } catch (Exception e) {
            throw e;
        }
        return 0 ;
    }
}
