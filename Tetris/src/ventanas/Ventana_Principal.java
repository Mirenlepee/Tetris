package ventanas;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Ventana_Principal extends JFrame{
	
	private BufferedImage imgFondo;
	private Ventana_Options ventanaOptions;
	
	private JButton btnPlay;
	private JButton btnStatistics;
	private JButton btnCredits;	
	
	
	public Ventana_Principal() {
		setTitle("Ventana Principal");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try {
            imgFondo = ImageIO.read(getClass().getResource("Fondo_VentanaPrincipal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // Capa inferior: Imagen de fondo
        JLabel fondoLabel = new JLabel(new ImageIcon(imgFondo));
        fondoLabel.setBounds(0, 0, getWidth(), getHeight());
        layeredPane.add(fondoLabel, JLayeredPane.DEFAULT_LAYER);

        initUI();
	}
    
	private void initUI() {
		addLogo();
		addButtons();
	}

	private void addLogo() {
        ImageIcon imgLogotipo = new ImageIcon(getClass().getResource("Logotipo.png"));
        JLabel lblLogotipo = new JLabel(imgLogotipo);
        lblLogotipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblLogotipo.setBounds(0, 0, getWidth(), imgLogotipo.getIconHeight());
        lblLogotipo.setOpaque(false); // Hacer que el JLabel sea transparente
        getContentPane().add(lblLogotipo, JLayeredPane.PALETTE_LAYER); // Establecer un z-index superior
    }
	
	private void addButtons() {
		JLayeredPane layeredPane = (JLayeredPane) getContentPane();
		
		btnPlay = new JButton("PLAY");
		btnPlay.setFont(new Font("Vendana", Font.BOLD, 16));
		btnPlay.setBackground(Color.WHITE);
		//btnPlay.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new Ventana_SignIn();
			}
		});
		btnPlay.setBounds(100, 200, 200, 30);
		layeredPane.add(btnPlay, JLayeredPane.PALETTE_LAYER);
		
		ventanaOptions = new Ventana_Options();		
		ImageIcon imgSettings = new ImageIcon(getClass().getResource("Settings.png"));
		JButton btnSettings = new JButton();
		btnSettings.setBackground(Color.WHITE);
		
		//Editar el tamaño del logo
		int nuevoAncho = 30;
		int nuevoAlto = 30;
		Image imagenDef = imgSettings.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		ImageIcon iconoDef = new ImageIcon(imagenDef);

		btnSettings.setIcon(iconoDef);

		btnSettings.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // TODO Auto-generated method stub
		    	updateBtnSoundImage(ventanaOptions);
		    	ventanaOptions.mostrarVentana(); 
		    }
		});
		btnSettings.setBounds(100, 250, 200, 30);
		layeredPane.add(btnSettings, JLayeredPane.PALETTE_LAYER);
		
		btnStatistics = new JButton("STATISTICS");
		btnStatistics.setFont(new Font("Vendana", Font.BOLD, 16));
		btnStatistics.setBackground(Color.WHITE);
		btnStatistics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// new Ventana_Estadistica();
			}			
		});
		
		btnStatistics.setBounds(100, 300, 200, 30);
		layeredPane.add(btnStatistics, JLayeredPane.PALETTE_LAYER);
		
		btnCredits = new JButton("CREDITS");
		btnCredits.setFont(new Font("Vendana", Font.BOLD, 16));
		btnCredits.setBackground(Color.WHITE);
		btnCredits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Creditos();
			}
		});
		btnCredits.setBounds(100, 350, 200, 30);
		layeredPane.add(btnCredits, JLayeredPane.PALETTE_LAYER);

	}
	
	private void updateBtnSoundImage(Ventana_Options ventanaOptions) {
		ImageIcon imgSettings;
	    if (ventanaOptions.getImgSoundState()) {
	        imgSettings = new ImageIcon(getClass().getResource("Sound.png"));
	    } else {
	        imgSettings = new ImageIcon(getClass().getResource("NoSound.png"));
	    }

	    int nuevoAncho = 30;
	    int nuevoAlto = 30;
	    Image imagenDef = imgSettings.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
	    ImageIcon iconoDef = new ImageIcon(imagenDef);

	    ventanaOptions.btnSound.setIcon(iconoDef);
	    ventanaOptions.setImgSoundState(!ventanaOptions.getImgSoundState());
	}
	
	public void actualizarIdioma(Ventana_Idioma.Idioma idioma) {
	    String[][] traducciones = Ventana_Idioma.traducirTodasLasPalabras(idioma);
	    btnPlay.setText(traducciones[0][0]); 
	    btnStatistics.setText(traducciones[1][0]);
	    btnCredits.setText(traducciones[2][0]);
	}
	
	public static void main(String[] args) {
		JFrame vent = new Ventana_Principal();
		vent.setVisible(true);
	}
}