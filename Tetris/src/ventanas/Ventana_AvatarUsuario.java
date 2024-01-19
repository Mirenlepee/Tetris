package ventanas;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Ventana_AvatarUsuario extends JFrame {

    private static JButton btnAvatar;
    private static JButton btnSeleccionarFoto;
    private static JButton btnAceptar;
    private static String directorioSeleccionado;

    public Ventana_AvatarUsuario() {
        this.setSize(200, 200);
        this.setTitle("Choose Avatar");
        setResizable(false);
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        
        btnSeleccionarFoto = new JButton("Select a Photo");
        btnSeleccionarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    directorioSeleccionado = selectedFile.getPath();
                    ImageIcon selectedImage = new ImageIcon(selectedFile.getPath());
                    int ancho = 150;
                    int alto = 150;
                    Image scaledImage = selectedImage.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                    ImageIcon newIcon = new ImageIcon(scaledImage);
                    btnAvatar.setIcon(newIcon);
                }
            }
        });

        btnAceptar = new JButton("Continue");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                dispose();
            }
        });

        panel.add(btnSeleccionarFoto);
        panel.add(btnAceptar);

        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
        
        if(Ventana_Idioma.getIdiomaSeleccionado()!=null) {
            if(Ventana_Idioma.getIdiomaSeleccionado()=="Español") {
            	
            cambiarTextosEspañol();	
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Français") {
            	
            	cambiarTextosFrances();
            	
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Deutsch") {
            	cambiarTextosAleman();
            	
            }
        }
    }

    

	public void setAvatarButton(JButton btnAvatar) {
        this.btnAvatar = btnAvatar;
    }
    
    public String obtenerDirectorioSeleccionado() {
        return directorioSeleccionado;
    }
    
	public static void cambiarTextosEspañol() {
		 btnSeleccionarFoto.setText("Selecciona una foto");
		 btnAceptar.setText("Aceptar");
	}
	
	public static void cambiarTextosIngles() {
		 btnSeleccionarFoto.setText("Select a photo");
		 btnAceptar.setText("Accept");
	}
	
	public static void cambiarTextosFrances() {
		 btnSeleccionarFoto.setText("Sélectionnez une photo");
		 btnAceptar.setText("Accepter");
	}
	
	public static void cambiarTextosEuskara() {
		 btnSeleccionarFoto.setText("Aukeratu argazki bat");
		 btnAceptar.setText("Onartu");
	}
	
	public static void cambiarTextosAleman() {
		 btnSeleccionarFoto.setText("Wählen Sie ein Foto aus");
		 btnAceptar.setText("Akzeptieren");
	}
	
	public static void main(String[] args) {
		Ventana_AvatarUsuario vent = new Ventana_AvatarUsuario();
		
	}
	
}
