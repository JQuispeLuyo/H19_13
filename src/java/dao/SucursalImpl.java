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
import modelo.Sucursal;

/**
 *
 * @author PC23
 */
public class SucursalImpl extends Conexion implements ICRUD<Sucursal> {

    @Override
    public void registrar(Sucursal modelo) throws Exception {
        try {

            this.conectar();
            String sql = "INSERT INTO UBICACION.SUCURSAL"
                    + "	(NOMSUC, ESTASUC)"
                    + "	VALUES(?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setString(1, modelo.getNOMSUC());
            ps.setString(2, "A");
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void modificar(Sucursal modelo) throws Exception {
       try {
            this.conectar();
            String sql = "UPDATE UBICACION.SUCURSAL"
                    + "	set NOMSUC=?"
                    + "	where IDEQUI=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setString(1, modelo.getNOMSUC());
            ps.setInt(2, modelo.getIDSUC());   
            ps.executeUpdate();
            ps.close();;
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Sucursal modelo) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE UBICACION.SUCURSAL"
                    + "	set ESTASUC='I'"
                    + "	where IDSUC=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setInt(1, modelo.getIDSUC());   
            ps.executeUpdate();
            ps.close();;
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

    @Override
    public List<Sucursal> listar() throws Exception {
       List<Sucursal> listado;
        Sucursal sucursal;
        try {
            this.conectar();
            String sql = "SELECT * FROM UBICACION.SUCURSAL";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                sucursal = new Sucursal();
                sucursal.setIDSUC(rs.getInt("IDSUC"));
                sucursal.setNOMSUC(rs.getString("NOMSUC"));
                sucursal.setESTASUC(rs.getString("ESTASUC"));
                
                listado.add(sucursal);
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
