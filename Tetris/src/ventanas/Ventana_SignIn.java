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

import org.mindrot.jbcrypt.BCrypt;

import BD.GestionBDUsuario;
import gestionUsuarios.Usuario;

import javax.mail.PasswordAuthentication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
	
import java.awt.*;
	
	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class Ventana_SignIn extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel signInlbl;
	private JPanel pnlLbl;
	private static JLabel usernamelbl;
	private JTextField usernamefld;
	private static JLabel lblCorreo;
	private JTextField txtCorreo;
	private static JLabel passwordlbl;
	private static JLabel forgotPasswordlbl;
	private static JButton continuebtn;
	private static JLabel noAccountlbl ;
	private static CustomPasswordField txtPassword;
	private static CustomPasswordField txtConfirm;
	private Usuario usuarioActual;
	static GestionBDUsuario base;
	public static HashMap<String, Usuario> mapaUsu;
	private static Logger logger = Logger.getLogger(Ventana_SignIn.class.getName());
	public static JLabel lblConfirm;
		
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
    	
    	super();
		
		try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        	// logger de prueba
        	logger.log(Level.SEVERE, "Error al configurar el look and feel", e);
        	JOptionPane.showMessageDialog(null, "Error al cargar la ventana: contacta con los informaticos.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

		// logger de prueba
        logger.log(Level.INFO, "Inicializando la ventana de inicio");
		
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
        usernamefld = new JTextField();
        aplicarEstiloCampo(usernamefld, "Username");
        pnlPrincipal.add(usernamelbl);
        pnlPrincipal.add(usernamefld);
        
         lblCorreo = new JLabel("Email:");
        txtCorreo = new JTextField("@gmail.com");
        pnlPrincipal.add(lblCorreo);
        pnlPrincipal.add(txtCorreo);
	
        passwordlbl = new JLabel("Password:");
        txtPassword = new CustomPasswordField();
        aplicarEstiloCampo(txtPassword, "Password");
        txtPassword.setEchoChar((char) 0);
        pnlPrincipal.add(passwordlbl);
        pnlPrincipal.add(txtPassword);
        
       lblConfirm = new JLabel("Confirm password:");
        txtConfirm = new CustomPasswordField();
        aplicarEstiloCampo(txtConfirm, "Confirm password");
        txtConfirm.setEchoChar((char) 0);
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
            	char[] passwordChar = txtPassword.getPassword();
    	        String contrasenia = new String(passwordChar);
    			String correo = txtCorreo.getText();

                if (!validarCorreo(correo)) {
                    // El correo es válido, puedes realizar acciones adicionales aquí
                    JOptionPane.showMessageDialog(null, "Correo con formato invalido válido");
                    return;
                }
                
    			if (verificarCredenciales(correo, contrasenia)) {
    				if (mostrarCondicionesDeUso()) {
    					usuarioActual = mapaUsu.get(correo);
    					JOptionPane.showMessageDialog(null, "Bienvenido de nuevo " + obtenerNombreUsuario(correo));
    					Ventana_Juego v = new Ventana_Juego();
    					dispose();
    					v.setVisible(true);
    					
    			        // Realiza acciones adicionales cuando el inicio de sesión sea exitoso
    				 }
    			}  else if (correo.isEmpty() || contrasenia.isEmpty()){
    				JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío "); 
    			} else {
    				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
    			}
            }
    		});
    		
    		try {
    			GestionBDUsuario.main(null);
    		} catch (SQLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		base = new GestionBDUsuario();
    		base.verUsuarios();
    		mapaUsu = base.crearMapa();
   
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

  

	

	public void mostrarOcultarContraseña() {
        // Obtener la contraseña actual
        char[] password = txtPassword.getPassword();

        // Cambiar el estado de visualización de la contraseña
        if (txtPassword.getEchoChar() == 0) {
        	txtPassword.setEchoChar('*');
        } else {
        	txtPassword.setEchoChar((char) 0);
        }
        txtPassword.setText(new String(password));
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
    
    private boolean mostrarCondicionesDeUso() {
        JTextArea txtArea = new JTextArea(
                "¡Bienvenido a Tetris!\n\n" +
                        "Por favor, lea y acepte los siguientes términos y condiciones antes de continuar:\n\n" +
                        "1. Al utilizar esta aplicación, usted acepta cumplir con los términos y condiciones establecidos.\n" +
                        "2. Tetris no se hace responsable de las pérdidas o daños derivados del uso de la aplicación.\n" +
                        "3. Los usuarios deben proporcionar información precisa y actualizada durante el registro.\n" +
                        "4. En Tetris asumimos la responsabilidad de proteger toda la información personal proporcionada.\n" +
                        "5. La información del usuario se utilizará de acuerdo con nuestra política de privacidad.\n\n" +
                        "Al hacer clic en Aceptar, confirma que ha leído y acepta estos términos y condiciones."
        );
        txtArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(scrollPane, BorderLayout.CENTER);

        int option = JOptionPane.showOptionDialog(
                this,
                messagePanel,
                "Condiciones de Uso",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Aceptar", "Cancelar"},
                "Aceptar"
        );

        return option == 0; // Devuelve true si el usuario hizo clic en "Aceptar"
    }
    
    private void mostrarPoliticaPrivacidad() {
        try {
        	JTextArea txtArea = new JTextArea(

                    "Nuestra Política de Privacidad!\n\n"+
                    	"Fecha de entrada en vigencia: [Fecha]\n\n"+
                    	"¡Bienvenido a Tetris! Agradecemos tu interés y confianza en nuestra aplicación. Esta Política de Privacidad tiene como objetivo explicar cómo recopilamos, utilizamos y protegemos la información personal que puedas proporcionar durante el uso de nuestra aplicación.\n\n"+
                    		
                    		"1. Información que Recopilamos:\n\n"+
                    		"Al utilizar nuestra aplicación, podemos recopilar la siguiente información:\n"+
                    		"1.1 Información del Usuario:\n"+

                            "\n\nAl hacer clic en Aceptar, confirmas que has leído y aceptas estos términos."
            );
        	txtArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(txtArea);
            scrollPane.setPreferredSize(new Dimension(600, 400));

            JPanel messagePanel = new JPanel(new BorderLayout());
            messagePanel.add(scrollPane, BorderLayout.CENTER);

            int option = JOptionPane.showOptionDialog(
                    this,
                    messagePanel,
                    "Política de Privacidad",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"Aceptar"},
                    "Aceptar"
            );
        }catch (Exception e) {
            logger.log(Level.SEVERE, "Error al mostrar la política de privacidad", e);
        }
		
    }
    
    public static boolean validarCorreo(String correo) {
        if (correo == null || correo.isEmpty()) {
            return false; // Correo nulo o vacío es inválido
        }

        // Expresión regular para validar un correo electrónico
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return correo.matches(regex);
    }
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	private boolean  verificarCredenciales (String correo, String contrasenia) {
		 if (mapaUsu.containsKey(correo)) {
           Usuario u = base.getUsuarioPorCorreo(correo);
	        String hashAlmacenado = u.getPassword();
	        if(hashAlmacenado.startsWith("$2a$")) {
		        if (BCrypt.checkpw(contrasenia, hashAlmacenado)) {
		            return true; // La contraseña es correcta
		        } else {
		            return false; // La contraseña es incorrecta
		        }
	        }else {			//Esta parte es paara comprobar con los usuarios de prueba
	        	if (contrasenia.equals(hashAlmacenado)) {
	                return true; // La contraseña sin encriptar es correcta
	            } else {
	                return false; // La contraseña sin encriptar es incorrecta
	            }
	        }
		 } else {
			 return false;// El correo no está registrado, la autenticación falla
		 }
	}
	
	public String obtenerNombreUsuario(String correo) {
		if (mapaUsu.containsKey(correo)) {
	        Usuario usuario = mapaUsu.get(correo);
	        return usuario.getUserName();
	    } else {
	        return "Nombre de usuario no encontrado";
	    }
	}
	
	 public static void cambiarTextosEspañol() {
		 
		 usernamelbl.setText("Nombre de Usuario:");
		 lblCorreo.setText("Correo:");
		 passwordlbl.setText("Contraseña:");
		 lblConfirm.setText("Confirmar contraseña:");
		 forgotPasswordlbl.setText("<html><u>¿Has olvidado la contraseña?</u></html>");
		 continuebtn.setText("Continuar");
		 noAccountlbl.setText("<html><u>¿No tienes cuenta? Crea una</u></html>");
		 txtPassword.setText("Contraseña");
		 txtConfirm.setText("Confirmar Contraseña");
	 }
	
	 public static void cambiarTextosIngles() {
		 
		 usernamelbl.setText("Username:");
		 lblCorreo.setText("Mail:");
		 passwordlbl.setText("Password:");
		 lblConfirm.setText("Confirm Password:");
		 forgotPasswordlbl.setText("<html><u>Have you forgotten your password?</u></html>");
		 continuebtn.setText("Continue");
		 noAccountlbl.setText("<html><u>Don't have an account? Create one</u></html>");
		 txtPassword.setText("Password");
		 txtConfirm.setText("Confirm Password:");
	 }
	
	 private void cambiarTextosFrances() {
			// TODO Auto-generated method stub
		  usernamelbl.setText("Nom d'utilisateur :");
		    lblCorreo.setText("Courriel :");
		    passwordlbl.setText("Mot de passe :");
		    lblConfirm.setText("Confirmer le mot de passe :");
		    forgotPasswordlbl.setText("<html><u>Vous avez oublié le mot de passe ?</u></html>");
		    continuebtn.setText("Continuer");
		    noAccountlbl.setText("<html><u>Vous n'avez pas de compte ? Créez-en un</u></html>");
		    txtPassword.setText("Mot de passe");
		    txtConfirm.setText("Confirmer le Mot de passe");	
	  }

	  public static void cambiarTextosEuskara() {
			 
			 usernamelbl.setText("Erabiltzaile izena:");
			 lblCorreo.setText("Posta:");
			 passwordlbl.setText("Pasahitza:");
			 lblConfirm.setText("Pasahitza baieztatu:");
			 forgotPasswordlbl.setText("<html><u>Ahaztu al duzu pasahitza?</u></html>");
			 continuebtn.setText("Continuar");
			 noAccountlbl.setText("<html><u>Ez duzu konturik? Sortu bat</u></html>");
			 txtPassword.setText("Pasahitza");
			 txtConfirm.setText("Pasahitza baieztatu");
		 }
	  
	
	  private void cambiarTextosAleman() {
			// TODO Auto-generated method stub
		  usernamelbl.setText("Benutzername :");
		  lblCorreo.setText("E-Mail :");
		  passwordlbl.setText("Passwort :");
		  lblConfirm.setText("Passwort bestätigen :");
		  forgotPasswordlbl.setText("<html><u>Sie haben Ihr Passwort vergessen?</u></html>");
		  continuebtn.setText("Fortfahren");
		  noAccountlbl.setText("<html><u>Sie haben kein Konto? Erstellen Sie eins</u></html>");
		  txtPassword.setText("Passwort");
		  txtConfirm.setText("Passwort bestätigen");
	
		}
	
    public static void main(String[] args) {
        Ventana_SignIn v = new Ventana_SignIn();
    }

   

}