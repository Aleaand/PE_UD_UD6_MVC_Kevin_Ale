package app.controladores;

import app.modelos.Jugador;

import java.util.ArrayList;
import java.util.List;

public class JugadorControlador {
    private List<Jugador> jugadores = new ArrayList<>();

    public void agregarJugador(String nombre, int nivel, int puntuacion) {
        int id = jugadores.size() + 1;
        Jugador jugador = new Jugador(id, nombre, nivel, puntuacion);
        jugadores.add(jugador);
        System.out.println("Jugador agregado: " + jugador);
    }

    public List<Jugador> listarJugadores() {
        return jugadores;
    }
}
