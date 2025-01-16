package app.controladores;

import app.modelos.VideoJuego;

import java.util.ArrayList;
import java.util.List;

public class VideoJuegoControlador {
    private List<VideoJuego> videojuegos = new ArrayList<>();

    public void agregarVideojuego(String titulo, String genero, double precio) {
        int id = videojuegos.size() + 1;
        VideoJuego videojuego = new VideoJuego(id, titulo, genero, precio);
        videojuegos.add(videojuego);
        System.out.println("Videojuego agregado: " + videojuego);
    }

    public List<VideoJuego> listarVideojuegos() {
        return videojuegos;
    }
}