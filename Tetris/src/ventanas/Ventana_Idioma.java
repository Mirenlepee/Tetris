package ventanas;

import javax.swing.*;

public class Ventana_Idioma extends JFrame {
    private JComboBox<String> comboBoxIdioma;
    public static String idiomaSeleccionado ;

    public Ventana_Idioma() {
        setTitle("Select a Language");
        setSize(300, 150);

        comboBoxIdioma = new JComboBox<>(new String[]{"Español", "English"});
        comboBoxIdioma.setSelectedIndex(1); 

        JButton btnAceptar = new JButton("Accept  ");
        btnAceptar.addActionListener(e -> {
            idiomaSeleccionado = (String) comboBoxIdioma.getSelectedItem();
            
            if(idiomaSeleccionado=="Español") {
            	Ventana_Options.cambiarTextosEspañol();
            	Ventana_Principal.cambiarTextosEspañol();
	}else {
		idiomaSeleccionado=null;
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
