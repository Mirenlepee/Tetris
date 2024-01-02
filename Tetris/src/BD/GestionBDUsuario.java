package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import gestionUsuarios.Usuario;

public class GestionBDUsuario {
	private Usuario usu;
	private static Logger logger;
	private static Connection con;
	private static Statement s;
	private static ResultSet rs;
	private static HashMap<String, Usuario> mapaUsuarios;

	
	public static void main(String[] args) throws SQLException{
		
		try {
			logger = Logger.getLogger("BaseDeDatos");
			logger.addHandler(new FileHandler("BasesDeDatos.xml"));
		}catch (Exception e){}
		
		String comentarioSQL = "";
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
			s = con.createStatement();
			
			//crear tabla Usuario
			try {				
				comentarioSQL = "create table usuario (userName string, password string, email string, avatarURL string, descripcion string)";
				logger.log(Level.INFO, "BD: " + comentarioSQL);
				s.executeUpdate(comentarioSQL);
			} catch (SQLException e) {
				// se lanza si la tabla ya existe - no hay problema
				logger.log(Level.INFO, "La tabla ya está creada");
			}
			// Añadir columna ultimocambiodecontraseña a la tabla Usuario
		    try {
		        comentarioSQL = "ALTER TABLE Usuario ADD COLUMN ultimoCambioContrasena string";
		        logger.log(Level.INFO, "BD: " + comentarioSQL);
		        s.executeUpdate(comentarioSQL);
		    } catch (SQLException e) {
		        logger.log(Level.INFO, "La columna ya esta creada");
		    }
			
			try {
		        comentarioSQL = "UPDATE Usuario SET ultimoCambioContrasena = '2023-12-01' WHERE ultimoCambioContrasena = 'null'";
		        logger.log(Level.INFO, "BD: " + comentarioSQL);
		        s.executeUpdate(comentarioSQL);
		    } catch (SQLException e) {
		        logger.log(Level.WARNING, "Error al actualizar usuarios con diasdesdeultimocambio a null", e);
		        e.printStackTrace();
		    }
			
			// Ver si existe admin
			comentarioSQL = "select * from Usuario where email = 'admin'";
			logger.log(Level.INFO, "BD: " + comentarioSQL);
			rs = s.executeQuery(comentarioSQL);
			if (!rs.next()) {
				// Añadirlo si no existe
				comentarioSQL = "insert into Usuario ( userName, password, email, avatarURL, descripcion ) values ('admin', 'admin', 'admin', 'admin', 'admin')";
				logger.log(Level.INFO, "BD: " + comentarioSQL);
				s.executeUpdate(comentarioSQL);
				Usuario moma = new Usuario("OihaneCam", "GK842aeiou", "oihanecam@gmail.com", "Tetris/src/imagenes/avatar.png", "null");
				anadirUsuarioNuevo(moma);
				Usuario kepa = new Usuario("MirenLe", "mMiaz45#g", "mirenle@gmail.com", "Tetris/src/imagenes/avatar.png", "null");
				anadirUsuarioNuevo(kepa);
				Usuario miguel = new Usuario("NaiaLo", "mNaia68n", "mirenle@gmail.com", "Tetris/src/imagenes/avatar.png", "null");
				anadirUsuarioNuevo(miguel);
				Usuario laura = new Usuario("AlbaDe", "#FAK8539mjsa", "albade@gmail.com", "Tetris/src/imagenes/avatar.png", "null");
				anadirUsuarioNuevo(laura);
			}			
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Último comando: " + comentarioSQL);
			e.printStackTrace();
			}
		}
	
	public Connection getConnection() {
		return con;
	}
	public static void anadirUsuarioNuevo(Usuario usu) {
		String com = "";
		try {
			// Ver si existe usuario
			// Si queremos asegurar el string habría que hacer algo así...
			// String nick = tfUsuario.getText().replaceAll( "'", "''" );
			// ...si no, cuidado con lo que venga en el campo de entrada.
			// "select * from Usuario where nick = 'admin'";
			com = "select * from Usuario where correoUsuario = '" + usu.getEmail() + "'";
			logger.log( Level.INFO, "BD: " + com );
			rs = s.executeQuery( com );
			if (!rs.next()) {
				// "insert into Usuario ( nick, pass ) values ('admin', 'admin')";
				com = "insert into Usuario (userName, password, email, avatarURL, ultimoCambioContrasena) values ('"+ 
						usu.getUserName() +"', '" + usu.getPassword() +"', '" + usu.getEmail()+"', '" + usu.getAvatarURL()+ "', '" + usu.getUltimoCambioContrasena() +"')";
				logger.log( Level.INFO, "BD: " + com );
				int val = s.executeUpdate( com );
				if (val!=1) {
					JOptionPane.showMessageDialog( null, "Error en inserción" );
				}
			} else {
				JOptionPane.showMessageDialog( null, "Usuario " + usu.getEmail() + " ya existe" );
			}
		} catch (SQLException e2) {
			System.out.println( "Último comando: " + com );
			e2.printStackTrace();
		}
	}
	
	public void modificarUsuarioYaRegistradoContrasena(Usuario usu) {
		//update Usuario set contrasena = 'valor1' where correoUsuario = 'valor2'
	    String sent = "update Usuario set contrasena = '" + secu(usu.getPassword()) + "', ultimoCambioContrasena = '" + usu.getUltimoCambioContrasena() + "' where correoUsuario = '" + secu(usu.getEmail()) + "'";
		logger.log(Level.INFO, "BD: " + sent);
		try {
			s.executeUpdate(sent);
		} catch (SQLException e1) {
			logger.log(Level.WARNING, sent, e1);
			e1.printStackTrace();
		}
	}
	public void modificarUsuarioYaRegistrado(Usuario usu) {		
		String sent = "update Usuario set nombreUsuario= '"+ secu(usu.getUserName())+  "' where correoUsuario = '"+ secu(usu.getEmail()) + "'";
		logger.log(Level.INFO, "BD: " + sent);
	
		try {
			s.executeUpdate(sent);
		} catch (SQLException e1) {
			logger.log(Level.WARNING, sent, e1);	
			e1.printStackTrace();
		}
	}
	public void modificarUsuarioImagenPerfil(Usuario usu) {
		String sent = "update Usuario set imagenPerfil= '" +
	            secu(usu.getAvatarURL()) + "' where correoUsuario = '"+ secu(usu.getEmail()) + "'";
		logger.log(Level.INFO, "BD: " + sent);
	
		try {
			s.executeUpdate(sent);
		} catch (SQLException e1) {
			logger.log(Level.WARNING, sent, e1);
			e1.printStackTrace();
		}
	}

	public void modificarDescripcionUsuario(Usuario usu) {
	    String sent = "update Usuario set descripcion= '" + usu.getDescripcion() + "' where correoUsuario= '" + secu(usu.getEmail())+"'";
	    logger.log(Level.INFO, "BD: " + sent);
	    try {
	    	s.executeUpdate(sent);
	    } catch (SQLException e) {
	        System.out.println("Último comando: " + sent);
	        e.printStackTrace();
	    }
	}

	
	public void borrarUsuarioRegistrado(Usuario usu) {
		if (!usu.getEmail().isEmpty() && !usu.getPassword().isEmpty()) {
			String com = "";
			try {
				// Borrar usuario
				com = "delete from Usuario where correoUsuario = '"+ secu(usu.getEmail()) +"'";
				logger.log( Level.INFO, "BD: " + com );
				s.executeUpdate( com );
			} catch (SQLException e2) {
				System.out.println( "Último comando: " + com );
				e2.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog( null, "Debes rellenar los dos campos" );
		}
	}

	
	public void cerrarConexiones() {
		try {
			rs.close();
			s.close();
			con.close();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

// Posible función de "securización" para evitar errores o ataques
	private static String secu( String sqlInicial ) {
		return sqlInicial;
		// Si lo reemplazamos por esto, es mucho más seguro:
		// return sqlInicial.replaceAll( "'", "''" );
	}

	
//visualizar por consola los usuarios registrados	
	public void verUsuarios() {
    String com = "select * from Usuario";
    logger.log(Level.INFO, "BD: " + com);

    try {
        rs = s.executeQuery(com);

        System.out.println("Usuarios en la base de datos:");

        while (rs.next()) {
            String userName = rs.getString("userName");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String avatarURL = rs.getString("avatarURL");
            String descripcion = rs.getString("descripcion");
            Usuario usuario = new Usuario(userName, password, email, avatarURL, descripcion);

            System.out.println("Nombre: " + userName +
            		", Contraseña: " + password +
                    ", Correo: " + email +
                    ", Descripción: " + descripcion);
        }

    } catch (SQLException e) {
        System.out.println("Último comando: " + com);
        e.printStackTrace();
    }
}

	public HashMap<String, Usuario> crearMapa() {
        mapaUsuarios = new HashMap<>();

        String com = "select * from Usuario";
        logger.log(Level.INFO, "BD: " + com);

        try {
            rs = s.executeQuery(com);

            while (rs.next()) {
            	String userName = rs.getString("userName");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String avatarURL = rs.getString("avatarURL");
                String descripcion = rs.getString("descripcion");
                Usuario usuario = new Usuario(userName, password, email, avatarURL, descripcion);
                mapaUsuarios.put(email, usuario);
            }

        } catch (SQLException e) {
            System.out.println("Último comando: " + com);
            e.printStackTrace();
        }

        return mapaUsuarios;
    }
	
	public static Usuario getUsuarioPorCorreo(String correo) {
		if(mapaUsuarios.containsKey(correo)) {
			return mapaUsuarios.get(correo);
		}
		else {
			return null;
		}
	}
	
	public LocalDate obtenerUltimoCambioContrasena(Usuario usuario) {
	    String com = "SELECT ultimoCambioContrasena FROM Usuario WHERE correoUsuario = ?";
	    logger.log(Level.INFO, "BD: " + com);

	    try (PreparedStatement preparedStatement = con.prepareStatement(com)) {
	        preparedStatement.setString(1, usuario.getEmail());
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            String fechaUltimoCambio = rs.getString("ultimoCambioContrasena");
	         // Ajusta el formato de parseo según el formato real de tu fecha
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	            
	         // Verificar si la cadena no es nula
	    	    if (fechaUltimoCambio != null) {
	    	        try {
	    	            // Intentar parsear la cadena a LocalDate
	    	            return LocalDate.parse(fechaUltimoCambio, formatter);
	    	        } catch (DateTimeParseException e) {
	    	            // Manejar la excepción (puedes imprimir un mensaje de error, log, etc.)
	    	            e.printStackTrace();
	    	        }
	    	    }
	            
	            return LocalDate.parse(fechaUltimoCambio, formatter);	        }
	    } catch (SQLException e) {
	        System.out.println("Último comando: " + com);
	        e.printStackTrace();
	    }

	    // Si la cadena es nula o no se puede parsear, devolver un valor por defecto
	    return LocalDate.now();
	}

}