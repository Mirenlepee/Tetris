package P1;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana_Idioma extends JFrame{
	public Ventana_Idioma () {
		setTitle("Language");
        setSize(300, 200);

        JPanel panel = new JPanel();

        // Crear un JComboBox con opciones de idioma
        String[] idiomas = {"English","Deutsch", "Español", "Euskara", "Français"};
        JComboBox<String> comboBox = new JComboBox<>(idiomas);

        panel.add(comboBox);

        add(panel);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		Ventana_Idioma idiomas = new Ventana_Idioma();
	}    
		
}
