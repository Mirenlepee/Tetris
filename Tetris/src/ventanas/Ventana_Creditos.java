package ventanas;
	
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ventanas.Ventana_Idioma.Idioma;
	

public class Ventana_Creditos extends JFrame {
    private JPanel pnlContenido;
    private JPanel pnlMedio;
    private JLabel lblTitulo;
    private JLabel lblCreadoras;
    private JTextArea txtCreadoras;
    private JLabel lblDiseñadoras;
    private JTextArea txtDiseñadoras;
    private JTextArea txtInformacion;
    private JButton btnVolver;
    private JTextArea txtInformacionRelevante;
    

    public Ventana_Creditos() {
        setTitle("Tetris - Credits");
        setSize(800, 600); 

        pnlContenido = new JPanel(new BorderLayout());
        pnlContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        lblTitulo = new JLabel("Credits");
        lblTitulo.setFont(new Font("Cambria", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLUE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        pnlMedio = new JPanel();
        pnlMedio.setLayout(new BoxLayout(pnlMedio, BoxLayout.Y_AXIS));

        // CREADORAS
        lblCreadoras = new JLabel("Creators: ");
        lblCreadoras.setFont(new Font("Cambria", Font.BOLD, 18));
        lblCreadoras.setForeground(Color.BLACK);

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

        // DISEÑADORAS
        lblDiseñadoras = new JLabel("Designers: ");
        lblDiseñadoras.setFont(new Font("Cambria", Font.BOLD, 18));
        lblDiseñadoras.setForeground(Color.BLACK);

        txtDiseñadoras = new JTextArea(
                "Oihane Camacho\n" +
                        "Miren Lépée\n" +
                        "Naia Lorente \n\n"
        );
        txtDiseñadoras.setFont(new Font("Cambria", Font.PLAIN, 14));
        txtDiseñadoras.setForeground(Color.BLACK);
        txtDiseñadoras.setLineWrap(true);
        txtDiseñadoras.setWrapStyleWord(true);
        txtDiseñadoras.setEditable(false);

        // INFORMACIÓN ADICIONAL
        txtInformacion = new JTextArea(
                "Tetris es un proyecto desarrollado por un equipo de estudiantes como parte de un curso de programación.\n"
                        + "Agradecemos a todos los que contribuyeron al desarrollo de este juego.\n"
                        + "Versión: 1.0\n"
        );
        txtInformacion.setFont(new Font("Cambria", Font.PLAIN, 14));
        txtInformacion.setForeground(Color.BLACK);
        txtInformacion.setLineWrap(true);
        txtInformacion.setWrapStyleWord(true);
        txtInformacion.setEditable(false);
        
        
        
        txtInformacionRelevante = new JTextArea(
                "Información relevante sobre el proyecto:\n"
                        + "- Tetris fue desarrollado utilizando Java y la biblioteca Swing para la interfaz gráfica.\n"
                        + "- El equipo trabajó en el proyecto durante seis semanas como parte de un proyecto final.\n"
                        + "- Agradecemos a los profesores por su orientación y apoyo durante el desarrollo.\n"
                        + "- Se implementaron características avanzadas como rotación de piezas y puntuación dinámica.\n"
                        + "- El juego fue probado por un grupo de usuarios para mejorar la experiencia del jugador.\n"
                        + "- El código fuente está disponible en GitHub para su revisión y contribuciones.\n"
                      
        );
        txtInformacionRelevante.setFont(new Font("Cambria", Font.PLAIN, 14));
        txtInformacionRelevante.setForeground(Color.BLACK);
        txtInformacionRelevante.setLineWrap(true);
        txtInformacionRelevante.setWrapStyleWord(true);
        txtInformacionRelevante.setEditable(false);

        btnVolver = new JButton("Return");
        btnVolver.setFont(new Font("Cambria", Font.PLAIN, 16));
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        JScrollPane scrollCreadoras = new JScrollPane(txtCreadoras);
        JScrollPane scrollDiseñadoras = new JScrollPane(txtDiseñadoras);
        JScrollPane scrollInformacion = new JScrollPane(txtInformacion);
        JScrollPane scrollInformacionRelevante = new JScrollPane(txtInformacionRelevante);

    
        pnlMedio.add(lblCreadoras);
        pnlMedio.add(scrollCreadoras);
        pnlMedio.add(lblDiseñadoras);
        pnlMedio.add(scrollDiseñadoras);
        pnlMedio.add(scrollInformacion);
        pnlMedio.add(scrollInformacionRelevante);

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