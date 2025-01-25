package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DatabaseConfig {
    private static String url;
    private static String user;
    private static String password;

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

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
