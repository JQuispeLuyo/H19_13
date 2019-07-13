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
import modelo.Persona;

/**
 *
 * @author PC
 */
public class PersonaImpl extends Conexion implements ICRUD<Persona> {
    @Override
    public void registrar(Persona modelo) throws Exception {

        try {

            this.conectar();
            String sql = "INSERT INTO PERSONA.PERSONA" +
                        " (NOMPER,APEPER,DNIPER,TIPPER,PSWPER,ESTAPER)" +
                        " values (?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setString(1, modelo.getNOMPER());
            ps.setString(2, modelo.getAPEPER());
            ps.setString(3, modelo.getDNIPER());
            ps.setString(4, modelo.getTIPPER());
            ps.setString(5, modelo.getPSWPER());
            ps.setString(6, modelo.getESTAPER());
            
            ps.executeUpdate();
            ps.close();;
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }

    }

    @Override
    public void modificar(Persona modelo) throws Exception {

         try {

            this.conectar();
            String sql = "UPDATE PERSONA.PERSONA" +
                        " (NOMPER=?,APEPER=?,DNIPER=?,TIPPER=?,PSWPER=?,ESTAPER=?)" +
                        " where IDPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setString(1, modelo.getNOMPER());
            ps.setString(2, modelo.getAPEPER());
            ps.setString(3, modelo.getDNIPER());
            ps.setString(4, modelo.getTIPPER());
            ps.setString(5, modelo.getPSWPER());
            ps.setString(6, modelo.getESTAPER());
            ps.setInt(7, modelo.getIDPER());
            ps.close();;
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }

    }

    @Override
    public void eliminar(Persona modelo) throws Exception {

        
        try {

            this.conectar();
            String sql = "UPDATE PERSONA.PERSONA"
                    + "	set ESTAPER='I'"
                    + "	where IDPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setInt(7, modelo.getIDPER());
            ps.close();;
            
            ps.executeUpdate();
            ps.close();;
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }

    }

    @Override
    public List listar() throws Exception {

        List<Equipo> listado;
        Equipo equipo;
        try {
            this.conectar();
            String sql = "SELECT * FROM PRODUCTO.EQUIPO";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                equipo = new Equipo();
                equipo.setIDEQUI(rs.getInt("IDEQUI"));
                equipo.setDESEQUI(rs.getString("IDEQUI"));
                equipo.setPREEQUI(rs.getDouble("IDEQUI"));
                equipo.setESTAEQUI(rs.getString("IDEQUI"));

                listado.add(equipo);
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
