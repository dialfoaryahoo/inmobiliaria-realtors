
package simcards;
import java.awt.Dialog;
import java.sql.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;
import org.jvnet.substance.SubstanceLookAndFeel;

public class Conexion{

     public Connection conexion = null;  
    
     public Connection establecer_conexion(){
        if (conexion != null)
            return conexion;
        String url = "jdbc:postgresql://localhost/cine";
        try{
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url,"postgres","123456");
            if (conexion!=null){
                System.out.println("CONECTADO");
            }
        }
        catch(Exception e){
            System.out.println("PROBLEMA PARA CONECTAR");
        }
        return conexion;
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
              JOptionPane.showMessageDialog(null,"Guardado Correcto!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Problemas con el Insert!");;
    }
                 
     }
     public int insertar_venta(String insertarQuery){
          Statement s = null;
          int a=0;
          try{
            s = conexion.createStatement();
            s.executeUpdate(insertarQuery);
            a=1;
            }
            catch(Exception e){
            }
            return a; 
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
     
        //Metodos  Diego
     public int Dinsertar(String insertarQuery){
          Statement s = null;
          int vinsert = 0;
          try {
            s = conexion.createStatement();
            s.executeUpdate(insertarQuery);            
            System.out.println("Guardado Correcto");
            JOptionShowMessage("+1", "center", "Guardado Correcto");
            vinsert = 1;
            return vinsert;
            
         } catch (Exception e) {
             System.out.println("Problemas con el Insert");
             return vinsert;
         }
     }  
     
     public int Dactualizar(String ActualizarQuery, String mensaje){
          Statement s = null;
          int vupdate = 0;
          try{
            s = conexion.createStatement();
            s.executeUpdate(ActualizarQuery);
              System.out.println("Actualizado Correctamente!");
              JOptionShowMessage("+1", "center", mensaje);
            vupdate=1;
            return vupdate;
          }
        catch(Exception e){
            System.out.println("PROBLEMA CON EL UPDATE");
            vupdate=0;
            return vupdate ;
        }
                 
     }
     
     public static void JOptionShowMessage (String tamaño,String alineacion,String mensaje){   
                     JOptionPane.showMessageDialog(null,"<html><font size="+tamaño+" face='Tahoma'><h3 align='"+alineacion+"'>"+mensaje+"</h3></font></html>");
        }
     public static void dialog(){
        JDialog.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceNegatedTheme");
        
     }
     public static boolean validar(Object[] datos) { 
        for (int i = 0; i <= datos.length - 1; i++) { 
                if (datos[i].toString().isEmpty()) {
                JOptionShowMessage("+1", null, "Algunos campos estan vacios");
                return false;
                } 
        
        }
        return true;
    } 
    }
   