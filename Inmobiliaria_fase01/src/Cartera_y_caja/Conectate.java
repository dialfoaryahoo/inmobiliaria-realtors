
package Cartera_y_caja;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;

public class Conectate{

     public Connection conexion = null;  
    
     public void establecer_conexion(){
        if (conexion != null)
            return;
        String url = "jdbc:postgresql://localhost/DB_INMOBILIARIA";
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
    }
     public ResultSet consulta(String ConsultaQuery){
        ResultSet rs = null;
        Statement s = null;
        try{
            s = conexion.createStatement();
            rs=s.executeQuery(ConsultaQuery);
                  
        }
        catch(Exception e){
            System.out.println("PROBLEMA CON LA CONSULTA");
    }
    return rs;
    }
     public int insertar(String insertarQuery){
          Statement s = null;
          int vinsert = 0;
          try{
            s = conexion.createStatement();
            s.executeUpdate(insertarQuery);
              JOptionPane.showMessageDialog(null,"Guardado Correcto!");
              vinsert = 1;
              return vinsert;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Problemas con el Insert!");;
            return vinsert;
    }
         
                 
     }
     public int actualizar(String ActualizarQuery){
          Statement s = null;
          int vupdate = 0;
          try{
            s = conexion.createStatement();
            s.executeUpdate(ActualizarQuery);
              System.out.println("Actualizado Correctamente!");
            System.out.println("Update Sucess");
            vupdate=1;
            return vupdate;
          }
        catch(Exception e){
            System.out.println("PROBLEMA CON EL UPDATE");
            vupdate=0;
            return vupdate ;
    }
                 
     }

    void insertar(ResultSet insert) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   }       
          
//     public void consulta(String nom){
//         this.establecer_conexion();
//if(conexion != null){
//    ResultSet n = datos(nom);
//
//    try{
//        while(n.next()){
//            JOptionPane.showMessageDialog(null,n.getString(2));
//
//            }
//    }
//    catch(Exception e){
//        System.out.println("Error");
//    }
//}
//     }

