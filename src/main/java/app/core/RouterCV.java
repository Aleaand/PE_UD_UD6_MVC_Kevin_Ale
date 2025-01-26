package app.core;

import app.modelos.Jugador;
import app.modelos.Partida;
import app.modelos.Videojuego;
import app.vistas.Vista;
import app.vistas.VistaJavaFX;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * La clase {@code RouterCV} es responsable de gestionar las vistas y controladores dentro de la aplicación.
 * Esta clase actúa como un enrutador para las vistas y controladores, permitiendo registrar, obtener e inicializar vistas
 * y ejecutar acciones en los controladores.
 *
 * <p>El router también maneja el paso de parámetros correctos a los métodos de los controladores mediante el uso de reflexión.</p>
 */
public class RouterCV {
    private final Map<String, Object> controladores = new HashMap<>();
    private final Map<String, Vista> vistas = new HashMap<>();
    private final Stage primaryStage;

    /**
     * Constructor de la clase {@code RouterCV}.
     * Inicializa el router con el escenario principal de JavaFX.
     *
     * @param primaryStage El escenario principal que se utilizará para las vistas JavaFX.
     */
    public RouterCV(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Registra un controlador en el enrutador.
     *
     * @param nombre      Nombre con el que se registrará el controlador.
     * @param controlador El controlador que se quiere registrar.
     */
    public void registrarControlador(String nombre, Object controlador) {
        controladores.put(nombre, controlador);
    }

    /**
     * Registra una vista en el enrutador.
     * Si la vista es una instancia de {@link VistaJavaFX}, se le asigna el {@link Stage} principal.
     *
     * @param nombre Nombre con el que se registrará la vista.
     * @param vista  La vista que se quiere registrar.
     */
    public void registrarVista(String nombre, Vista vista) {
        if (vista instanceof VistaJavaFX) {
            ((VistaJavaFX) vista).setStage(primaryStage);
        }
        vistas.put(nombre, vista);
    }

    /**
     * Obtiene un controlador registrado por su nombre.
     *
     * @param nombre El nombre del controlador que se quiere obtener.
     * @return El controlador registrado, o {@code null} si no se encuentra.
     */
    public Object obtenerControlador(String nombre) {
        return controladores.get(nombre);
    }

    /**
     * Obtiene una vista registrada por su nombre.
     *
     * @param nombre El nombre de la vista que se quiere obtener.
     * @return La vista registrada, o {@code null} si no se encuentra.
     */
    public Vista obtenerVista(String nombre) {
        return vistas.get(nombre);
    }

    /**
     * Inicializa una vista registrada, es decir, llama al método {@code iniciar()} de la vista.
     *
     * @param nombre El nombre de la vista que se desea inicializar.
     */
    public void inicializarVista(String nombre) {
        Vista vista = vistas.get(nombre);
        if (vista != null) {
            vista.iniciar();
        } else {
            System.err.println("La vista '" + nombre + "' no está registrada.");
        }
    }

    /**
     * Ejecuta una acción en un controlador, pasándole los parámetros necesarios.
     * Se utiliza reflexión para invocar el método correspondiente del controlador.
     *
     * @param nombre     El nombre del controlador donde se ejecutará la acción.
     * @param accion     El nombre de la acción que se desea ejecutar.
     * @param parametros Los parámetros que se pasarán al método de la acción.
     * @return El resultado de la acción ejecutada, o {@code null} si hubo un error.
     */
    public Object ejecutarAccion(String nombre, String accion, Object... parametros) {
        Object controlador = controladores.get(nombre);
        if (controlador != null) {
            try {
                var metodo = controlador.getClass().getMethod(accion, convertirParametros(parametros));
                return metodo.invoke(controlador, parametros);
            } catch (Exception e) {
                System.err.println("Error al ejecutar la acción: " + e.getMessage());
            }
        } else {
            System.err.println("Controlador no encontrado: " + nombre);
        }
        return null;
    }

    /**
     * Convierte un array de parámetros a los tipos adecuados para ser usados en reflexión.
     *
     * @param parametros El array de parámetros que se desea convertir.
     * @return Un array de clases que representa los tipos de los parámetros.
     */
    private Class<?>[] convertirParametros(Object[] parametros) {
        if (parametros == null) {
            return new Class<?>[0];
        }

        // Mapear cada parámetro a su clase, manejando primitivas correctamente
        return java.util.Arrays.stream(parametros)
                .map(parametro -> {
                    if (parametro == null) return Object.class; // Manejo de null
                    if (parametro instanceof Integer) return int.class; // Convertir Integer a int
                    if (parametro instanceof Double) return double.class; // Convertir Double a double
                    if (parametro instanceof Float) return float.class; // Convertir Float a float
                    if (parametro instanceof Boolean) return boolean.class; // Convertir Boolean a boolean
                    if (parametro instanceof Long) return long.class; // Convertir Long a long
                    if (parametro instanceof Jugador) return Jugador.class; // Manejo específico para Juego
                    if (parametro instanceof Videojuego) return Videojuego.class; // Manejo específico para Videojuego
                    if (parametro instanceof Partida) return Partida.class; // Manejo específico para Partida
                    return parametro.getClass(); // Cualquier otro tipo
                })
                .toArray(Class<?>[]::new);
    }
}