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
		{ "Play", "Spielen", "Jugar", "Jolastu", "Jouer" }
	};
	
	public static String[][] traducirTodasLasPalabras(Idioma idioma) {
        int indiceIdioma = idioma.ordinal();
        String[][] traduccionesIdioma = new String[traducciones.length][traducciones[0].length];
        
        for (int i = 0; i < traducciones.length; i++) {
            for (int j = 0; j < traducciones[i].length; j++) {
                if (j == 0) {
                    traduccionesIdioma[i][j] = traducciones[i][j]; // Mantiene la palabra original
                } else {
                    traduccionesIdioma[i][j] = traducciones[i][indiceIdioma]; // Traduce al idioma seleccionado
                }
            }
        }
        return traduccionesIdioma;
    }

	 public static void main(String[] args) {
		 // Aquí se realizaría la selección del idioma, por ejemplo:
	        Idioma idiomaSeleccionado = Idioma.FRA;

	        // Llamamos al método para traducir todas las palabras al idioma seleccionado
	        String[][] palabrasTraducidas = traducirTodasLasPalabras(idiomaSeleccionado);

	        // Imprimimos las palabras traducidas
	        for (String[] fila : palabrasTraducidas) {
	            for (String palabra : fila) {
	                System.out.print(palabra + " ");
	            }
	            System.out.println();
	        }
	 }   	
}