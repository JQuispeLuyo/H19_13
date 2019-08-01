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
import modelo.Persona;

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
            String sql = "SELECT * FROM PERSONA.PERSONA WHERE DNIPER = ? AND PSWPER = ?";
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
    
}
