/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;


public class Padres_Familia {

public int id_padre_familia;
public String cedula;
public String nombre_completo;
public String direccion_residencia;
public String barrio;
public String telefono;
public String celular;
public String email;
public String tipo_estudio;
public String titulo;
public String direccion_oficina;
public String empresa_labora;
public String cargo;
public int estado;
public Date fecha_reg;

    public Padres_Familia() {
    }

    public Padres_Familia(int id_padre_familia, String cedula, String nombre_completo, String direccion_residencia, String barrio, String telefono, String celular, String email, String tipo_estudio, String titulo, String direccion_oficina, String empresa_labora, String cargo, int estado, Date fecha_reg) {
        this.id_padre_familia = id_padre_familia;
        this.cedula = cedula;
        this.nombre_completo = nombre_completo;
        this.direccion_residencia = direccion_residencia;
        this.barrio = barrio;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.tipo_estudio = tipo_estudio;
        this.titulo = titulo;
        this.direccion_oficina = direccion_oficina;
        this.empresa_labora = empresa_labora;
        this.cargo = cargo;
        this.estado = estado;
        this.fecha_reg = fecha_reg;
    }

    public int getId_padre_familia() {
        return id_padre_familia;
    }

    public void setId_padre_familia(int id_padre_familia) {
        this.id_padre_familia = id_padre_familia;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getDireccion_residencia() {
        return direccion_residencia;
    }

    public void setDireccion_residencia(String direccion_residencia) {
        this.direccion_residencia = direccion_residencia;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo_estudio() {
        return tipo_estudio;
    }

    public void setTipo_estudio(String tipo_estudio) {
        this.tipo_estudio = tipo_estudio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDireccion_oficina() {
        return direccion_oficina;
    }

    public void setDireccion_oficina(String direccion_oficina) {
        this.direccion_oficina = direccion_oficina;
    }

    public String getEmpresa_labora() {
        return empresa_labora;
    }

    public void setEmpresa_labora(String empresa_labora) {
        this.empresa_labora = empresa_labora;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(Date fecha_reg) {
        this.fecha_reg = fecha_reg;
    }


    
}

