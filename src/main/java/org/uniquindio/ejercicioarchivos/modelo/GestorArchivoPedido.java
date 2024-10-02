package org.uniquindio.ejercicioarchivos.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorArchivoPedido {
    private static int ultimoCodigo = 0;
    public static void guardarPedidoEnArchivo(Pedido pedido, String nombreArchivo) throws IOException {
        ultimoCodigo++;
        String codigoPedido = String.format("P%03d", ultimoCodigo);

        try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
            writer.write(pedido.getCodigo() + "," + pedido.getFechaPedido() + "," + pedido.getTotal() + "," + pedido.getIva() + "\n");

            for (Componente componente : pedido.getComponentes()) {
                writer.write(componente.getCodigo() + "," + componente.getNombre() + "," + componente.getPrecio() + "\n");
            }
            writer.write("\n");
        }
    }

    public static List<Pedido> leerPedidosDesdeArchivo(String nombreArchivo) throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            Pedido pedidoActual = null;

            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length == 4) {
                    String codigo = datos[0];
                    LocalDate fecha = LocalDate.parse(datos[1]);
                    double total = Double.parseDouble(datos[2]);
                    double iva = Double.parseDouble(datos[3]);

                    pedidoActual = new Pedido (codigo, fecha);
                    pedidoActual.calcularTotal();
                    pedidos.add(pedidoActual);
                } else if (datos.length == 3 && pedidoActual != null) {
                    String codigoComponente = datos[0];
                    String nombreComponente = datos[1];
                    double precioComponente = Double.parseDouble(datos[2]);

                    Componente componente = new Componente(codigoComponente, nombreComponente, precioComponente);
                    pedidoActual.agregarComponente(componente);
                }
            }
        }

        return pedidos;
    }

    public static void inicializarUltimoCodigo(String nombreArchivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 0 && datos[0].startsWith("P")) {
                    int codigoActual = Integer.parseInt(datos[0].substring(1));
                    if (codigoActual > ultimoCodigo) {
                        ultimoCodigo = codigoActual;
                    }
                }
            }
        }
    }
}
