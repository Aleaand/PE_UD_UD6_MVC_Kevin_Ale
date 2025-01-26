package app.controladores;

import app.dao.JugadorDAO;
import app.modelos.Jugador;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar las operaciones relacionadas con los jugadores en el sistema.
 * Esta clase interactúa con el {@link JugadorDAO} para realizar operaciones de persistencia y recuperación
 * de los jugadores desde la base de datos.
 */
public class JugadorControlador {

    private List<Jugador> jugadores = new ArrayList<>();
    private JugadorDAO jugadorDAO = new JugadorDAO();

    /**
     * Agrega un nuevo jugador al sistema.
     *
     * @param nombre     El nombre del jugador.
     * @param nivel      El nivel del jugador.
     * @param puntuacion La puntuación del jugador.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String agregarJugador(String nombre, int nivel, int puntuacion) {
        int id = jugadores.size() + 1;
        Jugador jugador = new Jugador(id, nombre, nivel, puntuacion);

        return jugadorDAO.guardar(jugador);
    }

    /**
     * Actualiza los datos de un jugador existente.
     *
     * @param id         El ID del jugador que se desea actualizar.
     * @param nombre     El nuevo nombre del jugador.
     * @param nivel      El nuevo nivel del jugador.
     * @param puntuacion La nueva puntuación del jugador.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String actualizarJugador(int id, String nombre, int nivel, int puntuacion) {
        Jugador jugador = new Jugador(id, nombre, nivel, puntuacion);

        return jugadorDAO.actualizar(jugador);
    }

    /**
     * Elimina un jugador del sistema.
     *
     * @param id El ID del jugador que se desea eliminar.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String eliminarJugador(int id) {
        return jugadorDAO.eliminar(id);
    }

    /**
     * Recupera los datos de un jugador específico por su ID.
     *
     * @param id El ID del jugador que se desea buscar.
     * @return El jugador correspondiente al ID proporcionado, o {@code null} si no existe.
     */
    public Jugador verJugadorID(int id) {
        return jugadorDAO.buscarPorId(id);
    }

    /**
     * Lista todos los jugadores en el sistema.
     *
     * @return Una lista de todos los jugadores registrados en el sistema.
     */
    public List<Jugador> listarJugadores() {
        return jugadorDAO.listarTodos();
    }

    /**
     * Recupera las estadísticas de puntuación de todos los jugadores.
     *
     * @return Una lista de jugadores ordenada por puntuación.
     */
    public List<Jugador> verEstadisticasPuntuacion() {
        return jugadorDAO.verEstadisticasPuntuacion();
    }

    /**
     * Recupera las estadísticas de experiencia de todos los jugadores.
     *
     * @return Una lista de jugadores ordenada por nivel de experiencia.
     */
    public List<Jugador> verEstadisticasExperiencia() {
        return jugadorDAO.verEstadisticasExperiencia();
    }
}
