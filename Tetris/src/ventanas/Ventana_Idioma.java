package ventanas;

import javax.swing.*;

public class Ventana_Idioma extends JFrame {
    private JComboBox<String> comboBoxIdioma;
    public static String idiomaSeleccionado ;

    public Ventana_Idioma() {
        setTitle("Select Language");
        setSize(300, 150);

        comboBoxIdioma = new JComboBox<>(new String[]{"Español", "English", "Français", "Euskara", "Deutsch"});
        comboBoxIdioma.setSelectedIndex(0); 

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            idiomaSeleccionado = (String) comboBoxIdioma.getSelectedItem();
            
            if(idiomaSeleccionado=="Español") {
            	Ventana_Options.cambiarTextosEspañol();
            	Ventana_Principal.cambiarTextosEspañol();
            	Ventana_AvatarUsuario.cambiarTextosEspañol();
            	Ventana_Creditos.cambiarTextosEspañol();
            }
            if(idiomaSeleccionado=="English") {
            	Ventana_Options.cambiarTextosIngles();
            	Ventana_Principal.cambiarTextosIngles();
            	Ventana_AvatarUsuario.cambiarTextosIngles();
            	Ventana_Creditos.cambiarTextosIngles();            	
            }
            if(idiomaSeleccionado=="Français") {
            	Ventana_Options.cambiarTextosFrances();
            	Ventana_Principal.cambiarTextosFrances();
            	Ventana_AvatarUsuario.cambiarTextosFrances();
            	Ventana_Creditos.cambiarTextosFrances();
            }
            if(idiomaSeleccionado=="Euskara") {
            	Ventana_Options.cambiarTextosEuskara();
            	Ventana_Principal.cambiarTextosEuskara();
            	Ventana_AvatarUsuario.cambiarTextosEuskara();
            	Ventana_Creditos.cambiarTextosEuskara();
            }
            if(idiomaSeleccionado=="Deutsch") {
            	Ventana_Options.cambiarTextosAleman();
            	Ventana_Principal.cambiarTextosAleman();
            	Ventana_AvatarUsuario.cambiarTextosAleman();
            	Ventana_Creditos.cambiarTextosAleman();
            }
            dispose(); 
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Selecciona el idioma: "));
        panel.add(comboBoxIdioma);

        panel.add(btnAceptar);
        add(panel);

        setLocationRelativeTo(null);         
    }

	public static String getIdiomaSeleccionado() {
		return idiomaSeleccionado;
	}

	public void setIdiomaSeleccionado(String idiomaSeleccionado) {
		this.idiomaSeleccionado = idiomaSeleccionado;
	}
}