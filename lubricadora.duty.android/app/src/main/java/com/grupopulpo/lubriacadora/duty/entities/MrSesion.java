package com.grupopulpo.lubriacadora.duty.entities;

/**
 * Created by Junior on 03/07/2017.
 */
public class MrSesion {
    private boolean isLogin;
    private int id;
    private String cedulaID;
    private String nombre;
    private String apellido;
    private String email;

    public String getCedulaID() {
        return cedulaID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setCedulaID(String cedulaID) {
        this.cedulaID = cedulaID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public MrSesion() {

    }


    public boolean isLogin() {
    return isLogin;
}

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

}