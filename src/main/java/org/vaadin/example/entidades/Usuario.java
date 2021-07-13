package org.vaadin.example.entidades;

import javax.validation.constraints.NotNull;

public class Usuario {

    public enum Perfil {
        ADMINISTRADOR, SUPERVISOR, ASESOR
    }

    private String usuario;

    private String clave;

    private String nombre;

    private Usuario.Perfil perfil;

    private String email;

    private String telefono;

    private Double meta_diaria;

    private String codigo_especial;


    public Usuario(){

    }

    public Usuario(String usuario, String clave){
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getMeta_diaria() {
        return meta_diaria;
    }

    public void setMeta_diaria(Double meta_diaria) {
        this.meta_diaria = meta_diaria;
    }

    public String getCodigo_especial() {
        return codigo_especial;
    }

    public void setCodigo_especial(String codigo_especial) {
        this.codigo_especial = codigo_especial;
    }
}
