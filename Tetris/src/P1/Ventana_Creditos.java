	package P1;
	
	import java.awt.*;
	import java.awt.event.*;
	
	import javax.swing.*;
	
	public class Ventana_Creditos extends JFrame{
		private JPanel pnlContenido;
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


	        //CREADORAS    	
	    	lblCreadoras = new JLabel ("CREATORS: ");
	    	lblCreadoras.setFont(new Font("Verdana", Font.BOLD, 20));
	    	lblCreadoras.setForeground(Color.BLACK);
	        lblCreadoras.setHorizontalAlignment(SwingConstants.CENTER);
	    	
	    	txtCreadoras = new JTextArea(
	                "Oihane Camacho\n" +
	                "Miren Lépée\n" +
	                "Naia Lorente \n\n"
	            );
	    	txtCreadoras.setFont(new Font("Verdana", Font.PLAIN, 16));
	    	txtCreadoras.setForeground(Color.BLACK);
	    	txtCreadoras.setLineWrap(true);
	    	txtCreadoras.setWrapStyleWord(true);
	    	txtCreadoras.setEditable(false);
	    	
	    	add(txtCreadoras, BorderLayout.CENTER);
	    	
	    	//DISEÑADORAS    	
	    	lblDiseñadoras = new JLabel ("DESIGNERS: ");
	    	lblDiseñadoras.setFont(new Font("Verdana", Font.BOLD, 20));
	    	lblDiseñadoras.setForeground(Color.BLACK);
	    	lblDiseñadoras.setHorizontalAlignment(SwingConstants.CENTER);
	    	
	    	txtDiseñadoras = new JTextArea(
	                "Oihane Camacho\n" +
	                "Miren Lépée\n" +
	                "Naia Lorente \n\n" +
	                "Diseñadoras: \n" +
	                "Oihane Camacho\n" +
	                "Miren Lépée\n" +
	                "Naia Lorente \n\n" +
	                "Versión: 1.0\n"
	            );
	    	// Personaliza la apariencia del JTextArea
	    	txtDiseñadoras.setFont(new Font("Verdana", Font.PLAIN, 16));
	    	txtDiseñadoras.setForeground(Color.BLACK);
	    	txtDiseñadoras.setLineWrap(true);
	    	txtDiseñadoras.setWrapStyleWord(true);
	    	txtDiseñadoras.setEditable(false);
	    	
	    	add(txtDiseñadoras, BorderLayout.CENTER);
	    	
	    	//BOTON
	    	btnVolver = new JButton("Volver");
	    	btnVolver.setFont(new Font("Verdana", Font.PLAIN, 16));
	    	
	    	btnVolver.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
	    	});
	    	
	    	pnlContenido.add(lblTitulo, BorderLayout.NORTH);
	    	pnlContenido.add(lblCreadoras, BorderLayout.CENTER);
	    	pnlContenido.add(txtCreadoras, BorderLayout.CENTER);
	    	pnlContenido.add(lblDiseñadoras, BorderLayout.CENTER);
	    	pnlContenido.add(txtDiseñadoras, BorderLayout.CENTER);
	    	pnlContenido.add(btnVolver, BorderLayout.SOUTH);
	        add(pnlContenido);
	    	setVisible(true);
	    }
	}
