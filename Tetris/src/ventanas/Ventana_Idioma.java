package ventanas;

import javax.swing.*;

public class Ventana_Idioma extends JFrame {
    private JComboBox<String> comboBoxIdioma;
    public static String idiomaSeleccionado ;

    public Ventana_Idioma() {
        setTitle("Select a Language");
        setSize(300, 150);


        comboBoxIdioma = new JComboBox<>(new String[]{"Español", "English","Français","Deutsch"});
        comboBoxIdioma.setSelectedIndex(1); 

        JButton btnAceptar = new JButton("Accept  ");
        btnAceptar.addActionListener(e -> {
            idiomaSeleccionado = (String) comboBoxIdioma.getSelectedItem();
            if(idiomaSeleccionado=="Español") {
            	Ventana_Options.cambiarTextosEspañol();
            	Ventana_Principal.cambiarTextosEspañol();
            	Ventana_AvatarUsuario.cambiarTextosEspañol();
            	Ventana_Creditos.cambiarTextosEspañol();
            	Ventana_SignIn.cambiarTextosEspañol();
            }
            if(idiomaSeleccionado=="English") {
            	Ventana_Options.cambiarTextosIngles();
            	Ventana_Principal.cambiarTextosIngles();
            	Ventana_AvatarUsuario.cambiarTextosIngles();
            	Ventana_Creditos.cambiarTextosIngles();    
            	Ventana_SignIn.cambiarTextosIngles();
            }
            if(idiomaSeleccionado=="Français") {
            	Ventana_Options.cambiarTextosFrances();
            	Ventana_Principal.cambiarTextosFrances();
            	Ventana_AvatarUsuario.cambiarTextosFrances();
            	Ventana_Creditos.cambiarTextosFrances();
            	Ventana_SignIn.cambiarTextosFrances();
            }
            if(idiomaSeleccionado=="Euskara") {
            	Ventana_Options.cambiarTextosEuskara();
            	Ventana_Principal.cambiarTextosEuskara();
            	Ventana_AvatarUsuario.cambiarTextosEuskara();
            	Ventana_Creditos.cambiarTextosEuskara();
            	Ventana_SignIn.cambiarTextosEuskara();
            }
            if(idiomaSeleccionado=="Deutsch") {
            	Ventana_Options.cambiarTextosAleman();
            	Ventana_Principal.cambiarTextosAleman();
            	Ventana_AvatarUsuario.cambiarTextosAleman();
            	Ventana_Creditos.cambiarTextosAleman();
            	Ventana_SignIn.cambiarTextosAleman();
            }

            dispose(); 
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select a Lenguage: "));
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