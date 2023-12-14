package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import ventanas.Ventana_Idioma.Idioma;

public class Ventana_Idioma extends JFrame{
	
	private static List<JFrame> ventanasDelJuego = new ArrayList<>();
	private static Idioma idiomaActual = Idioma.ESP;
	
	private JLabel lblLanguage;
	private JButton btnReturn;
	
	public Ventana_Idioma () {
		setTitle("Language");
        setSize(300, 200);
        
        lblLanguage = new JLabel("Language");
        lblLanguage.setFont(new Font("Cambria", Font.BOLD, 24));
    	
    	JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblLanguage, BorderLayout.CENTER);
        add(pnlLbl, BorderLayout.NORTH);

        JPanel panel = new JPanel();

        String[] idiomas = {"English", "Deutsch", "Español", "Euskara", "Français"};
        JComboBox<String> comboBox = new JComboBox<>(idiomas);
        
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceIdiomaSeleccionado = comboBox.getSelectedIndex();
                idiomaActual = Idioma.values()[indiceIdiomaSeleccionado];
                cambiarIdiomaEnTodasLasVentanas();
            }
        });
        
        panel.add(comboBox);
        
        JPanel panel2 = new JPanel();
        
        btnReturn = new JButton("Return");
        btnReturn.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}        	
        });
        panel2.add(btnReturn);
        
        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        setVisible(true);
	}
	
	private static void cambiarIdiomaEnTodasLasVentanas() {
        for (JFrame ventana : ventanasDelJuego) {
            actualizarIdiomaEnVentana(ventana);
        }
    }

	private static void actualizarIdiomaEnVentana(JFrame ventana) {
	    if (ventana instanceof Ventana_Principal) {
	        ((Ventana_Principal) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_SignIn) {
	    	((Ventana_SignIn) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_SignUp) {
	    	((Ventana_SignUp) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_AvatarUsuario) {
	    	((Ventana_AvatarUsuario) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_Creditos) {
	    	((Ventana_Creditos) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_Estadistica) {
	    	((Ventana_Estadistica) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_GameOver) {
	    	((Ventana_GameOver) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_Idioma) {
	    	((Ventana_Idioma) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_Instrucciones) {
	    	((Ventana_Instrucciones) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_Juego) {
	    	((Ventana_Juego) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_Options) {
	    	((Ventana_Options) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_Pausa) {
	    	((Ventana_Pausa) ventana).actualizarIdioma(idiomaActual);
	    } else if (ventana instanceof Ventana_PerfilDeUsuario) {
	    	((Ventana_PerfilDeUsuario) ventana).actualizarIdioma(idiomaActual);
	    } else {
	        System.out.println("La ventana no es una instancia de Ventana_Principal.");
	    }
	}
	
	public static enum Idioma { ENG, DEU, ESP, EUS, FRA };  
	
	private static String[][] traducciones = {
		{ "Play", "Spielen", "Jugar", "Jolastu", "Jouer" },
		{ "STATISTICS", "ESTADÍSTICA", "STATISTIKEN", "ESTADISTIKA", "STATISTIQUES"},
		{ "CREDITS", "CRÉDITOS", "GUTHABEN", "KREDITUAK", "CRÉDITS"},
		{ "Sign In", "Iniciar sesión", "Anmelden", "Saioa hasi", "Connexion"},
		{ "Username:", "Nombre de usuario:", "Benutzername:", "Erabiltzaile izena:", "Nom d'utilisateur:"},
		{ "Password:", "Contraseña:", "Passwort:", "Pasahitza:", "Mot de passe:"},
		{ "<html><u>Forgot Password?</u></html>", "<html><u>¿Olvidaste la contraseña?</u></html>", "<html><u>Passwort vergessen?</u></html>", "<html><u>Pasahitzaren ahaztu duzu?</u></html>", "<html><u>Mot de passe oublié ?</u></html>"},
		{ "Continue", "Continuar", "Fortfahren", "Jarraitu", "Continuer"},
		{ "<html><u>Don't have an account? Create one</u></html>", "<html><u>¿No tienes una cuenta? Crea una</u></html>", "<html><u>Sie haben kein Konto? Erstellen Sie eins</u></html>", "<html><u>Ez duzu konturik? Sortu bat</u></html>", "<html><u>Vous n'avez pas de compte? Créez-en un</u></html>"},
		{ "Sign Up", "Registrarse", "Registrieren", "Erregistratu", "S'inscrire"},
		{ "Username:", "Nombre de usuario:", "Benutzername:", "Erabiltzaile izena:", "Nom d'utilisateur:"},
		{ "Password:", "contraseña:", "passwort:", "pasahitza:", "mot de passe:" },
		{ "Confirm Password:", "confirmar contraseña:", "passwort bestätigen:", "pasahitza berretsi:", "confirmer le mot de passe:"},
		{ "Email:", "correo electrónico:", "e-mail:", "posta elektronikoa:", "e-mail:"},
		{ "Back", "atrás", "zurück", "atzera", "retour"},
		{ "Quit", "salir", "beenden", "irten", "quitter"},
		{ "Continue", "continuar", "fortfahren", "jarraitu", "continuer"}		
	};
	
	public static String[][] traducirTodasLasPalabras(Idioma idioma) {
        int indiceIdioma = idioma.ordinal();
        String[][] traduccionesIdioma = new String[traducciones.length][traducciones[0].length];
        
        for (int i = 0; i < traducciones.length; i++) {
            for (int j = 0; j < traducciones[i].length; j++) {
                if (j == 0) {
                    traduccionesIdioma[i][j] = traducciones[i][j];
                } else {
                    traduccionesIdioma[i][j] = traducciones[i][indiceIdioma];
                }
            }
        }
        return traduccionesIdioma;
    }
	
	public void actualizarIdioma(Idioma idiomaActual) {
		String[][] traducciones = traducirTodasLasPalabras(idiomaActual);
		lblLanguage.setText(traducciones[0][0]);
		btnReturn.setText(traducciones[0][0]);
	}

	 public static void main(String[] args) {
	        Idioma idiomaSeleccionado = Idioma.FRA;

	        String[][] palabrasTraducidas = traducirTodasLasPalabras(idiomaSeleccionado);

	        for (String[] fila : palabrasTraducidas) {
	            for (String palabra : fila) {
	                System.out.print(palabra + " ");
	            }
	            System.out.println();
	        }
	 }   	
}