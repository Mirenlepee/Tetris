package ventanas;
	
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ventanas.Ventana_Idioma.Idioma;
	
public class Ventana_Creditos extends JFrame{
	private JPanel pnlContenido;
	private JPanel pnlMedio;
	private JLabel lblTitulo;
	private JLabel lblCreadoras;
    private JTextArea txtCreadoras;
	private JLabel lblDiseñadoras;
    private JTextArea txtDiseñadoras;
    private JButton btnVolver;
	
    public Ventana_Creditos () {
    	setTitle("Tetris - Credits");
        setSize(600, 250);

        pnlContenido = new JPanel(new BorderLayout());
        pnlContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	       
        // TITULO
        lblTitulo = new JLabel("Credits");
        lblTitulo.setFont(new Font("Cambria", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        //pnlMedio = new JPanel(new BoxLayout(pnlMedio, BoxLayout.Y_AXIS));
        pnlMedio = new JPanel();
        pnlMedio.setLayout(new BoxLayout(pnlMedio, BoxLayout.Y_AXIS)); // Set a vertical layout

        //CREADORAS    	
    	lblCreadoras = new JLabel ("Creators: ");
    	lblCreadoras.setFont(new Font("Cambria", Font.BOLD, 18));
    	lblCreadoras.setForeground(Color.BLACK);
    	lblCreadoras.setAlignmentX(Component.CENTER_ALIGNMENT);

    	
    	txtCreadoras = new JTextArea(
                "Oihane Camacho\n" +
                "Miren Lépée\n" +
                "Naia Lorente \n\n"
            );
    	txtCreadoras.setFont(new Font("Cambria", Font.PLAIN, 14));
    	txtCreadoras.setForeground(Color.BLACK);
    	txtCreadoras.setLineWrap(true);
	    txtCreadoras.setWrapStyleWord(true);
    	txtCreadoras.setEditable(false);
    	
	    	
    	//DISEÑADORAS    	
    	lblDiseñadoras = new JLabel ("Designers: ");
    	lblDiseñadoras.setFont(new Font("Cambria", Font.BOLD, 18));
    	lblDiseñadoras.setForeground(Color.BLACK);
    	lblDiseñadoras.setHorizontalAlignment(SwingConstants.CENTER);

    	lblDiseñadoras.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	txtDiseñadoras = new JTextArea(
                "Oihane Camacho\n" +
                "Miren Lépée\n" +
                "Naia Lorente \n\n" 
            );
    	// Personaliza la apariencia del JTextArea
    	txtDiseñadoras.setFont(new Font("Cambria", Font.PLAIN, 14));
    	txtDiseñadoras.setForeground(Color.BLACK);
    	txtDiseñadoras.setLineWrap(true);
    	txtDiseñadoras.setWrapStyleWord(true);
    	txtDiseñadoras.setEditable(false);
    	
   	
    	//BOTON
    	btnVolver = new JButton("Return");
    	btnVolver.setFont(new Font("Cambria", Font.PLAIN, 16));
    	btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	});
    	
    	pnlMedio.add(lblCreadoras);
    	pnlMedio.add(txtCreadoras);
    	pnlMedio.add(lblDiseñadoras);
    	pnlMedio.add(txtDiseñadoras);
    	
    	pnlContenido.add(lblTitulo, BorderLayout.NORTH);
    	pnlContenido.add(pnlMedio, BorderLayout.CENTER);
    	pnlContenido.add(btnVolver, BorderLayout.SOUTH);
        add(pnlContenido);
    	setVisible(true);
    }

	public void actualizarIdioma(Idioma idiomaActual) {
		String[][] traducciones = Ventana_Idioma.traducirTodasLasPalabras(idiomaActual);
		lblTitulo.setText(traducciones[19][0]);
		lblCreadoras.setText(traducciones[20][0]);
		txtCreadoras.setText(traducciones[21][0]);
		lblDiseñadoras.setText(traducciones[22][0]);
		txtDiseñadoras.setText(traducciones[23][0]);
		btnVolver.setText(traducciones[24][0]);
	}
}