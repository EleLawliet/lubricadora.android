package com.grupopulpo.lubriacadora.duty.entities;

/**
 * Created by Junior on 17/07/2017.
 */

public class MrTips {


    private int publicacion_id ;

    public MrTips(int publicacion_id, String enlace, String titulo, String descripcion, String fecha_ingresa) {
        this.publicacion_id = publicacion_id;
        this.enlace = enlace;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_ingresa = fecha_ingresa;
    }

    private String enlace;
    private String titulo;
    private String descripcion;
    private String fecha_ingresa;


    public String getFecha_ingresa() {
        return fecha_ingresa;
    }

    public void setFecha_ingresa(String fecha_ingresa) {
        this.fecha_ingresa = fecha_ingresa;
    }


    public MrTips(){

    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPublicacion_id() {
        return publicacion_id;
    }

    public void setPublicacion_id(int publicacion_id) {
        this.publicacion_id = publicacion_id;
    }
}

