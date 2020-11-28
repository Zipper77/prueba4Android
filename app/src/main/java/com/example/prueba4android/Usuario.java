package com.example.prueba4android;

public class Usuario {
    private String id;
    private String username;
    private String contraseña;

    public Usuario() {
    }

    public Usuario(String id, String username, String contraseña) {
        this.id = id;
        this.username = username;
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
