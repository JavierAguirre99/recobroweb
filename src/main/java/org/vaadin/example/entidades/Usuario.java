package org.vaadin.example.entidades;

public class Usuario {

    String usuario;
    String clave;

    private Usuario.Perfil perfil;

    public enum Perfil {
        ADMINISTRADOR, SUPERVISOR, ASESOR
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
