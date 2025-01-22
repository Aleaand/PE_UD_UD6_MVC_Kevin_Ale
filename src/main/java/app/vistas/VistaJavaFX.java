package app.vistas;

import app.core.RouterCV;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class VistaJavaFX implements Vista {
    private final RouterCV routerCV;
    private Stage stage;

    private final ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
    private final ObservableList<VideoJuego> videoJuegos = FXCollections.observableArrayList();

    public VistaJavaFX(RouterCV routerCV) {
        this.routerCV = routerCV;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void iniciar() {
        VBox layout = new VBox(10);

        Label titleLabel = new Label("Gestión de Partidas - JavaFX");

        Button agregarJugadorBtn = new Button("Agregar Jugador");
        Button agregarVideoJuegoBtn = new Button("Agregar VideoJuego");


        layout.getChildren().addAll(
                titleLabel,
                agregarJugadorBtn, agregarVideoJuegoBtn
        );
        Scene scene = new Scene(layout, 700, 600);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Gestión de VideoJuegos y Partidas");
        stage.show();
    }

}
