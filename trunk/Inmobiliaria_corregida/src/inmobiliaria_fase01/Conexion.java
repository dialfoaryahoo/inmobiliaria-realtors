
package inmobiliaria_fase01;
import java.awt.Dialog;
import java.sql.*;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;
import org.jvnet.substance.SubstanceLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import javax.swing.*;

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
     
     public void Dactualizar2(String ActualizarQuery){
          Statement s = null;
          try{
            s = conexion.createStatement();
            s.executeUpdate(ActualizarQuery);
              System.out.println("Actualizado Correctamente!");

          }
        catch(Exception e){
            System.out.println("PROBLEMA CON EL UPDATE");
        }
                 
     }     

     public void Dinsertar2(String insertarQuery){
          Statement s = null;
          try {
            s = conexion.createStatement();
            s.executeUpdate(insertarQuery);            
            System.out.println("Guardado Correcto");
         } catch (Exception e) {
             System.out.println("Problemas con el Insert");
         }
     }      
     
     //AutoCompletar Diego
     
//     
//    public void cierraConexion()
//    {
//        try
//        {
//            conexion.close();
//            System.out.println("cierro conexion..........");
//        } catch (Exception e)
//        {
//            //e.printStackTrace();
//            System.err.println("dio error en el cierre de la conexion");
//        }
//    }     
//     
//
//    public ResultSet dameLista(String campos, String tabla, String condicion)
//    {
//        ResultSet rs = null;
//        try
//        {
//            // Se crea un Statement, para realizar la consulta
//            Statement s = conexion.createStatement();
//
//            // Se realiza la consulta. Los resultados se guardan en el
//            // ResultSet rs
//            rs = s.executeQuery("select "+campos+" from "+tabla+" "+condicion);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion",
//            JOptionPane.INFORMATION_MESSAGE);
//            System.out.println("select "+campos+" from "+tabla+" "+condicion);
//        }
//        return rs;
//    }
//
//    /**
//     * Crea un resultado segun la consulta que hagamos
//     * @param sql
//     * @return
//     */
//    public ResultSet dameLista(String sql)
//    {
//        ResultSet rs = null;
//        try
//        {
//            // Se crea un Statement, para realizar la consulta
//            Statement s = conexion.createStatement();
//
//            // Se realiza la consulta. Los resultados se guardan en el
//            // ResultSet rs
//            rs = s.executeQuery(sql);
//
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion",
//            JOptionPane.INFORMATION_MESSAGE);
//        }
//        System.out.println(sql);
//        return rs;
//    }
//    /**
//     * Carga un conjunto de combox
//     * @param sql
//     * @param combo
//     */
//    public void cargarCombo(String sql, JComboBox[]combo)
//    {
//        for (int i = 0; i < combo.length; i++)
//            cargarCombo(sql, combo[i]);
//    }
//    /**
//     * Permite colocar datos previos antes de la consulta sql. Recomendacion rapida: Si queremos usar algo mas rapido podremos declarar: new String[][]{ {"-","-"} }
//     * @param datosPrevios
//     * @param sql
//     * @param combo
//     */
//    public void cargarCombo(String[][]datosPrevios, String sql, JComboBox combo)
//    {
//        ResultSet rs = null;
//        int contar=0;
//        combo.removeAllItems();
//        try
//        {
//            /**
//             * Donde agregamos primero los datos que seran antes y luego los que seran segun la consulta sql
//             */
//            for (int i = 0; i < datosPrevios.length; i++)
//            {
//                String columnas[] = new String[2];
//                columnas[0]=datosPrevios[i][0];
//                columnas[1]=datosPrevios[i][1];
//                combo.addItem(columnas);
//            }
//            Statement s = conexion.createStatement();
//            rs = s.executeQuery(sql);
//            System.out.println(sql);
//            while (rs.next())
//            {
//                String columnas[] = new String[2];
//		columnas[0] = rs.getString(1);
//		columnas[1] = rs.getString(2);
//		combo.addItem (columnas);
//                contar++;
//            }
//        } catch(Exception e)
//        {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Ocurrio Un error: "+e.toString() , "Atencion1",
//            JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//        if (contar>0)
//        {
//            combo.setRenderer (new DefaultListCellRenderer()
//            {
//		  public java.awt.Component getListCellRendererComponent (
//		    JList l,Object o,int i,boolean s, boolean f)
//		  {
//                     try{
//		     return new JLabel (((String[])o)[1]);
//                     }catch(Exception e){
//                        JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
//                        JOptionPane.INFORMATION_MESSAGE);
//                        return null;
//                    }
//		  }
//		});
//        }else
//        //variante modificada...
//        {
//            //en caso de error probar lo siguiente
//                //com_horarios.removeAllItems(); puede que no lleve porque siempre se le pasa datos vacios
//                //com_horarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"",""}));
//            //fin caso de error
//
//            //prototipo de error 1
//                combo.addItem(new String[]{"",""});
//                combo.setRenderer (new DefaultListCellRenderer()
//                {
//                      public java.awt.Component getListCellRendererComponent (
//                        JList l,Object o,int i,boolean s, boolean f)
//                      {
//                         try{
//                         return new JLabel (((String[])o)[1]);
//                         }catch(Exception e){
//                            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
//                            JOptionPane.INFORMATION_MESSAGE);
//                            return null;
//                        }
//                      }
//                    });
//            //prototipo de error 1
//        }
//        //fin modificacion
//    }
//    /**
//     * Permite colocar datos posteriores despues de la consulta sql. Recomendacion rapida: Si queremos usar algo mas rapido podremos declarar: new String[][]{ {"-","-"} }
//     * @param datosPrevios
//     * @param sql
//     * @param combo
//     */
//    public void cargarCombo(String sql, String[][]datosPosteriores, JComboBox combo)
//    {
//        ResultSet rs = null;
//        int contar=0;
//        combo.removeAllItems();
//        try
//        {
//            Statement s = conexion.createStatement();
//            rs = s.executeQuery(sql);
//            System.out.println(sql);
//            while (rs.next())
//            {
//                String columnas[] = new String[2];
//		columnas[0] = rs.getString(1);
//		columnas[1] = rs.getString(2);
//		combo.addItem (columnas);
//                contar++;
//            }
//            /**
//             * Donde agregamos primero los datos que seran antes y luego los que seran segun la consulta sql
//             */
//            for (int i = 0; i < datosPosteriores.length; i++)
//            {
//                String columnas[] = new String[2];
//                columnas[0]=datosPosteriores[i][0];
//                columnas[1]=datosPosteriores[i][1];
//                combo.addItem(columnas);
//            }
//        } catch(Exception e)
//        {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Ocurrio Un error: "+e.toString() , "Atencion1",
//            JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//        if (contar>0)
//        {
//            combo.setRenderer (new DefaultListCellRenderer()
//            {
//		  public java.awt.Component getListCellRendererComponent (
//		    JList l,Object o,int i,boolean s, boolean f)
//		  {
//                     try{
//		     return new JLabel (((String[])o)[1]);
//                     }catch(Exception e){
//                        JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
//                        JOptionPane.INFORMATION_MESSAGE);
//                        return null;
//                    }
//		  }
//		});
//        }else
//        //variante modificada...
//        {
//            //en caso de error probar lo siguiente
//                //com_horarios.removeAllItems(); puede que no lleve porque siempre se le pasa datos vacios
//                //com_horarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"",""}));
//            //fin caso de error
//
//            //prototipo de error 1
//                combo.addItem(new String[]{"",""});
//                combo.setRenderer (new DefaultListCellRenderer()
//                {
//                      public java.awt.Component getListCellRendererComponent (
//                        JList l,Object o,int i,boolean s, boolean f)
//                      {
//                         try{
//                         return new JLabel (((String[])o)[1]);
//                         }catch(Exception e){
//                            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
//                            JOptionPane.INFORMATION_MESSAGE);
//                            return null;
//                        }
//                      }
//                    });
//            //prototipo de error 1
//        }
//        //fin modificacion
//    }
//    /**
//     * Carga un combox a partir de la consulta sql
//     * @param sql
//     * @param combo
//     */
//    public void cargarCombo(String sql, JComboBox combo)
//    {
//        ResultSet rs = null;
//        int contar=0;
//        combo.removeAllItems();
//        try{
//            Statement s = conexion.createStatement();
//            rs = s.executeQuery(sql);
//            System.out.println(sql);
//            while (rs.next())
//            {
//                String columnas[] = new String[2];
//		columnas[0] = rs.getString(1);
//		columnas[1] = rs.getString(2);
//		combo.addItem (columnas);
//                contar++;
//            }
//        } catch(Exception e)
//        {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Ocurrio Un error: "+e.toString() , "Atencion1",
//            JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//        if (contar>0)
//        {
//            combo.setRenderer (new DefaultListCellRenderer()
//            {
//		  public java.awt.Component getListCellRendererComponent (
//		    JList l,Object o,int i,boolean s, boolean f)
//		  {
//                     try{
//		     return new JLabel (((String[])o)[1]);
//                     }catch(Exception e){
//                        JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
//                        JOptionPane.INFORMATION_MESSAGE);
//                        return null;
//                    }
//		  }
//		});
//        }else
//        //variante modificada...
//        {
//            //en caso de error probar lo siguiente
//                //com_horarios.removeAllItems(); puede que no lleve porque siempre se le pasa datos vacios
//                //com_horarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"",""}));
//            //fin caso de error
//            
//            //prototipo de error 1
//                combo.addItem(new String[]{"",""});
//                combo.setRenderer (new DefaultListCellRenderer()
//                {
//                      public java.awt.Component getListCellRendererComponent (
//                        JList l,Object o,int i,boolean s, boolean f)
//                      {
//                         try{
//                         return new JLabel (((String[])o)[1]);
//                         }catch(Exception e){
//                            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
//                            JOptionPane.INFORMATION_MESSAGE);
//                            return null;
//                        }
//                      }
//                    });
//            //prototipo de error 1
//        }
//        //fin modificacion
//        return;
//    }
//    /**
//     * Carga un combox a partir de la consulta sql
//     * @param sql
//     * @param combo
//     */
//    public void cargarComboPrototipe(String sql, JComboBox combo){
//        ResultSet rs = null;
//        int contar=0;
//        combo.removeAllItems();
//        try{
//            Statement s = conexion.createStatement();
//            rs = s.executeQuery(sql);
//            System.out.println(sql);
//            while (rs.next())
//            {
//                String columnas[] = new String[2];
//		columnas[0] = rs.getString(1);
//		columnas[1] = rs.getString(2);
//		combo.addItem (columnas);
//                contar++;
//            }
//        } catch(Exception e)
//        {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Ocurrio Un error: "+e.toString() , "Atencion1",
//            JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//        if (contar>0)
//        {
//            combo.setRenderer (new DefaultListCellRenderer()
//            {
//		  public java.awt.Component getListCellRendererComponent (
//		    JList l,Object o,int i,boolean s, boolean f)
//		  {
//                    //l.setPreferredSize(new Dimension (300, 200));
//                    
//                    l.setFixedCellHeight(20);
//
//                     try{
//		     return new JLabel (((String[])o)[1]);
//                     }catch(Exception e){
//                        JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
//                        JOptionPane.INFORMATION_MESSAGE);
//                        return null;
//                    }
//		  }
//		});
//        }else
//        //variante modificada...
//        {
//            //en caso de error probar lo siguiente
//                //com_horarios.removeAllItems(); puede que no lleve porque siempre se le pasa datos vacios
//                //com_horarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"",""}));
//            //fin caso de error
//
//            //prototipo de error 1
//                combo.addItem(new String[]{"",""});
//                combo.setRenderer (new DefaultListCellRenderer()
//                {
//                      public java.awt.Component getListCellRendererComponent (
//                        JList l,Object o,int i,boolean s, boolean f)
//                      {
//                         try{
//                         return new JLabel (((String[])o)[1]);
//                         }catch(Exception e){
//                            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
//                            JOptionPane.INFORMATION_MESSAGE);
//                            return null;
//                        }
//                      }
//                    });
//            //prototipo de error 1
//        }
//        //fin modificacion
//        return;
//    }     
    
    
     //////////////////////////
     
     
   
     
     public static void JOptionShowMessage (String tamaño,String alineacion,String mensaje){   
                     JOptionPane.showMessageDialog(null,"<html><font size="+tamaño+" face='Tahoma'><h3 align='"+alineacion+"'>"+mensaje+"</h3></font></html>");
        }
     public static void dialog(){
        JDialog.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceNegatedTheme");
        
     }
    }
   