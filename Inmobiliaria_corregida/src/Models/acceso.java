/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;




public class acceso {
    public acceso() {
    }
    
    public static String usuario = null;
    public static String nivel = null;
    public static String sesion = null;    

 
   public void setUsuario(String usuario) {
        this.usuario = usuario;
        System.out.println(usuario);
    }
   
  public String getUsuario() {
        return usuario;
    }
   

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getSesion() {
        return sesion;
    }

    public void setSesion(String sesion) {
        this.sesion = sesion;
    }



    
}
