package P1;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana_Idioma {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Selección de Idioma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();

        // Crear un JComboBox con opciones de idioma
        String[] idiomas = {"English","Deutsch", "Español", "Euskara", "Français"};
        JComboBox<String> comboBox = new JComboBox<>(idiomas);

        panel.add(comboBox);

        frame.add(panel);
        frame.setVisible(true);
    }
}
