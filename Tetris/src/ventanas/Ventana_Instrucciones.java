package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ventana_Instrucciones extends JFrame{
	private static JLabel lblTitle;
    private static JTextArea TxtAreaInstructions;
    private static JButton closeBtn;
    
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
        if(Ventana_Idioma.getIdiomaSeleccionado()!=null) {
            if(Ventana_Idioma.getIdiomaSeleccionado()=="Español") {
            	
            cambiarTextosEspañol();	
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Français") {
            	
            	cambiarTextosFrances();
            	
            }
        }
    }
    private void cambiarTextosFrances() {
		// TODO Auto-generated method stub
    	
    	 lblTitle.setText("Instructions");
    	    TxtAreaInstructions.setText("L'objectif dans Tetris est simple : vous faites descendre des blocs depuis le haut de l'écran.\n"+
    	        "Vous pouvez déplacer les blocs, soit de gauche à droite et/ou les faire pivoter.\n"+
    	        "Les blocs tombent à une vitesse déterminée, mais vous pouvez les faire tomber plus rapidement si vous êtes sûr de votre position.\n\n"+
    	        "1. Utilisez les touches de flèche gauche et droite pour déplacer les blocs qui tombent horizontalement.\n"+
    	        "2. Appuyez sur la touche de flèche vers le bas pour faire tomber les blocs plus rapidement.\n"+
    	        "3. Faites pivoter les blocs en utilisant la touche de flèche vers le haut pour les ajuster dans les espaces vides.\n"+
    	        "4. Complétez une ligne horizontale sans lacunes pour la faire disparaître et gagnez des points.\n"+
    	        "5. Au fur et à mesure que le jeu progresse, les blocs tombent plus rapidement, mettant votre vitesse et votre agilité à l'épreuve.\n"+
    	        "6. Le jeu se termine si les blocs atteignent le haut de l'écran.\n\n"+
    	        "Défiez-vous et voyez jusqu'où vous pouvez marquer en battant vos propres records ! Bonne chance !");
    	    
    	    closeBtn.setText("Continuer");
    	
    	
    	
    	
		
	}
	public static void cambiarTextosEspañol() {
		
    	
		lblTitle.setText("Instrucciones");
		TxtAreaInstructions.setText("El objetivo en Tetris es simple: haces descender bloques desde la parte superior de la pantalla.\n"+
		"Puedes mover los bloques, ya sea de izquierda a derecha y/o puedes rotarlos.\n"+
				"Los bloques caen a una velocidad determinada, pero puedes hacer que caigan más rápido si estás seguro de tu posición.\n\n"+
		"1. Utiliza las teclas de flecha izquierda y derecha para mover los bloques que caen horizontalmente.\n"+
				"2.Presiona la tecla de flecha hacia abajo para hacer que los bloques caigan más rápido.\n"+
		"3.Rota los bloques usando la tecla de flecha hacia arriba para encajarlos en espacios vacíos.\n"+
				"4.Completa una línea horizontal sin brechas para que desaparezca y ganes puntos.\n"+
		"5.A medida que avanza el juego, los bloques caen más rápido, desafiando tu velocidad y agilidad.\n"+
				"6.El juego termina si los bloques llegan a la parte superior de la pantalla.\n\n"+
		"¡Desafíate a ti mismo y mira qué tan alto puedes puntuar rompiendo tus propios récords! ¡Buena suerte!");
		
	closeBtn.setText("Continuar");	
		
	    }
    

}