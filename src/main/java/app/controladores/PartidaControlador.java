package app.controladores;

import app.dao.PartidaDAO;
import app.modelos.Partida;
import app.modelos.Videojuego;
import app.modelos.Jugador;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class PartidaControlador {

    private PartidaDAO partidaDAO = new PartidaDAO();

    // Método para agregar una nueva partida
    public String agregarPartida(Jugador jugador, Videojuego videojuego, int horasJugadas, int puntosObtenidos, LocalDate fechaPartida) {
        Partida partida = new Partida(jugador, videojuego,puntosObtenidos, horasJugadas,  fechaPartida);
       return partidaDAO.guardar(partida);
    }

    // Método para listar todas las partidas
    public List<Partida> listarPartidas() {
        return partidaDAO.listarTodos();
    }

    // Método para obtener una partida por su ID
    public Partida getPartida(int id) {
        return partidaDAO.buscarPorId(id);
    }

    // Método para eliminar una partida
    public String eliminarPartida(int id) {
        return partidaDAO.eliminar(id);
    }

    // Método para actualizar una partida
    public String actualizarPartida(Partida partida) {
        return partidaDAO.actualizar(partida);
    }
    // verEstadisticasHoras
    public List<Partida> verEstadisticasHoras() {
        return partidaDAO.verEstadisticasHoras();
    }

    public List<Map<String, Object>> obtenerClasificacionVideojuegos() {
        return partidaDAO.obtenerClasificacionVideojuegos();
    }
}
