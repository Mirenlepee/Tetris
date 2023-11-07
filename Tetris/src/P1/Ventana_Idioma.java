package P1;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Ventana_Idioma{
	private JFrame ventana;
	
	public Ventana_Idioma() {
		ventana = new JFrame("Language");
		ventana.setSize(400, 400);
		ventana.setTitle("Language");
		ventana.setLayout(new BorderLayout());
		
		ventana.setVisible(true);
		
	}

}
