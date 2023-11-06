package P1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ventana_GameOver{
	private JFrame ventana;
	
	public Ventana_GameOver() {
		ventana = new JFrame("Game Over");
		ventana.setSize(500, 300);
		ventana.setTitle("Game Over");
		ventana.setLayout(new BorderLayout());
    	
    	JLabel lblGameOver = new JLabel("Game Over :(");
    	lblGameOver.setFont(new Font("Cambria", Font.BOLD, 24));
    	
    	JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblGameOver, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
        
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new GridLayout(3,2));
        
        JLabel lblScore = new JLabel("Score:");
        JTextField tfScore = new JTextField(15);
        tfScore.setEnabled(false);
        pnlPrincipal.add(lblScore);
        pnlPrincipal.add(tfScore);
        
        JLabel lblBestScore = new JLabel("Best Score:");
        JTextField tfBestScore = new JTextField(15);
        tfBestScore.setEnabled(false);
        pnlPrincipal.add(lblBestScore);
        pnlPrincipal.add(tfBestScore);
        
        ImageIcon imgHome = new ImageIcon(getClass().getResource("Home.png"));
		JButton btnHome = new JButton();
		int ancho1 = 50;
		int alto1 = 50;
		Image imagenDef1 = imgHome.getImage().getScaledInstance(ancho1, alto1, Image.SCALE_SMOOTH);
		ImageIcon iconoDef1 = new ImageIcon(imagenDef1);
		btnHome.setIcon(iconoDef1);
		btnHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana.dispose();
				Ventana_Principal ventPrincipal = new Ventana_Principal();
				ventPrincipal.setVisible(true);
			}
			
		});
		pnlPrincipal.add(btnHome);
		
		ImageIcon imgRetry = new ImageIcon(getClass().getResource("Retry.png"));
		JButton btnRetry = new JButton();
		int ancho2 = 50;
		int alto2 = 50;
		Image imagenDef2 = imgRetry.getImage().getScaledInstance(ancho2, alto2, Image.SCALE_SMOOTH);
		ImageIcon iconoDef2 = new ImageIcon(imagenDef2);
		btnRetry.setIcon(iconoDef2);
		btnRetry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		pnlPrincipal.add(btnRetry);
        
		ventana.add(pnlPrincipal, BorderLayout.CENTER);
		ventana.setVisible(true);
	}
	public static void main(String[] args) {
        Ventana_GameOver v = new Ventana_GameOver();
    }
}
