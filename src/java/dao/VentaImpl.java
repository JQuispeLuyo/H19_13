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
import modelo.Equipo;
import modelo.Venta;

/**
 *
 * @author PC
 */
public class VentaImpl extends Conexion implements ICRUD<Venta>{

    @Override
    public void registrar(Venta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar(Venta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Venta modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venta> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void registrar(Venta modelo) throws Exception {
//
//        try {
//
//            this.conectar();
//            String sql = "INSERT INTO PRODUCTO.VENTA"
//                    + "	(DESEQUI, PREEQUI, ESTAEQUI)"
//                    + "	VALUES(?,?,?)";
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
//            
//            ps.setDouble(2, modelo.getPREEQUI());
//            ps.setString(3, modelo.getESTAEQUI());
//            
//            ps.executeUpdate();
//            ps.close();;
//        } catch (Exception e) {
//            throw e;
//        }finally{
//            this.cerrar();
//        }
//
//    }
//
//    @Override
//    public void modificar(Venta modelo) throws Exception {
//
//         try {
//
//            this.conectar();
//            String sql = "Update PRODUCTO.EQUIPO"
//                    + "	set DESEQUI=?, PREEQUI=?"
//                    + "	where IDEQUI=?";
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
//            
//            ps.setString(1, modelo.getDESEQUI());
//            ps.setDouble(2, modelo.getPREEQUI());   
//            ps.setInt(3, modelo.getIDEQUI());
//            ps.executeUpdate();
//            ps.close();;
//        } catch (Exception e) {
//            throw e;
//        }finally{
//            this.cerrar();
//        }
//
//    }
//
//    @Override
//    public void eliminar(Venta modelo) throws Exception {
//
//        
//        try {
//
//            this.conectar();
//            String sql = "Update PRODUCTO.EQUIPO"
//                    + "	set getESTAEQUI'I'"
//                    + "	where IDEQUI=?";
//            PreparedStatement ps = this.getCn().prepareStatement(sql);
//            
//            ps.setInt(1, modelo.getIDEQUI());
//            ps.executeUpdate();
//            ps.close();;
//        } catch (Exception e) {
//            throw e;
//        }finally{
//            this.cerrar();
//        }
//
//    }
//
//    @Override
//    public List listar() throws Exception {
//
//        List<Equipo> listado;
//        Equipo equipo;
//        try {
//            this.conectar();
//            String sql = "SELECT * FROM PRODUCTO.EQUIPO";
//            listado = new ArrayList();
//            Statement st = this.getCn().createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                equipo = new Equipo();
//                equipo.setIDEQUI(rs.getInt("IDEQUI"));
//                equipo.setDESEQUI(rs.getString("IDEQUI"));
//                equipo.setPREEQUI(rs.getDouble("IDEQUI"));
//                equipo.setESTAEQUI(rs.getString("IDEQUI"));
//
//                listado.add(equipo);
//            }
//            rs.close();
//            st.close();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            this.cerrar();
//        }
//        return listado;
//
//    }

 
}
