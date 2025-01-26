package app.modelos;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "videojuegos")
public class Videojuego {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "videojuegos_id_gen")
    @SequenceGenerator(name = "videojuegos_id_gen", sequenceName = "videojuegos_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "genero", length = 50)
    private String genero;

    @Column(name = "precio")
    private BigDecimal precio;

    @OneToMany(mappedBy = "idVideojuego")
    private Set<Partida> partidas = new LinkedHashSet<>();

    public Videojuego(int videojuegoId, String videojuegoNombre, String s, double i) {
        id = videojuegoId;
        titulo = videojuegoNombre;
        genero = s;
        precio = new BigDecimal(i);
    }

    public Videojuego() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Set<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(Set<Partida> partidas) {
        this.partidas = partidas;
    }

    @Override
    public String toString() {
        return String.format(
                "\n🎮 Videojuego 📜\n" +
                        "╔════════════════════════════════════════╗\n" +
                        "║ 🆔 ID:          %-22d ║\n" +
                        "║ 📌 Título:      %-22s ║\n" +
                        "║ 🎭 Género:      %-22s ║\n" +
                        "║ 💰 Precio:      $%-21.2f ║\n" +
                        "╚════════════════════════════════════════╝\n",
                id, titulo, genero, precio
        );
    }
}