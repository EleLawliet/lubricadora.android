package com.grupopulpo.lubriacadora.duty.entities;

/**
 * Created by Junior on 16/07/2017.
 */
public class ClaseVehiculo {

    private Integer clase_vehiculo_id;
    private Integer estado_id;
    private String  nombre       ;
    private String  descripcion       ;

    public Integer getClase_vehiculo_id() {
        return clase_vehiculo_id;
    }

    public void setClase_vehiculo_id(Integer clase_vehiculo_id) {
        this.clase_vehiculo_id = clase_vehiculo_id;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getUsuario_ingreso() {
        return usuario_ingreso;
    }

    public void setUsuario_ingreso(String usuario_ingreso) {
        this.usuario_ingreso = usuario_ingreso;
    }

    public String getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(String fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public String getUsuario_modificacion() {
        return usuario_modificacion;
    }

    public void setUsuario_modificacion(String usuario_modificacion) {
        this.usuario_modificacion = usuario_modificacion;
    }

    private String  fecha_ingreso   ;
    private String  usuario_ingreso   ;
    private String  fecha_modificacion   ;
    private String  usuario_modificacion   ;
}
