package app.controladores;

import app.dao.VideoJuegoDAO;
import app.modelos.Videojuego;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar las operaciones relacionadas con los videojuegos en el sistema.
 * Esta clase interactúa con el {@link VideoJuegoDAO} para realizar operaciones de persistencia
 * y recuperación de los videojuegos desde la base de datos.
 */
public class VideoJuegoControlador {
    private List<Videojuego> videojuegos = new ArrayList<>();
    private VideoJuegoDAO videoJuegoDAO = new VideoJuegoDAO();

    /**
     * Agrega un nuevo videojuego al sistema.
     *
     * @param titulo El título del videojuego.
     * @param genero El género del videojuego.
     * @param precio El precio del videojuego.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String agregarVideojuego(String titulo, String genero, double precio) {
        int id = videojuegos.size() + 1;
        Videojuego videojuego = new Videojuego(id, titulo, genero, precio);
        videojuegos.add(videojuego);
        return videoJuegoDAO.guardar(videojuego);
    }

    /**
     * Lista todos los videojuegos registrados en el sistema.
     *
     * @return Una lista de todos los videojuegos.
     */
    public List<Videojuego> listarVideojuegos() {
        return videoJuegoDAO.listarTodos();
    }

    /**
     * Obtiene un videojuego específico por su ID.
     *
     * @param id El ID del videojuego a recuperar.
     * @return El videojuego correspondiente al ID proporcionado, o {@code null} si no existe.
     */
    public Videojuego getVideojuego(int id) {
        return videoJuegoDAO.buscarPorId(id);
    }

    /**
     * Elimina un videojuego del sistema.
     *
     * @param id El ID del videojuego que se desea eliminar.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String eliminarVideojuego(int id) {
        return videoJuegoDAO.eliminar(id);
    }

    /**
     * Actualiza los datos de un videojuego existente.
     *
     * @param id     El ID del videojuego a actualizar.
     * @param titulo El nuevo título del videojuego.
     * @param genero El nuevo género del videojuego.
     * @param precio El nuevo precio del videojuego.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String actualizarVideojuego(int id, String titulo, String genero, double precio) {
        Videojuego videojuego = videoJuegoDAO.buscarPorId(id);
        videojuego.setTitulo(titulo);
        videojuego.setGenero(genero);
        videojuego.setPrecio(BigDecimal.valueOf(precio));
        return videoJuegoDAO.actualizar(videojuego);
    }
}
