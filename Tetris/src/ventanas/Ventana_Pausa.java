package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ventanas.Ventana_Idioma.Idioma;

public class Ventana_Pausa extends JFrame{
	private JFrame ventana;
	private JLabel lblPausa;
	private JButton btnResume;
	private JButton btnRestart;
	private JButton btnOptions;
	private JButton btnQuit;
	private Ventana_Juego ventanaJuego;
	
	public Ventana_Pausa(Ventana_Juego ventJuego) {
		this.ventanaJuego = ventJuego; 
		ventana = new JFrame("Pausa");
		ventana.setTitle("Pausa");
		ventana.setSize(400, 400);
		ventana.setLayout(new BorderLayout());
	
		lblPausa = new JLabel("Paused");
        lblPausa.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblPausa, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
        
        JPanel pnlBotones = new JPanel();
		pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.Y_AXIS));
		
		JPanel pnlCentro = new JPanel();
		
        btnResume = new JButton("Resume");
        pnlBotones.add(btnResume);
        pnlBotones.add(Box.createVerticalStrut(10));
        btnResume.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
        	
        });;
        
        btnRestart = new JButton("Restart");
        pnlBotones.add(btnRestart);
        pnlBotones.add(Box.createVerticalStrut(10));
        
        btnOptions = new JButton("Options");
        pnlBotones.add(btnOptions);
        pnlBotones.add(Box.createVerticalStrut(10));
        btnOptions.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Options();
			}
        	
        });
        
        btnQuit = new JButton("Quit");
        pnlBotones.add(btnQuit);
        btnQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int option = JOptionPane.showConfirmDialog(ventana, "Quit game?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					ventana.dispose();
					Ventana_Principal ventPrincipal = new Ventana_Principal();
					ventPrincipal.setVisible(true);
				}
			}        	
        });
       
        pnlCentro.add(pnlBotones);
        ventana.add(pnlCentro, BorderLayout.CENTER);
        ventana.setVisible(true); 
	}
	
	public void actualizarIdioma(Idioma idiomaActual) {
		String[][] traducciones = Ventana_Idioma.traducirTodasLasPalabras(idiomaActual);
		lblPausa.setText(traducciones[46][0]);
		btnResume.setText(traducciones[47][0]);
		btnRestart.setText(traducciones[48][0]);
		btnOptions.setText(traducciones[49][0]);
		btnQuit.setText(traducciones[50][0]);
	}
}