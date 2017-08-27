package com.grupopulpo.lubriacadora.duty.entities;

import java.util.List;

/**
 * Created by Junior on 23/11/2015.
 */
public class MrVehiculo {
    public int getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(int vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    private int vehiculo_id;

    public int getClase_vehiculo_id() {
        return clase_vehiculo_id;
    }

    public void setClase_vehiculo_id(int clase_vehiculo_id) {
        this.clase_vehiculo_id = clase_vehiculo_id;
    }

    public int getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(int estado_id) {
        this.estado_id = estado_id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getUso_personal() {
        return uso_personal;
    }

    public void setUso_personal(String uso_personal) {
        this.uso_personal = uso_personal;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getVehiculo_img() {
        return vehiculo_img;
    }

    public void setVehiculo_img(String vehiculo_img) {
        this.vehiculo_img = vehiculo_img;
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
    private int clase_vehiculo_id;
    private int estado_id;

    public int getCliente_vehiculo_id() {
        return cliente_vehiculo_id;
    }

    public void setCliente_vehiculo_id(int cliente_vehiculo_id) {
        this.cliente_vehiculo_id = cliente_vehiculo_id;
    }

    private int cliente_vehiculo_id;
    private String marca;

    public MrVehiculo(int vehiculo_id, int clase_vehiculo_id,int cliente_vehiculo_id, int estado_id, String marca, String color, String placa, String uso_personal, String anio, String fecha_ingreso, String usuario_ingreso, String fecha_modificacion, String usuario_modificacion, String clase_vehiculo_nombre, String clase_vehiculo_descripcion) {
        this.clase_vehiculo_id = clase_vehiculo_id;
        this.vehiculo_id = vehiculo_id;
        this.cliente_vehiculo_id = cliente_vehiculo_id;
        this.estado_id = estado_id;
        this.marca = marca;
        this.color = color;
        this.placa = placa;
        this.uso_personal = uso_personal;
        this.anio = anio;
        this.fecha_ingreso = fecha_ingreso;
        this.usuario_ingreso = usuario_ingreso;
        this.fecha_modificacion = fecha_modificacion;
        this.usuario_modificacion = usuario_modificacion;
        this.clase_vehiculo_nombre = clase_vehiculo_nombre;
        this.clase_vehiculo_descripcion = clase_vehiculo_descripcion;
    }

    private String color;
    private String placa;
    private String uso_personal;
    private String anio;
    private String vehiculo_img;
    private String fecha_ingreso;
    private String usuario_ingreso;
    private String fecha_modificacion;
    private String usuario_modificacion;

    public String getClase_vehiculo_nombre() {
        return clase_vehiculo_nombre;
    }

    public void setClase_vehiculo_nombre(String clase_vehiculo_nombre) {
        this.clase_vehiculo_nombre = clase_vehiculo_nombre;
    }

    public String getClase_vehiculo_descripcion() {
        return clase_vehiculo_descripcion;
    }

    public void setClase_vehiculo_descripcion(String clase_vehiculo_descripcion) {
        this.clase_vehiculo_descripcion = clase_vehiculo_descripcion;
    }

    private String clase_vehiculo_nombre;
    private String clase_vehiculo_descripcion;


}