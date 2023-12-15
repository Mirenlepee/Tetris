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
		{ "Continue", "continuar", "fortfahren", "jarraitu", "continuer"},
		{ "Accept","Aceptar", "Akzeptieren", "Onartu", "Accepter"}, 
		{ "Seleccionar Foto", "Seleccionar Foto", "Foto auswählen", "Argazkia Hautatu","Sélectionner une photo"}, 
		{ "Credits", "Créditos", "Credits", "Kredituak", "Crédits"},
		{ "Creators", "Creadores", "Schöpfer", "Sortzaileak", "Créateurs"},
		{ "Designers", "Diseñadores", "Designer", "Diseinatzaileak", "Concepteurs"},
		{ "Return", "Volver", "Zurück", "Itzuli", "Retour"}, 
		{ "Statistics", "Estadísticas", "Statistiken", "Estadistika", "Statistiques"},
		{ "Return", "Volver", "Zurück", "Itzuli", "Retour"}, 
		{ "Game Over", "Game Over", "Spiel vorbei", "Game Over", "Fin de partie"},
		{ "Score", "Puntuación", "Punktzahl", "Puntuazioa", "Score"},
		{ "Best Score", "Mejor Puntuación", "Beste Punktzahl", "Hurrengo Puntuazioa", "Meilleur score"},
		{ "Time played", "Tiempo jugado", "Spielzeit", "Jolastutako denbora", "Temps écoulé"},
		{ "Exit", "Salir", "Beenden", "Irten", "Quitter"},
		{ }, // Ventana_idioma
		{ "Instructions", "Instrucciones", "Anleitung", "Argibideak", "Instructions"},
		{"The aim in Tetris is simple; you bring down blocks from the top of the screen. \n " + "You can move the blocks around, either left to right and/or you can rotate them. \n " + "The blocks fall at a certain rate, but you can make them fall faster if you're sure of your positioning.\n" + "1. Use the left and right arrow keys to move the falling blocks horizontally.\n" + "2. Press the down arrow key to make the blocks fall faster.\n" + "3. Rotate the blocks using the up arrow key to fit them into empty spaces.\n" + "4. Complete a horizontal line with no gaps to make it disappear and earn points.\n" + "5. As the game progresses, the blocks fall faster, challenging your speed and agility.\n" + "6. The game ends if the blocks reach the top of the screen.\n\n" + "Challenge yourself and see how high you can score breaking your own records! Good luck!",
        		    "El objetivo en Tetris es simple; hacer caer bloques desde la parte superior de la pantalla.\n" + "Puedes mover los bloques, ya sea de izquierda a derecha y/o rotarlos.\n" + "Los bloques caen a cierta velocidad, pero puedes hacer que caigan más rápido si estás seguro de tu posición.\n" + "1. Usa las teclas de flecha izquierda y derecha para mover los bloques horizontalmente.\n" + "2. Presiona la tecla de flecha hacia abajo para hacer caer los bloques más rápido.\n" + "3. Rota los bloques usando la tecla de flecha hacia arriba para encajarlos en espacios vacíos.\n" + "4. Completa una línea horizontal sin espacios para hacerla desaparecer y ganar puntos.\n" + "5. A medida que avanza el juego, los bloques caen más rápido, desafiando tu velocidad y agilidad.\n" + "6. El juego termina si los bloques alcanzan la parte superior de la pantalla.\n\n" + "¡Desafíate a ti mismo y ve cuán alto puedes puntuar rompiendo tus propios récords! ¡Buena suerte!",
				    "Das Ziel bei Tetris ist einfach: Du lässt Blöcke von oben auf den Bildschirm fallen.\n" + "Du kannst die Blöcke bewegen, entweder von links nach rechts und/oder sie drehen.\n" + "Die Blöcke fallen mit einer bestimmten Geschwindigkeit, aber du kannst sie schneller fallen lassen, wenn du sicher bist, wo sie hinpassen.\n" + "1. Benutze die Pfeiltasten links und rechts, um die fallenden Blöcke horizontal zu bewegen.\n" + "2. Drücke die Pfeiltaste nach unten, um die Blöcke schneller fallen zu lassen.\n" + "3. Drehe die Blöcke mit der Pfeiltaste nach oben, um sie in leere Bereiche zu passen.\n" + "4. Schließe eine horizontale Linie ohne Lücken ab, um sie verschwinden zu lassen und Punkte zu verdienen.\n" + "5. Mit fortschreitendem Spielverlauf fallen die Blöcke schneller und fordern deine Geschwindigkeit und Agilität heraus.\n" + "6. Das Spiel endet, wenn die Blöcke die Oberseite des Bildschirms erreichen.\n\n" + "Fordere dich selbst heraus und sieh, wie hoch du punkten kannst, indem du deine eigenen Rekorde brichst! Viel Glück!",
				    "Tetrisen helburua erraza da: blokeak pantailaren goitik jaisten saiatzea.\n" + "Blokeak mugitu ditzakezu, eskuinetik ezkerrera eta/edo biratu.\n" + "Blokeak zenbaki jakin batean jaisten dira, baina azkarren jaisteko aukera daukazu posizioa zuzenean izango baldin baduzu.\n" + "1. Erabili gezia ezkerrera eta eskuinera blokeak mugitzeko horizontalki.\n" + "2. Sakatu beherako gezia blokeak azkarrago jaisteko.\n" + "3. Biratu blokeak gora zein behean sartu ahal izateko gezia erabiliz.\n" + "4. Beteko horizontal bat gainontzeko tartarik gabe desagertzeko eta puntuak irabazteko.\n" + "5. Jokoak aurrera egiten duelarik, blokeak azkarren jaisten dira, zure abiadura eta agintasuna erronkatzen.\n" + "6. Jokoak bukatzen du blokeak pantailaren goirera iristen direnean.\n\n" + "Erronka eman zaitez eta ikusi zenbat puntu altu lortu dezakezun, zure propio erregistroak hautsiz! Zorte on!",
				    "Le but dans Tetris est simple : faire descendre des blocs depuis le haut de l'écran.\n" + "Vous pouvez déplacer les blocs, soit de gauche à droite et/ou les faire tourner.\n" + "Les blocs tombent à une certaine vitesse, mais vous pouvez les faire tomber plus vite si vous êtes sûr de votre positionnement.\n" + "1. Utilisez les flèches gauche et droite pour déplacer les blocs qui tombent horizontalement.\n" + "2. Appuyez sur la flèche vers le bas pour faire tomber les blocs plus rapidement.\n" + "3. Tournez les blocs à l'aide de la flèche vers le haut pour les placer dans des espaces vides.\n" + "4. Complétez une ligne horizontale sans espaces pour la faire disparaître et gagner des points.\n" + "5. Au fur et à mesure que le jeu avance, les blocs tombent plus vite, mettant à l'épreuve votre vitesse et votre agilité.\n" + "6. Le jeu se termine si les blocs atteignent le haut de l'écran.\n\n" + "Défiez-vous et voyez à quel point vous pouvez marquer en battant vos propres records ! Bonne chance!"},
		{ "Continue", "Continuar", "Weiter", "Jarraitu", "Continuer"},
		{ "Level: 1", "Nivel: 1", "Stufe: 1", "Maila: 1", "Niveau : 1"},
		{ "Points:", "Puntos:", "Punkte:", "Puntuak:", "Points :"},
		{ "Options", "Opciones", "Optionen", "Aukerak", "Options"},
		{ "Sound", "Sonido", "Ton", "Soinua", "Son"},
		{ "Language", "Idioma", "Sprache", "Hizkuntza", "Langue"},
		{ "How to play", "Cómo jugar", "Spielanleitung", "Nola jolastu", "Comment jouer"},
		{ "Return", "Volver", "Zurück", "Itzuli", "Retour"},
		{ "Paused", "Pausado", "Pausiert", "Geldituta", "En pause"},
		{ "Resume", "Reanudar", "Fortsetzen", "Jarraitu", "Reprendre"},
		{ "Restart", "Reiniciar", "Neustart", "Berrabiarazi", "Recommencer"},
		{ "Options", "Opciones", "Optionen", "Aukerak", "Options"},
		{ "Quit", "Salir", "Beenden", "Irten", "Quitter"},
		{ "User Name:", "Nombre de usuario:", "Benutzername:", "Erabiltzaile izena:", "Nom d'utilisateur :"},
		{ "Biography:", "Biografía:", "Biografie:", "Biografia:", "Biographie :"},
		{ "Continue", "Continuar", "Fortfahren", "Jarraitu", "Continuer"}
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