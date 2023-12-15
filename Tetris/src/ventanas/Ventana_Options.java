package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

import ventanas.Ventana_Idioma.Idioma;
import ventanas.Ventana_Juego.CorazonPanel;


public class Ventana_Options extends JFrame{
	private JFrame ventana;
	private JLabel lblOptions;
	public JButton btnSound;
	private JLabel lblSound;
	private JLabel lblLanguage;
	private JLabel lblHTP;
	private JButton btnReturn;
	private boolean imgSound = true;
	private static Clip clip;	
	
	public Ventana_Options() {
	
		ventana = new JFrame("Options");
		ventana.setSize(400, 400);
		ventana.setTitle("Options");
		ventana.setLayout(new BorderLayout());
    	
    	lblOptions = new JLabel("Options");
        lblOptions.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblOptions, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
        
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnlPrincipal.setLayout(new javax.swing.BoxLayout(pnlPrincipal, javax.swing.BoxLayout.Y_AXIS));
        
        JPanel fila1 = new JPanel();
        JPanel fila2 = new JPanel();
        JPanel fila3 = new JPanel();
        JPanel fila4 = new JPanel();
        
        btnSound = new JButton();
        cambiarImgBtn();
		fila1.add(btnSound);

	    btnSound.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            cambiarImgBtn();
	        }
	    });

        lblSound = new JLabel("Sound");
        fila1.add(lblSound);
        
        ImageIcon imgLanguage = new ImageIcon(getClass().getResource("Language.png"));
        JButton btnLanguage = new JButton();
        int ancho2 = 30;
		int alto2 = 30;
		Image imagenDef2 = imgLanguage.getImage().getScaledInstance(ancho2, alto2, Image.SCALE_SMOOTH);
		ImageIcon iconoDef2 = new ImageIcon(imagenDef2);
		btnLanguage.setIcon(iconoDef2);
        fila2.add(btnLanguage);
        
        btnLanguage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Idioma();
			}
        });
        
        lblLanguage = new JLabel("Language");
        fila2.add(lblLanguage);
       
        
        ImageIcon imgHTP = new ImageIcon(getClass().getResource("HTP.png"));
        JButton btnHTP = new JButton();
        int ancho3 = 30;
		int alto3 = 30;
		Image imagenDef3 = imgHTP.getImage().getScaledInstance(ancho3, alto3, Image.SCALE_SMOOTH);
		ImageIcon iconoDef3 = new ImageIcon(imagenDef3);
		btnHTP.setIcon(iconoDef3);
        fila3.add(btnHTP);
        
        btnHTP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Instrucciones();
			}
        });
        
        lblHTP = new JLabel("How to play");
        fila3.add(lblHTP);
        
        btnReturn = new JButton("Return");
        fila4.add(btnReturn);
        
        btnReturn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana.dispose();
				setImgSoundState(imgSound);
			}
        });
        
        pnlPrincipal.add(fila1);
        pnlPrincipal.add(fila2);
        pnlPrincipal.add(fila3);
        pnlPrincipal.add(fila4);
        ventana.add(pnlPrincipal);
	}
 
	private void cambiarImgBtn() {
		if(imgSound) {
			ImageIcon imgSound = new ImageIcon(getClass().getResource("Sound.png"));
	        int ancho1 = 30;
			int alto1 = 30;
			Image imagenDef1 = imgSound.getImage().getScaledInstance(ancho1, alto1, Image.SCALE_SMOOTH);
			ImageIcon iconoDef1 = new ImageIcon(imagenDef1);
			btnSound.setIcon(iconoDef1);
		}else {
			ImageIcon imgNoSound = new ImageIcon(getClass().getResource("NoSound.png"));
	        int ancho = 30;
			int alto = 30;
			Image imagenDef = imgNoSound.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
			ImageIcon iconoDef = new ImageIcon(imagenDef);
			btnSound.setIcon(iconoDef);
		}
		imgSound = !imgSound;
	}
	
	public boolean getImgSoundState() {
        return imgSound;
    }
	
	public void setImgSoundState(boolean imgSoundState) {
        this.imgSound = imgSoundState;
    }
	
	public static void reproducirMusica(String archivo) {
		try {
            File file = new File(archivo);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproduce la música en bucle
            clip.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir el archivo de audio: " + e.getMessage());
        }		
	}
	 
	public static void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
	public void mostrarVentana() {
		cambiarImgBtn();
	    ventana.setVisible(true);
	}

	public void actualizarIdioma(Idioma idiomaActual) {
		String[][] traducciones = Ventana_Idioma.traducirTodasLasPalabras(idiomaActual);
		lblOptions.setText(traducciones[0][0]);
		btnSound.setText(traducciones[0][0]);
		lblSound.setText(traducciones[0][0]);
		lblLanguage.setText(traducciones[0][0]);
		lblHTP.setText(traducciones[0][0]);
		btnReturn.setText(traducciones[0][0]);
	}
}