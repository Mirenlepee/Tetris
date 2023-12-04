package ventanas;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.mail.PasswordAuthentication;

import java.util.Properties;

import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.awt.event.MouseEvent;

public class Ventana_SignIn extends JFrame {
	
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
        ventana.setSize(400, 200);
        ventana.setLayout(new BorderLayout());

        JLabel signInlbl = new JLabel("Sign In");
        signInlbl.setFont(new Font("Cambria", Font.BOLD, 24));

        JPanel pnlLbl = new JPanel();
        pnlLbl.add(signInlbl, BorderLayout.CENTER);
        ventana.add(pnlLbl, BorderLayout.NORTH);

        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new GridLayout(3, 2));

        JLabel usernamelbl = new JLabel("Username:");
        JTextField usernamefld = new JTextField(15);
        pnlPrincipal.add(usernamelbl);
        pnlPrincipal.add(usernamefld);

        JLabel passwordlbl = new JLabel("Password:");
        JPasswordField passwordfld = new JPasswordField(15);
        pnlPrincipal.add(passwordlbl);
        pnlPrincipal.add(passwordfld);

        JLabel forgotPasswordlbl = new JLabel("<html><u>Forgot Password?</u></html>");
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

		        int option = JOptionPane.showOptionDialog(null, dialogPanel, "Type in your email and username",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				 
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
        JButton continuebtn = new JButton("Continue");
        continuebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	char[] passwordChar = passwordfld.getPassword();
                String password = new String(passwordChar);
                String email = usernamefld.getText();
//                if(autentificarUsuario(email, password)) {
//                	JOptionPane.showMessageDialog(null, "Welcome!");
//                	log.log(Level.INFO, "Successful login - Email: " + email);
//                	dispose();
//                }else {
//                	JOptionPane.showMessageDialog(null, "Incorrect email or password.");
//                	log.log(Level.WARNING, "Alert - Failled login for the email: " + email);
//                }
            	new Ventana_Juego();
            }
        });
        Botonpnl.add(continuebtn);
        

        JPanel Mensagepnl = new JPanel();
        JLabel noAccountlbl = new JLabel("<html><u>Don't have an account? Create one</u></html>");
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
    
   
    public static void main(String[] args) {
        Ventana_SignIn v = new Ventana_SignIn();
    }
}