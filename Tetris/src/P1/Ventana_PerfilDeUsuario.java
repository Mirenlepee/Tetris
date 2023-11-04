package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_PerfilDeUsuario extends JFrame { 
	
	public Ventana_PerfilDeUsuario() {
        this.setSize(600, 800);
        this.setTitle("Perfil");
		this.setLayout(new BorderLayout());
		
		JPanel pnlCentro = new JPanel();
		this.add(pnlCentro, BorderLayout.CENTER);

        JPanel pnlPerfilDeUsuario = new JPanel(new GridBagLayout());
        pnlPerfilDeUsuario.setLayout(new BoxLayout(pnlPerfilDeUsuario, BoxLayout.Y_AXIS));
        pnlCentro.add(pnlPerfilDeUsuario);
        
        ImageIcon imgAvatar = new ImageIcon("C:\\Users\\Oihane\\git\\Tetris\\Tetris\\src\\P1\\Avatar.png");
        JButton btnAvatar = new JButton();
        int ancho = 100;
        int alto = 100;
        Image imagenDef = imgAvatar.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon iconoDef = new ImageIcon(imagenDef);
        btnAvatar.setIcon(iconoDef);
        btnAvatar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ventana_AvatarUsuario();
			}
        	
        });
        this.add(btnAvatar, BorderLayout.NORTH);
       
        JLabel lblNombre = new JLabel("User Name:");
        pnlPerfilDeUsuario.add(lblNombre);
        
        JTextField txtNombre = new JTextField(20);
        pnlPerfilDeUsuario.add(txtNombre);
        
        JLabel lblBiografia = new JLabel("Biografy:");
        pnlPerfilDeUsuario.add(lblBiografia);
        
        JTextArea txtBiografia = new JTextArea(5, 20);
        pnlPerfilDeUsuario.add(txtBiografia);
        
        JButton btnGuardar = new JButton("Continue");
        pnlPerfilDeUsuario.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CODIFICAR PARA GUARDAR EL NUEVO USUARIO
            	new Ventana_Juego();
            }
        });
        
        this.setVisible(true);
        
    }
}
