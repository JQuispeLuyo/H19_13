/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Persona;

/**
 *
 * @author PC23
 */
public class Login extends Conexion{
    
//    public Persona startSession(String User, String Pass) throws Exception {
//        this.conectar();
//        ResultSet rs;
//        Persona persona = null;
//        try {
//            String sql = "Select Codigo,Nombre,Apellido,Correo,DNI,Celular,Nivel from Usuarios where Usuario like ? and Pass like ? and Estado like 'A'";
//            PreparedStatement ps = this.getCn().prepareCall(sql);
//            ps.setString(1, User);
//            ps.setString(2, Pass);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                persona = new Persona();
//                persona.setIDPER(rs.getInt("IDPER"));
//                persona.setNOMPER(rs.getString("NOMPER"));
//                persona.setAPEPER(rs.getString("APEPER"));
//                persona.setDNIPER(rs.getString("DNIPER"));
//                persona.setPSWPER(rs.getString("PSWPER"));
//                persona.setTIPPER(rs.getString("TIPPER"));
//                persona.setESTAPER(rs.getString("ESTAPER"));
//                persona.setUsuario(User);
//                persona.setPassword(Pass);
//            }
//            return usuario;
//        } catch (SQLException e) {
//            throw e;
//        }
//    }
    
}
