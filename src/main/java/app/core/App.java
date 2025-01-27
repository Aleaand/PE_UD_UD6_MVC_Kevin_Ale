package app.core;

import app.controladores.JugadorControlador;
import app.controladores.PartidaControlador;
import app.controladores.VideoJuegoControlador;
import app.vistas.VistaConsolaRouter;
import app.vistas.VistaJavaFX;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * La clase {@code App} es la clase principal de la aplicación que extiende {@link javafx.application.Application}.
 * Esta clase es responsable de iniciar la aplicación JavaFX, registrar los controladores y vistas,
 * y gestionar la inicialización de la vista correspondiente.
 *
 * <p>La aplicación utiliza un {@link RouterCV} para gestionar las vistas y controladores. En el método {@link #start(Stage)},
 * se registra y asocia los controladores y vistas necesarios, y se inicializa la vista que se debe mostrar.</p>
 *
 * <p>El método {@link #main(String[])} lanza la aplicación JavaFX.</p>
 */
public class App extends Application {
    private static String tipo;

    /**
     * Establece el tipo de vista que se debe inicializar.
     *
     * @param tipo El tipo de vista: 1 para JavaFX, cualquier otro valor para Consola.
     */
    public static void setTipo(int tipo) {
        if (tipo == 1) {
            App.tipo = "javafx";
        }
    }

    /**
     * Método principal para iniciar la aplicación JavaFX.
     * Este método crea el {@link RouterCV}, registra los controladores de la aplicación
     * (para jugadores, videojuegos y partidas), registra las vistas (JavaFX y Consola),
     * y luego inicializa la vista correspondiente.
     *
     * <p>La vista inicializada en este caso es {@code vistaJavaFX} si el tipo es "javafx",
     * de lo contrario, se inicializa {@code vistaConsola}.</p>
     *
     * @param primaryStage El escenario principal de la aplicación JavaFX, que se pasa al {@link RouterCV}.
     */
    @Override
    public void start(Stage primaryStage) {
        RouterCV router = new RouterCV(primaryStage);

        // Registrar controladores
        router.registrarControlador("jugadores", new JugadorControlador());
        router.registrarControlador("videojuegos", new VideoJuegoControlador());
        router.registrarControlador("partidas", new PartidaControlador());

        // Registrar vistas
        router.registrarVista("vistaJavaFX", new VistaJavaFX(router));
        router.registrarVista("vistaConsola", new VistaConsolaRouter(router));

        // Inicializar la vista deseada
        if ("javafx".equalsIgnoreCase(tipo)) {
            router.inicializarVista("vistaJavaFX");
        } else {
            router.inicializarVista("vistaConsola");
        }
    }

    /**
     * Método principal que lanza la aplicación JavaFX.
     *
     * @param args Los argumentos de la línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
