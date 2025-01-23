package app.modelos;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "partidas")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partidas_id_gen")
    @SequenceGenerator(name = "partidas_id_gen", sequenceName = "partidas_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_videojuego")
    private Videojuego idVideojuego;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_jugador")
    private Jugador idJugador;

    @Column(name = "fecha_partida")
    private LocalDate fechaPartida;

    @Column(name = "horas_jugadas")
    private Integer horasJugadas;

    @Column(name = "puntos_obtenidos")
    private Integer puntosObtenidos;

    public Partida(int id, Jugador jugador, Videojuego videoJuego, int horasJugadas, int puntosObtenidos, LocalDate fechaPartida) {
        this.id = id;
        this.idVideojuego = videoJuego;
        this.idJugador = jugador;
        this.horasJugadas = horasJugadas;
        this.puntosObtenidos = puntosObtenidos;
        this.fechaPartida = fechaPartida;
    }

    public Partida(Jugador jugador, Videojuego videojuego, int horasJugadas, int puntosObtenidos, LocalDate fechaPartida) {
        this.idVideojuego = videojuego;
        this.idJugador = jugador;
        this.horasJugadas = horasJugadas;
        this.puntosObtenidos = puntosObtenidos;
        this.fechaPartida = fechaPartida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Videojuego getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Videojuego idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public Jugador getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

    public LocalDate getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(LocalDate fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public Integer getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(Integer horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    public Integer getPuntosObtenidos() {
        return puntosObtenidos;
    }

    public void setPuntosObtenidos(Integer puntosObtenidos) {
        this.puntosObtenidos = puntosObtenidos;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "id=" + id +
                ", idVideojuego=" + idVideojuego +
                ", idJugador=" + idJugador +
                ", fechaPartida=" + fechaPartida +
                ", horasJugadas=" + horasJugadas +
                ", puntosObtenidos=" + puntosObtenidos +
                '}';
    }
}