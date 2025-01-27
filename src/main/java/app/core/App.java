package app.core;

import app.controladores.JugadorControlador;
import app.controladores.PartidaControlador;
import app.controladores.VideoJuegoControlador;
import app.vistas.VistaConsolaRouter;
import app.vistas.VistaJavaFX;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private static String tipo;

    public static void setTipo(int tipo) {
        if (tipo == 1){
            App.tipo = "javafx";
        }
    }

    @Override
    public void start(Stage primaryStage) {
        RouterCV router = new RouterCV(primaryStage);

        router.registrarControlador("jugadores", new JugadorControlador());
        router.registrarControlador("videojuegos", new VideoJuegoControlador());
        router.registrarControlador("partidas", new PartidaControlador());

        router.registrarVista("vistaJavaFX", new VistaJavaFX(router));
        router.registrarVista("vistaConsola", new VistaConsolaRouter(router));

        if ("javafx".equalsIgnoreCase(tipo)) {
            router.inicializarVista("vistaJavaFX");
        } else {
            router.inicializarVista("vistaConsola");
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}
