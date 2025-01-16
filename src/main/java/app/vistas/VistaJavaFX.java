package app.vistas;

import app.core.Router;
import app.core.RouterCV;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaJavaFX implements Vista {
    private final RouterCV router;
    private Stage stage;

    public VistaJavaFX(RouterCV router) {
        this.router = router;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void iniciar() {
        VBox root = new VBox();
        Label titleLabel = new Label("Gestión de Jugadores");

        // Botón para agregar jugador
        Button agregarJugadorButton = new Button("Agregar Jugador");
        agregarJugadorButton.setOnAction(e -> router.ejecutarAccion("jugadores", "agregarJugador", "Mario", 10, 2000));

        // Botón para listar jugadores
        Button listarJugadoresButton = new Button("Listar Jugadores");
        listarJugadoresButton.setOnAction(e -> {
            Object resultado = router.ejecutarAccion("jugadores", "listarJugadores");
            if (resultado instanceof Iterable<?>) {
                ((Iterable<?>) resultado).forEach(System.out::println);
            }
        });

        root.getChildren().addAll(titleLabel, agregarJugadorButton, listarJugadoresButton);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Vista JavaFX");
        stage.show();
    }

    public void start(Stage stage) {
        VBox root = new VBox();
        Label titleLabel = new Label("Gestión de Jugadores");

        // Botón para agregar jugador
        Button agregarJugadorButton = new Button("Agregar Jugador");
        agregarJugadorButton.setOnAction(e -> router.ejecutarAccion("jugadores", "agregarJugador", "Mario", 10, 2000));

        // Botón para listar jugadores
        Button listarJugadoresButton = new Button("Listar Jugadores");
        listarJugadoresButton.setOnAction(e -> {
            Object resultado = router.ejecutarAccion("jugadores", "listarJugadores");
            if (resultado instanceof Iterable<?>) {
                ((Iterable<?>) resultado).forEach(System.out::println);
            }
        });

        root.getChildren().addAll(titleLabel, agregarJugadorButton, listarJugadoresButton);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Vista JavaFX");
        stage.show();
    }
}

