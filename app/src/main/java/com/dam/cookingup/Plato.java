package com.dam.cookingup;


public class Plato {
    private String nombre;
    private Double tiempo;
    private String descripcion;

    public Plato() {
        this.descripcion = "";
        this.nombre = "";
        this.tiempo = 0.0;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTiempo(Double tiempo) {
        this.tiempo = tiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return nombre+" T : "+this.tiempo;
    }
}
