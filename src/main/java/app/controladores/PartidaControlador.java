package app.controladores;

import app.dao.JugadorDAO;
import app.dao.PartidaDAO;
import app.dao.VideoJuegoDAO;
import app.modelos.Partida;
import app.modelos.Jugador;
import app.modelos.Videojuego;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartidaControlador {

    private PartidaDAO partidaDAO = new PartidaDAO();
    private JugadorDAO jugadorDAO = new JugadorDAO();
    private VideoJuegoDAO videoJuegoDAO = new VideoJuegoDAO();

    // Método para agregar una nueva partida
    public String agregarPartida( int idJugador, int idVideojuego, int horasJugadas, int puntosObtenidos, LocalDate fechaPartida) {
        Jugador jugador = jugadorDAO.buscarPorId(idJugador);
        Videojuego videojuego = videoJuegoDAO.buscarPorId(idVideojuego);
        Partida partida= new Partida(jugador,videojuego,horasJugadas,puntosObtenidos,fechaPartida);
        partidaDAO.actualizar(partida);
        return partida.toString();
    }
    // Método para actualizar una partida
    public String actualizarPartida( int id, int idJugador, int idVideojuego, int horasJugadas, int puntosObtenidos, LocalDate fechaPartida) {
        Jugador jugador = jugadorDAO.buscarPorId(idJugador);
        Videojuego videojuego = videoJuegoDAO.buscarPorId(idVideojuego);
        Partida partida= new Partida(id,jugador,videojuego,horasJugadas,puntosObtenidos,fechaPartida);
        partidaDAO.actualizar(partida);
        return partida.toString();
    }
    // Método para eliminar una partida
    public String eliminarPartida(int id) {
        return partidaDAO.eliminar(id);
    }
    // Método para obtener una partida por su ID
    public String verPartidaID(int id) {
        return partidaDAO.buscarPorId(id).toString();
    }
    // Método para listar todas las partidas
    public List<Partida> listarPartidas() {
        return partidaDAO.listarTodos();
    }
    public List<String> verEstadisticasHoras(){
        return null;
    }
}
