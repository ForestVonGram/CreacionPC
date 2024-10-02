package org.uniquindio.ejercicioarchivos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String codigo;
    private LocalDate fechaPedido;
    private double total;
    private double iva;
    private List<Componente> componentes;

    public Pedido(String codigo, LocalDate fechaPedido) {
        this.codigo = codigo;
        this.fechaPedido = fechaPedido;
        this.componentes = new ArrayList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public double getIva() {
        return iva;
    }

    public List<Componente> getComponentes() {
        return componentes;
    }

    public void agregarComponente(Componente componente) {
        this.componentes.add(componente);
        calcularTotal();
    }

    public void calcularTotal() {
        this.total = componentes.stream().mapToDouble(Componente::getPrecio).sum();
        this.iva = total * 0.19;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido: ").append(codigo).append("\n");
        sb.append("Fecha: ").append(fechaPedido).append("\n");
        sb.append("Total: ").append(total).append("\n");
        sb.append("IVA: ").append(iva).append("\n");
        sb.append("Componentes: \n");
        for (Componente componente : componentes) {
            sb.append(componente.toString()).append("\n");
        }
        return sb.toString();
    }
}
