package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_SignUp extends JFrame{
    public Ventana_SignUp() {
     
        JFrame ventana = new JFrame("SIGN UP");
        ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventana.setSize(400, 300);
        ventana.setVisible(true);

        JPanel panel = new JPanel();
        ventana.add(panel);
        panel.setLayout(new BorderLayout());

      
 
   

        JLabel lblTitulo = new JLabel("Sign Up");
        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField(20);
        JLabel lblPassword = new JLabel("Contraseña:");
        JTextField txtPassword = new JTextField(20);
        JLabel lblConfirmPassword = new JLabel("Confirmar Contraseña:");
        JTextField txtConfirmPassword = new JTextField(20);
        JLabel lblEmail = new JLabel("Correo Electrónico:");
        JTextField txtEmail = new JTextField(20);

        JButton btnAtras = new JButton("Atrás");
        JButton btnContinuar = new JButton("Continuar");
        JButton btnSalir = new JButton("Salir");

    
  
        
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // CODIFICAR PARA IR A LA VENTANA ANTERIOR
            }
        });
      

        btnContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // CODIFICAR PARA IR A LA SIGUIENTE VENTANA
            }
        });


        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CODIFICAR PARA IR A LA VENTANA DE CONFIRMACIÓN DE SALIR DEL JUEGO
            }
        });

      
    }
}
