package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ventana_SignIn extends JFrame {
    private JFrame ventana; // Declarar el marco como miembro de la clase.

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
        JTextField emailRecuperaPass = new JTextField();
        forgotPasswordlbl.addMouseListener( new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				 int option = JOptionPane.showOptionDialog(null,emailRecuperaPass, "Ingresa tu email", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, null, null);
				 if (option == JOptionPane.OK_OPTION) {
			            String textoIngresado = emailRecuperaPass.getText();       
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
                System.out.println("Continuar al juego.");
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
        //ventana.add(noAccountlbl, BorderLayout.PAGE_END);
        Mensagepnl.add(noAccountlbl);
        
        pnlSouth.add(Botonpnl);
        pnlSouth.add(noAccountlbl);

        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        Ventana_SignIn v = new Ventana_SignIn();
    }
}
