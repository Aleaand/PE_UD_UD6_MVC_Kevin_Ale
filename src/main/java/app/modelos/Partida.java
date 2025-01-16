package app.modelos;

import java.time.LocalDate;

import app.modelos.Jugador;
import app.modelos.VideoJuego;

public class Partida {
    private int id;
    private Jugador jugador;
    private VideoJuego videojuego;
    private int duracion;
    private LocalDate fecha;

    public Partida(int id, Jugador jugador, VideoJuego videojuego, int duracion, LocalDate fecha) {
        this.id = id;
        this.jugador = jugador;
        this.videojuego = videojuego;
        this.duracion = duracion;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public VideoJuego getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(VideoJuego videojuego) {
        this.videojuego = videojuego;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", jugador=" + jugador.getNombre() +
                ", videojuego=" + videojuego.getTitulo() +
                ", duracion=" + duracion + " minutos" +
                ", fecha=" + fecha +
                '}';
    }
}
