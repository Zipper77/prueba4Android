package com.example.prueba4android;

public class Pokémon {
    private String id;
    private String nombre;
    private String ataque;
    private String vida;
    private String defensa;
    private String dueño;

    public Pokémon() {
    }

    public Pokémon(String id, String nombre, String ataque, String vida, String defensa, String dueño) {
        this.id = id;
        this.nombre = nombre;
        this.ataque = ataque;
        this.vida = vida;
        this.defensa = defensa;
        this.dueño = dueño;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAtaque() {
        return ataque;
    }

    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    public String getVida() {
        return vida;
    }

    public void setVida(String vida) {
        this.vida = vida;
    }

    public String getDefensa() {
        return defensa;
    }

    public void setDefensa(String defensa) {
        this.defensa = defensa;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    @Override
    public String toString() {
        return "Pokémon : "+nombre+"                                    Ataque :"+ataque+"      Vida : "+vida+"     Defensa : "+defensa;
    }
}
