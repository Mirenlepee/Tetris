package ventanas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

import org.mindrot.jbcrypt.BCrypt;

import BD.GestionBDUsuario;
import gestionUsuarios.Usuario;

public class Ventana_PerfilDeUsuario extends JFrame { 
	
	private static JLabel lblBiografia;
	private static JButton btnGuardar;
	protected JTextArea txtBiografia;
	private JLabel lblContadorCaracteres;
	private String directorioFotoSeleccionada;
	private static Usuario usuarioActual;
	
	public Ventana_PerfilDeUsuario() {
        this.setSize(400, 400);
        this.setTitle("Perfil");
		this.setLayout(new BorderLayout());
		setResizable(false);
		
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
                if (!ventanaAvatar.isVisible()) {
                    directorioFotoSeleccionada = ventanaAvatar.obtenerDirectorioSeleccionado();
                } 
            }
        });
        
        this.add(btnAvatar, BorderLayout.NORTH);
        
        lblBiografia = new JLabel("Biography:");
        pnlPerfilDeUsuario.add(lblBiografia);
        
        txtBiografia = new JTextArea(8, 23);
        limitarCaracteres(txtBiografia, 200); // Limita la JTextArea a 200 caracteres
        txtBiografia.setLineWrap(true); // Activa el ajuste de línea automático
        txtBiografia.setWrapStyleWord(true); // Ajusta las líneas en palabras completas
        pnlPerfilDeUsuario.add(txtBiografia);
        
        lblContadorCaracteres = new JLabel("0/200");
        lblContadorCaracteres.setFont(new Font("Arial", Font.PLAIN, 10)); // Cambia la fuente a letra pequeña si es necesario
        pnlPerfilDeUsuario.add(lblContadorCaracteres);

        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actualizarContadorCaracteres();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                actualizarContadorCaracteres();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                actualizarContadorCaracteres();
            }
        };

        txtBiografia.getDocument().addDocumentListener(documentListener);
        
        btnGuardar = new JButton("Continue");
        pnlBoton.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CODIFICAR PARA GUARDAR EL NUEVO USUARIO
            	
	             String imagenP = "Tetris/src/ventanas/Avatar.png";
	             String descripcion = "Descripción vacía";
	             char[] password = Ventana_SignUp.getPassword();
	             String username = Ventana_SignUp.getUsername();
	             String email = Ventana_SignUp.getEmail();
	             String cont = new String(password);
	                
	             // Hash de la contraseña
	             String hashContrasenia = BCrypt.hashpw(cont, BCrypt.gensalt());

	             // Crear un nuevo usuario
	             Usuario u = new Usuario(username, hashContrasenia, email, imagenP, descripcion);

	             // Establecer la fecha actual como último cambio de contraseña
	             u.cambiarContrasena(cont);

	             usuarioActual = u;
	             GestionBDUsuario.anadirUsuarioNuevo(u);
	             JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
	             limpiarCampos();
	             Ventana_Juego vent = new Ventana_Juego();
	             dispose();  
	             vent.setVisible(true);
	            }
        });
        
        pnlPerfilDeUsuario.add(pnlBoton, BorderLayout.CENTER);
        pnlCentro.add(pnlPerfilDeUsuario);
        this.add(pnlCentro, BorderLayout.CENTER);
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
	 
	 private void limpiarCampos() {
			Ventana_SignUp.setUsername("");
			Ventana_SignUp.setEmail("");
			Ventana_SignUp.setPassword("");
			Ventana_SignUp.setConfirmPassword("");
		}
	private void limitarCaracteres(JTextComponent textComponent, int maxLength) {
	    Document document = textComponent.getDocument();
	    if (document instanceof AbstractDocument) {
	        AbstractDocument abstractDocument = (AbstractDocument) document;
	        abstractDocument.setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
	                    throws BadLocationException {
	                if ((fb.getDocument().getLength() + string.length()) <= maxLength) {
	                    super.insertString(fb, offset, string, attr);
	                }
	            }

	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
	                    throws BadLocationException {
	                int currentLength = fb.getDocument().getLength();
	                int overLimit = (currentLength + text.length()) - maxLength;
	                if (overLimit > 0) {
	                    text = text.substring(0, text.length() - overLimit);
	                }
	                super.replace(fb, offset, length, text, attrs);
	            }
	        });
	    }
	}
	public static Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	private void actualizarContadorCaracteres() {
	    int caracteresUtilizados = txtBiografia.getText().length();
	    lblContadorCaracteres.setText(caracteresUtilizados + "/200");
	}
	
	public static void cambiarTextosEspañol() {
		 lblBiografia.setText("Biografía:");
		 btnGuardar.setText("Continuar");
	}
	
	public static void cambiarTextosIngles() {
		 lblBiografia.setText("Biography:");
		 btnGuardar.setText("Continue");
	}
		
	public static void cambiarTextosFrances() {
		lblBiografia.setText("Biographie :");
	    btnGuardar.setText("Continuer");
	}
	
	public static void cambiarTextosEuskara() {
		 lblBiografia.setText("Biografia:");
		 btnGuardar.setText("Jarraitu");
	}

	public static void cambiarTextosAleman() {
		lblBiografia.setText("Biografie :");
		btnGuardar.setText("Fortfahren");
	}

//	public static void main(String[] args) {
//		Ventana_PerfilDeUsuario vent = new Ventana_PerfilDeUsuario();
//		
//	}
}