package com.grupopulpo.lubriacadora.duty.entities;

/**
 * Created by Junior on 21/07/2017.
 */
public class MrServicioVehiculo {
    public String getKilometrajeInicio() {
        return kilometrajeInicio;
    }

    public void setKilometrajeInicio(String kilometrajeInicio) {
        this.kilometrajeInicio = kilometrajeInicio;
    }

    public String getKilometrajeSustitucion() {
        return kilometrajeSustitucion;
    }

    public void setKilometrajeSustitucion(String kilometrajeSustitucion) {
        this.kilometrajeSustitucion = kilometrajeSustitucion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaSustitucion() {
        return fechaSustitucion;
    }

    public void setFechaSustitucion(String fechaSustitucion) {
        this.fechaSustitucion = fechaSustitucion;
    }

    public String getTipoServicioNombre() {
        return tipoServicioNombre;
    }

    public void setTipoServicioNombre(String tipoServicioNombre) {
        this.tipoServicioNombre = tipoServicioNombre;
    }

    public MrServicioVehiculo(String kilometrajeInicio, String kilometrajeSustitucion, String fechaInicio, String fechaSustitucion, String tipoServicioNombre, String tipoTiempoNombre, String tiempoServicio, String cantidadCambio) {
        this.kilometrajeInicio = kilometrajeInicio;
        this.kilometrajeSustitucion = kilometrajeSustitucion;
        this.fechaInicio = fechaInicio;
        this.fechaSustitucion = fechaSustitucion;
        this.tiempoServicio = tiempoServicio;
        this.tipoServicioNombre = tipoServicioNombre;
        this.tipoTiempoNombre = tipoTiempoNombre;
        this.cantidadCambio = cantidadCambio;
    }

    String  kilometrajeInicio;
    String kilometrajeSustitucion;
    String fechaInicio;
    String fechaSustitucion;
    String tipoServicioNombre;
    String tipoTiempoNombre;
    String cantidadCambio;

    public String getCantidadCambio() {
        return cantidadCambio;
    }

    public void setCantidadCambio(String cantidadCambio) {
        this.cantidadCambio = cantidadCambio;
    }


    public String getTipoTiempoNombre() {
        return tipoTiempoNombre;
    }

    public void setTipoTiempoNombre(String tipoTiempoNombre) {
        this.tipoTiempoNombre = tipoTiempoNombre;
    }

    public String getTiempoServicio() {
        return tiempoServicio;
    }

    public void setTiempoServicio(String tiempoServicio) {
        this.tiempoServicio = tiempoServicio;
    }

    String tiempoServicio;
}
