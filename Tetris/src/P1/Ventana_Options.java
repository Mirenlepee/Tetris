package P1;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana_Options{
	private JFrame ventana;
	
	public Ventana_Options() {
		ventana = new JFrame("Options");
		ventana.setSize(600, 800);
		ventana.setTitle("Options");
		ventana.setLayout(new BorderLayout());
    	
    	JLabel lblOptions = new JLabel("Options");
        lblOptions.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblOptions, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
        
        JButton btnSound = new JButton();
        btnSound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        JLabel lblSound = new JLabel("Sound");
        
        JButton btnLanguage = new JButton();
        btnLanguage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        JLabel lblLanguage = new JLabel("Language");
        
        JButton btnHTP = new JButton();
        btnHTP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        JLabel lblHTP = new JLabel("How to play");
        
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana.dispose();
			}
        	
        });
        
        ventana.setVisible(true);
	}
	
	public static void main(String[] args) {
        Ventana_Options v = new Ventana_Options();
    }
}
