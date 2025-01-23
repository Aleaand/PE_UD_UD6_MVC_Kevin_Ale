package app.core;

import app.controladores.JugadorControlador;
import app.controladores.VideoJuegoControlador;
import app.vistas.VistaConsolaRouter;
import app.vistas.VistaJavaFX;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

  @Override
    public void start(Stage primaryStage) {
        // Crear el router con el Stage principal
        //Router router = new Router(primaryStage);
        RouterCV router = new RouterCV(primaryStage);

        // Registrar controladores
        router.registrarControlador("jugadores", new JugadorControlador());
        router.registrarControlador("videojuegos", new VideoJuegoControlador());

        // Registrar vistas
        router.registrarVista("vistaJavaFX", new VistaJavaFX(router));
        router.registrarVista("vistaConsola", new VistaConsolaRouter(router));

        // Inicializar la vista deseada
        //router.inicializarVista("vistaJavaFX");
        // Alternativamente, puedes inicializar la vista de consola:
        router.inicializarVista("vistaConsola");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
