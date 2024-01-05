package ventanas;

import javax.swing.*;

public class Ventana_Idioma extends JFrame {
    private JComboBox<String> comboBoxIdioma;
    public static String idiomaSeleccionado ;

    public Ventana_Idioma() {
        setTitle("Select Language");
        setSize(300, 150);

        comboBoxIdioma = new JComboBox<>(new String[]{"Español", "Inglés"});
        comboBoxIdioma.setSelectedIndex(0); 

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            idiomaSeleccionado = (String) comboBoxIdioma.getSelectedItem();
            
            if(idiomaSeleccionado=="Español") {
            	Ventana_Options.cambiarTextosEspañol();
            	Ventana_Principal.cambiarTextosEspañol();
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
