package app.core;

import app.vistas.Vista;
import app.vistas.VistaJavaFX;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class RouterCV {
    private final Map<String, Object> controladores = new HashMap<>();
    private final Map<String, Vista> vistas = new HashMap<>();
    private final Stage primaryStage;

    public RouterCV(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    // Registrar un controlador
    public void registrarControlador(String nombre, Object controlador) {
        controladores.put(nombre, controlador);
    }

    // Registrar una vista
    public void registrarVista(String nombre, Vista vista) {
        if (vista instanceof VistaJavaFX) {
            ((VistaJavaFX) vista).setStage(primaryStage);
        }
        vistas.put(nombre, vista);
    }

    // Obtener un controlador
    public Object obtenerControlador(String nombre) {
        return controladores.get(nombre);
    }

    // Obtener una vista
    public Vista obtenerVista(String nombre) {
        return vistas.get(nombre);
    }

    // Inicializar una vista
    public void inicializarVista(String nombre) {
        Vista vista = vistas.get(nombre);
        if (vista != null) {
            vista.iniciar();
        } else {
            System.err.println("La vista '" + nombre + "' no está registrada.");
        }
    }


    // Ejecutar una acción en un controlador
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

    // Método para convertir parámetros a tipos adecuados
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

