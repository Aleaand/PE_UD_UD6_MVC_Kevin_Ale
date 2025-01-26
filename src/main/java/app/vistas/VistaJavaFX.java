package app.vistas;

import app.core.RouterCV;
import app.modelos.Jugador;
import app.modelos.Partida;
import app.modelos.Videojuego;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaJavaFX implements Vista {
    private final RouterCV routerCV;
    private Stage stage;
    private final ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
    private final ObservableList<Videojuego> videoJuegos = FXCollections.observableArrayList();

    public VistaJavaFX(RouterCV routerCV) {
        this.routerCV = routerCV;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void iniciar() {
        Platform.runLater(() -> {
            stage.setTitle("Menú Principal");
            stage.setScene(crearMenuPrincipal());
            stage.show();

            cargarDatosIniciales();
        });
    }

    private void cargarDatosIniciales() {
        jugadores.setAll((ObservableList<Jugador>) routerCV.ejecutarAccion("jugadores", "listarJugadores"));
        videoJuegos.setAll((ObservableList<Videojuego>) routerCV.ejecutarAccion("videojuegos", "listarVideojuegos"));
    }

    /// /////////Menus
    private void aplicarEstiloBoton(Button boton) {
        boton.setStyle("-fx-background-color: #e63946; "
                + "-fx-text-fill: white; "
                + "-fx-font-size: 16px; "
                + "-fx-font-weight: bold; "
                + "-fx-pref-width: 250px; "
                + "-fx-pref-height: 40px; "
                + "-fx-border-radius: 15px; "
                + "-fx-background-radius: 15px; "
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0.0, 0, 5);");
    }

    private Scene crearMenuPrincipal() {
        Label lblTitulo = new Label("Menú Principal");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");
        Button btnJugadores = new Button("Menú Jugadores");
        Button btnVideojuegos = new Button("Menú Videojuegos");
        Button btnPartidas = new Button("Menú Partidas");
        Button btnEstadisticas = new Button("Menú Estadísticas");
        Button btnSalir = new Button("Salir");

        btnJugadores.setOnAction(e -> stage.setScene(crearMenuJugadores()));
        btnVideojuegos.setOnAction(e -> stage.setScene(crearMenuVideojuegos()));
        btnPartidas.setOnAction(e -> stage.setScene(crearMenuPartidas()));
        btnEstadisticas.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));
        btnSalir.setOnAction(e -> stage.close());

        VBox layout = new VBox(15, lblTitulo, btnJugadores, btnVideojuegos, btnPartidas, btnEstadisticas, btnSalir);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #333333;");

        aplicarEstiloBoton(btnJugadores);
        aplicarEstiloBoton(btnVideojuegos);
        aplicarEstiloBoton(btnPartidas);
        aplicarEstiloBoton(btnEstadisticas);
        aplicarEstiloBoton(btnSalir);

        return new Scene(layout, 400, 500);
    }

    private Scene crearMenuJugadores() {
        Label lblTitulo = new Label("Menú de Jugadores");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");
        Button btnAgregar = new Button("Agregar Jugador");
        Button btnActualizar = new Button("Actualizar Jugador");
        Button btnEliminar = new Button("Eliminar Jugador");
        Button btnVerID = new Button("Ver Jugador por ID");
        Button btnListar = new Button("Ver todos los Jugadores");
        Button btnVolver = new Button("Volver");

        btnAgregar.setOnAction(e -> agregarJugador());
        btnActualizar.setOnAction(e -> actualizarJugador());
        btnEliminar.setOnAction(e -> eliminarJugador());
        btnVerID.setOnAction(e -> verJugadorPorID());
        btnListar.setOnAction(e -> listarJugadores());
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(15, lblTitulo, btnAgregar, btnActualizar, btnEliminar, btnVerID, btnListar, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #333333;");

        aplicarEstiloBoton(btnAgregar);
        aplicarEstiloBoton(btnActualizar);
        aplicarEstiloBoton(btnEliminar);
        aplicarEstiloBoton(btnVerID);
        aplicarEstiloBoton(btnListar);
        aplicarEstiloBoton(btnVolver);

        return new Scene(layout, 400, 500);
    }

    private Scene crearMenuVideojuegos() {
        Label lblTitulo = new Label("Menú de Videojuegos");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");
        Button btnAgregar = new Button("Agregar Videojuego");
        Button btnActualizar = new Button("Actualizar Videojuego");
        Button btnEliminar = new Button("Eliminar Videojuego");
        Button btnVerID = new Button("Ver Videojuego por ID");
        Button btnListar = new Button("Ver todos los Videojuegos");
        Button btnVolver = new Button("Volver");

        btnAgregar.setOnAction(e -> agregarVideojuego());
        btnActualizar.setOnAction(e -> actualizarVideojuego());
        btnEliminar.setOnAction(e -> eliminarVideojuego());
        btnVerID.setOnAction(e -> verVideojuegoPorID());
        btnListar.setOnAction(e -> listarVideojuegos());
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(15, lblTitulo, btnAgregar, btnActualizar, btnEliminar, btnVerID, btnListar, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #333333;");

        aplicarEstiloBoton(btnAgregar);
        aplicarEstiloBoton(btnActualizar);
        aplicarEstiloBoton(btnEliminar);
        aplicarEstiloBoton(btnVerID);
        aplicarEstiloBoton(btnListar);
        aplicarEstiloBoton(btnVolver);

        return new Scene(layout, 400, 500);
    }

    private Scene crearMenuPartidas() {
        Label lblTitulo = new Label("Menú de Partidas");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");
        Button btnAgregar = new Button("Agregar Partida");
        Button btnActualizar = new Button("Actualizar Partida");
        Button btnEliminar = new Button("Eliminar Partida");
        Button btnVerID = new Button("Ver Partida por ID");
        Button btnListar = new Button("Ver todas las Partidas");
        Button btnVolver = new Button("Volver");

        btnAgregar.setOnAction(e -> agregarPartida());
        btnActualizar.setOnAction(e -> actualizarPartida());
        btnEliminar.setOnAction(e -> eliminarPartida());
        btnVerID.setOnAction(e -> verPartidaPorID());
        btnListar.setOnAction(e -> listarPartidas());
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(15, lblTitulo, btnAgregar, btnActualizar, btnEliminar, btnVerID, btnListar, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #333333;");

        aplicarEstiloBoton(btnAgregar);
        aplicarEstiloBoton(btnActualizar);
        aplicarEstiloBoton(btnEliminar);
        aplicarEstiloBoton(btnVerID);
        aplicarEstiloBoton(btnListar);
        aplicarEstiloBoton(btnVolver);

        return new Scene(layout, 400, 500);
    }

    private Scene crearMenuEstadisticas() {
        Label lblTitulo = new Label("Menú de Estadísticas");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");
        Button btnHoras = new Button("Top horas de juego");
        Button btnPuntuacion = new Button("Top de puntuación");
        Button btnExperiencia = new Button("Top de experiencia");
        Button btnClasificacion = new Button("Top de Videojuegos");
        Button btnVolver = new Button("Volver");

        btnHoras.setOnAction(e -> verEstadisticasHoras());
        btnPuntuacion.setOnAction(e -> verEstadisticasPuntuacion());
        btnExperiencia.setOnAction(e -> verEstadisticasExperiencia());
        btnClasificacion.setOnAction(e -> mostrarClasificacionVideojuegos());
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(15, lblTitulo, btnHoras, btnPuntuacion, btnExperiencia, btnClasificacion, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #333333;");

        aplicarEstiloBoton(btnHoras);
        aplicarEstiloBoton(btnPuntuacion);
        aplicarEstiloBoton(btnExperiencia);
        aplicarEstiloBoton(btnClasificacion);
        aplicarEstiloBoton(btnVolver);

        return new Scene(layout, 400, 500);
    }

    /// //////////////////////// JUGADOR
    private void agregarJugador() {
        // Crear formulario de agregar jugador
        Label lblTitulo = new Label("Agregar Jugador");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblNombre = new Label("Nombre del jugador:");
        lblNombre.setStyle("-fx-text-fill: white;");
        TextField txtNombre = new TextField();
        txtNombre.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblNivel = new Label("Nivel del jugador:");
        lblNivel.setStyle("-fx-text-fill: white;");
        TextField txtNivel = new TextField();
        txtNivel.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblPuntuacion = new Label("Puntuación del jugador:");
        lblPuntuacion.setStyle("-fx-text-fill: white;");
        TextField txtPuntuacion = new TextField();
        txtPuntuacion.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnAgregar = new Button("Agregar");
        btnAgregar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblNombre, txtNombre, lblNivel, txtNivel, lblPuntuacion, txtPuntuacion, btnAgregar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnAgregar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de agregar jugador
        Scene escenaAgregarJugador = new Scene(layout, 500, 360);

        // Acción del botón agregar
        btnAgregar.setOnAction(e -> {
            String nombre = txtNombre.getText();
            int nivel;
            int puntuacion;

            try {
                nivel = Integer.parseInt(txtNivel.getText());
                puntuacion = Integer.parseInt(txtPuntuacion.getText());

                // Ejecutar la acción en el router
                String resultado = (String) routerCV.ejecutarAccion("jugadores", "agregarJugador", nombre, nivel, puntuacion);

                if (resultado.startsWith("Error")) {
                    // Mostrar error si el resultado es un error
                    Alert alert = new Alert(Alert.AlertType.ERROR, resultado, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar mensaje de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Jugador agregado exitosamente.", ButtonType.OK);
                    alert.showAndWait();

                    // Volver al menú principal
                    stage.setScene(crearMenuJugadores());
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si los campos no son números válidos
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese valores válidos para nivel y puntuación.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuJugadores()));

        // Mostrar la escena
        stage.setScene(escenaAgregarJugador);
    }

    private void actualizarJugador() {
        // Crear formulario de actualizar jugador
        Label lblTitulo = new Label("Actualizar Jugador");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblID = new Label("ID del jugador:");
        lblID.setStyle("-fx-text-fill: white;");
        TextField txtID = new TextField();
        txtID.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblNombre = new Label("Nuevo nombre del jugador:");
        lblNombre.setStyle("-fx-text-fill: white;");
        TextField txtNombre = new TextField();
        txtNombre.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblNivel = new Label("Nuevo nivel del jugador:");
        lblNivel.setStyle("-fx-text-fill: white;");
        TextField txtNivel = new TextField();
        txtNivel.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblPuntuacion = new Label("Nueva puntuación del jugador:");
        lblPuntuacion.setStyle("-fx-text-fill: white;");
        TextField txtPuntuacion = new TextField();
        txtPuntuacion.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnActualizar = new Button("Actualizar");
        btnActualizar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblID, txtID, lblNombre, txtNombre, lblNivel, txtNivel, lblPuntuacion, txtPuntuacion, btnActualizar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnActualizar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de actualizar jugador
        Scene escenaActualizarJugador = new Scene(layout, 500, 420);

        // Acción del botón actualizar
        btnActualizar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());
                String nombre = txtNombre.getText();
                int nivel = Integer.parseInt(txtNivel.getText());
                int puntuacion = Integer.parseInt(txtPuntuacion.getText());

                // Ejecutar la acción en el router
                String resultado = (String) routerCV.ejecutarAccion("jugadores", "actualizarJugador", id, nombre, nivel, puntuacion);

                if (resultado.startsWith("Error")) {
                    // Mostrar error si el resultado es un error
                    Alert alert = new Alert(Alert.AlertType.ERROR, resultado, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar mensaje de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Jugador actualizado exitosamente.", ButtonType.OK);
                    alert.showAndWait();

                    // Volver al menú principal
                    stage.setScene(crearMenuJugadores());
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si los campos no son números válidos
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese valores válidos.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuJugadores()));

        // Mostrar la escena
        stage.setScene(escenaActualizarJugador);
    }

    private void eliminarJugador() {
        // Crear formulario de eliminar jugador
        Label lblTitulo = new Label("Eliminar Jugador");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblID = new Label("ID del jugador:");
        lblID.setStyle("-fx-text-fill: white;");
        TextField txtID = new TextField();
        txtID.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblID, txtID, btnEliminar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnEliminar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de eliminar jugador
        Scene escenaEliminarJugador = new Scene(layout, 400, 200);

        // Acción del botón eliminar
        btnEliminar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());

                // Ejecutar la acción en el router
                String resultado = (String) routerCV.ejecutarAccion("jugadores", "eliminarJugador", id);

                if (resultado.startsWith("Error")) {
                    // Mostrar error si el resultado es un error
                    Alert alert = new Alert(Alert.AlertType.ERROR, resultado, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar mensaje de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Jugador eliminado exitosamente.", ButtonType.OK);
                    alert.showAndWait();

                    // Volver al menú principal
                    stage.setScene(crearMenuJugadores());
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si el ID no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese un ID válido.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuJugadores()));

        // Mostrar la escena
        stage.setScene(escenaEliminarJugador);
    }

    private void verJugadorPorID() {
        // Crear formulario de ver jugador por ID
        Label lblTitulo = new Label("Ver Jugador por ID");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblID = new Label("ID del jugador:");
        lblID.setStyle("-fx-text-fill: white;");
        TextField txtID = new TextField();
        txtID.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnVer = new Button("Ver Jugador");
        btnVer.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblID, txtID, btnVer, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnVer, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de ver jugador por ID
        Scene escenaVerJugador = new Scene(layout, 400, 200);

        // Acción del botón ver
        btnVer.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());

                // Ejecutar la acción en el router
                Jugador jugador = (Jugador) routerCV.ejecutarAccion("jugadores", "verJugadorID", id);

                if (jugador == null) {
                    // Mostrar error si no se encuentra el jugador
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Jugador no encontrado.", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar detalles del jugador
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "ID: " + jugador.getId() + "\n" +
                                    "Nombre: " + jugador.getNombre() + "\n" +
                                    "Nivel: " + jugador.getNivel() + "\n" +
                                    "Puntuación: " + jugador.getPuntuacion(), ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si el ID no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese un ID válido.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuJugadores()));

        // Mostrar la escena
        stage.setScene(escenaVerJugador);
    }

    private void listarJugadores() {
        // Obtener lista de jugadores
        List<Jugador> jugadores = (List<Jugador>) routerCV.ejecutarAccion("jugadores", "listarJugadores");

        // Crear botón de volver
        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Acción del botón: regresar al menú de jugadores
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuJugadores())); // Asumiendo que tienes el método `crearMenuJugadores()` para volver al menú principal

        // Crear la escena de listado
        VBox layout = new VBox(10);  // Se agrega un espaciado de 10 entre los elementos
        layout.setStyle("-fx-padding: 20; -fx-alignment: center-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Crear título
        Label lblTitulo = new Label("Lista de Jugadores");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        if (jugadores != null && !jugadores.isEmpty()) {
            // Crear un contenedor ScrollPane para hacer la lista desplazable
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true); // Ajustar al ancho de la ventana
            scrollPane.setStyle("-fx-background-color: #333333;"); // Fondo gris más oscuro para el ScrollPane

            // Crear un contenedor para los jugadores
            VBox listaJugadores = new VBox(5);  // Espaciado entre los jugadores
            for (Jugador jugador : jugadores) {
                // Crear una label con todos los detalles del jugador
                Label labelJugador = new Label(
                        "ID: " + jugador.getId() + "\n" +
                                "Nombre: " + jugador.getNombre() + "\n" +
                                "Nivel: " + jugador.getNivel() + "\n" +
                                "Puntuación: " + jugador.getPuntuacion());
                labelJugador.setStyle("-fx-text-fill: white; -fx-padding: 10px; -fx-background-color: #444444; -fx-border-radius: 5; -fx-margin: 5px;");
                labelJugador.setAlignment(Pos.CENTER); // Centrar el contenido dentro de cada label
                listaJugadores.getChildren().add(labelJugador);
            }

            // Agregar la lista de jugadores al ScrollPane
            scrollPane.setContent(listaJugadores);

            // Añadir el título y el ScrollPane al layout principal
            layout.getChildren().addAll(lblTitulo, scrollPane, btnCancelar);  // Asegura que el botón se muestre después de la lista
        } else {
            layout.getChildren().addAll(lblTitulo, new Label("No hay jugadores registrados."), btnCancelar);
        }

        // Crear la escena y mostrarla
        Scene escenaListarJugadores = new Scene(layout, 400, 300);
        stage.setScene(escenaListarJugadores);
    }

    /// //////////////////VIDEOJUEGO
    private void agregarVideojuego() {
        // Crear formulario de agregar videojuego
        Label lblTitulo = new Label("Agregar Videojuego");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblTituloJuego = new Label("Título del videojuego:");
        lblTituloJuego.setStyle("-fx-text-fill: white;");
        TextField txtTitulo = new TextField();
        txtTitulo.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblGenero = new Label("Género del videojuego:");
        lblGenero.setStyle("-fx-text-fill: white;");
        TextField txtGenero = new TextField();
        txtGenero.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblPrecio = new Label("Precio del videojuego:");
        lblPrecio.setStyle("-fx-text-fill: white;");
        TextField txtPrecio = new TextField();
        txtPrecio.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnAgregar = new Button("Agregar");
        btnAgregar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblTituloJuego, txtTitulo, lblGenero, txtGenero, lblPrecio, txtPrecio, btnAgregar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnAgregar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de agregar videojuego
        Scene escenaAgregarVideojuego = new Scene(layout, 500, 360);

        // Acción del botón agregar
        btnAgregar.setOnAction(e -> {
            String titulo = txtTitulo.getText();
            String genero = txtGenero.getText();
            double precio;

            try {
                precio = Double.parseDouble(txtPrecio.getText());

                // Ejecutar la acción en el router
                String resultado = (String) routerCV.ejecutarAccion("videojuegos", "agregarVideojuego", titulo, genero, precio);

                if (resultado.startsWith("Error")) {
                    // Mostrar error si el resultado es un error
                    Alert alert = new Alert(Alert.AlertType.ERROR, resultado, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar mensaje de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Videojuego agregado exitosamente.", ButtonType.OK);
                    alert.showAndWait();

                    // Volver al menú principal
                    stage.setScene(crearMenuVideojuegos());
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si los campos no son números válidos
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese un precio válido.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuVideojuegos()));

        // Mostrar la escena
        stage.setScene(escenaAgregarVideojuego);
    }

    private void actualizarVideojuego() {
        // Crear formulario de actualizar videojuego
        Label lblTitulo = new Label("Actualizar Videojuego");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblID = new Label("ID del videojuego:");
        lblID.setStyle("-fx-text-fill: white;");
        TextField txtID = new TextField();
        txtID.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblTituloJuego = new Label("Nuevo título del videojuego:");
        lblTituloJuego.setStyle("-fx-text-fill: white;");
        TextField txtTitulo = new TextField();
        txtTitulo.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblGenero = new Label("Nuevo género del videojuego:");
        lblGenero.setStyle("-fx-text-fill: white;");
        TextField txtGenero = new TextField();
        txtGenero.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblPrecio = new Label("Nuevo precio del videojuego:");
        lblPrecio.setStyle("-fx-text-fill: white;");
        TextField txtPrecio = new TextField();
        txtPrecio.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnActualizar = new Button("Actualizar");
        btnActualizar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblID, txtID, lblTituloJuego, txtTitulo, lblGenero, txtGenero, lblPrecio, txtPrecio, btnActualizar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnActualizar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de actualizar videojuego
        Scene escenaActualizarVideojuego = new Scene(layout, 500, 420);

        // Acción del botón actualizar
        btnActualizar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());
                String titulo = txtTitulo.getText();
                String genero = txtGenero.getText();
                double precio = Double.parseDouble(txtPrecio.getText());

                // Ejecutar la acción en el router
                String resultado = (String) routerCV.ejecutarAccion("videojuegos", "actualizarVideojuego", id, titulo, genero, precio);

                if (resultado.startsWith("Error")) {
                    // Mostrar error si el resultado es un error
                    Alert alert = new Alert(Alert.AlertType.ERROR, resultado, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar mensaje de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Videojuego actualizado exitosamente.", ButtonType.OK);
                    alert.showAndWait();

                    // Volver al menú principal
                    stage.setScene(crearMenuVideojuegos());
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si los campos no son números válidos
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese valores válidos.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuVideojuegos()));

        // Mostrar la escena
        stage.setScene(escenaActualizarVideojuego);
    }

    private void eliminarVideojuego() {
        // Crear formulario de eliminar videojuego
        Label lblTitulo = new Label("Eliminar Videojuego");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblID = new Label("ID del videojuego:");
        lblID.setStyle("-fx-text-fill: white;");
        TextField txtID = new TextField();
        txtID.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblID, txtID, btnEliminar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnEliminar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de eliminar videojuego
        Scene escenaEliminarVideojuego = new Scene(layout, 500, 200);

        // Acción del botón eliminar
        btnEliminar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());

                // Ejecutar la acción en el router
                String resultado = (String) routerCV.ejecutarAccion("videojuegos", "eliminarVideojuego", id);

                if (resultado.startsWith("Error")) {
                    // Mostrar error si el resultado es un error
                    Alert alert = new Alert(Alert.AlertType.ERROR, resultado, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar mensaje de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Videojuego eliminado exitosamente.", ButtonType.OK);
                    alert.showAndWait();

                    // Volver al menú principal
                    stage.setScene(crearMenuVideojuegos());
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si el ID no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese un ID válido.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuVideojuegos()));

        // Mostrar la escena
        stage.setScene(escenaEliminarVideojuego);
    }

    private void verVideojuegoPorID() {
        // Crear formulario de ver videojuego por ID
        Label lblTitulo = new Label("Ver Videojuego por ID");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblID = new Label("ID del videojuego:");
        lblID.setStyle("-fx-text-fill: white;");
        TextField txtID = new TextField();
        txtID.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnVer = new Button("Ver Videojuego");
        btnVer.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblID, txtID, btnVer, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnVer, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de ver videojuego por ID
        Scene escenaVerVideojuego = new Scene(layout, 500, 200);

        // Acción del botón ver
        btnVer.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtID.getText());

                // Ejecutar la acción en el router
                Videojuego videojuego = (Videojuego) routerCV.ejecutarAccion("videojuegos", "getVideojuego", id);

                if (videojuego == null) {
                    // Mostrar error si no se encuentra el videojuego
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Videojuego no encontrado.", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar detalles del videojuego
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "ID: " + videojuego.getId() + "\n" +
                                    "Título: " + videojuego.getTitulo() + "\n" +
                                    "Género: " + videojuego.getGenero() + "\n" +
                                    "Precio: " + videojuego.getPrecio(), ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si el ID no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese un ID válido.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuVideojuegos()));

        // Mostrar la escena
        stage.setScene(escenaVerVideojuego);
    }

    private void listarVideojuegos() {
        // Obtener lista de videojuegos
        List<Videojuego> videojuegos = (List<Videojuego>) routerCV.ejecutarAccion("videojuegos", "listarVideojuegos");

        // Crear botón de volver
        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Acción del botón: regresar al menú de videojuegos
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuVideojuegos())); // Asumiendo que tienes el método `crearMenuVideojuegos()` para volver al menú principal

        // Crear la escena de listado
        VBox layout = new VBox(10);  // Se agrega un espaciado de 10 entre los elementos
        layout.setStyle("-fx-padding: 20; -fx-alignment: center-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Crear título
        Label lblTitulo = new Label("Lista de Videojuegos");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        if (videojuegos != null && !videojuegos.isEmpty()) {
            // Crear un contenedor ScrollPane para hacer la lista desplazable
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true); // Ajustar al ancho de la ventana
            scrollPane.setStyle("-fx-background-color: #333333;"); // Fondo gris más oscuro para el ScrollPane

            // Crear un contenedor para los videojuegos
            VBox listaVideojuegos = new VBox(5);  // Espaciado entre los videojuegos
            for (Videojuego videojuego : videojuegos) {
                // Crear una label con todos los detalles del videojuego
                Label labelVideojuego = new Label(
                        "ID: " + videojuego.getId() + "\n" +
                                "Título: " + videojuego.getTitulo() + "\n" +
                                "Género: " + videojuego.getGenero() + "\n" +
                                "Precio: " + videojuego.getPrecio());
                labelVideojuego.setStyle("-fx-text-fill: white; -fx-padding: 10px; -fx-background-color: #444444; -fx-border-radius: 5; -fx-margin: 5px;");
                labelVideojuego.setAlignment(Pos.CENTER); // Centrar el contenido dentro de cada label
                listaVideojuegos.getChildren().add(labelVideojuego);
            }

            // Agregar la lista de videojuegos al ScrollPane
            scrollPane.setContent(listaVideojuegos);

            // Añadir el título y el ScrollPane al layout principal
            layout.getChildren().addAll(lblTitulo, scrollPane, btnCancelar);  // Asegura que el botón se muestre después de la lista
        } else {
            layout.getChildren().addAll(lblTitulo, new Label("No hay videojuegos registrados."), btnCancelar);
        }

        // Crear la escena y mostrarla
        Scene escenaListarVideojuegos = new Scene(layout, 400, 300);
        stage.setScene(escenaListarVideojuegos);
    }

    /// /////////////PARTIDA
    private void agregarPartida() {
        // Crear formulario de agregar partida
        Label lblTitulo = new Label("Agregar Partida");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblIdJugador = new Label("ID del Jugador:");
        lblIdJugador.setStyle("-fx-text-fill: white;");
        TextField txtIdJugador = new TextField();
        txtIdJugador.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblIdVideojuego = new Label("ID del Videojuego:");
        lblIdVideojuego.setStyle("-fx-text-fill: white;");
        TextField txtIdVideojuego = new TextField();
        txtIdVideojuego.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblDuracion = new Label("Duración (horas):");
        lblDuracion.setStyle("-fx-text-fill: white;");
        TextField txtDuracion = new TextField();
        txtDuracion.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblFecha = new Label("Fecha (yyyy-mm-dd):");
        lblFecha.setStyle("-fx-text-fill: white;");
        TextField txtFecha = new TextField();
        txtFecha.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblPuntos = new Label("Puntos:");
        lblPuntos.setStyle("-fx-text-fill: white;");
        TextField txtPuntos = new TextField();
        txtPuntos.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnAgregar = new Button("Agregar Partida");
        btnAgregar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblIdJugador, txtIdJugador, lblIdVideojuego, txtIdVideojuego, lblDuracion, txtDuracion, lblFecha, txtFecha, lblPuntos, txtPuntos, btnAgregar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnAgregar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de agregar partida
        Scene escenaAgregarPartida = new Scene(layout, 500, 500);

        // Acción del botón agregar
        btnAgregar.setOnAction(e -> {
            try {
                int idJugador = Integer.parseInt(txtIdJugador.getText());
                int idVideojuego = Integer.parseInt(txtIdVideojuego.getText());
                int duracion = Integer.parseInt(txtDuracion.getText());
                LocalDate fecha = LocalDate.parse(txtFecha.getText());
                int puntos = Integer.parseInt(txtPuntos.getText());

                // Obtén jugador y videojuego a través del router
                Jugador jugador = (Jugador) routerCV.ejecutarAccion("jugadores", "verJugadorID", idJugador);
                Videojuego videojuego = (Videojuego) routerCV.ejecutarAccion("videojuegos", "getVideojuego", idVideojuego);

                // Llama al método del controlador
                String resultado = (String) routerCV.ejecutarAccion("partidas", "agregarPartida", jugador, videojuego, puntos, duracion, fecha);

                // Muestra el resultado
                if (resultado.startsWith("Error")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error: " + resultado, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar mensaje de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Partida agregada exitosamente.", ButtonType.OK);
                    alert.showAndWait();

                    // Volver al menú principal
                    stage.setScene(crearMenuPartidas());
                }
            } catch (Exception ex) {
                // Manejo de error si los campos no son válidos
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese datos válidos.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuPartidas()));

        // Mostrar la escena
        stage.setScene(escenaAgregarPartida);
    }

    private void actualizarPartida() {
        // Crear formulario de actualizar partida
        Label lblTitulo = new Label("Actualizar Partida");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblIdPartida = new Label("ID de la Partida:");
        lblIdPartida.setStyle("-fx-text-fill: white;");
        TextField txtIdPartida = new TextField();
        txtIdPartida.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblIdJugador = new Label("ID del Jugador:");
        lblIdJugador.setStyle("-fx-text-fill: white;");
        TextField txtIdJugador = new TextField();
        txtIdJugador.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblIdVideojuego = new Label("ID del Videojuego:");
        lblIdVideojuego.setStyle("-fx-text-fill: white;");
        TextField txtIdVideojuego = new TextField();
        txtIdVideojuego.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblDuracion = new Label("Duración (horas):");
        lblDuracion.setStyle("-fx-text-fill: white;");
        TextField txtDuracion = new TextField();
        txtDuracion.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblFecha = new Label("Fecha (yyyy-mm-dd):");
        lblFecha.setStyle("-fx-text-fill: white;");
        TextField txtFecha = new TextField();
        txtFecha.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Label lblPuntos = new Label("Puntos:");
        lblPuntos.setStyle("-fx-text-fill: white;");
        TextField txtPuntos = new TextField();
        txtPuntos.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnActualizar = new Button("Actualizar Partida");
        btnActualizar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblIdPartida, txtIdPartida, lblIdJugador, txtIdJugador, lblIdVideojuego, txtIdVideojuego, lblDuracion, txtDuracion,
                lblFecha, txtFecha, lblPuntos, txtPuntos, btnActualizar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnActualizar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de actualizar partida
        Scene escenaActualizarPartida = new Scene(layout, 500, 600);

        // Acción del botón actualizar
        btnActualizar.setOnAction(e -> {
            try {
                int idPartida = Integer.parseInt(txtIdPartida.getText());
                int idJugador = Integer.parseInt(txtIdJugador.getText());
                int idVideojuego = Integer.parseInt(txtIdVideojuego.getText());
                int duracion = Integer.parseInt(txtDuracion.getText());
                LocalDate fecha = LocalDate.parse(txtFecha.getText());
                int puntos = Integer.parseInt(txtPuntos.getText());

                // Obtén jugador y videojuego a través del router
                Jugador jugador = (Jugador) routerCV.ejecutarAccion("jugadores", "verJugadorID", idJugador);
                Videojuego videojuego = (Videojuego) routerCV.ejecutarAccion("videojuegos", "getVideojuego", idVideojuego);

                // Crea la partida a actualizar
                Partida partida = new Partida(idPartida, jugador, videojuego, duracion, puntos, fecha);

                // Llama al método del controlador para actualizar
                String resultado = (String) routerCV.ejecutarAccion("partidas", "actualizarPartida", partida);

                // Muestra el resultado
                if (resultado.startsWith("Error")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error: " + resultado, ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar mensaje de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Partida actualizada exitosamente.", ButtonType.OK);
                    alert.showAndWait();

                    // Volver al menú de partidas
                    stage.setScene(crearMenuPartidas());
                }
            } catch (Exception ex) {
                // Manejo de error si los campos no son válidos
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese datos válidos.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuPartidas()));

        // Mostrar la escena
        stage.setScene(escenaActualizarPartida);
    }

    private void eliminarPartida() {
        // Crear formulario de eliminar partida
        Label lblTitulo = new Label("Eliminar Partida");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblIdPartida = new Label("ID de la Partida:");
        lblIdPartida.setStyle("-fx-text-fill: white;");
        TextField txtIdPartida = new TextField();
        txtIdPartida.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnEliminar = new Button("Eliminar Partida");
        btnEliminar.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Label lblResultado = new Label();
        lblResultado.setStyle("-fx-text-fill: white;");

        // Acción del botón eliminar
        btnEliminar.setOnAction(e -> {
            try {
                int idPartida = Integer.parseInt(txtIdPartida.getText());

                // Llama al método del controlador para eliminar la partida
                String resultado = (String) routerCV.ejecutarAccion("partidas", "eliminarPartida", idPartida);

                // Muestra el resultado
                if (resultado.startsWith("Error")) {
                    lblResultado.setText("Error: " + resultado);
                } else {
                    lblResultado.setText("Partida eliminada exitosamente.");
                }
            } catch (Exception ex) {
                lblResultado.setText("Error en los datos ingresados.");
            }
        });

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblIdPartida, txtIdPartida, btnEliminar, lblResultado);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnEliminar, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de eliminar partida
        Scene escenaEliminarPartida = new Scene(layout, 500, 250);

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuPartidas()));

        // Mostrar la escena
        stage.setScene(escenaEliminarPartida);
    }

    private void verPartidaPorID() {
        // Crear formulario de ver partida por ID
        Label lblTitulo = new Label("Ver Partida por ID");
        lblTitulo.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        Label lblIDPartida = new Label("ID de la Partida:");
        lblIDPartida.setStyle("-fx-text-fill: white;");
        TextField txtIdPartida = new TextField();
        txtIdPartida.setStyle("-fx-background-color: #444444; -fx-text-fill: white; -fx-prompt-text-fill: gray; -fx-border-color: #666666;");

        Button btnVer = new Button("Ver Partida");
        btnVer.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");

        // Diseño del formulario
        VBox layout = new VBox(15, lblTitulo, lblIDPartida, txtIdPartida, btnVer, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: top-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Alinear los botones a la izquierda
        HBox hboxBotones = new HBox(15, btnVer, btnCancelar);
        hboxBotones.setStyle("-fx-alignment: center-left;"); // Alineación a la izquierda

        // Agregar los botones al layout
        layout.getChildren().add(hboxBotones);

        // Crear escena de ver partida por ID
        Scene escenaVerPartida = new Scene(layout, 500, 200);

        // Acción del botón ver
        btnVer.setOnAction(e -> {
            try {
                int idPartida = Integer.parseInt(txtIdPartida.getText());

                // Ejecutar la acción en el router
                Partida partida = (Partida) routerCV.ejecutarAccion("partidas", "getPartida", idPartida);

                if (partida == null) {
                    // Mostrar error si no se encuentra la partida
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Partida no encontrada.", ButtonType.OK);
                    alert.showAndWait();
                } else {
                    // Mostrar detalles de la partida
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "ID: " + partida.getId() + "\n" +
                                    "Jugador: " + partida.getIdJugador() + "\n" +
                                    "Videojuego: " + partida.getIdVideojuego() + "\n" +
                                    "Duración: " + partida.getHorasJugadas() + " horas\n" +
                                    "Puntos: " + partida.getPuntosObtenidos() + "\n" +
                                    "Fecha: " + partida.getFechaPartida(), ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                // Manejo de error si el ID no es un número válido
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor ingrese un ID válido.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        // Acción del botón cancelar
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuPartidas()));

        // Mostrar la escena
        stage.setScene(escenaVerPartida);
    }

    private void listarPartidas() {
        // Obtener lista de partidas
        List<Partida> partidas = (List<Partida>) routerCV.ejecutarAccion("partidas", "listarPartidas");

        // Crear botón de volver
        Button btnCancelar = new Button("Volver");
        btnCancelar.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuPartidas())); // Acción para volver al menú de partidas

        // Crear la escena de listado
        VBox layout = new VBox(10);  // Espaciado entre los elementos
        layout.setStyle("-fx-padding: 20; -fx-alignment: center-left; -fx-background-color: #555555;"); // Fondo gris claro

        // Crear título
        Label lblTitulo = new Label("Lista de Partidas");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        if (partidas != null && !partidas.isEmpty()) {
            // Crear un contenedor ScrollPane para hacer la lista desplazable
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true); // Ajustar al ancho de la ventana
            scrollPane.setStyle("-fx-background-color: #333333;"); // Fondo gris más oscuro para el ScrollPane

            // Crear un contenedor para las partidas
            VBox listaPartidas = new VBox(5);  // Espaciado entre las partidas
            for (Partida partida : partidas) {
                // Crear una label con todos los detalles de la partida
                Label labelPartida = new Label(
                        "ID: " + partida.getId() + "\n" +
                                "Jugador: " + partida.getIdJugador() + "\n" +
                                "Videojuego: " + partida.getIdVideojuego() + "\n" +
                                "Fecha: " + partida.getFechaPartida());
                labelPartida.setStyle("-fx-text-fill: white; -fx-padding: 10px; -fx-background-color: #444444; -fx-border-radius: 5; -fx-margin: 5px;");
                labelPartida.setAlignment(Pos.CENTER); // Centrar el contenido dentro de cada label
                listaPartidas.getChildren().add(labelPartida);
            }

            // Agregar la lista de partidas al ScrollPane
            scrollPane.setContent(listaPartidas);

            // Añadir el título y el ScrollPane al layout principal
            layout.getChildren().addAll(lblTitulo, scrollPane, btnCancelar);  // Asegura que el botón se muestre después de la lista
        } else {
            layout.getChildren().addAll(lblTitulo, new Label("No hay partidas registradas."), btnCancelar);
        }

        // Crear la escena y mostrarla
        Scene escenaListarPartidas = new Scene(layout, 400, 300);
        stage.setScene(escenaListarPartidas);
    }

    /// ///////////ESTADISTICA
    private void verEstadisticasHoras() {
        // Obtener las partidas con las estadísticas de horas
        List<Partida> partidas = (List<Partida>) routerCV.ejecutarAccion("partidas", "verEstadisticasHoras");

        // Crear layout principal
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #555555;");

        // Título de la sección
        Label lblTitulo = new Label("Top 10 Estadísticas de Horas Jugadas");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        if (partidas != null && !partidas.isEmpty()) {
            // Crear ScrollPane para la lista de partidas
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setStyle("-fx-background-color: #333333;");

            // Crear contenedor para las partidas
            VBox listaPartidas = new VBox(5);  // Espaciado entre las partidas
            for (int i = 0; i < Math.min(partidas.size(), 10); i++) {
                Partida partida = partidas.get(i);

                // Establecer estilo degradado para los primeros tres puestos
                String fondo = "#444444"; // Fondo gris estándar
                if (i == 0) {
                    fondo = "linear-gradient(to right, #d32f2f, #b71c1c)";  // Rojo para el primer puesto
                } else if (i == 1) {
                    fondo = "linear-gradient(to right, #e64a19, #d84315)";  // Rojo claro para el segundo puesto
                } else if (i == 2) {
                    fondo = "linear-gradient(to right, #f57c00, #ff7043)";  // Naranja rojizo para el tercer puesto
                }

                // Crear una etiqueta para cada partida
                Label labelPartida = new Label(
                        (i + 1) + ". ID: " + partida.getId() + " - Jugador: " + partida.getIdJugador().getNombre() +
                                " - Videojuego: " + partida.getIdVideojuego().getTitulo() + " - Horas jugadas: " + partida.getHorasJugadas());
                labelPartida.setStyle("-fx-text-fill: white; -fx-padding: 10px; -fx-background-color: " + fondo + "; -fx-border-radius: 5; -fx-margin: 5px;");
                labelPartida.setAlignment(Pos.CENTER);  // Centrar el contenido dentro de cada label
                listaPartidas.getChildren().add(labelPartida);
            }

            // Añadir la lista de partidas al ScrollPane
            scrollPane.setContent(listaPartidas);

            // Añadir el título y el ScrollPane al layout principal
            layout.getChildren().addAll(lblTitulo, scrollPane);
        } else {
            layout.getChildren().addAll(lblTitulo, new Label("No se encontraron estadísticas de horas."));
        }

        // Crear botón de volver
        Button btnVolver = new Button("Volver al menú de Estadísticas");
        btnVolver.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));  // Acción para volver al menú de estadísticas

        // Añadir el botón al layout principal
        layout.getChildren().add(btnVolver);

        // Crear la escena y mostrarla
        Scene escenaEstadisticasHoras = new Scene(layout, 450, 400);
        stage.setScene(escenaEstadisticasHoras);
    }

    private void verEstadisticasPuntuacion() {
        // Obtener la lista de jugadores con las estadísticas de puntuación
        List<Jugador> jugadores = (List<Jugador>) routerCV.ejecutarAccion("jugadores", "verEstadisticasPuntuacion");

        // Crear layout principal
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #555555;");

        // Título de la sección
        Label lblTitulo = new Label("Top 10 Estadísticas de Puntuación");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        if (jugadores != null && !jugadores.isEmpty()) {
            // Crear ScrollPane para la lista de jugadores
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setStyle("-fx-background-color: #333333;");

            // Crear contenedor para los jugadores
            VBox listaJugadores = new VBox(5);  // Espaciado entre los jugadores
            for (int i = 0; i < Math.min(jugadores.size(), 10); i++) {
                Jugador jugador = jugadores.get(i);

                // Establecer estilo degradado para los primeros tres puestos
                String fondo = "#444444"; // Fondo gris estándar
                if (i == 0) {
                    fondo = "linear-gradient(to right, #d32f2f, #b71c1c)";  // Rojo para el primer puesto
                } else if (i == 1) {
                    fondo = "linear-gradient(to right, #e64a19, #d84315)";  // Rojo claro para el segundo puesto
                } else if (i == 2) {
                    fondo = "linear-gradient(to right, #f57c00, #ff7043)";  // Naranja rojizo para el tercer puesto
                }

                // Crear una etiqueta para cada jugador
                Label labelJugador = new Label(
                        (i + 1) + ". Jugador: " + jugador.getNombre() + " - Puntuación: " + jugador.getPuntuacion());
                labelJugador.setStyle("-fx-text-fill: white; -fx-padding: 10px; -fx-background-color: " + fondo + "; -fx-border-radius: 5; -fx-margin: 5px;");
                labelJugador.setAlignment(Pos.CENTER);  // Centrar el contenido dentro de cada label
                listaJugadores.getChildren().add(labelJugador);
            }

            // Añadir la lista de jugadores al ScrollPane
            scrollPane.setContent(listaJugadores);

            // Añadir el título y el ScrollPane al layout principal
            layout.getChildren().addAll(lblTitulo, scrollPane);
        } else {
            layout.getChildren().addAll(lblTitulo, new Label("No se encontraron estadísticas de puntuación."));
        }

        // Crear botón de volver
        Button btnVolver = new Button("Volver al menú");
        btnVolver.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));  // Acción para volver al menú de estadísticas

        // Añadir el botón al layout principal
        layout.getChildren().add(btnVolver);

        // Crear la escena y mostrarla
        Scene escenaEstadisticasPuntuacion = new Scene(layout, 450, 400);
        stage.setScene(escenaEstadisticasPuntuacion);
    }

    private void verEstadisticasExperiencia() {
        // Obtener la lista de jugadores con las estadísticas de experiencia
        List<Jugador> jugadores = (List<Jugador>) routerCV.ejecutarAccion("jugadores", "verEstadisticasExperiencia");

        // Crear layout principal
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #555555;");

        // Título de la sección
        Label lblTitulo = new Label("Top 10 Estadísticas de Experiencia");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        if (jugadores != null && !jugadores.isEmpty()) {
            // Crear ScrollPane para la lista de jugadores
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setStyle("-fx-background-color: #333333;");

            // Crear contenedor para los jugadores
            VBox listaJugadores = new VBox(5);  // Espaciado entre los jugadores
            for (int i = 0; i < Math.min(jugadores.size(), 10); i++) {
                Jugador jugador = jugadores.get(i);

                // Establecer estilo degradado para los primeros tres puestos
                String fondo = "#444444"; // Fondo gris estándar
                if (i == 0) {
                    fondo = "linear-gradient(to right, #d32f2f, #b71c1c)";  // Rojo para el primer puesto
                } else if (i == 1) {
                    fondo = "linear-gradient(to right, #e64a19, #d84315)";  // Rojo claro para el segundo puesto
                } else if (i == 2) {
                    fondo = "linear-gradient(to right, #f57c00, #ff7043)";  // Naranja rojizo para el tercer puesto
                }

                // Crear una etiqueta para cada jugador
                Label labelJugador = new Label(
                        (i + 1) + ". Jugador: " + jugador.getNombre() + " - Nivel: " + jugador.getNivel());
                labelJugador.setStyle("-fx-text-fill: white; -fx-padding: 10px; -fx-background-color: " + fondo + "; -fx-border-radius: 5; -fx-margin: 5px;");
                labelJugador.setAlignment(Pos.CENTER);  // Centrar el contenido dentro de cada label
                listaJugadores.getChildren().add(labelJugador);
            }

            // Añadir la lista de jugadores al ScrollPane
            scrollPane.setContent(listaJugadores);

            // Añadir el título y el ScrollPane al layout principal
            layout.getChildren().addAll(lblTitulo, scrollPane);
        } else {
            layout.getChildren().addAll(lblTitulo, new Label("No se encontraron estadísticas de experiencia."));
        }

        // Crear botón de volver
        Button btnVolver = new Button("Volver al menú de Estadísticas");
        btnVolver.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));  // Acción para volver al menú de estadísticas

        // Añadir el botón al layout principal
        layout.getChildren().add(btnVolver);

        // Crear la escena y mostrarla
        Scene escenaEstadisticasExperiencia = new Scene(layout, 450, 400);
        stage.setScene(escenaEstadisticasExperiencia);
    }

    private void mostrarClasificacionVideojuegos() {
        // Obtener la clasificación de videojuegos desde el controlador
        List<Map<String, Object>> clasificacion = (List<Map<String, Object>>) routerCV.ejecutarAccion("partidas", "obtenerClasificacionVideojuegos");

        // Crear layout principal
        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: #555555;");

        // Título de la sección
        Label lblTitulo = new Label("Top 5 Clasificación de Videojuegos");
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: white; -fx-alignment: center;");

        // Comprobar si hay clasificación disponible
        if (clasificacion != null && !clasificacion.isEmpty()) {
            // Crear ScrollPane para la lista de videojuegos
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true);
            scrollPane.setStyle("-fx-background-color: #333333;");

            // Crear contenedor para los videojuegos
            VBox listaVideojuegos = new VBox(5);  // Espaciado entre los videojuegos
            for (int i = 0; i < Math.min(clasificacion.size(), 10); i++) {
                Map<String, Object> clasificacionMap = clasificacion.get(i);
                String titulo = (String) clasificacionMap.get("nombre_videojuego");
                Integer puntos = (Integer) clasificacionMap.get("total_horas");

                // Establecer estilo degradado para los primeros tres puestos
                String fondo = "#444444"; // Fondo gris estándar
                if (i == 0) {
                    fondo = "linear-gradient(to right, #d32f2f, #b71c1c)";  // Rojo para el primer puesto
                } else if (i == 1) {
                    fondo = "linear-gradient(to right, #e64a19, #d84315)";  // Rojo claro para el segundo puesto
                } else if (i == 2) {
                    fondo = "linear-gradient(to right, #f57c00, #ff7043)";  // Naranja rojizo para el tercer puesto
                }

                // Crear una etiqueta para cada videojuego
                Label labelVideojuego = new Label(
                        (i + 1) + ". Videojuego: " + titulo + " - Puntos: " + puntos);
                labelVideojuego.setStyle("-fx-text-fill: white; -fx-padding: 10px; -fx-background-color: " + fondo + "; -fx-border-radius: 5; -fx-margin: 5px;");
                labelVideojuego.setAlignment(Pos.CENTER);  // Centrar el contenido dentro de cada label
                listaVideojuegos.getChildren().add(labelVideojuego);
            }

            // Añadir la lista de videojuegos al ScrollPane
            scrollPane.setContent(listaVideojuegos);

            // Añadir el título y el ScrollPane al layout principal
            layout.getChildren().addAll(lblTitulo, scrollPane);
        } else {
            layout.getChildren().addAll(lblTitulo, new Label("No se encontró la clasificación de videojuegos."));
        }

        // Crear botón de volver
        Button btnVolver = new Button("Volver al menú de Clasificación");
        btnVolver.setStyle("-fx-background-color: #757575; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20; -fx-background-radius: 5;");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));  // Acción para volver al menú de clasificación

        // Añadir el botón al layout principal
        layout.getChildren().add(btnVolver);

        // Crear la escena y mostrarla
        Scene escenaClasificacionVideojuegos = new Scene(layout, 450, 400);
        stage.setScene(escenaClasificacionVideojuegos);
    }
}
