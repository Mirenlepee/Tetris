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

public class Ventana_Pausa{
	private JFrame ventana;
	
	public Ventana_Pausa() {
		ventana = new JFrame("Pausa");
		ventana.setTitle("Pausa");
		ventana.setSize(400, 400);
		ventana.setLayout(new BorderLayout());
	
		JLabel lblPausa = new JLabel("Paused");
        lblPausa.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblPausa, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
        
        JPanel pnlBotones = new JPanel();
		pnlBotones.setLayout(new BoxLayout(pnlBotones, BoxLayout.Y_AXIS));
		
		JPanel pnlCentro = new JPanel();
		
        JButton btnResume = new JButton("Resume");
        pnlBotones.add(btnResume);
        pnlBotones.add(Box.createVerticalStrut(10));
        
        JButton btnRestart = new JButton("Restart");
        pnlBotones.add(btnRestart);
        pnlBotones.add(Box.createVerticalStrut(10));
        
        JButton btnOptions = new JButton("Options");
        pnlBotones.add(btnOptions);
        pnlBotones.add(Box.createVerticalStrut(10));
        btnOptions.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Options();
			}
        	
        });
        
        JButton btnQuit = new JButton("Quit");
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
	
	public static void main(String[] args) {
        Ventana_Pausa v = new Ventana_Pausa();
    }

}