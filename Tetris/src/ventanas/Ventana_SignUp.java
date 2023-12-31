package ventanas;

import javax.mail.Message;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.text.JTextComponent;

import org.mindrot.jbcrypt.BCrypt;

import gestionUsuarios.Usuario;
import ventanas.Ventana_Idioma.Idioma;

import javax.mail.PasswordAuthentication;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Ventana_SignUp extends JFrame{
	
	private JFrame ventana;
	private JLabel signUplbl;
	private JLabel usernamelbl;
	private JTextField usernamefld;
	private JLabel passwordlbl;
	private JLabel confirmPasswordlbl;
	private JLabel emaillbl;
	private JTextField emailfld;
	private JButton backbtn;
	private JButton exitbtn;
	private JButton continuebtn;
	private static CustomPasswordField passwordfld;
	private static CustomPasswordField confirmPasswordfld;
	
    public Ventana_SignUp() {
        ventana = new JFrame("Sign Up");
        ventana.setSize(400, 200);
        ventana.setLayout(new BorderLayout());

        signUplbl = new JLabel("Sign Up");
        signUplbl.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnllbl = new JPanel();
        pnllbl.add(signUplbl,BorderLayout.CENTER);
        
        ventana.add(pnllbl, BorderLayout.NORTH);

        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new GridLayout(4, 2));

        usernamelbl = new JLabel("Username:");
        usernamefld = new JTextField();
        aplicarEstiloCampo(usernamefld, "Username");
        pnlPrincipal.add(usernamelbl);
        pnlPrincipal.add(usernamefld);
        
        emaillbl = new JLabel("Email:");
        emailfld = new JTextField("@gmail.com");
        pnlPrincipal.add(emaillbl);
        pnlPrincipal.add(emailfld);

        passwordlbl = new JLabel("Password:");
        passwordfld = new CustomPasswordField();
        aplicarEstiloCampo(passwordfld, "Password");
        passwordfld.setEchoChar((char) 0);
        pnlPrincipal.add(passwordlbl);
        pnlPrincipal.add(passwordfld);

        confirmPasswordlbl = new JLabel("Confirm Password:");
        confirmPasswordfld = new CustomPasswordField();
        aplicarEstiloCampo(confirmPasswordfld, "Confirm password");
        confirmPasswordfld.setEchoChar((char) 0);
        pnlPrincipal.add(confirmPasswordlbl);
        pnlPrincipal.add(confirmPasswordfld);

        ventana.add(pnlPrincipal, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel();

        backbtn = new JButton("Back");
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             ventana.dispose();
             new Ventana_SignIn();
            }
        });
        buttonPanel.add(backbtn);

        exitbtn = new JButton("Quit");
        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        buttonPanel.add(exitbtn);

        continuebtn = new JButton("Continue");
        continuebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	 String username = usernamefld.getText();
				    String textoIngresado =emailfld.getText();
				    StringBuilder tx = new StringBuilder();
			        tx.append("Dear [Username],\r\n")
			          .append("\r\n")
			          .append("Welcome to Tetris, the ultimate destination for endless fun and excitement! We are thrilled to have you on board as a valued member of our gaming community.\r\n")
			          .append("\r\n")
			          .append("At Tetris, we believe in bringing people together through the joy of gaming, and we're excited that you've chosen to join us on this adventure. Your new account has been successfully created, and now it's time to dive into the world of Tetris!\r\n")
			          .append("\r\n")
			          .append("Here are a few things to get you started:\r\n")
			          .append("\r\n")
			          .append("1. Explore the Game: Discover the thrilling world of Tetris with our engaging gameplay and challenging puzzles. Whether you're a seasoned pro or a newcomer, there's always something new to experience.\r\n")
			          .append("\r\n")
			          .append("2. Personalize Your Profile: Make your mark in the Tetris world by personalizing your profile. Choose a unique username, set a profile picture, and let others see the Tetris master in you.\r\n")
			          .append("\r\n")
			          .append("3. Stay Informed: Keep an eye on your inbox for updates, exclusive offers, and news about upcoming features. We're constantly working to enhance your Tetris experience, and you'll be the first to know about any exciting developments.\r\n")
			          .append("\r\n")
			          .append("If you ever have questions, concerns, or just want to say hello, feel free to reach out to our dedicated support team at tetristeamhelp@gmail.com We're here to ensure your Tetris journey is as enjoyable as possible.\r\n")
			         .append("\r\n")
			         .append("Once again, welcome to Tetris! Get ready to stack, rotate, and clear lines in the most addictive puzzle game ever created.\r\n")
			         .append("\r\n")
			         .append("Best Regards,\r\n")
			         .append("\r\n")
			         .append("The Tetris Team");
			       
			        String rr=tx.toString();

		            rr=rr.replace("[Username]", username);

		            enviarCorreo(textoIngresado, " Welcome to Tetris - Let the Fun Begin!", rr);
		            
		            Pattern patronPassword = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$");
	                char[] password = passwordfld.getPassword();
	                char[] confirmada = confirmPasswordfld.getPassword();
	                String email = emailfld.getText();
	                String imagenP = "";
	                String descripcion = "Descripción vacía";
	                if (username.equals("Nombre") || email.equals("Correo") || password.length == 0 || confirmada.length == 0) {
	                    JOptionPane.showMessageDialog(null, "Para registrarse, debe introducir datos en todas las casillas.");
	                    return;
	                }
	                char[] confirmarContrasenia = confirmPasswordfld.getPassword();
	                if (!Arrays.equals(password, confirmarContrasenia)) {
	                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                String cont = new String(password);
	                Matcher matcher = patronPassword.matcher(cont);
	                if (!matcher.matches()) {
	                    StringBuilder mensajeError = new StringBuilder("La contraseña no cumple con los requisitos:\n");
	                    if (!matcher.matches()) {
	                        if (!contraseniaCumpleRequisito("[A-Z]", cont)) {
	                            mensajeError.append("- Debe contener al menos una letra mayúscula.\n");
	                        }
	                        if (!contraseniaCumpleRequisito("[a-z]", cont)) {
	                            mensajeError.append("- Debe contener al menos una letra minúscula.\n");
	                        }
	                        if (!contraseniaCumpleRequisito("\\d", cont)) {
	                            mensajeError.append("- Debe contener al menos un dígito.\n");
	                        }
	                        if (!contraseniaCumpleRequisito("[@$!%*?&]", cont)) {
	                            mensajeError.append("- Debe contener al menos un carácter especial (@$!%*?&).\n");
	                        }
	                        mensajeError.append("- Debe tener al menos 6 caracteres.\n");

	                        JOptionPane.showMessageDialog(null, mensajeError.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }
	                } else {
	                	// Hash de la contraseña
	                    String hashContrasenia = BCrypt.hashpw(cont, BCrypt.gensalt());

	                    // Crear un nuevo usuario
	                    Usuario u = new Usuario(username, hashContrasenia, email, imagenP, descripcion);

	                    // Establecer la fecha actual como último cambio de contraseña
	                    u.cambiarContrasena(cont);

//	                    BaseDeDatos.anadirUsuarioNuevo(u);
//	                    JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
//	                    limpiarCampos();
//	                    VentanaInicio ventanaInicio = new VentanaInicio();
//	                    dispose();  // Cierra la ventana actual
//	                    ventanaInicio.setVisible(true);
//	                    Main.setVentanaInicio(ventanaInicio);
	                }
                new Ventana_PerfilDeUsuario();
                ventana.dispose();     
                
            }
        });
        buttonPanel.add(continuebtn);

        ventana.add(buttonPanel, BorderLayout.SOUTH);

        ventana.setVisible(true);
    }
    
    
    private void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        final String usuario = "tetristeamhelp@gmail.com"; 
        final String contraseña = "lhbu fanv qyiv fdvo"; 

        
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");


        Session sesion = Session.getInstance(propiedades, new javax.mail.Authenticator() {

        	@Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, contraseña);
            }
        });
     
        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(usuario));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            Transport.send(mensaje);

            JOptionPane.showMessageDialog(null, "Email sent! Please check your inbox");
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "The email couldn't be sent. Try again");
        }
    }

    private boolean contraseniaCumpleRequisito(String regex, String contrasenia) {
        return Pattern.compile(regex).matcher(contrasenia).find();
    }
    
    public void mostrarOcultarContraseña() {
        // Obtener la contraseña actual
        char[] contraseña = passwordfld.getPassword();

        // Cambiar el estado de visualización de la contraseña
        if (passwordfld.getEchoChar() == 0) {
        	passwordfld.setEchoChar('\u2022');
        } else {
        	passwordfld.setEchoChar((char) 0);
        }
        passwordfld.setText(new String(contraseña));
    }
	private void limpiarCampos() {
		usernamefld.setText("");
		emailfld.setText("");		
		passwordfld.setText("");
		confirmPasswordfld.setText("");
	}
	
	private void aplicarEstiloCampo(JTextComponent textField, String texto) {
        textField.setForeground(new Color(169, 169, 169));
        textField.setPreferredSize(new Dimension(350, 30));
        textField.setText(texto);
        textField.addFocusListener(new FocusAdapter() {
        	
        	@Override
        	public void focusGained(FocusEvent e) {
                if (textField.getText().equals(texto)) {
                    textField.setText("");
                    if(textField instanceof JPasswordField) {
                    	 ((JPasswordField) textField).setEchoChar('\u2022');
                    }
                    textField.setForeground(Color.BLACK);
                }
            }
        	
        	@Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(texto);
                    if(textField instanceof JPasswordField) {
                    	((JPasswordField) textField).setEchoChar((char) 0); 
                    }
                    textField.setForeground(new Color(169, 169, 169));
                }
            }
        });
        textField.setBorder(new RoundBorder(new Color(51, 255, 233), 20));
    }
    private static ImageIcon ajustarIcon(ImageIcon icon) {
        int maxWidth = 20; // Tamaño máximo de ancho
        int maxHeight = 20; // Tamaño máximo de alto
        int newWidth, newHeight;
        Image img = icon.getImage();
        if (icon.getIconWidth() > icon.getIconHeight()) {
            newWidth = maxWidth;
            newHeight = (maxWidth * icon.getIconHeight()) / icon.getIconWidth();
        } else {
            newHeight = maxHeight;
            newWidth = (maxHeight * icon.getIconWidth()) / icon.getIconHeight();
        }
        // Redimensiona la imagen
        Image newImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        return icon;
    }

    private static class RoundBorder extends AbstractBorder {
        private final Color borderColor;
        private final int roundRadius;

        public RoundBorder(Color borderColor, int roundRadius) {
            this.borderColor = borderColor;
            this.roundRadius = roundRadius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(borderColor);
            g2.drawRoundRect(x, y, width - 1, height - 1, roundRadius, roundRadius);
            g2.dispose();
        }
    }
    
    private static class CustomPasswordField extends JPasswordField {
        private JButton button;

        public CustomPasswordField() {
            super();
            button = new JButton();
            setLayout(new BorderLayout());
            add(button, BorderLayout.EAST);
            button.setPreferredSize(new Dimension(30, 10));
            ImageIcon imgOjo = new ImageIcon(getClass().getResource("eyeClosed.png"));
    		button.setIcon((ajustarIcon(imgOjo)));
    		button.setBackground(Color.WHITE);
    		
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    char echoChar = getEchoChar();
                    if (echoChar == 0) {
                        setEchoChar('\u2022'); // Ocultar contraseña (punto negro)
                		ImageIcon imgOjo = new ImageIcon(getClass().getResource("eyeClosed.png"));
                		button.setIcon((ajustarIcon(imgOjo)));
                    } else {
                		ImageIcon imgOjo = new ImageIcon(getClass().getResource("eyeOpened.png"));
                		button.setIcon((ajustarIcon(imgOjo)));
                        setEchoChar((char) 0); // Mostrar contraseña
                    }
                }
            });
        }
        public JButton getButton() {
            return button;
        }
    }
    public static void main(String[] args) {
		Ventana_SignUp vent = new Ventana_SignUp();
		
	}
	public void actualizarIdioma(Idioma idiomaActual) {
		String[][] traducciones = Ventana_Idioma.traducirTodasLasPalabras(idiomaActual);
		signUplbl.setText(traducciones[9][0]);
		usernamelbl.setText(traducciones[10][0]);
		passwordlbl.setText(traducciones[11][0]);
		confirmPasswordlbl.setText(traducciones[12][0]);
		emaillbl.setText(traducciones[13][0]);
		backbtn.setText(traducciones[14][0]);
		exitbtn.setText(traducciones[15][0]);
		continuebtn.setText(traducciones[16][0]);	    
	}   
}