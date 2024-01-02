package gestionUsuarios;

import java.time.LocalDate;
import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {
	
	//Atributos
	protected static int cont = 0;
	private int id;
	private String userName;
	private String password;
	private String email;
	private String AvatarURL;
	private String descripcion;
	private LocalDate ultimoCambioContrasena;
	
	//Constructor
	public Usuario(String userName, String password, String email, String avatarURL, String descripcion) {
		super();
		this.id = cont;
		cont++;
		this.userName = userName;
		this.password = password;
		this.email = email;
		AvatarURL = avatarURL;
		this.descripcion = descripcion;
	}
	
	//Getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarURL() {
		return AvatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		AvatarURL = avatarURL;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public LocalDate getUltimoCambioContrasena() {
		return ultimoCambioContrasena;
	}

	public void setUltimoCambioContrasena(LocalDate ultimoCambioContrasena) {
		this.ultimoCambioContrasena = ultimoCambioContrasena;
	}

	//toString
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", AvatarURL=" + AvatarURL + ", descripcion=" + descripcion + "]";
	}
	
	public void cambiarContrasena(String nuevaContrasena) {
        // Código para cambiar la contraseña
        ultimoCambioContrasena = LocalDate.now();
        // Hash de la nueva contraseña
        String hashNuevaContrasena = BCrypt.hashpw(nuevaContrasena, BCrypt.gensalt());
        // Almacenar la contraseña encriptada
		this.password = hashNuevaContrasena;
    }
	
	
}
