package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana_PerfilDeUsuario extends JFrame {
  
       public Ventana_PerfilDeUsuario() {
        this.setSize(600, 800);

        JPanel pnlPerfilDeUsuario = new JPanel();
        this.add(pnlPerfilDeUsuario, BorderLayout.CENTER);
        
        ImageIcon imgAvatar = new ImageIcon("Avatar.png");
        JLabel lblAvatar = new JLabel(imgAvatar);
        pnlPerfilDeUsuario.add(lblAvatar);
       
        JLabel lblNombre = new JLabel("User Name:");
        pnlPerfilDeUsuario.add(lblNombre);
        
        JTextField txtNombre = new JTextField(20);
        pnlPerfilDeUsuario.add(txtNombre);
        
        JLabel lblBiografia = new JLabel("Biografy:");
        pnlPerfilDeUsuario.add(lblBiografia);
        
        JTextArea txtBiografia = new JTextArea(5, 20);
        pnlPerfilDeUsuario.add(txtBiografia);
        
        JButton btnGuardar = new JButton("Continue");
        pnlPerfilDeUsuario.add(btnGuardar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CODIFICAR PARA GUARDAR EL NUEVO USUARIO
            	new Ventana_Juego();
            }
        });
        
        this.setVisible(true);
        
    }
}
