package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import ventanas.Ventana_Idioma.Idioma;

public class Ventana_Instrucciones extends JFrame{
	private JLabel lblTitle;
    private JTextArea TxtAreaInstructions;
    private JButton closeBtn;
    
    public Ventana_Instrucciones () {
    	setTitle("Tetris - Instructions");
        setSize(600, 400);
        
        //Title
        lblTitle = new JLabel("Instructions");
        lblTitle.setFont(new Font("Cambria", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);
       
        TxtAreaInstructions = new JTextArea();
        TxtAreaInstructions.setText("The aim in Tetris is simple; you bring down blocks from the top of the screen. \n "
        		+ "You can move the blocks around, either left to right and/or you can rotate them. \n "
        		+ "The blocks fall at a certain rate, but you can make them fall faster if you're sure of your positioning.\n"
        		  + "1. Use the left and right arrow keys to move the falling blocks horizontally.\n"
        		    + "2. Press the down arrow key to make the blocks fall faster.\n"
        		    + "3. Rotate the blocks using the up arrow key to fit them into empty spaces.\n"
        		    + "4. Complete a horizontal line with no gaps to make it disappear and earn points.\n"
        		    + "5. As the game progresses, the blocks fall faster, challenging your speed and agility.\n"
        		    + "6. The game ends if the blocks reach the top of the screen.\n\n"
        		    + "Challenge yourself and see how high you can score breaking your own records! Good luck!");
        
        TxtAreaInstructions.setEditable(false);
        TxtAreaInstructions.setForeground(Color.BLACK);
        add(TxtAreaInstructions, BorderLayout.CENTER);    
        
        JScrollPane scroll = new JScrollPane(TxtAreaInstructions);
        add(scroll, BorderLayout.CENTER);
        
        closeBtn = new JButton("Continue");
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

	public void actualizarIdioma(Ventana_Idioma.Idioma idioma) {
		lblTitle.setText(Ventana_Idioma.getTrad("Instructions",idioma));
		TxtAreaInstructions.setText(Ventana_Idioma.getTrad("The aim in Tetris is simple; you bring down blocks from the top of the screen. \\n \"\r\n"
				+ "        		+ \"You can move the blocks around, either left to right and/or you can rotate them. \\n \"\r\n"
				+ "        		+ \"The blocks fall at a certain rate, but you can make them fall faster if you're sure of your positioning.\\n\"\r\n"
				+ "        		  + \"1. Use the left and right arrow keys to move the falling blocks horizontally.\\n\"\r\n"
				+ "        		    + \"2. Press the down arrow key to make the blocks fall faster.\\n\"\r\n"
				+ "        		    + \"3. Rotate the blocks using the up arrow key to fit them into empty spaces.\\n\"\r\n"
				+ "        		    + \"4. Complete a horizontal line with no gaps to make it disappear and earn points.\\n\"\r\n"
				+ "        		    + \"5. As the game progresses, the blocks fall faster, challenging your speed and agility.\\n\"\r\n"
				+ "        		    + \"6. The game ends if the blocks reach the top of the screen.\\n\\n\"\r\n"
				+ "        		    + \"Challenge yourself and see how high you can score breaking your own records! Good luck!",idioma));
		closeBtn.setText(Ventana_Idioma.getTrad("Continue",idioma));
	}
}