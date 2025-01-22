package app.controladores;

import app.dao.JugadorDAO;
import app.modelos.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JugadorControlador {
    private List<Jugador> jugadores = new ArrayList<>();
    private JugadorDAO jugadorDAO = new JugadorDAO();

    public void agregarJugador(String nombre, int nivel, int puntuacion) {
        int id = jugadores.size() + 1;
        Jugador jugador = new Jugador(id, nombre, nivel, puntuacion);

       jugadorDAO.guardar(jugador);
    }

    public List<Jugador> listarJugadores() {
        return jugadorDAO.listarTodos();
    }
    public Jugador getJugador(int id) {
        return jugadorDAO.buscarPorId(id);
    }
    public String eliminarJugador(int id) {
        return jugadorDAO.eliminar(id);
    }
    public String actualizarJugador(Jugador jugador  ) {
        return jugadorDAO.actualizar(jugador);
    }

}
