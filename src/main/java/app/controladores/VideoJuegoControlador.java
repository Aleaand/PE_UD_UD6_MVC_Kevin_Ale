package app.controladores;

import app.modelos.Videojuego;

import java.util.ArrayList;
import java.util.List;

public class VideoJuegoControlador {
    private List<Videojuego> videojuegos = new ArrayList<>();

    public void agregarVideojuego(String titulo, String genero, double precio) {
        int id = videojuegos.size() + 1;
        Videojuego videojuego = new Videojuego(id, titulo, genero, precio);
        videojuegos.add(videojuego);
        System.out.println("Videojuego agregado: " + videojuego);
    }

    public List<Videojuego> listarVideojuegos() {
        return videojuegos;
    }
}