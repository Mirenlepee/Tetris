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
    private JTextArea TxtAreaInstructions;
    private JButton btnClose;
    
    public Ventana_Instrucciones () {
    	setTitle("Tetris - Instructions");
        setSize(600, 400);
        
        //Title
        lblTitle = new JLabel("Instructions");
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        //Contenido
        TxtAreaInstructions = new JTextArea("");
        TxtAreaInstructions.setText("The aim in Tetris is simple; you bring down blocks from the top of the screen. \n "
        		+ "You can move the blocks around, either left to right and/or you can rotate them. \n "
        		+ "The blocks fall at a certain rate, but you can make them fall faster if you're sure of your positioning.");
        TxtAreaInstructions.setEnabled(false);
        add(TxtAreaInstructions, BorderLayout.CENTER);    
        
        JScrollPane scroll = new JScrollPane(TxtAreaInstructions);
        add(scroll);
        
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


