package P1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestionBDUsuario {
	
	public static void main(String[] args) {
		
		// Usuario de ejemplo
        Usuario usuario = new Usuario(1, "usuario1", "contraseña123", "usuario1@example.com", "url_avatar", "Descripción del usuario");

        // Insertar el usuario en la base de datos
        try (Connection conexion = ConexionBD.conectar()) {
            String sql = "INSERT INTO usuarios (userName, password, email, avatarURL, descripcion) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, usuario.getUserName());
                statement.setString(2, usuario.getPassword());
                statement.setString(3, usuario.getEmail());
                statement.setString(4, usuario.getAvatarURL());
                statement.setString(5, usuario.getDescripcion());

                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Usuario insertado con éxito");
                } else {
                    System.out.println("Error al insertar usuario");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
