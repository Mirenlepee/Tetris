package BD;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Types;
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
            // Inicializar el logger y establecer la conexión con la base de datos.
            inicializarLogger();
            establecerConexion();
            
            // Crear la tabla 'Usuario' si no existe.
            crearTablaUsuario();
            

            // Añadir columna 'ultimoCambioContrasena' a la tabla 'Usuario' si no existe.
            agregarColumnaUltimoCambioContrasena();
            
			// Actualizar 'ultimoCambioContrasena' para usuarios con valor 'null'.
            actualizarUltimoCambioContrasena();	
            
            // Crear la tabla 'EstadisticasJuego' si no existe.
            crearTablaEstadisticasJuego();
            
            // Verificar y agregar el usuario administrador si no existe.
            verificarYAgregarAdmin();
            //agregarUsuariosIniciales();
            
		} catch (SQLException | ClassNotFoundException e) {
            // Manejar excepciones e imprimir traza de error.
			manejarExcepcion(e);
		} finally {
//            // Cerrar la conexión con la base de datos.
			//NO ES EL SITIO ADECUADO 
//			cerrarConexiones();
        }
	}
		
	/**
     * Inicializa el logger para registrar eventos y errores en un archivo XML.
     */
	private static void inicializarLogger() {
        try {
            logger = Logger.getLogger("BaseDeDatos");
            logger.addHandler(new FileHandler("BasesDeDatos.xml"));
        } catch (Exception e) {
            manejarExcepcion(e);
        }
    }
	/**
     * Establece la conexión con la base de datos SQLite.
     * @throws SQLException Si hay un error al intentar establecer la conexión.
     * @throws ClassNotFoundException Si no se encuentra la clase del controlador JDBC.
     */
	private static void establecerConexion() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:usuarios.db");
        s = con.createStatement();
    }
	/**
     * Maneja una excepción imprimiendo el mensaje de error y la traza de la excepción.
     * @param e La excepción que se debe manejar.
     */
	private static void manejarExcepcion(Exception e) {
        System.out.println("Error: " + e.getMessage());
        e.printStackTrace();
    }
	/**
     * Crea la tabla 'usuario' en la base de datos si no existe.
     */
    private static void crearTablaUsuario() {
        String comentarioSQL = "CREATE TABLE IF NOT EXISTS usuario (userName string, password string, email string, avatarURL string, descripcion string)";
        ejecutarSQL(comentarioSQL, Level.INFO);
    }
    
    /**
     * Agrega la columna 'ultimoCambioContrasena' a la tabla 'Usuario' si no existe.
     */
    private static void agregarColumnaUltimoCambioContrasena() {
        String comentarioSQL = "ALTER TABLE Usuario ADD COLUMN ultimoCambioContrasena string";
        ejecutarSQL(comentarioSQL, Level.INFO);
    }
    /**
     * Actualiza la columna 'ultimoCambioContrasena' para usuarios con valor 'null'.
     */
    private static void actualizarUltimoCambioContrasena() {
        String comentarioSQL = "UPDATE Usuario SET ultimoCambioContrasena = '2023-12-01' WHERE ultimoCambioContrasena = 'null'";
        ejecutarSQL(comentarioSQL, Level.INFO);
    }
    
    /**
     * Ejecuta la sentencia SQL proporcionada y registra eventos en el logger.
     * @param sql La sentencia SQL a ejecutar.
     * @param logLevel El nivel de log para el evento.
     */
    private static void ejecutarSQL(String sql, Level logLevel) {
        try {
            logger.log(logLevel, "BD: " + sql);
            s.executeUpdate(sql);
        } catch (SQLException e) {
            if (logLevel == Level.INFO) {
                logger.log(Level.INFO, "La operación ya se realizó anteriormente");
            } else {
                manejarExcepcion(e);
            }
        }
    }
    
    /**
     * Verifica y agrega el usuario administrador si no existe.
     */
    private static void verificarYAgregarAdmin() {
        String comentarioSQL = "SELECT * FROM Usuario WHERE email = 'admin'";
        logger.log(Level.INFO, "BD: " + comentarioSQL);

        try {
            rs = s.executeQuery(comentarioSQL);
            if (!rs.next()) {
                // Añadir el usuario administrador si no existe.
				comentarioSQL = "insert into Usuario ( userName, password, email, avatarURL, descripcion ) values ('admin', 'admin', 'admin', 'admin', 'admin')";
                ejecutarSQL(comentarioSQL, Level.INFO);

                // Añadir otros usuarios y eventos iniciales...
                agregarUsuariosIniciales();
            }
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }
    /**
     * Añade usuarios y eventos iniciales a la base de datos.
     */
    private static void agregarUsuariosIniciales() {
    	Usuario oihane = new Usuario("OihaneCam", "GK842aeiou", "oihanecam@gmail.com", "Tetris/src/imagenes/avatar.png", "null");
		anadirUsuarioNuevo(oihane);
		Usuario miren = new Usuario("MirenLe", "mMiaz45#g", "mirenle@gmail.com", "Tetris/src/imagenes/avatar.png", "null");
		anadirUsuarioNuevo(miren);
		Usuario naia = new Usuario("NaiaLo", "mNaia68n", "naialo@gmail.com", "Tetris/src/imagenes/avatar.png", "null");
		anadirUsuarioNuevo(naia);
		Usuario alba = new Usuario("AlbaDe", "#FAK8539mjsa", "albade@gmail.com", "Tetris/src/imagenes/avatar.png", "null");
		anadirUsuarioNuevo(alba);
		
    }
    
    /**
     * Obtiene la conexión actual a la base de datos.
     * @return Objeto Connection que representa la conexión a la base de datos.
     */
	public Connection getConnection() {
		return con;
	}
	
	/**
	 * Añade un nuevo usuario a la base de datos.
	 * @param usu Objeto Usuario que se va a añadir.
	 */
	public static void anadirUsuarioNuevo(Usuario usu) {
		String com = "";
		try {			
	        // Verificar si el usuario ya existe en la base de datos.
			com = "select * from Usuario where email = '" + usu.getEmail() + "'";
			logger.log( Level.INFO, "BD: " + com );
			rs = s.executeQuery( com );
			if (!rs.next()) {
				com = "insert into Usuario (userName, password, email, avatarURL, ultimoCambioContrasena ) values ('"+ 
						usu.getUserName() +"', '" + usu.getPassword() +"', '" + usu.getEmail()+"', '" + usu.getAvatarURL()+ "', '" + usu.getUltimoCambioContrasena() +"')";
				logger.log( Level.INFO, "BD: " + com );
				
				int val = s.executeUpdate( com );
				
				if (val!=1) {
					// Mostrar mensaje de error si la inserción falla.
                    showError("Error en inserción");				
				}
			} else {
				// Mostrar mensaje si el usuario ya existe.
                showError("Usuario " + usu.getEmail() + " ya existe");
			}
		} catch (SQLException e2) {
			// Manejar excepciones e imprimir información de error.
	        handleException(e2, com);
		}
	}
	
	/**
	 * Maneja una excepción, imprime la información de error y muestra un mensaje de error.
	 * @param e Excepción que se debe manejar.
	 * @param lastCommand Último comando SQL ejecutado.
	 */
	private static void handleException(SQLException e, String lastCommand) {
	    System.out.println("Último comando: " + lastCommand);
	    e.printStackTrace();
	    // Mostrar mensaje de error.
	    showError("Error en la base de datos: " + e.getMessage());
	}
	/**
	 * Muestra un mensaje de error en una ventana de diálogo.
	 * @param message Mensaje de error a mostrar.
	 */
	private static void showError(String message) {
	    JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * Modifica la contraseña de un usuario registrado.
	 * @param usu Objeto Usuario con la nueva contraseña.
	 */
	public void modificarUsuarioYaRegistradoContrasena(Usuario usu) {
		 String sent = "update Usuario set contrasena = '" + secu(usu.getPassword()) + "', ultimoCambioContrasena = '" + usu.getUltimoCambioContrasena() + "' where email = '" + secu(usu.getEmail()) + "'";
		logger.log(Level.INFO, "BD: " + sent);
		try {
			s.executeUpdate(sent);
		} catch (SQLException e1) {
			logger.log(Level.WARNING, sent, e1);
	        handleException(e1, "Error al modificar la contraseña del usuario " + usu.getEmail());
		}
	}
	
	/**
	 * Modifica el nombre de usuario de un usuario registrado.
	 * @param usu Objeto Usuario con el nuevo nombre.
	 */
	public void modificarUsuarioYaRegistrado(Usuario usu) {		
		String sent = "update Usuario set userName= '"+ secu(usu.getUserName())+  "' where email = '"+ secu(usu.getEmail()) + "'";
		logger.log(Level.INFO, "BD: " + sent);
	
		try {
			s.executeUpdate(sent);
		} catch (SQLException e1) {
			logger.log(Level.WARNING, sent, e1);	
			// Manejar la excepción y mostrar mensaje de error
	        handleException(e1, "Error al modificar el nombre del usuario " + usu.getEmail());
		}
	}

	/**
	 * Modifica la imagen de perfil de un usuario registrado.
	 * @param usu Objeto Usuario con la nueva imagen de perfil.
	 */
	public void modificarUsuarioImagenPerfil(Usuario usu) {
		String sent = "update Usuario set avatarURL= '" +
	            secu(usu.getAvatarURL()) + "' where email = '"+ secu(usu.getEmail()) + "'";
		logger.log(Level.INFO, "BD: " + sent);
	
		try {
			s.executeUpdate(sent);
		} catch (SQLException e1) {
			logger.log(Level.WARNING, sent, e1);
			// Manejar la excepción y mostrar mensaje de error
	        handleException(e1, "Error al modificar la imagen de perfil del usuario " + usu.getEmail());
		}
	}
	
	/**
	 * Modifica la descripción de un usuario registrado.
	 * @param usu Objeto Usuario con la nueva descripción.
	 */
	public void modificarDescripcionUsuario(Usuario usu) {
	    String sent = "update Usuario set descripcion= '" + usu.getDescripcion() + "' where email= '" + secu(usu.getEmail())+"'";
	    logger.log(Level.INFO, "BD: " + sent);
	    try {
	    	s.executeUpdate(sent);
	    } catch (SQLException e) {
	    	// Manejar la excepción y mostrar mensaje de error
	        handleException(e, "Error al modificar la descripción del usuario " + usu.getEmail());
	    }
	}
	
	/**
	 * Borra un usuario registrado.
	 * @param usu Objeto Usuario que se va a borrar.
	 */
	public void borrarUsuarioRegistrado(Usuario usu) {
		if (!usu.getEmail().isEmpty() && !usu.getPassword().isEmpty()) {
			String com = "";
			try {
				// Borrar usuario
				com = "delete from Usuario where email = '"+ secu(usu.getEmail()) +"'";
				logger.log( Level.INFO, "BD: " + com );
				s.executeUpdate( com );
			} catch (SQLException e2) {
				// Manejar la excepción y mostrar mensaje de error
	            handleException(e2, "Error al borrar el usuario " + usu.getEmail());
			}
		} else {
			JOptionPane.showMessageDialog( null, "Debes rellenar los dos campos" );
		}
	}
	
	/**
     * Cierra la conexión, el statement y el resultado si están abiertos.
     */
	public static void cerrarConexiones() {
		try {
            if (rs != null) rs.close();
            if (s != null) s.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            manejarExcepcion(e);
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
    	 handleException(e, "Error al visualizar usuarios");
    }
}

	/**
	 * Mapa de usuarios a partir de la información en la base de datos.
	 * @return HashMap donde la clave es el correo del usuario y el valor es el objeto Usuario correspondiente.
	 */
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
	
	/**
	 * Obtiene un usuario por su correo electrónico desde el mapa de usuarios.
	 * @param correo Correo electrónico del usuario a buscar.
	 * @return Objeto Usuario correspondiente al correo o null si no se encuentra.
	 */
	public static Usuario getUsuarioPorCorreo(String correo) {
		if(mapaUsuarios.containsKey(correo)) {
			return mapaUsuarios.get(correo);
		}
		else {
			return null;
		}
	}
	/**
	 * Obtiene la fecha del último cambio de contraseña para un usuario.
	 * @param usuario Usuario del cual se quiere obtener la fecha del último cambio de contraseña.
	 * @return LocalDate que representa la fecha del último cambio de contraseña.
	 */
	public LocalDate obtenerUltimoCambioContrasena(Usuario usuario) {
	    String com = "SELECT ultimoCambioContrasena FROM Usuario WHERE email = ?";
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
	            
	            return LocalDate.parse(fechaUltimoCambio, formatter);	       
	        }
	    } catch (SQLException e) {
	        System.out.println("Último comando: " + com);
	        e.printStackTrace();
	    }

	    // Si la cadena es nula o no se puede parsear, devolver un valor por defecto
	    return LocalDate.now();
	}

	
	// BASE DE DATOS ESTADISTICA

	/**
     * Crea la tabla 'EstadisticasJuego' en la base de datos si no existe.
     */
	private static void crearTablaEstadisticasJuego() {
        String comentarioSQL = "CREATE TABLE IF NOT EXISTS EstadisticasJuego (email STRING PRIMARY KEY, timePlayed INTEGER, dailyPlaytime INTEGER, roundsPlayed INTEGER, maxPoints INTEGER, minPoints INTEGER, totalPoints INTEGER, dailyAveragePoints INTEGER, fecha DATE, FOREIGN KEY (email) REFERENCES Usuario(email))";
        ejecutarSQL(comentarioSQL, Level.INFO);
    }

	
	  /**
     * Obtiene las estadísticas de juego para un usuario específico.
     * 
     * @param usuarioId El ID del usuario.
     * @return ResultSet con las estadísticas de juego del usuario.
     */
	public static ResultSet obtenerEstadisticasPorUsuario(String email) {
	    ResultSet resultado = null;
	    
	    try {
	        // Verifica que la conexión no sea null
	        if (con != null) {
	            String query = "SELECT * FROM estadisticas WHERE email = ?";
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, email);
	            resultado = ps.executeQuery();
	        } else {
	            System.err.println("La conexión a la base de datos es null.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return resultado;
	}

    
    public static int obtenerRoundsPlayed(String email) {
        String query = "SELECT roundsPlayed FROM EstadisticasJuego WHERE email = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("roundsPlayed");
            }
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
        return 0; // Valor predeterminado si hay un error o no se encuentra el usuario
    }
    
    public static void actualizarRoundsPlayed(String email, Integer nuevoRoundsPlayed) {
        String query = "UPDATE EstadisticasJuego SET roundsPlayed = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            if (nuevoRoundsPlayed != null) {
                preparedStatement.setInt(1, nuevoRoundsPlayed);
            } else {
                preparedStatement.setNull(1, Types.INTEGER); 
            }
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }
    
    public static void actualizarMaxPoints(String email, int nuevosPuntos) {
        ResultSet resultSet = obtenerEstadisticasPorUsuario(email);

        try {
            if (resultSet.next()) {
                // El usuario ya tiene estadísticas
                int maxPuntosActual = resultSet.getInt("maxPoints");

                // Verifica si los nuevos puntos superan al máximo actual
                if (nuevosPuntos > maxPuntosActual) {
                    // Actualiza el maxPoints en la base de datos
                    String updateMaxPointsSQL = "UPDATE EstadisticasJuego SET maxPoints = ? WHERE email = ?";
                    try (PreparedStatement preparedStatement = con.prepareStatement(updateMaxPointsSQL)) {
                        preparedStatement.setInt(1, nuevosPuntos);
                        preparedStatement.setString(2, email);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        manejarExcepcion(e);
                    }
                }
            } else {
                // El usuario no tiene estadísticas, inserta una nueva fila con maxPoints
                String insertSQL = "INSERT INTO EstadisticasJuego (email, maxPoints) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(insertSQL)) {
                    preparedStatement.setString(1, email);
                    preparedStatement.setInt(2, nuevosPuntos);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    manejarExcepcion(e);
                }
            }
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }
    
    public static void actualizarMinPoints(String email, int nuevosPuntos) {
        ResultSet resultSet = obtenerEstadisticasPorUsuario(email);

        try {
            if (resultSet.next()) {
                // El usuario ya tiene estadísticas
                int minPuntosActual = resultSet.getInt("minPoints");

                // Si es la primera vez o los nuevos puntos son menores, actualiza minPoints
                if (minPuntosActual == 0 || nuevosPuntos < minPuntosActual) {
                    // Actualiza el minPoints en la base de datos
                    String updateMinPointsSQL = "UPDATE EstadisticasJuego SET minPoints = ? WHERE email = ?";
                    try (PreparedStatement preparedStatement = con.prepareStatement(updateMinPointsSQL)) {
                        preparedStatement.setInt(1, nuevosPuntos);
                        preparedStatement.setString(2, email);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        manejarExcepcion(e);
                    }
                }
            } else {
                // El usuario no tiene estadísticas, inserta una nueva fila con minPoints
                String insertSQL = "INSERT INTO EstadisticasJuego (email, minPoints) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(insertSQL)) {
                    preparedStatement.setString(1, email);
                    preparedStatement.setInt(2, nuevosPuntos);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    manejarExcepcion(e);
                }
            }
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }
    
    public static void actualizarTotalPoints(String email, int nuevosPuntos) {
        ResultSet resultSet = obtenerEstadisticasPorUsuario(email);

        try {
            if (resultSet.next()) {
                // El usuario ya tiene estadísticas
                int totalPuntosActual = resultSet.getInt("totalPoints");

                // Suma los nuevos puntos a totalPoints
                int nuevoTotalPuntos = totalPuntosActual + nuevosPuntos;

                // Actualiza totalPoints en la base de datos
                String updateTotalPointsSQL = "UPDATE EstadisticasJuego SET totalPoints = ? WHERE email = ?";
                try (PreparedStatement preparedStatement = con.prepareStatement(updateTotalPointsSQL)) {
                    preparedStatement.setInt(1, nuevoTotalPuntos);
                    preparedStatement.setString(2, email);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    manejarExcepcion(e);
                }
            } else {
                // El usuario no tiene estadísticas, inserta una nueva fila con totalPoints
                String insertSQL = "INSERT INTO EstadisticasJuego (email, totalPoints) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(insertSQL)) {
                    preparedStatement.setString(1, email);
                    preparedStatement.setInt(2, nuevosPuntos);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    manejarExcepcion(e);
                }
            }
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }
    
    public static void actualizarDailyAveragePoints(String email, int nuevaMedia) {
        String updateDailyAveragePointsSQL = "UPDATE EstadisticasJuego SET dailyAveragePoints = ? WHERE email = ?";

        try (PreparedStatement preparedStatement = con.prepareStatement(updateDailyAveragePointsSQL)) {
            preparedStatement.setInt(1, nuevaMedia);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }
    
    public static void actualizarTiempoTotalJugado(String email, int tiempoNuevo) {
        ResultSet resultSet = obtenerEstadisticasPorUsuario(email);

        try {
            if (resultSet.next()) {
                // El usuario ya tiene estadísticas
                int tiempoActual = resultSet.getInt("timePlayed");
                int tiempoDiarioActual = resultSet.getInt("dailyPlayTime");

                // Verifica si la última partida registrada fue hoy
                Date fechaUltimaPartida = resultSet.getDate("fecha");
                LocalDate fechaUltimaPartidaLocalDate = ((java.sql.Date) fechaUltimaPartida).toLocalDate();
                LocalDate fechaHoy = LocalDate.now();

                if (fechaUltimaPartidaLocalDate.isEqual(fechaHoy)) {
                    // Si la última partida fue hoy, actualiza solo dailyPlayTime
                    int tiempoDiario = tiempoDiarioActual + tiempoNuevo;

                    // Actualiza el tiempo diario en la base de datos
                    String updateDailyPlayTimeSQL = "UPDATE EstadisticasJuego SET dailyPlayTime = ? WHERE email = ?";
                    try (PreparedStatement preparedStatement = con.prepareStatement(updateDailyPlayTimeSQL)) {
                        preparedStatement.setInt(1, tiempoDiario);
                        preparedStatement.setString(2, email);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        manejarExcepcion(e);
                    }
                } else {
                    // Si la última partida no fue hoy, actualiza ambos campos
                    int tiempoTotal = tiempoActual + tiempoNuevo;
                    int tiempoDiario = tiempoNuevo;

                    // Actualiza el tiempo total jugado, dailyPlayTime y la fecha de la última partida en la base de datos
                    String updateSQL = "UPDATE EstadisticasJuego SET timePlayed = ?, dailyPlayTime = ?, fecha = ? WHERE email = ?";
                    try (PreparedStatement preparedStatement = con.prepareStatement(updateSQL)) {
                        preparedStatement.setInt(1, tiempoTotal);
                        preparedStatement.setInt(2, tiempoDiario);
                        preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                        preparedStatement.setString(4, email);
                        preparedStatement.executeUpdate();
                    } catch (SQLException e) {
                        manejarExcepcion(e);
                    }
                }
            } else {
                // El usuario no tiene estadísticas, inserta una nueva fila
                int tiempoDiario = tiempoNuevo;

                // Inserta una nueva fila con timePlayed, dailyPlayTime y la fecha de la última partida
                String insertSQL = "INSERT INTO EstadisticasJuego (email, timePlayed, dailyPlayTime, fecha) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = con.prepareStatement(insertSQL)) {
                    preparedStatement.setString(1, email);
                    preparedStatement.setInt(2, tiempoNuevo);
                    preparedStatement.setInt(3, tiempoDiario);
                    preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    manejarExcepcion(e);
                }
            }
        } catch (SQLException e) {
            manejarExcepcion(e);
        }
    }

    private static void insertarEstadisticasDePrueba() {
    	//insertarEstadisticasEnBD("oihanecam@gmail.com", 120, 30, 5, 500, 100, 1500, 300, "2024-01-19");
    }

    
    private static Connection obtenerConexion() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:usuarios.db");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }   
}