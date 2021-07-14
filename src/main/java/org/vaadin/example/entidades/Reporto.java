package org.vaadin.example.entidades;

import java.text.DecimalFormat;
import java.util.Date;

public class Reporto  {

    public static DecimalFormat numberFormat = new DecimalFormat("#,###,##0.00");

    private int idUsuario;

    private Date fecha_hora;

    private Double monto_acumulado = 0.00;

    private Double monto_seguro = 0.00;

    private Double monto_probable = 0.00;

    private Double meta_diaria = 0.00;

    private String comentario = "";

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Date fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Double getMonto_acumulado() {
        return monto_acumulado;
    }

    public void setMonto_acumulado(Double monto_acumulado) {
        this.monto_acumulado = monto_acumulado;
    }

    public Double getMonto_seguro() {
        return monto_seguro;
    }

    public void setMonto_seguro(Double monto_seguro) {
        this.monto_seguro = monto_seguro;
    }

    public Double getMonto_probable() {
        return monto_probable;
    }

    public void setMonto_probable(Double monto_probable) {
        this.monto_probable = monto_probable;
    }

    public Double getMeta_diaria() {
        return meta_diaria;
    }

    public void setMeta_diaria(Double meta_diaria) {
        this.meta_diaria = meta_diaria;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
