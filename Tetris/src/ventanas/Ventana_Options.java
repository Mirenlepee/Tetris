package ventanas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

import ventanas.Ventana_Juego.CorazonPanel;

public class Ventana_Options extends JFrame{
	private JFrame ventana;
	private static JLabel lblOptions;
	public JButton btnSound;
	private static JLabel lblSound;
	private static JLabel lblLanguage;
	private static JLabel lblHTP;
	private static JButton btnReturn;
	private boolean imgSound = true;
	private static Clip clip;	
	private JPanel pnlPrincipal;
	
	public static boolean presionado;
	
	public Ventana_Options() {
		ventana = new JFrame("Options");
		ventana.setSize(400, 400);
		ventana.setTitle("Options");
    	
    	lblOptions = new JLabel("Options");
        lblOptions.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnlLbl = new JPanel();
        pnlLbl.add(lblOptions, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
       
        
        
        
        ImageIcon imgLanguage = new ImageIcon(getClass().getResource("Language.png"));
        JButton btnLanguage = new JButton();
        

        int ancho2 = 30;
		int alto2 = 30;
		Image imagenDef2 = imgLanguage.getImage().getScaledInstance(ancho2, alto2, Image.SCALE_SMOOTH);
		ImageIcon iconoDef2 = new ImageIcon(imagenDef2);
		btnLanguage.setIcon(iconoDef2);
        
        lblSound = new JLabel("Sound");
        btnReturn = new JButton("Return");
        btnReturn.setMaximumSize(new Dimension(Integer.MAX_VALUE, btnReturn.getPreferredSize().height));

        btnSound = new JButton();
        lblLanguage = new JLabel("Language");
        
        if (!presionado) {
        cambiarImgBtn();
        
        }

        lblHTP = new JLabel("How to play");

        ImageIcon imgHTP = new ImageIcon(getClass().getResource("HTP.png"));
        JButton btnHTP = new JButton();
      

        int ancho3 = 30;
		int alto3 = 30;
		Image imagenDef3 = imgHTP.getImage().getScaledInstance(ancho3, alto3, Image.SCALE_SMOOTH);
		ImageIcon iconoDef3 = new ImageIcon(imagenDef3);
		btnHTP.setIcon(iconoDef3);
        
        
        
		pnlPrincipal = new JPanel();
		pnlPrincipal.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10); 
		gbc.anchor = GridBagConstraints.WEST;
		pnlPrincipal.add(btnSound, gbc);
		gbc.gridx = 1; 
		pnlPrincipal.add(createButtonLabelPanel(btnSound, lblSound), gbc);

		gbc.gridy++;
		gbc.gridx = 0; 
		pnlPrincipal.add(btnLanguage, gbc);
		gbc.gridx = 1;
		pnlPrincipal.add(createButtonLabelPanel(btnLanguage, lblLanguage), gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		pnlPrincipal.add(btnHTP, gbc);
		gbc.gridx = 1; 
		pnlPrincipal.add(createButtonLabelPanel(btnHTP, lblHTP), gbc);

		gbc.gridy++;
		gbc.gridx = 0;
		pnlPrincipal.add(btnReturn, gbc);
		gbc.gridx = 1; 
		pnlPrincipal.add(createButtonLabelPanel(btnReturn, new JLabel("")), gbc); 

		ventana.add(pnlPrincipal, BorderLayout.CENTER);
        
     

	    btnSound.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            cambiarImgBtn();
	            
	            if(presionado) {
		            setPresionado(false);

	            }else {
		           setPresionado(true);

	            }
	            System.out.println(obtenerPresionado());
	        }
	    });

       
        
   
        
        
        btnLanguage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  Ventana_Idioma ventanaIdioma = new Ventana_Idioma();
			        ventanaIdioma.setVisible(true);
			        
			       
			        
			        
			        
			        
			}
        });
        
        
       
        
   
    
        
        btnHTP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Instrucciones();
			}
        });
        
    
        
      
    
        
        btnReturn.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ventana.dispose();
				setImgSoundState(imgSound);
			}
        });
        
     
        ventana.add(pnlPrincipal,BorderLayout.CENTER);
        
        
        
           
       
	}
	public static boolean isPresionado() {
		return presionado;
	}
	public void setPresionado(boolean presionado) {
		this.presionado = presionado;
	}
	
	
	   public static boolean obtenerPresionado() {
	        return isPresionado();
	    }
	   
	   
	   
	   
	   
	private JPanel createButtonLabelPanel(JButton button, JLabel label) {
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    panel.add(button);
	    panel.add(Box.createRigidArea(new Dimension(10, 0))); 
	    panel.add(label);
	    return panel;
	}
 
	public void cambiarImgBtn() {
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
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
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
	
	public static void cambiarTextosEspañol() {
	     
			lblOptions.setText("Opciones");
	        lblSound.setText("Sonido");
	        lblLanguage.setText("Idioma");
	        lblHTP.setText("Cómo jugar");
	        btnReturn.setText("Volver");
    }
	public static void cambiarTextosIngles() {
	     
		lblOptions.setText("Options");
        lblSound.setText("Sound");
        lblLanguage.setText("Language");
        lblHTP.setText("How to play");
        btnReturn.setText("Return");
	}
	public static void cambiarTextosFrances() {
	     
		lblOptions.setText("Options");
        lblSound.setText("Son");
        lblLanguage.setText("Langage");
        lblHTP.setText("Comment jouer");
        btnReturn.setText("Retourner");
	}
	public static void cambiarTextosEuskara() {
	     
		lblOptions.setText("Aukerak");
        lblSound.setText("Soinua");
        lblLanguage.setText("Hizkuntza");
        lblHTP.setText("Nola jokatu");
        btnReturn.setText("Itzuli");
	}
	public static void cambiarTextosAleman() {
	     
		lblOptions.setText("Optionen");
        lblSound.setText("Klang");
        lblLanguage.setText("Sprache");
        lblHTP.setText("Spielanleitung");
        btnReturn.setText("Zurückkehren");
	}
}