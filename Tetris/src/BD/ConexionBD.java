package BD;

import java.sql.*;
import java.util.ArrayList;

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
    
    public static void guardarJugada(int idJugador, int puntos) throws SQLException {
        // Inserta una nueva jugada en la base de datos
        Connection conexion = conectar();
        String sql = "INSERT INTO jugadas (id_jugador, puntos) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idJugador);
            statement.setInt(2, puntos);
            statement.executeUpdate();
        } finally {
            desconectar(conexion);
        }
    }

    public static ArrayList<Integer> obtenerPuntosPorJugador(int idJugador) throws SQLException {
        // Obtiene los puntos de un jugador desde la base de datos
        ArrayList<Integer> puntos = new ArrayList<>();
        Connection conexion = conectar();
        String sql = "SELECT puntos FROM jugadas WHERE id_jugador = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idJugador);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                puntos.add(rs.getInt("puntos"));
            }
        } finally {
            desconectar(conexion);
        }
        return puntos;
    }
}
