package P1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSetUsuarios {

	private List<Usuario> lUsuarios = new ArrayList<Usuario>();
	
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
				Usuario u = new Usuario( id, userName, password, email, AvatarURL, descripcion );
				lUsuarios.add(u);
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				System.err.println( "Error en lectura de línea " + numLinea );
			}
		}
	}
	
	public List<Usuario> getListaUsuario(){
		return lUsuarios;
	}
	
	public void anyadir(Usuario u) {
		lUsuarios.add(u);
	}

}