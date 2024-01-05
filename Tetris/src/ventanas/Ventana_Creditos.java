package ventanas;
	
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

	

public class Ventana_Creditos extends JFrame {
    private JPanel pnlContenido;
    private JPanel pnlMedio;
    private static JLabel lblTitulo;
    private static JLabel lblCreadoras;
    private  JTextArea txtCreadoras;
    private static JLabel lblDiseñadoras;
    private JTextArea txtDiseñadoras;
    private static JTextArea txtInformacion;
    private JButton btnVolver;
    private static JTextArea txtInformacionRelevante;
    

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
                "Tetris is a project developed by a team of students as part of a programming course.\n"
                		+"We appreciate all those who contributed to the development of this game.\n"+
                		"Version: 1.0\n"
                
        );
        
        txtInformacion.setFont(new Font("Cambria", Font.PLAIN, 14));
        txtInformacion.setForeground(Color.BLACK);
        txtInformacion.setLineWrap(true);
        txtInformacion.setWrapStyleWord(true);
        txtInformacion.setEditable(false);
        
        
        
        txtInformacionRelevante = new JTextArea("Relevant information about the project:\n"+

"- Tetris was developed using Java and the Swing library for the graphical interface.\n"+
"- The team worked on the project for six weeks as part of a final project.\n"+
"- We thank the professors for their guidance and support during development.\n"+
"- Advanced features such as piece rotation and dynamic scoring were implemented.\n"+
"- The game was tested by a group of users to enhance the player experience.\n"+
"- The source code is available on GitHub for review and contributions.\n"
              
                      
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
        
        
        if(Ventana_Idioma.getIdiomaSeleccionado()!=null) {
        if(Ventana_Idioma.getIdiomaSeleccionado()=="Español") {
        	
        cambiarTextosEspañol();	
        }
    }}
    
    
    
    public static void cambiarTextosEspañol() {
    	
    	lblTitulo.setText("Créditos");
    	lblCreadoras.setText("Creadoras: ");
    	lblDiseñadoras.setText("Diseñadoras: ");
    	txtInformacion.setText("Tetris es un proyecto desarrollado por un equipo de estudiantes como parte de un curso de programación.\n"
                + "Agradecemos a todos los que contribuyeron al desarrollo de este juego.\n"
                + "Versión: 1.0\n");
    	
    	txtInformacionRelevante.setText(  "Información relevante sobre el proyecto:\n"
                + "- Tetris fue desarrollado utilizando Java y la biblioteca Swing para la interfaz gráfica.\n"
                + "- El equipo trabajó en el proyecto durante seis semanas como parte de un proyecto final.\n"
                + "- Agradecemos a los profesores por su orientación y apoyo durante el desarrollo.\n"
                + "- Se implementaron características avanzadas como rotación de piezas y puntuación dinámica.\n"
                + "- El juego fue probado por un grupo de usuarios para mejorar la experiencia del jugador.\n"
                + "- El código fuente está disponible en GitHub para su revisión y contribuciones.\n");
    	
    }

    public static void cambiarTextosEspañol() {
    	lblTitulo.setText("Créditos");
    	lblCreadoras.setText("Creadoras: ");
    	lblDiseñadoras.setText("Diseñadoras: ");
    	txtInformacion.setText("Tetris es un proyecto desarrollado por un equipo de estudiantes como parte de un curso de programación.\n"
                + "Agradecemos a todos los que contribuyeron al desarrollo de este juego.\n"
                + "Versión: 1.0\n");
    	txtInformacionRelevante.setText(  "Información relevante sobre el proyecto:\n"
                + "- Tetris fue desarrollado utilizando Java y la biblioteca Swing para la interfaz gráfica.\n"
                + "- El equipo trabajó en el proyecto durante seis semanas como parte de un proyecto final.\n"
                + "- Agradecemos a los profesores por su orientación y apoyo durante el desarrollo.\n"
                + "- Se implementaron características avanzadas como rotación de piezas y puntuación dinámica.\n"
                + "- El juego fue probado por un grupo de usuarios para mejorar la experiencia del jugador.\n"
                + "- El código fuente está disponible en GitHub para su revisión y contribuciones.\n");
    }
    
    public static void cambiarTextosIngles() {
    	lblTitulo.setText("Credits");
    	lblCreadoras.setText("Creators: ");
    	lblDiseñadoras.setText("Designers: ");
    	txtInformacion.setText("Tetris is a project developed by a team of students as part of a programming course.\n"
                + "We thank everyone who contributed to the development of this game.\n"
                + "Version: 1.0\n");
    	txtInformacionRelevante.setText(  "Relevant information about the project:\n"
                + "- Tetris was developed using Java and the Swing library for the graphical interface.\n"
                + "- The team worked on the project for six weeks as part of a final project.\n"
                + "- We thank the teachers for their guidance and support during development.\n"
                + "- Advanced features such as piece rotation and dynamic scoring have been implemented.\n"
                + "- The game was tested by a group of users to improve the player experience.\n"
                + "- The source code is available on GitHub for review and contributions.\n");
    }
    
    public static void cambiarTextosFrances() {
    	lblTitulo.setText("Crédits");
    	lblCreadoras.setText("Créateurs: ");
    	lblDiseñadoras.setText("Disegnadeurs: ");
    	txtInformacion.setText("Tetris est un projet développé par une équipe d'étudiants dans le cadre d'un cours de programmation.\n"
                + "Nous remercions tous ceux qui ont contribué au développement de ce jeu.\n"
                + "Version: 1.0\n");
    	txtInformacionRelevante.setText(  "Informations pertinentes sur le projet :\n"
                + "- Tetris a été développé en utilisant Java et la bibliothèque Swing pour l'interface graphique.\n"
                + "- L'équipe a travaillé sur le projet pendant six semaines dans le cadre d'un projet final.\n"
                + "- Nous remercions les enseignants pour leurs conseils et leur soutien pendant le développement.\n"
                + "- Des fonctionnalités avancées telles que la rotation des pièces et la notation dynamique ont été implémentées.\n"
                + "- Le jeu a été testé par un groupe d'utilisateurs pour améliorer l'expérience du joueur.\n"
                + "- Le code source est disponible sur GitHub pour révision et contributions.\n");
    }

    public static void cambiarTextosEuskara() {
    	lblTitulo.setText("Créditos");
    	lblCreadoras.setText("Creadoras: ");
    	lblDiseñadoras.setText("Diseñadoras: ");
    	txtInformacion.setText("Tetris es un proyecto desarrollado por un equipo de estudiantes como parte de un curso de programación.\n"
                + "Agradecemos a todos los que contribuyeron al desarrollo de este juego.\n"
                + "Versión: 1.0\n");
    	txtInformacionRelevante.setText(  "Proiektuari buruzko informazio garrantzitsua:\n"
                + "- Tetris Java eta Swing liburutegia erabiliz garatu da interfaze grafikorako.\n"
                + "- Taldeak sei astez lan egin zuen proiektuan azken proiektu baten barruan.\n"
                + "- Irakasleei eskerrak ematen dizkiegu garapenean zehar emandako gidaritza eta laguntzagatik.\n"
                + "- Piezen biraketa eta puntuazio dinamikoa bezalako funtzio aurreratuak ezarri dira.\n"
                + "- Jolasa erabiltzaile talde batek probatu du jokalariaren esperientzia hobetzeko.\n"
                + "- Iturburu-kodea GitHub-en eskuragarri dago berrikusteko eta ekarpenak egiteko.\n");
    }

    public static void cambiarTextosAleman() {
    	lblTitulo.setText("Credits");
    	lblCreadoras.setText("Ersteller: ");
    	lblDiseñadoras.setText("Designer: ");
    	txtInformacion.setText("Tetris ist ein Projekt, das von einem Studententeam im Rahmen eines Programmierkurses entwickelt wurde.\n"
    			+ "Wir danken allen, die zur Entwicklung dieses Spiels beigetragen haben.\n"
    			+ "Version: 1.0\n");
    	txtInformacionRelevante.setText(  "Relevante Informationen zum Projekt:\n"
                + "- Tetris wurde unter Verwendung von Java und der Swing-Bibliothek für die grafische Oberfläche entwickelt.\n"
                + "- Das Team arbeitete sechs Wochen lang im Rahmen eines Abschlussprojekts an dem Projekt.\n"
                + "- Wir danken den Lehrern für ihre Anleitung und Unterstützung während der Entwicklung.\n"
                + "- Erweiterte Funktionen wie Figurenrotation und dynamische Wertung wurden implementiert.\n"
                + "– Das Spiel wurde von einer Gruppe von Benutzern getestet, um das Spielerlebnis zu verbessern.\n"
                + "- Der Quellcode ist auf GitHub zur Überprüfung und Beiträgen verfügbar.\n");
    }



}