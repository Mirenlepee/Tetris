package P1;
	
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	
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
        setSize(600, 400);
        setLocationRelativeTo(null);

        pnlContenido = new JPanel(new BorderLayout());
        pnlContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	       
        // TITULO
        lblTitulo = new JLabel("CREDITS");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        //pnlMedio = new JPanel(new BoxLayout(pnlMedio, BoxLayout.Y_AXIS));
        pnlMedio = new JPanel();
        pnlMedio.setLayout(new BoxLayout(pnlMedio, BoxLayout.Y_AXIS)); // Set a vertical layout

        //CREADORAS    	
    	lblCreadoras = new JLabel ("CREATORS: ");
    	lblCreadoras.setFont(new Font("Verdana", Font.BOLD, 18));
    	lblCreadoras.setForeground(Color.BLACK);
        //lblCreadoras.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	txtCreadoras = new JTextArea(
                "Oihane Camacho\n" +
                "Miren Lépée\n" +
                "Naia Lorente \n\n"
            );
    	txtCreadoras.setFont(new Font("Verdana", Font.PLAIN, 14));
    	txtCreadoras.setForeground(Color.BLACK);
    	txtCreadoras.setLineWrap(true);
	    txtCreadoras.setWrapStyleWord(true);
    	txtCreadoras.setEditable(false);
    	
	    	
    	//DISEÑADORAS    	
    	lblDiseñadoras = new JLabel ("DESIGNERS: ");
    	lblDiseñadoras.setFont(new Font("Verdana", Font.BOLD, 18));
    	lblDiseñadoras.setForeground(Color.BLACK);
    	lblDiseñadoras.setHorizontalAlignment(SwingConstants.CENTER);
    	
    	txtDiseñadoras = new JTextArea(
                "Oihane Camacho\n" +
                "Miren Lépée\n" +
                "Naia Lorente \n\n" 
            );
    	// Personaliza la apariencia del JTextArea
    	txtDiseñadoras.setFont(new Font("Verdana", Font.PLAIN, 14));
    	txtDiseñadoras.setForeground(Color.BLACK);
    	txtDiseñadoras.setLineWrap(true);
    	txtDiseñadoras.setWrapStyleWord(true);
    	txtDiseñadoras.setEditable(false);
    	
   	
    	//BOTON
    	btnVolver = new JButton("Volver");
    	btnVolver.setFont(new Font("Verdana", Font.PLAIN, 16));
    	
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
}