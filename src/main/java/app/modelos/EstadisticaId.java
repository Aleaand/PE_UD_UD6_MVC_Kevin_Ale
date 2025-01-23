package app.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EstadisticaId implements Serializable {
    private static final long serialVersionUID = 2926641735644863267L;
    @Column(name = "id_jugador", nullable = false)
    private Integer idJugador;

    @Column(name = "id_videojuego", nullable = false)
    private Integer idVideojuego;

    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
    }

    public Integer getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Integer idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EstadisticaId entity = (EstadisticaId) o;
        return Objects.equals(this.idJugador, entity.idJugador) &&
                Objects.equals(this.idVideojuego, entity.idVideojuego);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJugador, idVideojuego);
    }

}