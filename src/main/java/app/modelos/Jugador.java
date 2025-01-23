package app.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "jugadores")
public class Jugador{
    @Id
    @ColumnDefault("nextval('jugadores_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @ColumnDefault("0")
    @Column(name = "nivel")
    private Integer nivel;

    @ColumnDefault("0")
    @Column(name = "puntuacion")
    private Integer puntuacion;
    public Jugador(Integer id, String nombre, Integer nivel, Integer puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.puntuacion = puntuacion;
    }

    public Jugador() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nivel=" + nivel +
                ", puntuacion=" + puntuacion +
                '}';
    }
}