
package Clases;

import java.sql.Date;
import java.sql.Timestamp;



public class Estudiante {
    
    public static int id_estudiante;
    public static String identificacion;
    public static String nombre_completo;
    public static int edad;
    public static String lugar_nac;
    public static Timestamp fecha_nac;
    public static String direccion;
    public static Timestamp fecha_reg; 
    public static int estado;
    public static int saldo;
    

    public Estudiante() {
    }

    public Estudiante(int id_estudiante, String identificacion, String nombre_completo, Timestamp fecha_nac, String lugar_nac, String direccion, int edad, Timestamp fecha_reg, int estado, int saldo) {
        Estudiante.id_estudiante = id_estudiante;
        Estudiante.identificacion = identificacion;
        Estudiante.nombre_completo = nombre_completo;
        Estudiante.fecha_nac = fecha_nac;
        Estudiante.lugar_nac = lugar_nac;
        Estudiante.direccion = direccion;
        Estudiante.edad = edad;
        Estudiante.fecha_reg = fecha_reg;
        Estudiante.estado = estado;
        Estudiante.saldo = saldo;
    }

    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
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

    public Timestamp getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Timestamp fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getLugar_nac() {
        return lugar_nac;
    }

    public void setLugar_nac(String lugar_nac) {
        this.lugar_nac = lugar_nac;
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

    public Timestamp getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Timestamp fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public static int getSaldo() {
        return saldo;
    }

    public static void setSaldo(int saldo) {
        Estudiante.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Estudiante{" + Estudiante.nombre_completo + " , " +Estudiante.lugar_nac + " , " + Estudiante.direccion + " , " +'}';
    }

  
    
}
