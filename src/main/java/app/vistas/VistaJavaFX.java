package app.vistas;

import app.core.RouterCV;
import app.modelos.Jugador;
import app.modelos.Partida;
import app.modelos.Videojuego;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaJavaFX implements Vista {
    private final RouterCV routerCV;
    private Stage stage;

    // Listas observables para jugadores y videojuegos
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

    private Scene crearMenuPrincipal() {
        Label lblTitulo = new Label("=== Menú Principal ===");
        Button btnJugadores = new Button("1. Jugadores");
        Button btnVideojuegos = new Button("2. Videojuegos");
        Button btnPartidas = new Button("3. Partidas");
        Button btnEstadisticas = new Button("4. Estadísticas");
        Button btnSalir = new Button("5. Salir");

        btnJugadores.setOnAction(e -> stage.setScene(crearMenuJugadores()));
        btnVideojuegos.setOnAction(e -> stage.setScene(crearMenuVideojuegos()));
        btnPartidas.setOnAction(e -> stage.setScene(crearMenuPartidas()));
        btnEstadisticas.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));
        btnSalir.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, lblTitulo, btnJugadores, btnVideojuegos, btnPartidas, btnEstadisticas, btnSalir);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 400, 300);
    }

    private Scene crearMenuJugadores() {
        Label lblTitulo = new Label("=== Menú de Jugadores ===");
        Button btnAgregar = new Button("1. Agregar Jugador");
        Button btnActualizar = new Button("2. Actualizar Jugador");
        Button btnEliminar = new Button("3. Eliminar Jugador");
        Button btnVerID = new Button("4. Ver Jugador por ID");
        Button btnListar = new Button("5. Ver todos los Jugadores");
        Button btnVolver = new Button("6. Volver al menú principal");

        btnAgregar.setOnAction(e -> agregarJugador());
        btnActualizar.setOnAction(e -> actualizarJugador());
        btnEliminar.setOnAction(e -> eliminarJugador());
        btnVerID.setOnAction(e -> verJugadorPorID());
        btnListar.setOnAction(e -> listarJugadores());
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(10, lblTitulo, btnAgregar, btnActualizar, btnEliminar, btnVerID, btnListar, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 400, 300);
    }

    private Scene crearMenuVideojuegos() {
        Label lblTitulo = new Label("=== Menú de Videojuegos ===");
        Button btnAgregar = new Button("1. Agregar Videojuego");
        Button btnActualizar = new Button("2. Actualizar Videojuego");
        Button btnEliminar = new Button("3. Eliminar Videojuego");
        Button btnVerID = new Button("4. Ver Videojuego por ID");
        Button btnListar = new Button("5. Ver todos los Videojuegos");
        Button btnVolver = new Button("6. Volver al menú principal");

        btnAgregar.setOnAction(e -> agregarVideojuego());
        btnActualizar.setOnAction(e -> actualizarVideojuego());
        btnEliminar.setOnAction(e -> eliminarVideojuego());
        btnVerID.setOnAction(e -> verVideojuegoPorID());
        btnListar.setOnAction(e -> listarVideojuegos());
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(10, lblTitulo, btnAgregar, btnActualizar, btnEliminar, btnVerID, btnListar, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 400, 300);
    }

    private Scene crearMenuPartidas() {
        Label lblTitulo = new Label("=== Menú de Partidas ===");
        Button btnAgregar = new Button("1. Agregar Partida");
        Button btnActualizar = new Button("2. Actualizar Partida");
        Button btnEliminar = new Button("3. Eliminar Partida");
        Button btnVerID = new Button("4. Ver Partida por ID");
        Button btnListar = new Button("5. Ver todas las Partidas");
        Button btnVolver = new Button("6. Volver al menú principal");

        btnAgregar.setOnAction(e -> agregarPartida());
        btnActualizar.setOnAction(e -> actualizarPartida());
        btnEliminar.setOnAction(e -> eliminarPartida());
        btnVerID.setOnAction(e -> verPartidaPorID());
        btnListar.setOnAction(e -> listarPartidas());
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(10, lblTitulo, btnAgregar, btnActualizar, btnEliminar, btnVerID, btnListar, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 400, 300);
    }

    private Scene crearMenuEstadisticas() {
        Label lblTitulo = new Label("=== Menú de Estadísticas ===");
        Button btnHoras = new Button("1. Ver Estadísticas de horas de juego");
        Button btnPuntuacion = new Button("2. Ver Estadísticas de puntuación");
        Button btnExperiencia = new Button("3. Ver Estadísticas de experiencia");
        Button btnClasificacion = new Button("4. Ver Clasificación de Videojuegos");
        Button btnVolver = new Button("5. Volver al menú principal");

        btnHoras.setOnAction(e -> verEstadisticasHoras());
        btnPuntuacion.setOnAction(e -> verEstadisticasPuntuacion());
        btnExperiencia.setOnAction(e -> verEstadisticasExperiencia());
        btnClasificacion.setOnAction(e -> mostrarClasificacionVideojuegos());
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(10, lblTitulo, btnHoras, btnPuntuacion, btnExperiencia, btnClasificacion, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 400, 300);
    }

    private Scene crearMenuStub(String titulo) {
        Label lblTitulo = new Label("=== " + titulo + " ===");
        Button btnVolver = new Button("Volver al menú principal");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPrincipal()));

        VBox layout = new VBox(10, lblTitulo, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");
        return new Scene(layout, 400, 300);
    }

    private void cargarDatosIniciales() {
        jugadores.setAll((ObservableList<Jugador>) routerCV.ejecutarAccion("jugadores", "listarJugadores"));
        videoJuegos.setAll((ObservableList<Videojuego>) routerCV.ejecutarAccion("videojuegos", "listarVideojuegos"));
    }

    /// //////////////////////// JUGADOR
    private void agregarJugador() {
        // Crear formulario de agregar jugador
        Label lblTitulo = new Label("Agregar Jugador");
        Label lblNombre = new Label("Nombre del jugador:");
        TextField txtNombre = new TextField();
        Label lblNivel = new Label("Nivel del jugador:");
        TextField txtNivel = new TextField();
        Label lblPuntuacion = new Label("Puntuación del jugador:");
        TextField txtPuntuacion = new TextField();
        Button btnAgregar = new Button("Agregar");
        Button btnCancelar = new Button("Volver");

        // Diseño del formulario
        VBox layout = new VBox(10, lblTitulo, lblNombre, txtNombre, lblNivel, txtNivel, lblPuntuacion, txtPuntuacion, btnAgregar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear escena de agregar jugador
        Scene escenaAgregarJugador = new Scene(layout, 400, 300);

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
        Label lblID = new Label("ID del jugador:");
        TextField txtID = new TextField();
        Label lblNombre = new Label("Nuevo nombre del jugador:");
        TextField txtNombre = new TextField();
        Label lblNivel = new Label("Nuevo nivel del jugador:");
        TextField txtNivel = new TextField();
        Label lblPuntuacion = new Label("Nueva puntuación del jugador:");
        TextField txtPuntuacion = new TextField();
        Button btnActualizar = new Button("Actualizar");
        Button btnCancelar = new Button("Volver");

        // Diseño del formulario
        VBox layout = new VBox(10, lblTitulo, lblID, txtID, lblNombre, txtNombre, lblNivel, txtNivel, lblPuntuacion, txtPuntuacion, btnActualizar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear escena de actualizar jugador
        Scene escenaActualizarJugador = new Scene(layout, 400, 300);

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
        Label lblID = new Label("ID del jugador:");
        TextField txtID = new TextField();
        Button btnEliminar = new Button("Eliminar");
        Button btnCancelar = new Button("Volver");

        // Diseño del formulario
        VBox layout = new VBox(10, lblTitulo, lblID, txtID, btnEliminar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear escena de eliminar jugador
        Scene escenaEliminarJugador = new Scene(layout, 400, 300);

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
        Label lblID = new Label("ID del jugador:");
        TextField txtID = new TextField();
        Button btnVer = new Button("Ver Jugador");
        Button btnCancelar = new Button("Volver");

        // Diseño del formulario
        VBox layout = new VBox(10, lblTitulo, lblID, txtID, btnVer, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear escena de ver jugador por ID
        Scene escenaVerJugador = new Scene(layout, 400, 300);

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
        btnCancelar.setStyle("-fx-font-size: 14px; -fx-padding: 10px;"); // Opcional: agregar estilo al botón

        // Acción del botón: regresar al menú de jugadores
        btnCancelar.setOnAction(e -> stage.setScene(crearMenuJugadores())); // Asumiendo que tienes el método `crearMenuJugadores()` para volver al menú principal

        // Crear la escena de listado
        VBox layout = new VBox(10);  // Se agrega un espaciado de 10 entre los elementos
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        if (jugadores != null && !jugadores.isEmpty()) {
            // Crear un contenedor ScrollPane para hacer la lista desplazable
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setFitToWidth(true); // Ajustar al ancho de la ventana

            // Crear un contenedor para los jugadores
            VBox listaJugadores = new VBox(5);  // Espaciado entre los jugadores
            for (Jugador jugador : jugadores) {
                listaJugadores.getChildren().add(new Label(jugador.getNombre()));
            }

            // Agregar la lista de jugadores al ScrollPane
            scrollPane.setContent(listaJugadores);

            // Añadir el ScrollPane al layout principal
            layout.getChildren().addAll(scrollPane, btnCancelar);  // Asegura que el botón se muestre después de la lista
        } else {
            layout.getChildren().addAll(new Label("No hay jugadores registrados."), btnCancelar);
        }

        // Crear la escena y mostrarla
        Scene escenaListarJugadores = new Scene(layout, 400, 300);
        stage.setScene(escenaListarJugadores);
    }


    /// //////////////////VIDEOJUEGO
    private void agregarVideojuego() {
        // Crear formulario de agregar videojuego
        Label lblTitulo = new Label("Agregar Videojuego");
        Label lblTituloJuego = new Label("Título del videojuego:");
        TextField txtTitulo = new TextField();
        Label lblGenero = new Label("Género del videojuego:");
        TextField txtGenero = new TextField();
        Label lblPrecio = new Label("Precio del videojuego:");
        TextField txtPrecio = new TextField();
        Button btnAgregar = new Button("Agregar");
        Button btnCancelar = new Button("Volver");

        // Diseño del formulario
        VBox layout = new VBox(10, lblTitulo, lblTituloJuego, txtTitulo, lblGenero, txtGenero, lblPrecio, txtPrecio, btnAgregar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear escena de agregar videojuego
        Scene escenaAgregarVideojuego = new Scene(layout, 400, 300);

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
        Label lblID = new Label("ID del videojuego:");
        TextField txtID = new TextField();
        Label lblTituloJuego = new Label("Nuevo título del videojuego:");
        TextField txtTitulo = new TextField();
        Label lblGenero = new Label("Nuevo género del videojuego:");
        TextField txtGenero = new TextField();
        Label lblPrecio = new Label("Nuevo precio del videojuego:");
        TextField txtPrecio = new TextField();
        Button btnActualizar = new Button("Actualizar");
        Button btnCancelar = new Button("Volver");

        // Diseño del formulario
        VBox layout = new VBox(10, lblTitulo, lblID, txtID, lblTituloJuego, txtTitulo, lblGenero, txtGenero, lblPrecio, txtPrecio, btnActualizar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear escena de actualizar videojuego
        Scene escenaActualizarVideojuego = new Scene(layout, 400, 300);

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
        Label lblID = new Label("ID del videojuego:");
        TextField txtID = new TextField();
        Button btnEliminar = new Button("Eliminar");
        Button btnCancelar = new Button("Volver");

        // Diseño del formulario
        VBox layout = new VBox(10, lblTitulo, lblID, txtID, btnEliminar, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear escena de eliminar videojuego
        Scene escenaEliminarVideojuego = new Scene(layout, 400, 300);

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
        Label lblID = new Label("ID del videojuego:");
        TextField txtID = new TextField();
        Button btnVer = new Button("Ver Videojuego");
        Button btnCancelar = new Button("Volver");

        // Diseño del formulario
        VBox layout = new VBox(10, lblTitulo, lblID, txtID, btnVer, btnCancelar);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear escena de ver videojuego por ID
        Scene escenaVerVideojuego = new Scene(layout, 400, 300);

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

        // Crear un VBox para contener los elementos de la lista
        VBox vboxContenido = new VBox(10);
        vboxContenido.setStyle("-fx-padding: 20; -fx-alignment: center;");

        if (videojuegos != null && !videojuegos.isEmpty()) {
            for (Videojuego videojuego : videojuegos) {
                vboxContenido.getChildren().add(new Label("ID: " + videojuego.getId() + " - Título: " + videojuego.getTitulo()));
            }
        } else {
            vboxContenido.getChildren().add(new Label("No hay videojuegos disponibles."));
        }

        // Crear un ScrollPane para envolver el contenido
        ScrollPane scrollPane = new ScrollPane(vboxContenido);
        scrollPane.setFitToWidth(true);  // Ajusta el ancho para que el contenido no se desborde

        // Crear el botón para volver al menú
        Button btnVolver = new Button("Volver al menú de Videojuegos");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuVideojuegos()));  // Cambia al menú de videojuegos

        // Crear un VBox para contener el ScrollPane y el botón
        VBox layout = new VBox(10, scrollPane, btnVolver);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Crear la escena con el layout
        Scene escenaListarVideojuegos = new Scene(layout, 400, 300);
        stage.setScene(escenaListarVideojuegos);
    }

    /// /////////////PARTIDA
    private void agregarPartida() {
        // Crear un formulario con campos de texto para ingresar datos
        TextField txtIdJugador = new TextField();
        TextField txtIdVideojuego = new TextField();
        TextField txtDuracion = new TextField();
        TextField txtFecha = new TextField();
        TextField txtPuntos = new TextField();

        Button btnAgregar = new Button("Agregar Partida");
        Label lblResultado = new Label();

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
                    lblResultado.setText("Error: " + resultado);
                } else {
                    lblResultado.setText("Partida agregada exitosamente.");
                }
            } catch (Exception ex) {
                lblResultado.setText("Error en los datos ingresados.");
            }
        });

        VBox vbox = new VBox(10, new Label("ID Jugador:"), txtIdJugador, new Label("ID Videojuego:"), txtIdVideojuego, new Label("Duración (horas):"), txtDuracion,
                new Label("Fecha (yyyy-mm-dd):"), txtFecha, new Label("Puntos:"), txtPuntos, btnAgregar, lblResultado);
        vbox.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
    }

    private void actualizarPartida() {
        // Crear campos para ingresar los datos
        TextField txtIdPartida = new TextField();
        TextField txtIdJugador = new TextField();
        TextField txtIdVideojuego = new TextField();
        TextField txtDuracion = new TextField();
        TextField txtFecha = new TextField();
        TextField txtPuntos = new TextField();

        Button btnActualizar = new Button("Actualizar Partida");
        Label lblResultado = new Label();

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
                    lblResultado.setText("Error: " + resultado);
                } else {
                    lblResultado.setText("Partida actualizada exitosamente.");
                }
            } catch (Exception ex) {
                lblResultado.setText("Error en los datos ingresados.");
            }
        });

        VBox vbox = new VBox(10, new Label("ID Partida:"), txtIdPartida, new Label("ID Jugador:"), txtIdJugador, new Label("ID Videojuego:"), txtIdVideojuego,
                new Label("Duración (horas):"), txtDuracion, new Label("Fecha (yyyy-mm-dd):"), txtFecha, new Label("Puntos:"), txtPuntos, btnActualizar, lblResultado);
        vbox.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
    }

    private void eliminarPartida() {
        TextField txtIdPartida = new TextField();
        Button btnEliminar = new Button("Eliminar Partida");
        Label lblResultado = new Label();

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

        VBox vbox = new VBox(10, new Label("ID Partida:"), txtIdPartida, btnEliminar, lblResultado);
        vbox.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(vbox, 400, 200);
        stage.setScene(scene);
    }

    private void verPartidaPorID() {
        TextField txtIdPartida = new TextField();
        Button btnBuscar = new Button("Ver Partida");
        Label lblResultado = new Label();

        btnBuscar.setOnAction(e -> {
            try {
                int idPartida = Integer.parseInt(txtIdPartida.getText());

                // Llama al método del controlador para obtener la partida
                Partida partida = (Partida) routerCV.ejecutarAccion("partidas", "getPartida", idPartida);

                if (partida != null) {
                    lblResultado.setText("Partida encontrada: " + partida.toString());
                } else {
                    lblResultado.setText("Partida no encontrada.");
                }
            } catch (Exception ex) {
                lblResultado.setText("Error en los datos ingresados.");
            }
        });

        VBox vbox = new VBox(10, new Label("ID Partida:"), txtIdPartida, btnBuscar, lblResultado);
        vbox.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(vbox, 400, 200);
        stage.setScene(scene);
    }

    private void listarPartidas() {
        List<Partida> partidas = (List<Partida>) routerCV.ejecutarAccion("partidas", "listarPartidas");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        if (partidas != null && !partidas.isEmpty()) {
            for (Partida partida : partidas) {
                layout.getChildren().add(new Label("ID: " + partida.getId() + " - Jugador: " + partida.getIdJugador().getNombre() +
                        " - Videojuego: " + partida.getIdVideojuego().getTitulo() + " - Fecha: " + partida.getFechaPartida()));
            }
        } else {
            layout.getChildren().add(new Label("No se encontraron partidas."));
        }

        // Agregar botón para volver al menú
        Button btnVolver = new Button("Volver al menú de Partidas");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuPartidas()));  // Asume que tienes un método crearMenuPartidas() para regresar al menú principal

        // Crear ScrollPane
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        VBox vbox = new VBox(10, scrollPane, btnVolver);
        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
    }

    /// ///////////ESTADISTICA
    private void verEstadisticasHoras() {
        List<Partida> partidas = (List<Partida>) routerCV.ejecutarAccion("partidas", "verEstadisticasHoras");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        if (partidas != null && !partidas.isEmpty()) {
            for (Partida partida : partidas) {
                layout.getChildren().add(new Label("ID: " + partida.getId() + " - Jugador: " + partida.getIdJugador().getNombre() +
                        " - Videojuego: " + partida.getIdVideojuego().getTitulo() + " - Horas jugadas: " + partida.getHorasJugadas()));
            }
        } else {
            layout.getChildren().add(new Label("No se encontraron estadísticas de horas."));
        }

        // Agregar botón para volver al menú
        Button btnVolver = new Button("Volver al menú de Estadísticas");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));  // Método que te lleva al menú de estadísticas

        // Crear ScrollPane
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        VBox vbox = new VBox(10, scrollPane, btnVolver);
        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
    }

    private void verEstadisticasPuntuacion() {
        List<Jugador> jugadores = (List<Jugador>) routerCV.ejecutarAccion("jugadores", "verEstadisticasPuntuacion");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        if (jugadores != null && !jugadores.isEmpty()) {
            for (Jugador jugador : jugadores) {
                layout.getChildren().add(new Label("Jugador: " + jugador.getNombre() + " - Puntuación: " + jugador.getPuntuacion()));
            }
        } else {
            layout.getChildren().add(new Label("No se encontraron estadísticas de puntuación."));
        }

        // Agregar botón para volver al menú
        Button btnVolver = new Button("Volver al menú de Estadísticas");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));  // Método que te lleva al menú de estadísticas

        // Crear ScrollPane
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        VBox vbox = new VBox(10, scrollPane, btnVolver);
        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
    }

    private void verEstadisticasExperiencia() {
        List<Jugador> jugadores = (List<Jugador>) routerCV.ejecutarAccion("jugadores", "verEstadisticasExperiencia");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        if (jugadores != null && !jugadores.isEmpty()) {
            for (Jugador jugador : jugadores) {
                layout.getChildren().add(new Label("Jugador: " + jugador.getNombre() + " - Experiencia/Nivel: " + jugador.getNivel()));
            }
        } else {
            layout.getChildren().add(new Label("No se encontraron estadísticas de experiencia."));
        }

        // Agregar botón para volver al menú
        Button btnVolver = new Button("Volver al menú de Estadísticas");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));  // Método que te lleva al menú de estadísticas

        // Crear ScrollPane
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        VBox vbox = new VBox(10, scrollPane, btnVolver);
        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
    }

    private void mostrarClasificacionVideojuegos() {
        // Obtener la clasificación de videojuegos desde el controlador
        List<Map<String, Object>> clasificacion = (List<Map<String, Object>>) routerCV.ejecutarAccion("partidas", "obtenerClasificacionVideojuegos");

        VBox layout = new VBox(10);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        if (clasificacion != null && !clasificacion.isEmpty()) {
            // Recorremos cada mapa que representa un videojuego y sus detalles
            for (Map<String, Object> clasificacionMap : clasificacion) {
                String titulo = (String) clasificacionMap.get("titulo");  // Obtenemos el título del videojuego
                Integer puntos = (Integer) clasificacionMap.get("puntos");  // Obtenemos los puntos del videojuego

                // Agregamos una etiqueta que contiene el nombre y los puntos del videojuego
                layout.getChildren().add(new Label("Videojuego: " + titulo + " - Puntos: " + puntos));
            }
        } else {
            // Si no hay datos, mostramos un mensaje de error
            layout.getChildren().add(new Label("No se encontró la clasificación de videojuegos."));
        }

        // Agregar botón para volver al menú
        Button btnVolver = new Button("Volver al menú de Clasificación");
        btnVolver.setOnAction(e -> stage.setScene(crearMenuEstadisticas()));  // Método que te lleva al menú de clasificación

        // Crear ScrollPane para hacer la vista desplazable
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        // Colocar el ScrollPane y el botón de volver en un VBox
        VBox vbox = new VBox(10, scrollPane, btnVolver);
        Scene scene = new Scene(vbox, 400, 300);
        stage.setScene(scene);
    }
}
