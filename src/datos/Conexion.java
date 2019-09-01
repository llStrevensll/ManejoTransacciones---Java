/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
/**
 *
 * @author angue
 */
public class Conexion {
    //Valores de conexion mysql
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //El puerto es opcional
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/sga?useSSL=false";//ssl=false para evitar warning;
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "admin";
    private static Driver driver = null;
    //Para que no haya problemas al obtener la conexion de
    //manera concurrente, se usa la palabra synchronized
    public static synchronized Connection getConnection() throws SQLException {//Synchronized->Permite que solo un hilo puede usar el metodo a la vez
        if(driver == null){
            try {
                
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();//nueva instancia
                DriverManager.registerDriver(driver);//registrar driver
                
            } catch (Exception e){
                System.out.println("Fallo en cargar el driver JDBC");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);//regresa objeto de tipo coneection ya instanciado
    }
    
    //Cierre del resultSet
    public static void close(ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        } catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    //Cierre del PrepareStatement
    public static void close(PreparedStatement stmt){
        try{
            if(stmt != null){
                stmt.close();
            }
        } catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    //Cierre de la conexión
    public static void close(Connection conn){
        try{
            if(conn != null){
                conn.close();
            }
        } catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
