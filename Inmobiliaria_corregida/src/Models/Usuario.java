
package Models;

public class Usuario {
   private String nombre;
   private String usuario;
   private String contraseña;
   private String nivel;
   private String ultacceso;
   private String fecha_creacion;

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNivel() {
        return nivel;
    }

    public String getUltacceso() {
        return ultacceso;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public void setUltacceso(String ultacceso) {
        this.ultacceso = ultacceso;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}
