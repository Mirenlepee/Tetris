package P1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana_Principal extends JFrame{
	
	private BufferedImage imgFondo;
	
	public Ventana_Principal() {
		setTitle("Ventana Principal");
		setSize(550, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		
/*		try {
			imgFondo = ImageIO.read(new File("C:\\Users\\Oihane\\git\\Tetris\\Tetris\\src\\P1\\Fondo_VentanaPrincipal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
		
		 try {
	            imgFondo = ImageIO.read(getClass().getResource("Fondo_VentanaPrincipal.png"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
        
		setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imgFondo, 0, 0, getWidth(), getHeight(), this);
            }
        });
		
/*		ImageIcon imgLogotipo = new ImageIcon(Ventana_Principal.class.getResource("Logotipo.png"));
		JLabel lblLogotipo = new JLabel(imgLogotipo);
		this.add(lblLogotipo, BorderLayout.NORTH);
*/
		
		ImageIcon imgLogotipo = new ImageIcon(getClass().getResource("Logotipo.png"));
        JLabel lblLogotipo = new JLabel(imgLogotipo);
        add(lblLogotipo, BorderLayout.NORTH);
		
		JPanel pnlCentro = new JPanel();
		this.add(pnlCentro, BorderLayout.CENTER);
		
		JPanel pnlBotonera = new JPanel();
		pnlBotonera.setLayout(new BoxLayout(pnlBotonera, BoxLayout.Y_AXIS));
		// Agrega un espacio fijo vertical entre los botones para centrarlos
		pnlBotonera.add(Box.createRigidArea(new Dimension(0, 10)));
		//pnlBotonera.setLayout(new BoxLayout(pnlBotonera, BoxLayout.Y_AXIS));
		pnlCentro.add(pnlBotonera);
		
		JButton btnPlay = new JButton("PLAY");
		btnPlay.setFont(new Font("Vendana", Font.BOLD, 16));
		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_SignIn();
				
			}
			
		});
		pnlBotonera.add(btnPlay);
		pnlBotonera.add(Box.createRigidArea(new Dimension(0, 10)));
		
		ImageIcon imgSettings = new ImageIcon("C:\\Users\\miren\\git\\.project\\Tetris\\src\\P1\\Settings.png");
		JButton btnSettings = new JButton();
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
		        new Ventana_Options();
		    }
		});

		pnlBotonera.add(btnSettings);
		pnlBotonera.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton btnStatistics = new JButton("STATISTICS");
		btnStatistics.setFont(new Font("Vendana", Font.BOLD, 16));
		btnStatistics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Estadistica();
			}
			
		});
		pnlBotonera.add(btnStatistics);
		pnlBotonera.add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton btnCredits = new JButton("CREDITS");
		btnCredits.setFont(new Font("Vendana", Font.BOLD, 16));
		btnCredits.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Creditos();
			}
			
		});
		
		pnlBotonera.add(btnCredits);
		
	}
	public static void main(String[] args) {
		JFrame vent = new Ventana_Principal();
		vent.setVisible(true);
		
	}
}
