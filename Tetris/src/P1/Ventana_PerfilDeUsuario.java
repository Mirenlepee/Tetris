package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_PerfilDeUsuario extends JFrame {
  
       public Ventana_PerfilDeUsuario() {
        JFrame ventana = new JFrame("Perfil de Usuario");
        ventana.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ventana.setSize(400, 300);

        JPanel panel = new JPanel();
        ventana.setVisible(true);
       

        
        JLabel lblNombre = new JLabel("Nombre de Usuario:");
        JTextField txtNombre = new JTextField(20);
        JLabel lblBiografia = new JLabel("Biografía:");
        JTextArea txtBiografia = new JTextArea(5, 20);
        JButton btnGuardar = new JButton("Continuar");

       
      

        ventana.add(panel);

       
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CODIFICAR PARA GUARDAR EL NUEVO USUARIO
            }
        });

        
        

        
    }
}
