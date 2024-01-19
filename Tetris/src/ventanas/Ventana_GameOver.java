package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ventanas.Ventana_Juego.CorazonPanel;

public class Ventana_GameOver extends JFrame {
	protected JTextField tfScore;
	protected JTextField tfTimePlayed;
	private Ventana_Juego ventanaJuego;
	
	private static JLabel lblGameOver;
	private static JLabel lblScore;
	private static JLabel lblBestScore;
	private static  JLabel lblTimePlayed;
	private static JButton btnExit;
	
	public Ventana_GameOver(Ventana_Juego ventJuego) {
		this.ventanaJuego = ventJuego; 
		setSize(400, 200);
		setLocationRelativeTo(ventJuego);
		setResizable(false);
		setLayout(new BorderLayout());
    	
    	lblGameOver = new JLabel("Game Over");
    	lblGameOver.setFont(new Font("Cambria", Font.BOLD, 24));
    	
    	JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblGameOver, BorderLayout.CENTER);
        add(pnlLbl, BorderLayout.NORTH);
        
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new GridLayout(3,3));
        
        lblScore = new JLabel("Score:");
        tfScore = new JTextField(15);
        tfScore.setEditable(false);
        tfScore.setForeground(Color.MAGENTA);
        pnlPrincipal.add(lblScore);
        pnlPrincipal.add(tfScore);
        
        lblBestScore = new JLabel("Best Score:");
        JTextField tfBestScore = new JTextField(15);
        tfBestScore.setEditable(false);
        tfBestScore.setForeground(Color.MAGENTA);
        pnlPrincipal.add(lblBestScore);
        pnlPrincipal.add(tfBestScore);
        
        lblTimePlayed = new JLabel("Time played:");
        tfTimePlayed = new JTextField(15);
        tfTimePlayed.setEditable(false);
        tfTimePlayed.setForeground(Color.MAGENTA);
        pnlPrincipal.add(lblTimePlayed);
        pnlPrincipal.add(tfTimePlayed);
        
        JPanel pnlBotonera = new JPanel();
        
        ImageIcon imgHome = new ImageIcon(getClass().getResource("Home.png"));
		JButton btnHome = new JButton();
		int ancho1 = 30;
		int alto1 = 30;
		Image imagenDef1 = imgHome.getImage().getScaledInstance(ancho1, alto1, Image.SCALE_SMOOTH);
		ImageIcon iconoDef1 = new ImageIcon(imagenDef1);
		btnHome.setIcon(iconoDef1);
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventJuego.volverAlMenu();
				
				//Cierra la Ventana_GameOver
				setVisible(false);
				dispose();
			}			
		});
		pnlBotonera.add(btnHome);
		
		ImageIcon imgRetry = new ImageIcon(getClass().getResource("Retry.png"));
		JButton btnRetry = new JButton();
		int ancho2 = 30;
		int alto2 = 30;
		Image imagenDef2 = imgRetry.getImage().getScaledInstance(ancho2, alto2, Image.SCALE_SMOOTH);
		ImageIcon iconoDef2 = new ImageIcon(imagenDef2);
		btnRetry.setIcon(iconoDef2);
		btnRetry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ventanaJuego.vidas = 3;
		        ((CorazonPanel) ventanaJuego.PanelEspacio2).vidasMostradas = ventanaJuego.vidas;
		        ventanaJuego.etiquetaTiempo.setText("00:00");
		        ventanaJuego.minutos = 0;
		        ventanaJuego.segundos = 0;
		        ventanaJuego.timerContador.restart();
		        ventanaJuego.reiniciarJuego();

		        // Cierra la Ventana_GameOver
		        setVisible(false);
		        dispose();
		    }
		});
		pnlBotonera.add(btnRetry);

		btnExit = new JButton("Exit");
		btnExit.setPreferredSize(btnHome.getPreferredSize());
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventJuego.clip.stop();
	            System.exit(0);
			}
		});
		pnlBotonera.add(btnExit);
        
		add(pnlPrincipal, BorderLayout.CENTER);
		add(pnlBotonera, BorderLayout.SOUTH);
		realizarActualizacionCampos();
        setVisible(true);
        
        if(Ventana_Idioma.getIdiomaSeleccionado()!=null) {
            if(Ventana_Idioma.getIdiomaSeleccionado()=="Español") {
            	
            cambiarTextosEspañol();	
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Français") {
            	
            	cambiarTextosFrances();
            	
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Deutsch") {
            	cambiarTextosAleman();
            	
            }
        }
	}

	// Método para realizar la actualización de campos
    private void realizarActualizacionCampos() {
        ventanaJuego.actualizarCampos(this);
    }
    
	public static void cambiarTextosEspañol() {
		lblGameOver.setText("Fin del juego");
		lblScore.setText("Puntuación:");
		lblBestScore.setText("Mejor Puntuación:");
		lblTimePlayed.setText("Tiempo Jugado:");
		btnExit.setText("Salir");
	}
	
	public static void cambiarTextosIngles() {
		lblGameOver.setText("Game Over");
		lblScore.setText("Score:");
		lblBestScore.setText("Best Score:");
		lblTimePlayed.setText("Played Time:");
		btnExit.setText("Exit");
	}
	
	public static void cambiarTextosFrances() {
		lblGameOver.setText("Fin du jeu");
		lblScore.setText("Score :");
		lblBestScore.setText("Meilleur score :");
		lblTimePlayed.setText("Temps de jeu :");
		btnExit.setText("Quitter");
	}
	
	public static void cambiarTextosEuskara() {
		lblGameOver.setText("Jokoa amaitu");
		lblScore.setText("Puntuazioa:");
		lblBestScore.setText("Puntuaziorik onena:");
		lblTimePlayed.setText("Jokatutako denbora:");
		btnExit.setText("Irten");
	}
	
	public static void cambiarTextosAleman() {
		lblGameOver.setText("Spielende");
		lblScore.setText("Punktzahl :");
		lblBestScore.setText("Beste Punktzahl :");
		lblTimePlayed.setText("Spielzeit :");
		btnExit.setText("Beenden");
	}

    
}