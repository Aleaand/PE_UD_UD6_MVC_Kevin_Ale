package app.controladores;

import app.dao.PartidaDAO;
import app.modelos.Partida;
import app.modelos.Jugador;
import app.modelos.Videojuego;

import java.util.List;

public class PartidaControlador {

    private PartidaDAO partidaDAO = new PartidaDAO();

    // Método para agregar una nueva partida
    public void agregarPartida(Jugador jugador, Videojuego videojuego, int horasJugadas, int puntosObtenidos, String fechaPartida) {
        Partida partida = new Partida(jugador, videojuego, horasJugadas, puntosObtenidos, fechaPartida);
        partidaDAO.guardar(partida);
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
    public void actualizarPartida(Partida partida) {
        partidaDAO.actualizar(partida);
    }
}
