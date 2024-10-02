module org.uniquindio.ejercicioarchivos {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.uniquindio.ejercicioarchivos to javafx.fxml;
    exports org.uniquindio.ejercicioarchivos;
}