package ventanas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class Ventana_Idioma extends JFrame{
	public Ventana_Idioma () {
		setTitle("Language");
        setSize(300, 200);
        
        JLabel lblLanguage = new JLabel("Language");
        lblLanguage.setFont(new Font("Cambria", Font.BOLD, 24));
    	
    	JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblLanguage, BorderLayout.CENTER);
        add(pnlLbl, BorderLayout.NORTH);

        JPanel panel = new JPanel();

        // Crear un JComboBox con opciones de idioma
        String[] idiomas = {"English","Deutsch", "Español", "Euskara", "Français"};
        JComboBox<String> comboBox = new JComboBox<>(idiomas);
        panel.add(comboBox);
        
        JPanel panel2 = new JPanel();
        
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
        	
        });
        panel2.add(btnReturn);
        

        add(panel, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        setVisible(true);
	}
	
	public static enum Idioma { ENG, DEU, ESP, EUS, FRA };  
	
	private static String[][] traducciones = {
		{ "Play","", "Jugar", "Jolastu", "Jouer" }
	};
	
	public static String getTrad( String original, Idioma idioma ) {
		for (int i=0; i<traducciones.length; i++) {
			if (traducciones[i][0].equalsIgnoreCase(original)) {
				return traducciones[i][idioma.ordinal()];
			}
		}
		return "NO-TRADUCIDO: " + original;
	}
	
	public static void main(String[] args) {
		Ventana_Idioma idiomas = new Ventana_Idioma();
	}    	
}