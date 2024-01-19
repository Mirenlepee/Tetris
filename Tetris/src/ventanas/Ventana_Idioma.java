package ventanas;

import javax.swing.*;

public class Ventana_Idioma extends JFrame {
    private JComboBox<String> comboBoxIdioma;
    public static String idiomaSeleccionado ;

    public Ventana_Idioma() {
        setTitle("Select a Language");
        setSize(300, 150);
        setResizable(false);

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
            	Ventana_Estadistica.cambiarTextosEspañol();
            	Ventana_GameOver.cambiarTextosEspañol();
            	Ventana_Juego.cambiarTextosEspañol();
            	Ventana_Pausa.cambiarTextosEspañol();
            	Ventana_PerfilDeUsuario.cambiarTextosEspañol();
            	Ventana_SignUp.cambiarTextosEspañol();
            } else if(idiomaSeleccionado=="English") {
            	Ventana_Options.cambiarTextosIngles();
            	Ventana_Principal.cambiarTextosIngles();
            	Ventana_AvatarUsuario.cambiarTextosIngles();
            	Ventana_Creditos.cambiarTextosIngles();    
            	Ventana_SignIn.cambiarTextosIngles();
            	Ventana_Estadistica.cambiarTextosIngles();
            	Ventana_GameOver.cambiarTextosIngles();
            	Ventana_Juego.cambiarTextosIngles();
            	Ventana_Pausa.cambiarTextosIngles();
            	Ventana_PerfilDeUsuario.cambiarTextosIngles();
            	Ventana_SignUp.cambiarTextosIngles();
            } else if(idiomaSeleccionado=="Français") {
            	Ventana_Options.cambiarTextosFrances();
            	Ventana_Principal.cambiarTextosFrances();
            	Ventana_AvatarUsuario.cambiarTextosFrances();
            	Ventana_Creditos.cambiarTextosFrances();
            	Ventana_SignIn.cambiarTextosFrances();
            	Ventana_Estadistica.cambiarTextosFrances();
            	Ventana_GameOver.cambiarTextosFrances();
            	Ventana_Juego.cambiarTextosFrances();
            	Ventana_Pausa.cambiarTextosFrances();
            	Ventana_PerfilDeUsuario.cambiarTextosFrances();
            	Ventana_SignUp.cambiarTextosFrances();
            } else if(idiomaSeleccionado=="Euskara") {
            	Ventana_Options.cambiarTextosEuskara();
            	Ventana_Principal.cambiarTextosEuskara();
            	Ventana_AvatarUsuario.cambiarTextosEuskara();
            	Ventana_Creditos.cambiarTextosEuskara();
            	Ventana_SignIn.cambiarTextosEuskara();
            	Ventana_Estadistica.cambiarTextosEuskara();
            	Ventana_GameOver.cambiarTextosEuskara();
            	Ventana_Juego.cambiarTextosEuskara();
            	Ventana_Pausa.cambiarTextosEuskara();
            	Ventana_PerfilDeUsuario.cambiarTextosEuskara();
            	Ventana_SignUp.cambiarTextosEuskara();
            } else if(idiomaSeleccionado=="Deutsch") {
            	Ventana_Options.cambiarTextosAleman();
            	Ventana_Principal.cambiarTextosAleman();
            	Ventana_AvatarUsuario.cambiarTextosAleman();
            	Ventana_Creditos.cambiarTextosAleman();
            	Ventana_SignIn.cambiarTextosAleman();
            	Ventana_Estadistica.cambiarTextosAleman();
            	Ventana_GameOver.cambiarTextosAleman();
            	Ventana_Juego.cambiarTextosAleman();
            	Ventana_Pausa.cambiarTextosAleman();
            	Ventana_PerfilDeUsuario.cambiarTextosAleman();
            	Ventana_SignUp.cambiarTextosAleman();
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