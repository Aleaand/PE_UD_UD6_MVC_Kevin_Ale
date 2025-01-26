package app.controladores;

import app.dao.VideoJuegoDAO;
import app.modelos.Videojuego;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VideoJuegoControlador {
    private List<Videojuego> videojuegos = new ArrayList<>();
    private VideoJuegoDAO videoJuegoDAO = new VideoJuegoDAO();
    public String agregarVideojuego(String titulo, String genero, double precio) {
        int id = videojuegos.size() + 1;
        Videojuego videojuego = new Videojuego(id, titulo, genero, precio);
        videojuegos.add(videojuego);
        return videoJuegoDAO.guardar(videojuego);
    }

    public List<Videojuego> listarVideojuegos() {
        return videoJuegoDAO.listarTodos();
    }

    public Videojuego getVideojuego(int id) {
        return videoJuegoDAO.buscarPorId(id);
    }
    public String eliminarVideojuego(int id) {
        return videoJuegoDAO.eliminar(id);
    }
    public String actualizarVideojuego(int id, String titulo, String genero, double precio ) {
        Videojuego videojuego = videoJuegoDAO.buscarPorId(id);
        videojuego.setTitulo(titulo);
        videojuego.setGenero(genero);
        videojuego.setPrecio(BigDecimal.valueOf(precio));
        return videoJuegoDAO.actualizar(videojuego);
    }
}