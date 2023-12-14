package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ventanas.Ventana_Idioma.Idioma;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_PerfilDeUsuario extends JFrame { 
	
	private JLabel lblNombre;
	private JLabel lblBiografia;
	private JButton btnGuardar;
	
	public Ventana_PerfilDeUsuario() {
        this.setSize(400, 400);
        this.setTitle("Perfil");
		this.setLayout(new BorderLayout());
		
		JPanel pnlCentro = new JPanel();

        JPanel pnlPerfilDeUsuario = new JPanel(new GridBagLayout());
        pnlPerfilDeUsuario.setLayout(new BoxLayout(pnlPerfilDeUsuario, BoxLayout.Y_AXIS));
        
        JPanel pnlBoton = new JPanel();
        
        ImageIcon imgAvatar = new ImageIcon(getClass().getResource("Avatar.png"));
        JButton btnAvatar = new JButton();
        int ancho = 150;
        int alto = 150;
        Image imagenDef = imgAvatar.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon iconoDef = new ImageIcon(imagenDef);
        
        btnAvatar.setContentAreaFilled(false); 
        
        btnAvatar.setIcon(iconoDef);
        btnAvatar.setBackground(Color.WHITE);
        btnAvatar.setBorder(new EmptyBorder(0, 0, 0, 0));
             
        btnAvatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana_AvatarUsuario ventanaAvatar = new Ventana_AvatarUsuario();
                ventanaAvatar.setAvatarButton(btnAvatar); 
            }
        });
        
        this.add(btnAvatar, BorderLayout.NORTH);
       
        lblNombre = new JLabel("User Name:");
        pnlPerfilDeUsuario.add(lblNombre);
        
        JTextField txtNombre = new JTextField(20);
        pnlPerfilDeUsuario.add(txtNombre);
        
        lblBiografia = new JLabel("Biografy:");
        pnlPerfilDeUsuario.add(lblBiografia);
        
        JTextArea txtBiografia = new JTextArea(5, 20);
        pnlPerfilDeUsuario.add(txtBiografia);
        
        btnGuardar = new JButton("Continue");
        pnlBoton.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CODIFICAR PARA GUARDAR EL NUEVO USUARIO
            	new Ventana_Juego();
            }
        });
        
        pnlPerfilDeUsuario.add(pnlBoton, BorderLayout.CENTER);
        pnlCentro.add(pnlPerfilDeUsuario);
        this.add(pnlCentro, BorderLayout.CENTER);
        this.setVisible(true);
    }
	
	public void actualizarIdioma(Idioma idiomaActual) {
		String[][] traducciones = Ventana_Idioma.traducirTodasLasPalabras(idiomaActual);
		lblNombre.setText(traducciones[0][0]);
		lblBiografia.setText(traducciones[0][0]);
		btnGuardar.setText(traducciones[0][0]);
	}
	
	public static void main(String[] args) {
		Ventana_PerfilDeUsuario vent = new Ventana_PerfilDeUsuario();
		
	}
}