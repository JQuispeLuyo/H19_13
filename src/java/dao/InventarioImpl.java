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
import modelo.Inventario;

/**
 *
 * @author PC31
 */
public class InventarioImpl extends Conexion implements ICRUD<Inventario> {

    @Override
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

    @Override
    public void modificar(Inventario modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Inventario modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Inventario> listar() throws Exception {

        List<Inventario> lista = new ArrayList();
        Inventario inventario;
        try {
            this.conectar();

            String sql = "SELECT * FROM PRODUCTO.INVENTARIO";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                inventario = new Inventario();
                inventario.setIDINV(rs.getInt("IDINV"));
                inventario.setIDEQUI(rs.getInt("IDEQUI"));
                inventario.setFECINV(rs.getDate("FECINV"));
                inventario.setCANTINV(rs.getInt("CANTINV"));
                inventario.setTIPINV(rs.getString("TIPINV"));
                lista.add(inventario);
            }
        } catch (Exception e) {
            throw e;
        }
        return lista;
    }

}
