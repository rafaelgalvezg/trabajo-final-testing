package com.rafaelgalvezg.app.model;

public class Propietario {

    private String nombre;

    private String ciudad;

    private String telefono;

    public Propietario() {
    }

    public Propietario(String nombre, String ciudad, String telefono) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
