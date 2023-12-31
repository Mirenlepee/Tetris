package gestionUsuarios;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

public class DataSetUsuarios {

	private static List<Usuario> lUsuarios = new ArrayList<Usuario>();
	private static HashMap<String, Usuario> mapaUsuarios = new HashMap<String, Usuario>();
	
	public DataSetUsuarios(String nombreFichero) throws IOException{
		File ficUsuarios = new File( nombreFichero );
		Scanner lecturaFic = null;
		if (ficUsuarios.exists()) {
			lecturaFic = new Scanner( ficUsuarios );
		} else {
			lecturaFic = new Scanner( DataSetUsuarios.class.getResourceAsStream( nombreFichero ) );
		}
		int numLinea = 0;
		while (lecturaFic.hasNextLine()) {
			numLinea++;
			String linea = lecturaFic.nextLine();
			String[] partes = linea.split( "\t" );
			try {
				int id = Integer.parseInt( partes[0] );
				String userName = partes[1];
				String password = partes[2];
				String email = partes[3];
				String AvatarURL = partes[4];
				String descripcion = partes[5];
				Usuario u = new Usuario(userName, password, email, AvatarURL, descripcion );
				mapaUsuarios.put(email, u);
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				System.err.println( "Error en lectura de línea " + numLinea );
			}
		}
	}
	
	public static void cargarMapaUsuarios(String email, Usuario u) {
		for(int i = 0; i<lUsuarios.size(); i++) {
			if(!mapaUsuarios.containsKey(u)) {
				mapaUsuarios.put(u.getEmail(), u);
			}
		}
	}
	
	public List<Usuario> getListaUsuario(){
		return lUsuarios;
	}
	
	public void anyadir(Usuario u) {
		cargarMapaUsuarios(u.getEmail(), u);
	}

}
