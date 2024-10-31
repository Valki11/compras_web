package miumg.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static final String username = "root";
    public static final String password = "";
    public static final String database = "db_compra";
    public static final String url = "jdbc:mysql://localhost:3306/" + database;
    
    public static Connection getConnection(){
        System.out.println("entra 1");
        Connection cn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexion satisfactoria");
        }catch(Exception ex){
            System.out.println("error");
            ex.printStackTrace();
        }
        
        return cn;
    }
}
