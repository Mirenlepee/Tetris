package P1;

import javax.swing.*;
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
        JTextField passwordfld = new JTextField(20);
        pnlPrincipal.add(passwordlbl);
        pnlPrincipal.add(passwordfld);


        JLabel confirmPasswordlbl = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordfld = new JPasswordField(20);
        pnlPrincipal.add(confirmPasswordlbl);
        pnlPrincipal.add(confirmPasswordfld);

      
        JLabel emaillbl = new JLabel("Email:");
        JTextField emailfld = new JTextField(20);
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
                
                System.out.println("Ir a la ventana de Perfil de Usuario.");
                new Ventana_PerfilDeUsuario();
            }
        });
        buttonPanel.add(continuebtn);

        ventana.add(buttonPanel, BorderLayout.SOUTH);

        ventana.setVisible(true);
    }

   
}
