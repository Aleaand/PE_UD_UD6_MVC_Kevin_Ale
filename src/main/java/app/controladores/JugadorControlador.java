package app.controladores;

import app.dao.JugadorDAO;
import app.modelos.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JugadorControlador {
    private List<Jugador> jugadores = new ArrayList<>();
    private JugadorDAO jugadorDAO = new JugadorDAO();

    public String agregarJugador(String nombre, int nivel, int puntuacion) {
        int id = jugadores.size() + 1;
        Jugador jugador = new Jugador(id, nombre, nivel, puntuacion);
        jugadorDAO.guardar(jugador);
        return jugador.toString();
    }
    public String actualizarJugador (int id,String nombre, int nivel, int puntuacion) {
        Jugador jugador = new Jugador(id, nombre, nivel, puntuacion);
        jugadorDAO.actualizar(jugador);
        return jugador.toString();
    }
    public String eliminarJugador (int id){
        return jugadorDAO.eliminar(id);
    }
    public String verJugadorID (int id){
        return jugadorDAO.buscarPorId(id).toString();
    }
    public List<Jugador> listarJugadores() {

        return jugadorDAO.listarTodos();
    }
    public List<String> verEstadisticasPuntuacion() {
        return null;
    }
    public List<String> verEstadisticasExperiencia() {
        return null;
    }
}
