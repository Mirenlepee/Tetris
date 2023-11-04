package P1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Ventana_Principal extends JFrame{
	
	private BufferedImage imgFondo;
	
	public Ventana_Principal() {
		setTitle("Ventana Principal");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		try {
			imgFondo = ImageIO.read(new File("C:\\Users\\Oihane\\git\\Tetris\\Tetris\\src\\P1\\Fondo_VentanaPrincipal.png"));
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
		
		ImageIcon imgLogotipo = new ImageIcon(Ventana_Principal.class.getResource("Logotipo.png"));
		JLabel lblLogotipo = new JLabel(imgLogotipo);
		this.add(lblLogotipo, BorderLayout.NORTH);
		
		JPanel pnlCentro = new JPanel();
		this.add(pnlCentro, BorderLayout.CENTER);
		
		JPanel pnlBotonera = new JPanel(new GridBagLayout());
		pnlBotonera.setLayout(new BoxLayout(pnlBotonera, BoxLayout.Y_AXIS));
		pnlCentro.add(pnlBotonera);
		
		JButton btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_SignIn();
				
			}
			
		});
		pnlBotonera.add(btnPlay);
		
		ImageIcon imgSettings = new ImageIcon("C:\\Users\\miren\\git\\.project\\Tetris\\src\\P1\\Settings.png");
		JButton btnSettings = new JButton();

		int nuevoAncho = 50;
		int nuevoAlto = 50;
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
		
		JButton btnStatistics = new JButton("STATISTICS");
		btnStatistics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_Estadistica();
			}
			
		});
		pnlBotonera.add(btnStatistics);
		
		JButton btnCredits = new JButton("CREDITS");
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
