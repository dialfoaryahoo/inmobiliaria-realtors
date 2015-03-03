/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Usuario
 */
public class Ficheros {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      static String ip, user, contraseña, database;
      
      public void leer(){
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
          String ruta=System.getProperty("user.dir");
          ruta=ruta.replace("\\", "/");
          String path=ruta+"/src/Ficheros/Variable.txt";
          archivo = new File (path);
          fr = new FileReader (archivo);
          br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         int i = 1;
         while((linea=br.readLine())!=null){
             switch (i){
                 case 1:
                   ip=linea;  
                 break;
                 case 2:
                   user=linea;
                 break;
                 case 3:
                   contraseña=linea;
                 break;  
                 case 4:
                   database=linea;
                 break;
                 
             };
             i++;
         }
         }
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
      }
      
      public void borrar(){
          String ruta=System.getProperty("user.dir");
          ruta=ruta.replace("\\", "/");
          String path=ruta+"/src/Ficheros/Variable.txt";
          File archivo = new File(path); 
          if ( archivo.delete() ) {
              System.out.println("Borrado");
          }
    }
}
