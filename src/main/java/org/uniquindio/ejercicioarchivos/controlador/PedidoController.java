package org.uniquindio.ejercicioarchivos.controlador;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import org.uniquindio.ejercicioarchivos.modelo.Componente;
import org.uniquindio.ejercicioarchivos.modelo.GestorArchivoPedido;
import org.uniquindio.ejercicioarchivos.modelo.Pedido;

public class PedidoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Componente> almacenamiento_selector;

    @FXML
    private ComboBox<Componente> cpu_selector;

    @FXML
    private ComboBox<Componente> grafica_selector;

    @FXML
    private Text iva_total;

    @FXML
    private ComboBox<Componente> ram_selector;

    @FXML
    private Text total;

    @FXML
    private Label lista_pedidos;

    private Pedido pedido;

    @FXML
    void initialize() {

        assert almacenamiento_selector != null : "fx:id=\"almacenamiento_selector\" was not injected: check your FXML file 'PedidoView.fxml'.";
        assert cpu_selector != null : "fx:id=\"cpu_selector\" was not injected: check your FXML file 'PedidoView.fxml'.";
        assert grafica_selector != null : "fx:id=\"grafica_selector\" was not injected: check your FXML file 'PedidoView.fxml'.";
        assert iva_total != null : "fx:id=\"iva_total\" was not injected: check your FXML file 'PedidoView.fxml'.";
        assert ram_selector != null : "fx:id=\"ram_selector\" was not injected: check your FXML file 'PedidoView.fxml'.";
        assert total != null : "fx:id=\"total\" was not injected: check your FXML file 'PedidoView.fxml'.";

        try {
            GestorArchivoPedido.inicializarUltimoCodigo("pedidos.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        pedido = new Pedido(String.format("P%03d", GestorArchivoPedido.getUltimoCodigo()), LocalDate.now());

        ram_selector.getItems().addAll(
                new Componente("RAM001", "8gb RAM", 90000),
                new Componente("RAM002", "16gb RAM", 170000),
                new Componente("RAM003", "32gb RAM", 320000),
                new Componente("RAM004", "64gb RAM", 800000)
        );

        cpu_selector.getItems().addAll(
                new Componente("CPU001", "Intel Core i3", 440000),
                new Componente("CPU002", "Intel Core i5", 1300000),
                new Componente("CPU003", "Intel Core i7", 2000000),
                new Componente("CPU004", "Intel Core i9", 2500000)
        );

        grafica_selector.getItems().addAll(
                new Componente("GPU001", "Nvidia RTX 3060", 1600000),
                new Componente("GPU002", "Nvidia RTX 4060", 1700000),
                new Componente("GPU003", "Nvidia RTX 4070", 3100000),
                new Componente("GPU004", "Nvidia RTX 4080", 5850000),
                new Componente("GPU005", "Nvidia RTX 4090", 10200000)
        );

        almacenamiento_selector.getItems().addAll(
                new Componente("NVMe001", "NVMe SSD 500gb", 200000),
                new Componente("NVMe002", "NVMe SSD 1tb", 280000),
                new Componente("NVMe003", "NVMe SSD 2tb", 525000),
                new Componente("NVMe004", "NVMe SSD 4tb", 1050000)
        );

        ram_selector.setOnAction(e -> agregarComponenteSeleccionado(ram_selector.getValue()));
        cpu_selector.setOnAction(e -> agregarComponenteSeleccionado(cpu_selector.getValue()));
        grafica_selector.setOnAction(e -> agregarComponenteSeleccionado(grafica_selector.getValue()));
        almacenamiento_selector.setOnAction(e -> agregarComponenteSeleccionado(almacenamiento_selector.getValue()));

    }

    private void agregarComponenteSeleccionado(Componente componente) {
        if (componente != null) {
            pedido.agregarComponente(componente);
            total.setText(String.format("$%.2f", pedido.getTotal()));
            iva_total.setText(String.format("$%.2f", pedido.getIva()));
        }
    }

    public void guardarPedido() {
        try {
            GestorArchivoPedido.guardarPedidoEnArchivo(pedido, "pedidos.txt");
            lista_pedidos.setText(pedido.getCodigo() + ", " + pedido.getFechaPedido() + ", " + pedido.getTotal() + ", " + pedido.getIva() + "\n");

            String nuevoCodigo = String.format("P%03d", GestorArchivoPedido.getUltimoCodigo() + 1);
            pedido = new Pedido(nuevoCodigo, LocalDate.now());

            total.setText("0.00");
            iva_total.setText("0.00");

            ram_selector.getSelectionModel().clearSelection();
            cpu_selector.getSelectionModel().clearSelection();
            grafica_selector.getSelectionModel().clearSelection();
            almacenamiento_selector.getSelectionModel().clearSelection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
