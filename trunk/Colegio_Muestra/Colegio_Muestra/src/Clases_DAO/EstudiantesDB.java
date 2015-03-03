package Clases_DAO;

import Clases.Estudiante;
import System.Conexion;
import System.Utilidades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EstudiantesDB {
    public static String Llave_Primaria="id_estudiantes";
    public static String Nombre_Tabla="estudiantes";
    //Indicar el numero de campos relacionados en la variable =16
    public static String Campos_Tabla=" identificacion, nombre_completo, fecha_nac, lugar_nac, convive, direccion,edad,estado";
    
    public static int insertar(){
        PreparedStatement stmt = null;
        Conexion.establecer_conexion();
        try{
            stmt = Conexion.conexion.prepareStatement
                ("INSERT INTO "+Nombre_Tabla+" ("+Campos_Tabla+") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, Estudiante.identificacion);
                stmt.setString(2, Estudiante.nombre_completo);
                stmt.setDate(3, new java.sql.Date(Estudiante.fecha_nac.getTime()));
                stmt.setString(4, Estudiante.lugar_nac);
                stmt.setString(5, Estudiante.convive);
                stmt.setString(6, Estudiante.direccion);
                stmt.setInt(7, Estudiante.edad);
                stmt.setInt(8, 1);
                int retorno = stmt.executeUpdate();
                return retorno;
        }
        catch(Exception e){
                System.out.println(e); 
                Utilidades.JOptionShowMessage("+1", "Problemas al guardar...  ERROR= "+e);
        }
        return  0;
    }
    
    public static boolean load_ONE(String codigo, int param){
        PreparedStatement stmt = null;
        Conexion.establecer_conexion();
        boolean existe=false;
        try{
            if(param==1){
            stmt = Conexion.conexion.prepareStatement
            ("select * from estudiantes where  identificacion="+codigo+"");
            }else if(param==2){
            stmt = Conexion.conexion.prepareStatement
            ("select * from estudiantes where  nombre_completo='"+codigo+"'");
            }
            ResultSet n = stmt.executeQuery();
            while(n.next()){
                existe=true;
                Estudiante est = new Estudiante(n.getInt("id_estudiante"), n.getInt("id_padre_familia"), n.getString("identificacion"), 
                                                n.getString("nombre_completo"), n.getDate("fecha_nac"), n.getString("lugar_nac"), 
                                                n.getString("convive"), n.getString("direccion"), n.getInt("edad"), n.getInt("estado"));
            }
            System.out.println(stmt.toString());
        }
        catch(Exception e){
                System.out.println(e); 
                Utilidades.JOptionShowMessage("+1", "ERROR= "+e);
        }
        return existe;
		
    }
    
    public static int actualizar(int codigo){
          PreparedStatement stmt = null;
          Conexion.establecer_conexion();
          String Actualizar_campos=Campos_Tabla.replace(",", "=?,");
          Actualizar_campos+="=?";
          System.out.println(Actualizar_campos);
          try{
            stmt = Conexion.conexion.prepareStatement
            ("update "+Nombre_Tabla+" set "+Actualizar_campos+" where "+Llave_Primaria+"=?");
                int retorno = stmt.executeUpdate();
                return retorno;
          }
        catch(Exception e){
                System.out.println(e); 
                Utilidades.JOptionShowMessage("+1", "Problemas al actualizar... ERROR= "+e);
                return 0;
        }
        
     }
    
    public static int cantcolumn(){
          int columnas=0;
          PreparedStatement stmt = null;
          Conexion.establecer_conexion();
          boolean existe=false;
          try{
            stmt = Conexion.conexion.prepareStatement
            ("select count(*) from "+Nombre_Tabla); 
            ResultSet n = stmt.executeQuery();
            while(n.next()){
                columnas=n.getInt(1);
            }
          }catch(Exception e){
              
          }
          return columnas;
    }
    
    public static String[][] load_ALL(){
          PreparedStatement stmt = null;
          Conexion.establecer_conexion();
          boolean existe=false;
          try{
            stmt = Conexion.conexion.prepareStatement
            ("select * from "+Nombre_Tabla); 
            ResultSet n = stmt.executeQuery();
            int columnas=n.getMetaData().getColumnCount();
            String [][]datos = new String [cantcolumn()+1][columnas];
            
            int i=0;
            while(n.next()){
                System.out.println(i+" "+columnas+" "+cantcolumn());
                for(int j=0;j<columnas;j++){
                    
                datos[i][j]=n.getString(j+1);
                
                }
                i++;
            }
            return datos;
	
          }
        catch(Exception e){
                System.out.println(e); 
                return null;
        }
}

    public static String[] Vector(){
          PreparedStatement stmt = null;
          Conexion.establecer_conexion();
          boolean existe=false;
          try{
            stmt = Conexion.conexion.prepareStatement
            ("select nombre_completo from "+Nombre_Tabla+" order by nombre_completo"); 
            ResultSet n = stmt.executeQuery();
            String [] nombre;
            int i=1;
            nombre= new String[cantcolumn()+1];
            nombre[0]="SELECCIONE UN ESTUDIANTE";
            while(n.next()){
                nombre[i]=n.getString(1).toUpperCase();
                i++;
            }
            return nombre;
	
          }
        catch(Exception e){
                System.out.println(e); 
                return null;
        }
}


}
