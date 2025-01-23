package app.dao;

import app.modelos.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAO implements DAO<Jugador> {

    @Override
    public void guardar(Jugador jugador) {
        String sql = "INSERT INTO jugadores (nombre, nivel, puntuacion) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, jugador.getNombre());
            stmt.setInt(2, jugador.getNivel());
            stmt.setInt(3, jugador.getPuntuacion());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                jugador.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Jugador buscarPorId(int id) {
        String sql = "SELECT * FROM jugadores WHERE id = ?";
        Jugador jugador = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                jugador = new Jugador(rs.getInt("id"), rs.getString("nombre"),
                        rs.getInt("nivel"), rs.getInt("puntuacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugador;
    }

    @Override
    public List<Jugador> listarTodos() {
        String sql = "SELECT * FROM jugadores";
        List<Jugador> jugadores = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                jugadores.add(new Jugador(rs.getInt("id"), rs.getString("nombre"),
                        rs.getInt("nivel"), rs.getInt("puntuacion")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jugadores;
    }

    @Override
    public String eliminar(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Jugador eliminado";
        } catch (SQLException e) {
            return "jugador no eliminado";
        }
    }
    // Método para actualizar los detalles de un jugador en la base de datos
    public String actualizar(Jugador jugador) {
        String sql = "UPDATE jugadores SET nombre = ?, nivel = ?, puntuacion = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jugador.getNombre());
            stmt.setInt(2, jugador.getNivel());
            stmt.setInt(3, jugador.getPuntuacion());
            stmt.setInt(4, jugador.getId());

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                return "Jugador actualizado con éxito: " + jugador;
            } else {
                return "No se encontró el jugador con ID: " + jugador.getId();
            }
        } catch (SQLException e) {
            return "hubo un error al actualizar el jugador";
        }
    }
    
}
