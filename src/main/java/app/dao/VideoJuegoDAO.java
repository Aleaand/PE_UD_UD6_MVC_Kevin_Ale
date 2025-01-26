package app.dao;

import app.modelos.Videojuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz {@link DAO} para la entidad {@link Videojuego}.
 * Esta clase proporciona operaciones CRUD (crear, leer, actualizar, eliminar) para la tabla de videojuegos.
 */
public class VideoJuegoDAO implements DAO<Videojuego> {

    /**
     * Guarda un nuevo videojuego en la base de datos.
     *
     * @param videojuego El objeto {@link Videojuego} que se desea guardar.
     * @return Un mensaje indicando si la operación fue exitosa o no.
     */
    @Override
    public String guardar(Videojuego videojuego) {
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
            return "Videojuego guardado";
        } catch (SQLException e) {
            return "Error Videojuego no guardado";
        }
    }

    /**
     * Busca un videojuego por su ID en la base de datos.
     *
     * @param id El ID del videojuego a buscar.
     * @return El objeto {@link Videojuego} correspondiente al ID proporcionado, o null si no se encuentra.
     */
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

    /**
     * Lista todos los videojuegos registrados en la base de datos.
     *
     * @return Una lista de objetos {@link Videojuego} con todos los videojuegos.
     */
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

    /**
     * Elimina un videojuego de la base de datos por su ID.
     *
     * @param id El ID del videojuego a eliminar.
     * @return Un mensaje indicando si la operación fue exitosa o no.
     */
    @Override
    public String eliminar(int id) {
        String sql = "DELETE FROM videojuegos WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Videojuego eliminado";
        } catch (SQLException e) {
            return "Error Videojuego no eliminado";
        }
    }

    /**
     * Actualiza los detalles de un videojuego en la base de datos.
     *
     * @param videojuego El objeto {@link Videojuego} con los nuevos detalles.
     * @return Un mensaje indicando si la operación fue exitosa o no.
     */
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
                return ("Error No se encontró el videojuego con ID: " + videojuego.getId());
            }
        } catch (SQLException e) {
            return "Error Videojuego no encontrado";
        }
    }
}