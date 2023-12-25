package ventanas;
	
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.text.JTextComponent;

import ventanas.Ventana_Idioma.Idioma;

import javax.mail.PasswordAuthentication;

import java.util.Arrays;
import java.util.Properties;
	
import java.awt.*;
	
	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.MouseEvent;
	
public class Ventana_SignIn extends JFrame {
	
	private JLabel signInlbl;
	private JPanel pnlLbl;
	private JLabel usernamelbl;
	private JTextField usernamefld;
	private JTextField txtCorreo;
	private JLabel passwordlbl;
	private JLabel forgotPasswordlbl;
	private JButton continuebtn;
	private JLabel noAccountlbl ;
	private static CustomPasswordField txtPassword;
	private static CustomPasswordField txtConfirm;
		
	private String generateRandomPassword() {
		String lowercase = "abcdefghijklmnopqrstuvwxyz";
	    String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String numbers = "0123456789";
	    String specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";

	    String allCharacters = lowercase + uppercase + numbers + specialCharacters;

	    Random random = new Random();
	    StringBuilder password = new StringBuilder();

	    for (int i = 0; i < 12; i++) { 
	    	int randomIndex = random.nextInt(allCharacters.length());
	        password.append(allCharacters.charAt(randomIndex));
	    }

	    return password.toString();
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
	
    private JFrame ventana;
	
    public Ventana_SignIn() {
        ventana = new JFrame("Sign In");
        ventana.setSize(400, 250);
        ventana.setLayout(new BorderLayout());
	
        signInlbl = new JLabel("Sign In");
        signInlbl.setFont(new Font("Cambria", Font.BOLD, 24));
	
        pnlLbl = new JPanel();
        pnlLbl.add(signInlbl, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);
	
        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new GridLayout(5, 2));
	
        usernamelbl = new JLabel("Username:");
        usernamefld = new JTextField(15);
        pnlPrincipal.add(usernamelbl);
        pnlPrincipal.add(usernamefld);
        
        JLabel lblCorreo = new JLabel("Email:");
        txtCorreo = new JTextField(15);
        pnlPrincipal.add(lblCorreo);
        pnlPrincipal.add(txtCorreo);
	
        passwordlbl = new JLabel("Password:");
        txtPassword = new CustomPasswordField();
        pnlPrincipal.add(passwordlbl);
        pnlPrincipal.add(txtPassword);
        
        JLabel lblConfirm = new JLabel("Confirm password:");
        txtConfirm = new CustomPasswordField();
        pnlPrincipal.add(lblConfirm);
        pnlPrincipal.add(txtConfirm);
	
        forgotPasswordlbl = new JLabel("<html><u>Forgot Password?</u></html>");
        forgotPasswordlbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotPasswordlbl.setForeground(Color.BLUE);
        forgotPasswordlbl.addMouseListener( new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JPanel dialogPanel = new JPanel();
				
				dialogPanel.setLayout(new GridLayout(2,2));

				JTextField emailRecuperaPass = new JTextField("@gmail.com");
				JTextField usernameField = new JTextField();

				dialogPanel.add(new JLabel("Email:"));
				dialogPanel.add(emailRecuperaPass);
				dialogPanel.add(new JLabel("Username:"));
				dialogPanel.add(usernameField);

				int option = JOptionPane.showOptionDialog(null, dialogPanel, "Type in your email and username",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (option == JOptionPane.OK_OPTION) {
					String username = usernameField.getText();
					String textoIngresado = emailRecuperaPass.getText();
					StringBuilder tx = new StringBuilder();
					tx.append("Dear [Username],\r\n")
					.append("\r\n")
					.append("We recently received a request to recover the password for your Tetris account associated with this email address. To enhance the security of your account, we have generated a new temporary password for you.\r\n")
					.append("\r\n")
					.append("New Password: [GeneratedPassword]\r\n")
					.append("\r\n")
					.append("For security reasons, we recommend changing this temporary password to a personalized one as soon as you log in. You can update your password in the account settings section.\r\n")
					.append("\r\n")
					.append("If you did not initiate this password recovery request, please contact our support team immediately at tetristeamhelp@gmail.com.\r\n")
					.append("\r\n")
					.append("Thank you for choosing Tetris!\r\n")
					.append("\r\n")
					.append("Best Regards,\r\n")
					.append("The Tetris Team\r\n");
					
					String rr=tx.toString();
	
					String generatedPassword = generateRandomPassword();
					rr = rr.replace("[GeneratedPassword]", generatedPassword);
					rr=rr.replace("[Username]", username);
	
					enviarCorreo(textoIngresado, "Password Recovery", rr);
	    
					//He pensado crear una contraseña random que reemplace la contraseña del usuario
					//Y que esta sea guardada en lugar de la que tenía.
				}
			}	        	
        });
        
	    pnlPrincipal.add(forgotPasswordlbl);
        pnlPrincipal.add(new JLabel());
	
        ventana.add(pnlPrincipal, BorderLayout.CENTER);

