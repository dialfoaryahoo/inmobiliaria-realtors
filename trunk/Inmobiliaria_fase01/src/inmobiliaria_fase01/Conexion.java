
package inmobiliaria_fase01;
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
        String url = "jdbc:postgresql://localhost/BD_INMOBILIARIA";
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
              JOptionShowMessage("+1", "center", "Guardado Correcto");
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
     public void actualizar(String ActualizarQuery, String mensaje){
          Statement s = null;
          try{
            s = conexion.createStatement();
            s.executeUpdate(ActualizarQuery);
              System.out.println("Datos Actualizados Correctamente");
              JOptionShowMessage("+1", "center", mensaje);
          }
        catch(Exception e){
            System.out.println("PROBLEMA CON EL UPDATE");
    }
                 
     }
     public static void JOptionShowMessage (String tamaño,String alineacion,String mensaje){   
                     JOptionPane.showMessageDialog(null,"<html><font size="+tamaño+" face='Tahoma'><h3 align='"+alineacion+"'>"+mensaje+"</h3></font></html>");
        }
     public static void dialog(){
        JDialog.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceNegatedTheme");
        
     }
    }
   