package app.controladores;

import app.dao.VideoJuegoDAO;
import app.modelos.Jugador;
import app.modelos.Videojuego;

import java.util.ArrayList;
import java.util.List;

public class VideoJuegoControlador {
    private List<Videojuego> videojuegos = new ArrayList<>();
    private VideoJuegoDAO videoJuegoDAO = new VideoJuegoDAO();

    public String agregarVideojuego(String titulo, String genero, double precio) {
        int id = videojuegos.size() + 1;
        Videojuego videojuego = new Videojuego(id, titulo, genero, precio);
        videoJuegoDAO.guardar(videojuego);
        return videojuego.toString();
    }

    public String actualizarVideojuego(int id,String titulo, String genero, double precio) {
        Videojuego videojuego = new Videojuego(id, titulo, genero, precio);
        videoJuegoDAO.actualizar(videojuego);
        return videojuego.toString();
    }
    public String eliminarVideojuego(int id) {
        return videoJuegoDAO.eliminar(id);
    }
    public String verVideojuegoID(int id) {
        return videoJuegoDAO.buscarPorId(id).toString();
    }

    public List<Videojuego> listarVideojuegos() {
        return videoJuegoDAO.listarTodos();
    }

    public Videojuego getVideojuego(int id) {
        return videoJuegoDAO.buscarPorId(id);
    }
    public String actualizarJugador(Videojuego videojuego  ) {
        return videoJuegoDAO.actualizar(videojuego);
    }
}