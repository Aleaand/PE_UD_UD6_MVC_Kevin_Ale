package app.dao;

import app.modelos.Jugador;
import app.modelos.Partida;
import app.modelos.Videojuego;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class PartidaDAO implements DAO<Partida> {

    @Override
    public String guardar(Partida partida) {
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
            return "Partida guardada con exito";
        } catch (SQLException e) {
            return "Error al guardar partida";
        }
    }

    @Override
    public Partida buscarPorId(int id) {
        String sql = "SELECT p.id, p.horas_jugadas, p.puntos_obtenidos, p.fecha_partida, " +
                "       j.id AS jugador_id, j.nombre AS jugador_nombre, j.puntuacion, j.nivel, " +
                "       v.id AS videojuego_id, v.titulo AS videojuego_nombre, v.genero, v.precio " +
                "FROM Partidas p " +
                "JOIN Jugadores j ON p.id_jugador = j.id " +
                "JOIN Videojuegos v ON p.id_videojuego = v.id " +
                "WHERE p.id = ?";


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
    public List<Map<String, Object>> obtenerClasificacionVideojuegos() {
        List<Map<String, Object>> clasificacion = new ArrayList<>();

        String query = """
        SELECT v.titulo AS nombre_videojuego, 
               SUM(p.horas_jugadas) AS total_horas,
               COUNT(DISTINCT p.id_jugador) AS total_jugadores
        FROM partidas p
        JOIN videojuegos v ON p.id_videojuego = v.id
        GROUP BY v.id, v.titulo
        ORDER BY total_horas DESC, total_jugadores DESC;
    """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> videojuegoStats = new HashMap<>();
                videojuegoStats.put("nombre_videojuego", rs.getString("nombre_videojuego"));
                videojuegoStats.put("total_horas", rs.getInt("total_horas"));
                videojuegoStats.put("total_jugadores", rs.getInt("total_jugadores"));
                clasificacion.add(videojuegoStats);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clasificacion;
    }

    @Override
    public List<Partida> listarTodos() {
        String sql = "SELECT p.id, p.horas_jugadas, p.puntos_obtenidos, p.fecha_partida, " +
                "j.id AS jugador_id, j.nombre AS jugador_nombre, j.puntuacion, j.nivel," +
                "v.id AS videojuego_id, v.titulo AS videojuego_nombre, v.genero, v.precio " +
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
    public String eliminar(int id) {
        String sql = "DELETE FROM Partidas WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);  // Establecer el ID de la partida
            stmt.executeUpdate();
            return "Partida eliminada";
        } catch (SQLException e) {
            return "Error partida no eliminada";
        }
    }
    public String actualizar(Partida partida) {
        String sql = "UPDATE Partidas SET id_videojuego = ?, id_jugador = ?, fecha_partida = ?, " +
                "horas_jugadas = ?, puntos_obtenidos = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, partida.getIdVideojuego().getId());  // ID del videojuego
            stmt.setInt(2, partida.getIdJugador().getId());     // ID del jugador
            stmt.setDate(3, Date.valueOf(partida.getFechaPartida())); // Fecha de la partida
            stmt.setInt(4, partida.getHorasJugadas());            // Horas jugadas
            stmt.setInt(5, partida.getPuntosObtenidos());    // Puntos obtenidos
            stmt.setInt(6, partida.getId());                     // ID de la partida para actualizar

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                return ("Partida actualizada con éxito: " + partida);
            } else {
                return("Error no se encontró la partida con ID: " + partida.getId());
            }
        } catch (SQLException e) {
            return "Error al actualizar la partida con ID: " + partida.getId();
        }
    }
    //verEstadisticasHoras ver las 10 partidas mas largas
    public List<Partida> verEstadisticasHoras() {
        List<Partida> partidas = new ArrayList<>();
        String sql = "SELECT p.id, p.horas_jugadas, p.puntos_obtenidos, p.fecha_partida, " +
                "j.id AS jugador_id, j.nombre AS jugador_nombre, j.puntuacion, j.nivel, " +
                "v.id AS videojuego_id, v.titulo AS videojuego_nombre, v.genero, v.precio " +
                "FROM Partidas p " +
                "JOIN Jugadores j ON p.id_jugador = j.id " +
                "JOIN Videojuegos v ON p.id_videojuego = v.id " +
                "ORDER BY p.horas_jugadas DESC LIMIT 10";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Crear el objeto Jugador
                Jugador jugador = new Jugador(rs.getInt("jugador_id"), rs.getString("jugador_nombre"),
                        rs.getInt("nivel"), rs.getInt("puntuacion"));

                // Crear el objeto Videojuego
                Videojuego videoJuego = new Videojuego(rs.getInt("videojuego_id"), rs.getString("videojuego_nombre"),
                        rs.getString("genero"), rs.getDouble("precio"));

                // Crear la partida
                Partida partida = new Partida(rs.getInt("id"), jugador, videoJuego, rs.getInt("horas_jugadas"),
                        rs.getInt("puntos_obtenidos"), rs.getDate("fecha_partida").toLocalDate());

                // Añadir la partida a la lista
                partidas.add(partida);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partidas;
    }
}
