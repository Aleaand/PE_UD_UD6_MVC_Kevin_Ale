package app.dao;

import app.modelos.Partida;
import app.modelos.Jugador;
import app.modelos.Videojuego;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO implements DAO<Partida> {

    @Override
    public void guardar(Partida partida) {
        String sql = "INSERT INTO Partidas (id_videojuego, id_jugador, fecha_partida, horas_jugadas, puntos_obtenidos) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, partida.getIdVideojuego().getId());  // ID del videojuego
            stmt.setInt(2, partida.getIdJugador().getId());     // ID del jugador
            stmt.setDate(3, Date.valueOf(partida.getFechaPartida())); // Fecha de la partida
            stmt.setInt(4, partida.getHorasJugadas());            // Horas jugadas en la partida
            stmt.setInt(5, partida.getPuntosObtenidos());    // Puntos obtenidos en la partida

            // Ejecutar la actualización
            stmt.executeUpdate();

            // Obtener el ID generado para la partida
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                partida.setId(rs.getInt(1));  // Establecer el ID generado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Partida buscarPorId(int id) {
        String sql = "SELECT p.id, p.horas_jugadas, p.puntos_obtenidos, p.fecha_partida, " +
                "j.id AS jugador_id, j.nombre AS jugador_nombre,j.puntuacion, j.nivel" +
                "v.id AS videojuego_id, v.nombre AS videojuego_nombre, v.genero,v.precio" +
                "FROM Partidas p " +
                "JOIN Jugadores j ON p.id_jugador = j.id " +
                "JOIN Videojuegos v ON p.id_videojuego = v.id";


        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Establecer el ID de la partida

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Obtener el jugador
                Jugador jugador = new Jugador(rs.getInt("jugador_id"), rs.getString("jugador_nombre"), Integer.parseInt(rs.getString("nivel")),  Integer.parseInt(rs.getString("puntuacion")));

                // Obtener el videojuego
                Videojuego videoJuego = new Videojuego (rs.getInt("videojuego_id"), rs.getString("videojuego_nombre"), rs.getString("genero"), rs.getDouble("precio"));

                // Crear la partida
                Partida partida = new Partida(rs.getInt("id"), jugador, videoJuego, rs.getInt("horas_jugadas"), rs.getInt("puntos_obtenidos"), rs.getDate("fecha_partida").toLocalDate());

                return partida;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Partida> listarTodos() {
        String sql = "SELECT p.id, p.horas_jugadas, p.puntos_obtenidos, p.fecha_partida, " +
                "j.id AS jugador_id, j.nombre AS jugador_nombre,j.puntuacion, j.nivel" +
                "v.id AS videojuego_id, v.nombre AS videojuego_nombre, v.genero,v.precio" +
                "FROM Partidas p " +
                "JOIN Jugadores j ON p.id_jugador = j.id " +
                "JOIN Videojuegos v ON p.id_videojuego = v.id";

        List<Partida> partidas = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Obtener el jugador
                Jugador jugador = new Jugador(rs.getInt("jugador_id"), rs.getString("jugador_nombre"), Integer.parseInt(rs.getString("nivel")),  Integer.parseInt(rs.getString("puntuacion")));

                // Obtener el videojuego
                Videojuego videoJuego = new Videojuego(rs.getInt("videojuego_id"), rs.getString("videojuego_nombre"), rs.getString("genero"), rs.getDouble("precio"));

                // Crear la partida
                Partida partida = new Partida(rs.getInt("id"), jugador, videoJuego, rs.getInt("horas_jugadas"), rs.getInt("puntos_obtenidos"), rs.getDate("fecha_partida").toLocalDate());

                partidas.add(partida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidas;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM Partidas WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Establecer el ID de la partida
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
