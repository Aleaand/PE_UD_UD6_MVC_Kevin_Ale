package app.dao;

import app.modelos.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code JugadorDAO} es responsable de gestionar las operaciones de acceso a datos para los objetos {@code Jugador}.
 * Implementa la interfaz {@code DAO} para las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la base de datos.
 */
public class JugadorDAO implements DAO<Jugador> {

    /**
     * Guarda un nuevo jugador en la base de datos.
     * Inserta un nuevo registro en la tabla {@code jugadores} con los detalles del jugador proporcionado.
     *
     * @param jugador El objeto {@code Jugador} a guardar.
     * @return Un mensaje indicando si el jugador fue guardado correctamente o si ocurrió un error.
     */
    @Override
    public String guardar(Jugador jugador) {
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
            return "Jugador guardado";
        } catch (SQLException e) {
            return "Error al guardar";
        }
    }

    /**
     * Busca un jugador en la base de datos por su ID.
     * Recupera un jugador específico de la tabla {@code jugadores} utilizando el ID proporcionado.
     *
     * @param id El ID del jugador a buscar.
     * @return El objeto {@code Jugador} correspondiente al ID proporcionado, o {@code null} si no se encuentra.
     */
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

    /**
     * Obtiene todos los jugadores de la base de datos.
     * Recupera todos los registros de la tabla {@code jugadores} y los devuelve como una lista de objetos {@code Jugador}.
     *
     * @return Una lista de objetos {@code Jugador}.
     */
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

    /**
     * Elimina un jugador de la base de datos por su ID.
     * Borra el registro correspondiente al jugador con el ID proporcionado de la tabla {@code jugadores}.
     *
     * @param id El ID del jugador a eliminar.
     * @return Un mensaje indicando si el jugador fue eliminado correctamente o si ocurrió un error.
     */
    @Override
    public String eliminar(int id) {
        String sql = "DELETE FROM jugadores WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return "Jugador eliminado";
        } catch (SQLException e) {
            return "Error jugador no eliminado";
        }
    }

    /**
     * Actualiza los detalles de un jugador en la base de datos.
     * Modifica el nombre, nivel y puntuación de un jugador específico en la tabla {@code jugadores}.
     *
     * @param jugador El objeto {@code Jugador} con los nuevos detalles.
     * @return Un mensaje indicando si el jugador fue actualizado correctamente o si ocurrió un error.
     */
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
                return "Error No se encontró el jugador con ID: " + jugador.getId();
            }
        } catch (SQLException e) {
            return "Error al actualizar el jugador";
        }
    }

    /**
     * Obtiene las estadísticas de los jugadores con las mejores puntuaciones.
     * Devuelve una lista con los 10 jugadores con mayor puntuación de la tabla {@code jugadores}.
     *
     * @return Una lista de los 10 jugadores con mayor puntuación.
     */
    public List<Jugador> verEstadisticasPuntuacion() {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugadores ORDER BY puntuacion DESC LIMIT 10"; // consulta para obtener top 10 puntuaciones

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

    /**
     * Obtiene las estadísticas de los jugadores con los mayores niveles de experiencia.
     * Devuelve una lista con los 10 jugadores con el nivel más alto de la tabla {@code jugadores}.
     *
     * @return Una lista de los 10 jugadores con mayor nivel.
     */
    public List<Jugador> verEstadisticasExperiencia() {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugadores ORDER BY nivel DESC LIMIT 10"; // consulta para obtener top 10 experiencia

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
}
