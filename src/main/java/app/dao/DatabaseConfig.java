package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * La clase {@code DatabaseConfig} se encarga de gestionar la configuración de la conexión a la base de datos.
 * Lee la configuración desde un archivo JSON y proporciona métodos para obtener una conexión a la base de datos.
 * La configuración se carga estáticamente durante la inicialización de la clase.
 *
 * <p>La clase utiliza un archivo JSON para almacenar la URL, el usuario y la contraseña de la base de datos.
 * Estos valores se utilizan posteriormente para establecer una conexión a la base de datos cuando se invoca el método {@code getConnection()}.</p>
 */
public class DatabaseConfig {
    private static String url;
    private static String user;
    private static String password;

    /**
     * Bloque estático que se ejecuta cuando la clase {@code DatabaseConfig} es cargada.
     * Lee el archivo de configuración {@code config.json} para obtener los valores de la URL,
     * el nombre de usuario y la contraseña de la base de datos. Si ocurre algún error durante la lectura
     * del archivo, se captura la excepción {@code IOException}.
     */
    static {
        try {
            String configPath = "src/main/java/app/config/config.json";
            JSONTokener tokener = new JSONTokener(new FileReader(configPath));
            JSONObject config = new JSONObject(tokener);

            url = config.getString("url");
            user = config.getString("user");
            password = config.getString("password");
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo de configuración: " + e.getMessage());
            e.printStackTrace();
            // Proporcionar valores predeterminados o lanzar una excepción
        }
    }

    /**
     * Obtiene una conexión a la base de datos utilizando los valores de configuración cargados previamente.
     * Utiliza {@link DriverManager} para establecer la conexión.
     *
     * @return Una conexión a la base de datos.
     * @throws SQLException Si ocurre algún error al intentar establecer la conexión.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}