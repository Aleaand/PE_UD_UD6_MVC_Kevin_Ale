package app.dao;

import app.modelos.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoJuegoDAO implements DAO<Videojuego> {

    @Override
    public void guardar(Videojuego videojuego) {
        String sql = "INSERT INTO videojuegos (titulo, genero, precio) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, videojuego.getTitulo());
            stmt.setString(2, videojuego.getGenero());
            stmt.setBigDecimal(3, videojuego.getPrecio());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                videojuego.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Videojuego buscarPorId(int id) {
        String sql = "SELECT * FROM videojuegos WHERE id = ?";
        Videojuego videojuego = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                videojuego = new Videojuego(rs.getInt("id"), rs.getString("titulo"),
                        rs.getString("genero"), rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videojuego;
    }

    @Override
    public List<Videojuego> listarTodos() {
        String sql = "SELECT * FROM videojuegos";
        List<Videojuego> videojuegos = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                videojuegos.add(new Videojuego(rs.getInt("id"), rs.getString("titulo"),
                        rs.getString("genero"), rs.getDouble("precio")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videojuegos;
    }

    @Override
    public String eliminar(int id) {
        String sql = "DELETE FROM videojuegos WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Videojuego eliminado";
        } catch (SQLException e) {
            return "Videojuego no eliminado";
        }
    }
    // Método para actualizar los detalles de un videojuego
    public String actualizar(Videojuego videojuego) {
        String sql = "UPDATE videojuegos SET titulo = ?, genero = ?, precio = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, videojuego.getTitulo());
            stmt.setString(2, videojuego.getGenero());
            stmt.setBigDecimal(3, videojuego.getPrecio());
            stmt.setInt(4, videojuego.getId());  // ID del videojuego para actualizar

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                return("Videojuego actualizado con éxito: " + videojuego);
            } else {
                return ("No se encontró el videojuego con ID: " + videojuego.getId());
            }
        } catch (SQLException e) {
            return "Videojuego no encontrado";
        }
    }
}
