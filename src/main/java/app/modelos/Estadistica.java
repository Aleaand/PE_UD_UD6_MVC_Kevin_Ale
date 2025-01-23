package app.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "estadisticas")
public class Estadistica {
    @EmbeddedId
    private EstadisticaId id;

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

}