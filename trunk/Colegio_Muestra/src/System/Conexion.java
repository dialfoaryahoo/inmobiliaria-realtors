/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import Config.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Conexion {
    public static Connection conexion = null;  
    
    public static void establecer_conexion(){
        if (conexion != null)
            return;
        String url = "jdbc:postgresql://"+Database.IP_DB+"/"+Database.Nombre_DB;
        try{
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url,Database.Usuario_DB,Database.Contraseña_DB);
            if (conexion!=null){
                System.out.println("Conexion Exitosa");
            }
        }
        catch(Exception e){
               System.out.println("Problemas para conectar\nCod Error: "+e);
        }
    }
     
    public void actualizar(String ActualizarQuery){
          Statement s = null;
          try{
            s = conexion.createStatement();
            s.executeUpdate(ActualizarQuery);
              System.out.println("Datos Actualizados Correctamente");
          }
        catch(Exception e){
            System.out.println("PROBLEMA CON EL UPDATE");
    }
                 
     }
    public int insertar_venta(String insertarQuery){
          Statement s = null;
          int a=0;
          try{
            s = conexion.createStatement();
            s.executeUpdate(insertarQuery);
            a=1;
              System.out.println("guardado");
            }
            catch(Exception e){
            }
            return a; 
     }
     
    public ResultSet consulta(String ConsultaQuery){
        ResultSet rs = null;
        Statement s = null;
        try{
            s = conexion.createStatement();
            rs=s.executeQuery(ConsultaQuery);
                  
        }
        catch(Exception e){
            System.out.println(e);
    }
    return rs;
    }
        
    public void insertar(String insertarQuery){
          Statement s = null;
          try{
            s = conexion.createStatement();
            s.executeUpdate(insertarQuery);
            Utilidades.JOptionShowMessage("+1",  "GUARDADO CORRECTO!");
        }
        catch(Exception e){
           Utilidades.JOptionShowMessage("+1", "PROBLEMAS AL GUARDAR, Por favor verifique todos los datos");
        }
                 
     }
    
    public int Dinsertar(String insertarQuery){
          Statement s = null;
          int vinsert = 0;
          try {
            s = conexion.createStatement();
            s.executeUpdate(insertarQuery);            
            System.out.println("Guardado Correcto");
            Utilidades.JOptionShowMessage("+1", "Guardado Correcto");
            vinsert = 1;
            return vinsert;
            
         } catch (Exception e) {
             System.out.println("Problemas con el Insert");
             return vinsert;
         }
     }
    
    public void actualizar(String ActualizarQuery, String mensaje){
          Statement s = null;
          try{
            s = conexion.createStatement();
            s.executeUpdate(ActualizarQuery);
              System.out.println("Datos Actualizados Correctamente");
              Utilidades.JOptionShowMessage("+1",  mensaje);
          }
        catch(Exception e){
            System.out.println("PROBLEMA CON EL UPDATE");
    }
          
     }

}