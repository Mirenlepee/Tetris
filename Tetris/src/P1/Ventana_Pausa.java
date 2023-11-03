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
		
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		
		JLabel lblPausa = new JLabel("Paused");
        lblPausa.setFont(new Font("Cambria", Font.BOLD, 24));
        pnl1.add(lblPausa, BorderLayout.CENTER);
        
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
				JOptionPane.showConfirmDialog(ventana, "Quit game?", "Confirm", JOptionPane.YES_NO_OPTION);
			}
        	
        });
        
        ventana.add(pnl1, BorderLayout.NORTH);
        ventana.add(pnl2, BorderLayout.CENTER);
        ventana.setVisible(true); 
	}
	
	public static void main(String[] args) {
        Ventana_Pausa v = new Ventana_Pausa();
    }

}
