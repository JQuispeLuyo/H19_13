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
            ps.setString(6, "A");
            
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
                        " set NOMPER=?, APEPER=?, DNIPER=?, TIPPER=?" +
                        " where IDPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setString(1, modelo.getNOMPER());
            ps.setString(2, modelo.getAPEPER());
            ps.setString(3, modelo.getDNIPER());
            ps.setString(4, modelo.getTIPPER());
            ps.setInt(5, modelo.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }

    }

    public void modificarCredencial(Persona modelo) throws Exception {

         try {

            this.conectar();
            String sql = "UPDATE PERSONA.PERSONA" +
                        " set PSWPER=?" +
                        " where IDPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            
            ps.setString(1, modelo.getPSWPER());
            ps.setInt(2, modelo.getIDPER());
            ps.executeUpdate();
            ps.close();
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
            
            ps.setInt(1, modelo.getIDPER());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            throw e;
        }finally{
            this.cerrar();
        }

    }

    @Override
    public List listar() throws Exception {

        
        List<Persona> listado;
        Persona persona;
        try {
            this.conectar();
            String sql = "SELECT * FROM PERSONA.PERSONA";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persona = new Persona();
                persona.setIDPER(rs.getInt("IDPER"));
                persona.setNOMPER(rs.getString("NOMPER"));
                persona.setAPEPER(rs.getString("APEPER"));
                persona.setDNIPER(rs.getString("DNIPER"));
                persona.setPSWPER(rs.getString("PSWPER"));
                persona.setTIPPER(rs.getString("TIPPER"));
                persona.setESTAPER(rs.getString("ESTAPER"));

                listado.add(persona);
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

    
    public List listarJefe() throws Exception {

        
        List<Persona> listado;
        Persona persona;
        try {
            this.conectar();
            String sql = "SELECT IDPER," +
                    "		CONCAT(NOMPER,' ',APEPER) AS NOMCOMJEF," +
                    "		DNIPER" +
                    "	FROM PERSONA.PERSONA" +
                    "	where TIPPER = 'J'";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                persona = new Persona();
                persona.setIDPER(rs.getInt("IDPER"));
                persona.setNOMPER(rs.getString("NOMCOMJEF"));
                persona.setDNIPER(rs.getString("DNIPER"));

                listado.add(persona);
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
