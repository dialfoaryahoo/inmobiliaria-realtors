/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Timestamp;


public class Padres {

public static int id_padre_familia;
public static String cedula;
public static String nombre_completo;
public static String direccion_residencia;
public static String barrio;
public static String telefono;
public static String celular;
public static String email;
public static String tipo_estudio;
public static String titulo;
public static String direccion_oficina;
public static String empresa_labora;
public static String cargo;
public static int estado;
public static Timestamp fecha_reg;

    public Padres() {
    }

    
    
    public Padres(int id_padre_familia, String cedula, String nombre_completo, String direccion_residencia, String barrio, String telefono, String celular, String email, String tipo_estudio, String titulo, String direccion_oficina, String empresa_labora, String cargo, int estado, Timestamp fecha_reg) {
        Padres.id_padre_familia = id_padre_familia;
        Padres.cedula = cedula;
        Padres.nombre_completo = nombre_completo;
        Padres.direccion_residencia = direccion_residencia;
        Padres.barrio = barrio;
        Padres.telefono = telefono;
        Padres.celular = celular;
        Padres.email = email;
        Padres.tipo_estudio = tipo_estudio;
        Padres.titulo = titulo;
        Padres.direccion_oficina = direccion_oficina;
        Padres.empresa_labora = empresa_labora;
        Padres.cargo = cargo;
        Padres.estado = estado;
        Padres.fecha_reg = fecha_reg;
    }

    public int getId_padre_familia() {
        return id_padre_familia;
    }

    public void setId_padre_familia(int id_padre_familia) {
        Padres.id_padre_familia = id_padre_familia;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        Padres.cedula = cedula;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        Padres.nombre_completo = nombre_completo;
    }

    public String getDireccion_residencia() {
        return direccion_residencia;
    }

    public void setDireccion_residencia(String direccion_residencia) {
        Padres.direccion_residencia = direccion_residencia;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        Padres.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        Padres.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        Padres.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Padres.email = email;
    }

    public String getTipo_estudio() {
        return tipo_estudio;
    }

    public void setTipo_estudio(String tipo_estudio) {
        Padres.tipo_estudio = tipo_estudio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        Padres.titulo = titulo;
    }

    public String getDireccion_oficina() {
        return direccion_oficina;
    }

    public void setDireccion_oficina(String direccion_oficina) {
        Padres.direccion_oficina = direccion_oficina;
    }

    public String getEmpresa_labora() {
        return empresa_labora;
    }

    public void setEmpresa_labora(String empresa_labora) {
        Padres.empresa_labora = empresa_labora;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        Padres.cargo = cargo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        Padres.estado = estado;
    }

    public Timestamp getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Timestamp fecha_reg) {
        Padres.fecha_reg = fecha_reg;
    }


    
}

