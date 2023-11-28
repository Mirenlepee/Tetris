package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	private static final String URL = "jdbc:sqlite:miBaseDeDatosUsuarios.db"; // Nombre de la base de datos en SQLite

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void desconectar(Connection conexion) throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}
