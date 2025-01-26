package app.controladores;

import app.dao.PartidaDAO;
import app.modelos.Partida;
import app.modelos.Videojuego;
import app.modelos.Jugador;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Clase encargada de gestionar las operaciones relacionadas con las partidas en el sistema.
 * Esta clase interactúa con el {@link PartidaDAO} para realizar operaciones de persistencia y recuperación
 * de las partidas desde la base de datos.
 */
public class PartidaControlador {

    private PartidaDAO partidaDAO = new PartidaDAO();

    /**
     * Agrega una nueva partida al sistema.
     *
     * @param jugador         El jugador que participó en la partida.
     * @param videojuego      El videojuego en el que se jugó.
     * @param horasJugadas    La cantidad de horas jugadas en la partida.
     * @param puntosObtenidos Los puntos obtenidos por el jugador durante la partida.
     * @param fechaPartida    La fecha en la que se jugó la partida.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String agregarPartida(Jugador jugador, Videojuego videojuego, int horasJugadas, int puntosObtenidos, LocalDate fechaPartida) {
        Partida partida = new Partida(jugador, videojuego, puntosObtenidos, horasJugadas, fechaPartida);
        return partidaDAO.guardar(partida);
    }

    /**
     * Lista todas las partidas registradas en el sistema.
     *
     * @return Una lista de todas las partidas.
     */
    public List<Partida> listarPartidas() {
        return partidaDAO.listarTodos();
    }

    /**
     * Obtiene una partida específica por su ID.
     *
     * @param id El ID de la partida a recuperar.
     * @return La partida correspondiente al ID proporcionado, o {@code null} si no existe.
     */
    public Partida getPartida(int id) {
        return partidaDAO.buscarPorId(id);
    }

    /**
     * Elimina una partida del sistema.
     *
     * @param id El ID de la partida que se desea eliminar.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String eliminarPartida(int id) {
        return partidaDAO.eliminar(id);
    }

    /**
     * Actualiza los datos de una partida existente.
     *
     * @param partida La partida con los nuevos datos a guardar.
     * @return Un mensaje indicando el éxito o el fracaso de la operación.
     */
    public String actualizarPartida(Partida partida) {
        return partidaDAO.actualizar(partida);
    }

    /**
     * Recupera las estadísticas de horas jugadas de todas las partidas.
     *
     * @return Una lista de partidas ordenadas por la cantidad de horas jugadas.
     */
    public List<Partida> verEstadisticasHoras() {
        return partidaDAO.verEstadisticasHoras();
    }

    /**
     * Recupera la clasificación de videojuegos con la cantidad total de horas jugadas y jugadores.
     *
     * @return Una lista de mapas con la información de cada videojuego y su clasificación.
     */
    public List<Map<String, Object>> obtenerClasificacionVideojuegos() {
        return partidaDAO.obtenerClasificacionVideojuegos();
    }
}
