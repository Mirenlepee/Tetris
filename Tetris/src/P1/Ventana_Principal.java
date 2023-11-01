package P1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana_Principal extends JFrame{
	
	public Ventana_Principal() {
		setTitle("Ventana Principal");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		ImageIcon imgLogotipo = new ImageIcon(Ventana_Principal.class.getResource("Logotipo.png"));
		JLabel lblLogotipo = new JLabel(imgLogotipo);
		this.add(lblLogotipo, BorderLayout.NORTH);
		
		JPanel pnlBotonera = new JPanel();
		this.add(pnlBotonera, BorderLayout.CENTER);
		
		JButton btnPlay = new JButton("PLAY");
		pnlBotonera.add(btnPlay);
		
		ImageIcon imgSettings = new ImageIcon("");
		JButton btnSettings = new JButton(imgSettings);
		pnlBotonera.add(btnSettings);
		
		JButton btnStatistics = new JButton("STATISTICS");
		pnlBotonera.add(btnStatistics);
		
		JButton btnCredits = new JButton("CREDITS");
		btnCredits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Creditos();
			}
			
		});
		
		pnlBotonera.add(btnCredits);
		
		
	}
	public static void main(String[] args) {
		JFrame vent = new Ventana_Principal();
		vent.setVisible(true);
	}
}
