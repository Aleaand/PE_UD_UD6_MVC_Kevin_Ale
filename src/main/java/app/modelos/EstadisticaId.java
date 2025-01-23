package app.modelos;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
        if (o == null || getClass() != o.getClass()) return false;
        EstadisticaId entity = (EstadisticaId) o;
        return Objects.equals(this.idJugador, entity.idJugador) &&
                Objects.equals(this.idVideojuego, entity.idVideojuego);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJugador, idVideojuego);
    }

    @Override
    public String toString() {
        return "EstadisticaId{" +
                "idJugador=" + idJugador +
                ", idVideojuego=" + idVideojuego +
                '}';
    }
}