        JPanel pnlSouth = new JPanel();
        pnlSouth.setLayout(new GridLayout(2,1));
        ventana.add(pnlSouth, BorderLayout.SOUTH);
	        
	        
        JPanel Botonpnl = new JPanel();
        Botonpnl.setLayout(new FlowLayout(FlowLayout.CENTER));
        continuebtn = new JButton("Continue");
        continuebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//            	Pattern patronPassword = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$");
//                char[] password = txtPassword.getPassword();
//                char[] confirmada = txtConfirm.getPassword();
//                String username = usernamefld.getText();
//                String email = txtCorreo.getText();
//                String imagenP = "Sell_it/src/imagenes/perfil.png";
//                String descripcion = "Descripción vacía";
//                if (username.equals("Nombre") || email.equals("Correo") || password.length == 0 || confirmada.length == 0) {
//                    JOptionPane.showMessageDialog(null, "Para registrarse, debe introducir datos en todas las casillas.");
//                    return;
//                }
//                char[] confirmarContrasenia = txtConfirm.getPassword();
//                if (!Arrays.equals(password, confirmarContrasenia)) {
//                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                String cont = new String(password);
//                Matcher matcher = patronPassword.matcher(cont);
//                if (!matcher.matches()) {
//                    StringBuilder mensajeError = new StringBuilder("La contraseña no cumple con los requisitos:\n");
//                    if (!matcher.matches()) {
//                        if (!contraseniaCumpleRequisito("[A-Z]", cont)) {
//                            mensajeError.append("- Debe contener al menos una letra mayúscula.\n");
//                        }
//                        if (!contraseniaCumpleRequisito("[a-z]", cont)) {
//                            mensajeError.append("- Debe contener al menos una letra minúscula.\n");
//                        }
//                        if (!contraseniaCumpleRequisito("\\d", cont)) {
//                            mensajeError.append("- Debe contener al menos un dígito.\n");
//                        }
//                        if (!contraseniaCumpleRequisito("[@$!%*?&]", cont)) {
//                            mensajeError.append("- Debe contener al menos un carácter especial (@$!%*?&).\n");
//                        }
//                        mensajeError.append("- Debe tener al menos 6 caracteres.\n");
//
//                        JOptionPane.showMessageDialog(null, mensajeError.toString(), "Error", JOptionPane.ERROR_MESSAGE);
//                        return;
//                    }
//                } else {
//                	// Hash de la contraseña
//                    String hashContrasenia = BCrypt.hashpw(cont, BCrypt.gensalt());
//
//                    // Crear un nuevo usuario
//                    Usuario u = new Usuario(username, email, hashContrasenia, imagenP, descripcion);
//
//                    // Establecer la fecha actual como último cambio de contraseña
//                    u.cambiarContrasena(cont);
//
//                    BaseDeDatos.anadirUsuarioNuevo(u);
//                    JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
//                    limpiarCampos();
//                    VentanaInicio ventanaInicio = new VentanaInicio();
//                    dispose();  // Cierra la ventana actual
//                    ventanaInicio.setVisible(true);
//                    Main.setVentanaInicio(ventanaInicio);
//                }
            	ventana.dispose();
            	new Ventana_Juego();
            }
        });
        Botonpnl.add(continuebtn);
        
        JPanel Mensagepnl = new JPanel();
        noAccountlbl = new JLabel("<html><u>Don't have an account? Create one</u></html>");
        noAccountlbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        noAccountlbl.setForeground(Color.BLUE);
        noAccountlbl.setHorizontalAlignment(JLabel.CENTER);
  
        noAccountlbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ventana.dispose();
                new Ventana_SignUp();
            }
        });
       
        Mensagepnl.add(noAccountlbl);
        
        pnlSouth.add(Botonpnl);
        pnlSouth.add(noAccountlbl);
    
        ventana.setVisible(true);
    }
    
    private boolean contraseniaCumpleRequisito(String regex, String contrasenia) {
        return Pattern.compile(regex).matcher(contrasenia).find();
    }

    public void mostrarOcultarContraseña() {
        // Obtener la contraseña actual
        char[] contraseña = txtPassword.getPassword();

        // Cambiar el estado de visualización de la contraseña
        if (txtPassword.getEchoChar() == 0) {
        	txtPassword.setEchoChar('\u2022');
        } else {
        	txtPassword.setEchoChar((char) 0);
        }
        txtPassword.setText(new String(contraseña));
    }
	private void limpiarCampos() {
		usernamefld.setText("");
		txtCorreo.setText("");		
		txtPassword.setText("");
		txtConfirm.setText("");
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
        Ventana_SignIn v = new Ventana_SignIn();
    }

    public void actualizarIdioma(Idioma idiomaActual) {
		String[][] traducciones = Ventana_Idioma.traducirTodasLasPalabras(idiomaActual);
	    signInlbl.setText(traducciones[3][0]);
	    usernamelbl.setText(traducciones[4][0]); 
	    passwordlbl.setText(traducciones[5][0]); 
	    forgotPasswordlbl.setText(traducciones[6][0]); 
	    continuebtn.setText(traducciones[7][0]); 
	    noAccountlbl.setText(traducciones[8][0]);
	}

}