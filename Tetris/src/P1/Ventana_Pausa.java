package P1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		ventana.setSize(600, 800);
		ventana.setLayout(new BorderLayout());
	
		JPanel pnl2 = new JPanel();
		JPanel pnlCentro = new JPanel();
		
		JLabel lblPausa = new JLabel("Paused");
        lblPausa.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblPausa, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
        
        JButton btnResume = new JButton("Resume");
        pnl2.add(btnResume);
        
        JButton btnRestart = new JButton("Restart");
        pnl2.add(btnRestart);
        
        JButton btnOptions = new JButton("Options");
        pnl2.add(btnOptions);
        btnOptions.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Options();
			}
        	
        });
        
        JButton btnQuit = new JButton("Quit");
        pnl2.add(btnQuit);
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
       
        ventana.add(pnl2, BorderLayout.CENTER);
        ventana.setVisible(true); 
	}
	
	public static void main(String[] args) {
        Ventana_Pausa v = new Ventana_Pausa();
    }

}
