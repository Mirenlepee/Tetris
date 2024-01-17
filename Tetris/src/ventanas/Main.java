package ventanas;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import BD.GestionBDUsuario;
import gestionUsuarios.Usuario;
import java.util.logging.Handler;
import java.util.logging.FileHandler;
import java.util.logging.StreamHandler;
import java.util.logging.SimpleFormatter;

public class Main {
	
	private static Logger logger = Logger.getLogger(Main.class.getName());

	static HashMap<String, Usuario> mapaUsu;
	private static Ventana_Principal ventanaP;
	static GestionBDUsuario base; 


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ventanaP = new Ventana_Principal();
			ventanaP.setVisible(true);
			Thread.sleep(5000);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error en el main.", e);
			JOptionPane.showMessageDialog(ventanaP, "Error grave: contacta con los informáticos.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		logger.setLevel(Level.ALL);
		
		try {
		    Handler consoleHandler = new StreamHandler(System.out, new SimpleFormatter());
		    consoleHandler.setLevel(Level.FINEST);

		    Handler fileHandler = new FileHandler("Main.log.xml");
		    fileHandler.setLevel(Level.ALL);

		    logger.addHandler(consoleHandler);
		    logger.addHandler(fileHandler);
		} catch (Exception e) {
		    logger.log(Level.SEVERE, e.toString(), e);
		}
	}

}
