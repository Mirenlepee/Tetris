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

public class Ventana_GameOver extends JFrame{
	private JFrame ventana;
	protected JTextField tfScore;
	protected JTextField tfTimePlayed;
	private Ventana_Juego ventJuego;
	
	public Ventana_GameOver(Ventana_Juego ventJuego) {
		this.ventJuego = ventJuego;
		ventana = new JFrame("Game Over");
		ventana.setSize(400, 200);
		ventana.setTitle("Game Over");
		ventana.setLocationRelativeTo(null);
		ventana.setLayout(new BorderLayout());
    	
    	JLabel lblGameOver = new JLabel("Game Over");
    	lblGameOver.setFont(new Font("Cambria", Font.BOLD, 24));
    	
    	JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblGameOver, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
        
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new GridLayout(3,3));
        
        JLabel lblScore = new JLabel("Score:");
        tfScore = new JTextField(15);
        tfScore.setEnabled(false);
        tfScore.setForeground(Color.MAGENTA);
        pnlPrincipal.add(lblScore);
        pnlPrincipal.add(tfScore);
        
        JLabel lblBestScore = new JLabel("Best Score:");
        JTextField tfBestScore = new JTextField(15);
        tfBestScore.setEnabled(false);
        tfBestScore.setForeground(Color.MAGENTA);
        pnlPrincipal.add(lblBestScore);
        pnlPrincipal.add(tfBestScore);
        
        JLabel lblTimePlayed = new JLabel("Time played:");
        tfTimePlayed = new JTextField(15);
        tfTimePlayed.setEnabled(false);
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
				ventana.dispose();
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
				ventJuego.vidas = 3;
				((CorazonPanel) ventJuego.PanelEspacio2).vidasMostradas = ventJuego.vidas;
				ventJuego.etiquetaTiempo.setText("00:00");
				ventJuego.minutos = 0;
	        	ventJuego.segundos = 0;
	        	ventJuego.timerContador.restart();
	        	ventJuego.reiniciarJuego();
	        	
	        	//Cierra la Ventana_GameOver
	        	ventana.dispose();
			}
			
		});
		pnlBotonera.add(btnRetry);

		JButton btnExit = new JButton("Exit");
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
        
		ventana.add(pnlPrincipal, BorderLayout.CENTER);
		ventana.add(pnlBotonera, BorderLayout.SOUTH);
		ventana.setVisible(true);
	}
	
}