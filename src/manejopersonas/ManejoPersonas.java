/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejopersonas;

import datos.Conexion;
import datos.PersonaJDBC;
import java.sql.*;
import domain.Persona;
import java.util.List;
/**
 *
 * @author angue
 */
public class ManejoPersonas {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        PersonaJDBC personasJDBC = new PersonaJDBC();
	
        //Creamos un objeto conexion, se va a compartir
        //para todos los queries que ejecutemos
        Connection conn = null;

        try {
            conn = Conexion.getConnection();
			//Revisamos si la conexion esta en modo autocommit
            //por default es autocommit == true
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
			//Creamos el objeto PersonasJDBC
            //proporcionamos la conexion creada
            PersonaJDBC personas = new PersonaJDBC(conn);

			//empezamos a ejecutar sentencias
            //recordar que una transaccion agrupa varias
            //sentencias SQL
            //si algo falla no se realizan los cambios en 
            //la BD
            //cambio correcto
            personas.update(2, "asdasd", "asdasda");

            //Provocamos un error superando los 45 caracteres
            //del campo de apellido
            personas.insert("Miguel2",
                    "Ayala12341234123412341234123412341234123414123412341234123412341234");
                    //"Ayala2");
            //guardamos los cambios
            conn.commit();
        } catch (SQLException e) {
            //Hacemos rollback en caso de error
            try {
                System.out.println("Entramos al rollback");
                //Imprimimos la excepcion a la consola
                e.printStackTrace(System.out);
                //Hacemos rollback
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace(System.out);
            }
        }
        
    }
    
}
