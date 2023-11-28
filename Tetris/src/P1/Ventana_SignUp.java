package P1;

import javax.mail.Message;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.mail.PasswordAuthentication;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_SignUp {
	
    public Ventana_SignUp() {
        JFrame ventana = new JFrame("Sign Up");
        ventana.setSize(400, 200);
        ventana.setLayout(new BorderLayout());

        JLabel signUplbl = new JLabel("Sign Up");
        signUplbl.setFont(new Font("Cambria", Font.BOLD, 24));
        
        JPanel pnllbl=new JPanel();
        pnllbl.add(signUplbl,BorderLayout.CENTER);
        

        ventana.add(pnllbl, BorderLayout.NORTH);


        JPanel pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new GridLayout(4, 2));


        JLabel usernamelbl = new JLabel("Username:");
        JTextField usernamefld = new JTextField(20);
        pnlPrincipal.add(usernamelbl);
        pnlPrincipal.add(usernamefld);

        
        JLabel passwordlbl = new JLabel("Password:");
        JPasswordField passwordfld = new JPasswordField(20);
        pnlPrincipal.add(passwordlbl);
        pnlPrincipal.add(passwordfld);


        JLabel confirmPasswordlbl = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordfld = new JPasswordField(20);
        pnlPrincipal.add(confirmPasswordlbl);
        pnlPrincipal.add(confirmPasswordfld);

      
        JLabel emaillbl = new JLabel("Email:");
        JTextField emailfld = new JTextField("@gmail.com");
        pnlPrincipal.add(emaillbl);
        pnlPrincipal.add(emailfld);

        ventana.add(pnlPrincipal, BorderLayout.CENTER);

    
        JPanel buttonPanel = new JPanel();


        JButton backbtn = new JButton("Back");
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             ventana.dispose();
             new Ventana_SignIn();
            }
        });
        buttonPanel.add(backbtn);

        JButton exitbtn= new JButton("Quit");
        exitbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });
        buttonPanel.add(exitbtn);

        JButton continuebtn = new JButton("Continue");
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
		            Pattern patternPassword = Pattern.compile("");
	                char[] password = passwordfld.getPassword();
	                char[] confirmed = confirmPasswordfld.getPassword();
	                String email = emailfld.getText();
	                if(username.equals("") || email.equals("") || password.length == 0 || confirmed.length == 0) {
	                	JOptionPane.showMessageDialog(null, "To register, you must fill all the information requested.");
	                	return;
	                }
	                if(!Arrays.equals(password, confirmed)) {
	                	JOptionPane.showMessageDialog(null, "The passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
	                	return;
	                }
	                String pass = new String(password);
	                Matcher matcher = patternPassword.matcher(pass);
//	                if(!matcher.matches()) {
//	                	StringBuilder errorMessage = new StringBuilder("The password does not meet the requirements:\n");
//	                	if(!matcher.matches()) {
//	                		if(!passwordMeetsRequirements("[A-Z]", pass)) {
//	                			errorMessage.append("- It must contain at least one uppercase letter.\n");
//	                		}
//	                		errorMessage.append("- It must have at least 6 characters.\n");
//	                		
//	                		JOptionPane.showMessageDialog(null, errorMessage.toString(), "Error", JOptionPane.ERROR_MESSAGE);
//	                		return;
//	                	}
//	                }else {
//	                	String hashPassword = BCrypt.hashpw(pass, BCrypt.gensalt());
//	                	Usuario u = new Usuario(username, hashPassword, email, "", "");
//	                	if(!usuariosRegistrados.containsKey(email)) {
//	                		registrarUsuario(u);
//	                		JOptionPane.showMessageDialog(null, "You have successfully been registered");
//	                		ventana.log.log(Level.INFO, "New user registerd - Username: " + username + ", email: " + email);
//	                	}else {
//	                		JOptionPane.showMessageDialog(null, "The email is already in use. Please choose a different one.");
//	                	}
//	                }
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

   
}
