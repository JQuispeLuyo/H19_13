/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Conexion {

    private Connection cn;

    public void conectar() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://db-server-ventas.database.windows.net;databaseName=H19_13_BD";
            String user = "ServerUser";
            String password = "dbPassF!nd";

            this.cn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cerrar() throws SQLException {

        if (this.cn != null) {
            if (this.cn.isClosed() == false) {
                this.cn.close();
            }
        }
    }

    public static void main(String[] args) {
        Conexion con = new Conexion();
        System.out.println("entra");
        con.conectar();
        System.out.println("sale");
        if (con.cn != null) {
            System.out.println("Conectado :D");
        }
    }

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    
    

}
