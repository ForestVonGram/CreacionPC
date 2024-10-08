package org.uniquindio.ejercicioarchivos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("PedidoView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);

        stage.setTitle("Arma tu PC con La Pecetera");

        Image logo = new Image(getClass().getResourceAsStream("imagenes/logo-la-pecetera.png"));
        stage.getIcons().add(logo);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
