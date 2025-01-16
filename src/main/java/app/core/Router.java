package app.core;

import app.vistas.VistaJavaFX;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private final Map<String, Object> controladores = new HashMap<>();
    private final Map<String, Object> vistas = new HashMap<>();
    private final Stage primaryStage;

    // Constructor que recibe el Stage principal
    public Router(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Método para registrar controladores o vistas
    public void registrarRuta(String nombre, Object controlador) {
        controladores.put(nombre, controlador);
    }

    // Método para obtener una ruta
    public Object obtenerRuta(String nombre) {
        return controladores.get(nombre);
    }

    // Método para inicializar una vista JavaFX
    public void inicializarVista(String nombre) {
        Object vista = controladores.get(nombre);
        if (vista instanceof VistaJavaFX) {
            ((VistaJavaFX) vista).start(primaryStage);
        } else {
            System.err.println("La vista '" + nombre + "' no está registrada o no es válida.");
        }
    }

    // Método para ejecutar una acción en un controlador
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
            System.err.println("Ruta no encontrada: " + nombre);
        }
        return null;
    }

    private Class<?>[] convertirParametros(Object[] parametros) {
        if (parametros == null) {
            return new Class<?>[0];
        }

        // Mapear cada parámetro a su clase, manejando primitivas correctamente
        return java.util.Arrays.stream(parametros)
                .map(parametro -> {
                    if (parametro instanceof Integer) return int.class; // Convertir Integer a int
                    if (parametro instanceof Double) return double.class; // Convertir Double a double
                    if (parametro instanceof Float) return float.class; // Convertir Float a float
                    if (parametro instanceof Boolean) return boolean.class; // Convertir Boolean a boolean
                    if (parametro instanceof Long) return long.class; // Convertir Long a long
                    return parametro.getClass();
                })
                .toArray(Class<?>[]::new);
    }

}