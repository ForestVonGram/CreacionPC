package org.uniquindio.ejercicioarchivos.modelo;

public class Componente {
    private String codigo;
    private String nombre;
    private double precio;

    public Componente(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return codigo + ", " + nombre + ", " + precio;
    }
}
