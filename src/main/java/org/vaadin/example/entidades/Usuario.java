package org.vaadin.example.entidades;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Usuario {

    public enum Perfil {
        ADMINISTRADOR, SUPERVISOR, ASESOR
    }

    private int idUsuario;

    private String usuario;

    private String clave;

    private String nombre;

    private Usuario.Perfil perfil;

    private String email;

    private String telefono;

    private Date ultimoLogin;

    private String codigoEspecial;

    private Double metaDiaria;

    private String estatus;


    public Usuario(){

    }

    public Usuario(String usuario, String clave){
        this.usuario = usuario;
        this.clave = clave;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public String getCodigoEspecial() {
        return codigoEspecial;
    }

    public void setCodigoEspecial(String codigoEspecial) {
        this.codigoEspecial = codigoEspecial;
    }

    public Double getMetaDiaria() {
        return metaDiaria;
    }

    public void setMetaDiaria(Double metaDiaria) {
        this.metaDiaria = metaDiaria;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
