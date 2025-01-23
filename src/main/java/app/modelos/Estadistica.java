package app.modelos;

import javax.persistence.*;

@Entity
@Table(name = "estadisticas")
public class Estadistica {
    @SequenceGenerator(name = "estadisticas_id_gen", sequenceName = "jugadores_id_seq", allocationSize = 1)
    @EmbeddedId
    private EstadisticaId id;

    @MapsId("idJugador")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugador idJugador;

    @MapsId("idVideojuego")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_videojuego", nullable = false)
    private Videojuego idVideojuego;

    @Column(name = "horas_jugadas")
    private Integer horasJugadas;

    @Column(name = "puntos_totales")
    private Integer puntosTotales;

    public EstadisticaId getId() {
        return id;
    }

    public void setId(EstadisticaId id) {
        this.id = id;
    }

    public Jugador getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Jugador idJugador) {
        this.idJugador = idJugador;
    }

    public Videojuego getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Videojuego idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public Integer getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(Integer horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    public Integer getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(Integer puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    @Override
    public String toString() {
        return "Estadistica{" +
                "id=" + id +
                ", idJugador=" + idJugador +
                ", idVideojuego=" + idVideojuego +
                ", horasJugadas=" + horasJugadas +
                ", puntosTotales=" + puntosTotales +
                '}';
    }
}