package app.dao;

import app.modelos.VideoJuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoJuegoDAO implements DAO<VideoJuego> {

    @Override
    public void guardar(VideoJuego videojuego) {
        String sql = "INSERT INTO videojuegos (titulo, genero, precio) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, videojuego.getTitulo());
            stmt.setString(2, videojuego.getGenero());
            stmt.setDouble(3, videojuego.getPrecio());
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
    public VideoJuego buscarPorId(int id) {
        String sql = "SELECT * FROM videojuegos WHERE id = ?";
        VideoJuego videojuego = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                videojuego = new VideoJuego(rs.getInt("id"), rs.getString("titulo"),
                        rs.getString("genero"), rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videojuego;
    }

    @Override
    public List<VideoJuego> listarTodos() {
        String sql = "SELECT * FROM videojuegos";
        List<VideoJuego> videojuegos = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                videojuegos.add(new VideoJuego(rs.getInt("id"), rs.getString("titulo"),
                        rs.getString("genero"), rs.getDouble("precio")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videojuegos;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM videojuegos WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
