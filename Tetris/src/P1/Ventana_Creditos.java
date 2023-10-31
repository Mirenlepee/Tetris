package P1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Ventana_Creditos extends JFrame{
	private JLabel lblTitulo;
    private JLabel lblTexto1;
    private JLabel lblTexto2;
    private JLabel lblTexto3;
    private JButton btnVolver;

    public Ventana_Creditos () {
    	setTitle("Ventana de creditos");
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( 800, 600 );
		setLocationRelativeTo( null );
		setLayout(new BorderLayout());
    	lblTitulo = new JLabel("CREDITOS");
    	add(lblTitulo, BorderLayout.NORTH);
    	lblTexto1 = new JLabel (" Oihane Camacho ");
    	add(lblTexto1, BorderLayout.NORTH);
    	
    	lblTexto3 = new JLabel (" Naia Lorente ");
    	add(lblTexto3, BorderLayout.NORTH);
    	btnVolver = new JButton();
    	add(btnVolver, BorderLayout.NORTH);
    	
    	btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			}
    		
    	});
    }
    
    
}
