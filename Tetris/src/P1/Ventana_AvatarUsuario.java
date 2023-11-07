package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Ventana_AvatarUsuario extends JFrame {

    private JButton btnAvatar;

    public Ventana_AvatarUsuario() {
        this.setSize(200, 200);
        this.setTitle("Seleccionar Avatar");
        this.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        
        JButton btnSeleccionarFoto = new JButton("Seleccionar Foto");
        btnSeleccionarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon selectedImage = new ImageIcon(selectedFile.getPath());
                    int ancho = 150;
                    int alto = 150;
                    Image scaledImage = selectedImage.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                    ImageIcon newIcon = new ImageIcon(scaledImage);
                    btnAvatar.setIcon(newIcon);
                }
            }
        });

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(btnSeleccionarFoto);
        panel.add(btnAceptar);

        this.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void setAvatarButton(JButton btnAvatar) {
        this.btnAvatar = btnAvatar;
    }
}
