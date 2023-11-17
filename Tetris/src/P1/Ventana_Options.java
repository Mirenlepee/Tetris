package P1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Ventana_Options{
	private JFrame ventana;
	
    
	public Ventana_Options() {
		ventana = new JFrame("Options");
		ventana.setSize(400, 400);
		ventana.setTitle("Options");
		ventana.setLayout(new BorderLayout());
    	
    	JLabel lblOptions = new JLabel("Options");
        lblOptions.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblOptions, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
        
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlPrincipal.setLayout(new javax.swing.BoxLayout(pnlPrincipal, javax.swing.BoxLayout.Y_AXIS));
        
        JPanel fila1 = new JPanel();
        JPanel fila2 = new JPanel();
        JPanel fila3 = new JPanel();
        JPanel fila4 = new JPanel();
        
        ImageIcon imgSound = new ImageIcon(getClass().getResource("Sound.png"));
        JButton btnSound = new JButton();
        int ancho1 = 30;
		int alto1 = 30;
		Image imagenDef1 = imgSound.getImage().getScaledInstance(ancho1, alto1, Image.SCALE_SMOOTH);
		ImageIcon iconoDef1 = new ImageIcon(imagenDef1);
		btnSound.setIcon(iconoDef1);
		fila1.add(btnSound);
		
        btnSound.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        JLabel lblSound = new JLabel("Sound");
        fila1.add(lblSound);
        
        ImageIcon imgLanguage = new ImageIcon(getClass().getResource("Language.png"));
        JButton btnLanguage = new JButton();
        int ancho2 = 30;
		int alto2 = 30;
		Image imagenDef2 = imgLanguage.getImage().getScaledInstance(ancho2, alto2, Image.SCALE_SMOOTH);
		ImageIcon iconoDef2 = new ImageIcon(imagenDef2);
		btnLanguage.setIcon(iconoDef2);
        fila2.add(btnLanguage);
        
        btnLanguage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Idioma();
			}
        	
        });
        
        JLabel lblLanguage = new JLabel("Language");
        fila2.add(lblLanguage);
       
        
        ImageIcon imgHTP = new ImageIcon(getClass().getResource("HTP.png"));
        JButton btnHTP = new JButton();
        int ancho3 = 30;
		int alto3 = 30;
		Image imagenDef3 = imgHTP.getImage().getScaledInstance(ancho3, alto3, Image.SCALE_SMOOTH);
		ImageIcon iconoDef3 = new ImageIcon(imagenDef3);
		btnHTP.setIcon(iconoDef3);
        fila3.add(btnHTP);
        
        btnHTP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Instrucciones();
			}
        	
        });
        
        JLabel lblHTP = new JLabel("How to play");
        fila3.add(lblHTP);
        
        JButton btnReturn = new JButton("Return");
        fila4.add(btnReturn);
        
        btnReturn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana.dispose();
			}
        	
        });
        
        pnlPrincipal.add(fila1);
        pnlPrincipal.add(fila2);
        pnlPrincipal.add(fila3);
        pnlPrincipal.add(fila4);
        ventana.add(pnlPrincipal);
        ventana.setVisible(true);
	}
	
	
}
