
package Clases;

import java.sql.Date;



public class Estudiante {
    
  public static int id_estudiante, id_padre_familia;
  public static String identificacion, nombre_completo;
  public static Date fecha_nac;
  public static String lugar_nac, convive,direccion;
  public static int edad,estado;

    public Estudiante(int id_estudiante, int id_padre_familia, String identificacion, String nombre_completo, Date fecha_nac, String lugar_nac, String convive, String direccion, int edad, int estado) {
        this.id_estudiante = id_estudiante;
        this.id_padre_familia = id_padre_familia;
        this.identificacion = identificacion;
        this.nombre_completo = nombre_completo;
        this.fecha_nac = fecha_nac;
        this.lugar_nac = lugar_nac;
        this.convive = convive;
        this.direccion = direccion;
        this.edad = edad;
        this.estado = estado;
    }

    public Estudiante() {
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getId_padre_familia() {
        return id_padre_familia;
    }

    public void setId_padre_familia(int id_padre_familia) {
        this.id_padre_familia = id_padre_familia;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getLugar_nac() {
        return lugar_nac;
    }

    public void setLugar_nac(String lugar_nac) {
        this.lugar_nac = lugar_nac;
    }

    public String getConvive() {
        return convive;
    }

    public void setConvive(String convive) {
        this.convive = convive;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estudiante{" + Estudiante.nombre_completo + " , " +Estudiante.lugar_nac + " , " + Estudiante.direccion + " , " +'}';
    }

  
    
}
