package Clases_DAO;



import Clases.Padres;
import System.Conexion;
import System.Utilidades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


public class PadresDB {
    
    
    public static String Llave_Primaria="id_padre_familia";
    public static String Nombre_Tabla="padres_familia";
    //Indicar el numero de campos relacionados en la variable =16
    public static String Campos_Tabla="cedula, nombre_completo, direccion_residencia, barrio, telefono, celular, email, tipo_estudio, titulo, direccion_oficina, empresa_labora, cargo ,estado, fecha_reg";
    
    public static int insertar(){
        PreparedStatement stmt = null;
        Conexion.establecer_conexion();
        Padres pf = new Padres();
        try{
            stmt = Conexion.conexion.prepareStatement
                ("INSERT INTO "+Nombre_Tabla+" ("+Campos_Tabla+") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, pf.getCedula());
//                stmt.setDate(3, new java.sql.Date(Rutas.fecha_reg.getTime()));
                stmt.setString(2, pf.getNombre_completo());
                stmt.setString(3, pf.getDireccion_residencia());
                stmt.setString(4, pf.getBarrio());
                stmt.setString(5, pf.getTelefono());
                stmt.setString(6, pf.getCelular());
                stmt.setString(7, pf.getEmail());
                stmt.setString(8, pf.getTipo_estudio());
                stmt.setString(9, pf.getTitulo());
                stmt.setString(10, pf.getDireccion_oficina());
                stmt.setString(11, pf.getEmpresa_labora());
                stmt.setString(12, pf.getCargo());
                stmt.setInt(13, 1);
                stmt.setTimestamp(14, Utilidades.getCurrentTimeStamp());
                System.out.println(""+stmt.toString());
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
              ("select * from padres_familia where  cedula ='"+codigo+"' and estado = 1");
              }else if(param==2){
              stmt = Conexion.conexion.prepareStatement
              ("select * from padres_familia where  nombre_completo='"+codigo+"' and estado = 1");
              }
            ResultSet n = stmt.executeQuery();
            while(n.next()){
                existe=true;
                Padres pd = new Padres(n.getInt("id_padre_familia"), n.getString("cedula"), n.getString("nombre_completo"), n.getString("direccion_residencia"), n.getString("barrio"), n.getString("telefono"), n.getString("celular"), n.getString("email"), n.getString("tipo_estudio"), n.getString("titulo"), n.getString("direccion_oficina"), n.getString("empresa_labora"), n.getString("cargo"), n.getInt("estado"), n.getTimestamp("fecha_reg"));
            }
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
          System.out.println(Padres.id_padre_familia);
          try{
             Padres pf = new Padres();
            stmt = Conexion.conexion.prepareStatement
            ("update "+Nombre_Tabla+" set "+Actualizar_campos+" where "+Llave_Primaria+"=?");
                stmt.setString(1, pf.getCedula());
                stmt.setString(2, pf.getNombre_completo());
                stmt.setString(3, pf.getDireccion_residencia());
                stmt.setString(4, pf.getBarrio());
                stmt.setString(5, pf.getTelefono());
                stmt.setString(6, pf.getCelular());
                stmt.setString(7, pf.getEmail());
                stmt.setString(8, pf.getTipo_estudio());
                stmt.setString(9, pf.getTitulo());
                stmt.setString(10, pf.getDireccion_oficina());
                stmt.setString(11, pf.getEmpresa_labora());
                stmt.setString(12, pf.getCargo());
                stmt.setInt(13, pf.getEstado());
                stmt.setTimestamp(14, pf.getFecha_reg());
                stmt.setInt(15, pf.getId_padre_familia());
                System.out.println(stmt.toString());
                int retorno = stmt.executeUpdate();
                return retorno;
          }
        catch(Exception e){
                System.out.println(e); 
                Utilidades.JOptionShowMessage("+1", "Problemas al actualizar... ERROR= "+e);
                return 0;
        }
        
     }
    public static int desactivar(int codigo){
          PreparedStatement stmt = null;
          Conexion.establecer_conexion();
          String Actualizar_campos=Campos_Tabla.replace(",", "=?,");
          Actualizar_campos+="=?";
          System.out.println(Actualizar_campos);
          System.out.println(Padres.id_padre_familia);
          try{
             Padres pf = new Padres();
            stmt = Conexion.conexion.prepareStatement
            ("update "+Nombre_Tabla+" set "+Actualizar_campos+" where "+Llave_Primaria+"=?");
                stmt.setString(1, pf.getCedula());
                stmt.setString(2, pf.getNombre_completo());
                stmt.setString(3, pf.getDireccion_residencia());
                stmt.setString(4, pf.getBarrio());
                stmt.setString(5, pf.getTelefono());
                stmt.setString(6, pf.getCelular());
                stmt.setString(7, pf.getEmail());
                stmt.setString(8, pf.getTipo_estudio());
                stmt.setString(9, pf.getTitulo());
                stmt.setString(10, pf.getDireccion_oficina());
                stmt.setString(11, pf.getEmpresa_labora());
                stmt.setString(12, pf.getCargo());
                stmt.setInt(13, 2);
                stmt.setTimestamp(14, pf.getFecha_reg());
                stmt.setInt(15, pf.getId_padre_familia());
                System.out.println(stmt.toString());
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

    public static Vector combo_EstudiantesDB(){
        Conexion conn = new Conexion();
        Vector estudiantes = new Vector();
        try{
         conn.establecer_conexion();
         String sql="select nombre_completo from estudiantes order by nombre_completo";
         ResultSet resultado = conn.consulta(sql);
         estudiantes.addElement("Seleccione un Estudiante");
         while(resultado.next()){
             estudiantes.addElement(resultado.getString(1));
         }
            return estudiantes;
        }catch(Exception e){
            return null;    
        }
    }


}
