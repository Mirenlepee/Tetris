package P1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ventana_Instrucciones extends JFrame{
	private JLabel lblTitle;
    private JTextArea TextAreaInstructions;
    private JButton btnClose;
    
    public Ventana_Instrucciones () {
    	setTitle("Tetris - Credits");
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        //Title
        lblTitle = new JLabel("INSTRUCTIONS");
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        //Contenido
        
        //Boton
        JButton closeBtn = new JButton("Continue");
        closeBtn.setFont(new Font("Cambria", Font.BOLD, 16));
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(closeBtn, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    public static void main(String[] args) {
    	Ventana_Instrucciones i = new Ventana_Instrucciones();
    }
}


