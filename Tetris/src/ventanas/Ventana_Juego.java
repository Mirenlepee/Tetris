package ventanas;

import javax.swing.*;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Map;

import javax.swing.border.LineBorder;

import BD.GestionBDUsuario;
import gestionUsuarios.Usuario;
import juego.Celda;
import juego.Pieza;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.geom.GeneralPath;

import javax.sound.sampled.*;

public class Ventana_Juego extends JFrame {

	private static final int ANCHO_TABLERO = 10;
    private static final int ALTO_TABLERO = 20;
    private static final int TAMANO_CELDA = 30;
    private boolean gameOver = false;
    private Pieza siguientePieza;
    private JButton btnPausa;
        
    public static JLabel lblNivel;
    private boolean corazon=false;

    private int nivelActual = 1;
    private int velocidadDeCaida=300;
    protected int minutos = 0;
    protected int segundos = 0;
    private boolean gameOverDisplayed = false;
    protected Timer timerContador;

    private JPanel PanelEspacio1;
    protected JPanel PanelEspacio2;
    protected static int puntos = 0;    
    private List<Pieza> piezasEnTablero = new ArrayList<>();

    public static JLabel etiquetaPuntos;
    private Celda[][] tablero;

    private Pieza piezaActual;
    protected Timer timer;

    protected boolean musica = true;
    protected  JLabel etiquetaTiempo;
    protected Clip clip;
    protected int vidas=3;
    protected Ventana_GameOver ventGo;
    protected PanelJuego panelJuego;
    private Map<LocalDate, Integer> puntosDiarios = new HashMap<>();
    private Map<LocalDate, Integer> partidasDiarias = new HashMap<>();
    private LocalDate ultimaFechaJuego;
    
    protected boolean pres;

    public Ventana_Juego() {  
    	
    	setTitle("Tetris");
    	try {
             File audioFile = new File("Tetris/src/tetris.wav");
             
             if (audioFile.exists()) {
                 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
                 clip = AudioSystem.getClip();
                 clip.open(audioInputStream);
             } else {
                 System.out.println("Audio file not found!");
             }

         } catch (Exception e) {
             e.printStackTrace();
         }

    	pres = Ventana_Options.obtenerPresionado();
        if(!pres) {
         	clip.start();
        }    	   	
    	
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
     	
     	timerContador = new Timer(1000, new ActionListener() {
     	    @Override
     	    public void actionPerformed(ActionEvent e) {
     	        segundos++;
     	        if (segundos == 60) {
     	            minutos++;
     	            segundos = 0;
     	        }
     	        actualizarEtiquetaTiempo();
     	    }
     	});
     	

     	timerContador.start();
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        panelJuego = new PanelJuego();
        panelJuego.setPreferredSize(new Dimension(ANCHO_TABLERO * TAMANO_CELDA, ALTO_TABLERO * TAMANO_CELDA));

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

        etiquetaPuntos = new JLabel("Score: " + puntos);
        
        etiquetaTiempo = new JLabel("00:00");
        panelDerecho.add(Box.createVerticalStrut(10)); 
        panelDerecho.add(etiquetaTiempo);
        

        PanelEspacio1 = new JPanel();
        PanelEspacio1.setPreferredSize(new Dimension(130, 50));
        PanelEspacio1.setBorder(new LineBorder(Color.WHITE));

        PanelEspacio2 = new CorazonPanel(); 
        PanelEspacio2.setPreferredSize(new Dimension(100, 50));
        PanelEspacio2.setBorder(new LineBorder(Color.WHITE));

		ImageIcon imgSettings = new ImageIcon(getClass().getResource("pausa.png"));
		btnPausa = new JButton();
		btnPausa.setBackground(Color.WHITE);

		int nuevoAncho = 30;
		int nuevoAlto = 30;
		Image imagenDef = imgSettings.getImage().getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
		ImageIcon iconoDef = new ImageIcon(imagenDef);

		btnPausa.setIcon(iconoDef);
		lblNivel = new JLabel("Level: 1");
        
        panelDerecho.add(Box.createVerticalGlue());
        panelDerecho.add(etiquetaPuntos);
        panelDerecho.add(lblNivel);
        panelDerecho.add(Box.createVerticalStrut(10)); 
        panelDerecho.add(PanelEspacio1);
        panelDerecho.add(Box.createVerticalStrut(10)); 
        panelDerecho.add(PanelEspacio2);
        panelDerecho.add(Box.createVerticalGlue());
        panelDerecho.add(btnPausa);
        
        btnPausa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	clip.stop();
            	timer.stop();
            	timerContador.stop();
            	Ventana_Pausa ventP = new Ventana_Pausa(Ventana_Juego.this);
            	
            	if(ventP.isPresionado()==true) {
            		clip.stop();
            		
            		System.out.println("para el clip");
            	}else {
            		           
            	}          	
            }
        });
        
        panelPrincipal.add(panelJuego, BorderLayout.CENTER);
        panelPrincipal.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);
        
        add(panelPrincipal);
	    pack();
        
	    tablero = new Celda[ALTO_TABLERO][ANCHO_TABLERO];
	    for (int i = 0; i < ALTO_TABLERO; i++) {
	        for (int j = 0; j < ANCHO_TABLERO; j++) {
	            tablero[i][j] = new Celda();
	        }
	    }

        piezaActual = new Pieza();

        iniciarJuego();

        timer = new Timer(velocidadDeCaida, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                   moverPiezaAbajo();
                   repaint();
              }
           }
        });
        timer.start();
      
        
        
        panelJuego.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent evt) {
                teclaPresionada(evt);
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
        if(Ventana_Idioma.getIdiomaSeleccionado()!=null) {
            if(Ventana_Idioma.getIdiomaSeleccionado()=="Español") {
            	
            cambiarTextosEspañol();	
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Français") {
            	cambiarTextosFrances();
            	
            }else if(Ventana_Idioma.getIdiomaSeleccionado()=="Deutsch") {
            	cambiarTextosAleman();
            	
            }
        }

    }
    

	private void actualizarEtiquetaTiempo() {
        String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
        etiquetaTiempo.setText(tiempoFormateado);
    }

    private void iniciarJuego() {
    	int rondasActuales = GestionBDUsuario.obtenerRoundsPlayed(obtenerUsuarioActual().getEmail());
    	rondasActuales++;
    	GestionBDUsuario.actualizarRoundsPlayed(obtenerUsuarioActual().getEmail(), rondasActuales);
        piezaActual = new Pieza();
        siguientePieza = new Pieza();
        actualizarPanelEspacio1();
       
         tablero = new Celda[ALTO_TABLERO][ANCHO_TABLERO];
        for (int i = 0; i < ALTO_TABLERO; i++) {
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                tablero[i][j] = new Celda();
            }
        }

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverPiezaAbajo();
                repaint();
            }
        });
    }

    private void actualizarPanelEspacio1() {
        PanelEspacio1.removeAll();
        PiezaPanel piezaPanel = new PiezaPanel(siguientePieza);
        PanelEspacio1.add(piezaPanel);
        PanelEspacio1.revalidate();
        PanelEspacio1.repaint();
    }


    private void moverPiezaAbajo() {
        piezaActual.moverAbajo();
        if (verificarColision()) {
        	 
            fijarPiezaEnTablero();
            piezaActual = siguientePieza; 
           
            siguientePieza = new Pieza();  
            actualizarPanelEspacio1();      
            if (verificarGameOver() && vidas > 0) {
                gameOver = true;
                timer.stop();
                mostrarMessageCorazon();
                //insertarEstadisticasBD();
            }else if(verificarGameOver() && vidas == 0) {
            	gameOver = true;
                timer.stop();
            	mostrarGameOver();
            }
        }
    }
    
    private boolean verificarGameOver() {
        int[][] forma = piezaActual.obtenerForma();
        int fila = piezaActual.obtenerFila();
        int columna = piezaActual.obtenerColumna();

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
               
                if (forma[i][j] == 1 && tablero[fila + i][columna + j].isOcupada()) {
                    return true;
                }
            }
        }
        
        if (vidas == 0) {
            return true;
        }

        return false;
    }

    private void mostrarGameOver() {
        
        if (!gameOverDisplayed) {
            gameOverDisplayed = true;
            timerContador.stop();
            
            // Insertar estadísticas en la base de datos al mostrar el Game Over 
            //insertarEstadisticasBD();

            ventGo = new Ventana_GameOver(this);
            
            
            StringTokenizer tokenizer = new StringTokenizer(etiquetaPuntos.getText(), ":");
            tokenizer.nextToken();
            String numerosComoTexto = tokenizer.nextToken().trim();
            
            
            ventGo.tfScore.setText(numerosComoTexto);

           clip.stop();
            if (!ventGo.isVisible()) {
            	ventGo.setResizable(false);
                ventGo.tfScore.setText(String.valueOf(puntos));
                ventGo.tfTimePlayed.setText(getTiempoJugado());
                ventGo.setVisible(true);
            }
        }
    }
    
    public void actualizarCampos(Ventana_GameOver vent) {
    	vent.tfScore.setText(String.valueOf(this.getPuntuacion()));
    	vent.tfBestScore.setText(String.valueOf(this.getPuntuacion()));
	    vent.tfTimePlayed.setText(this.getTiempoJugado());
    }
    
    private void mostrarMessageCorazon() {
        vidas--;
        corazon=true;
        if (vidas > 0) {
            Object[] options = {"Continue"};

            int choice = JOptionPane.showOptionDialog(
                    this,
                    "You lost a life! Lives remaining: " + vidas,
                    "Oh No!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (choice == JOptionPane.OK_OPTION) {
                reiniciarJuego();
                ((CorazonPanel) PanelEspacio2).restarVida();
            }
        } else {
            gameOver = true;
            timer.stop();
            mostrarGameOver();
            corazon=false;
        }
    }


    protected void volverAlMenu() {
        dispose();
        Ventana_Principal m=new Ventana_Principal();
        clip.stop();
        m.setVisible(true);
    }

    protected void reiniciarJuego() {
       if(!corazon) {
    	   puntos=0;
       }
       actualizarEtiquetaPuntos();
        piezasEnTablero.clear();
        tablero  = new Celda[ALTO_TABLERO][ANCHO_TABLERO];
	    for (int i = 0; i < ALTO_TABLERO; i++) {
	        for (int j = 0; j < ANCHO_TABLERO; j++) {
	            tablero[i][j] = new Celda();
	        }
	    }
        piezaActual = new Pieza();
        gameOver = false;
        gameOverDisplayed = false;
       
     // Reiniciar la música
        clip.setFramePosition(0);
        clip.start();
        
        timer.start();
        repaint();
    }
    
    private void bajarPiezaRapidamente() {
        int originalFila = piezaActual.obtenerFila();
        int[][] originalForma = piezaActual.obtenerForma();

        while (!verificarColision()) {
            if (originalFila + originalForma.length < ALTO_TABLERO) {
                piezaActual.moverAbajo();
            } else {
                break;
            }
        }

        fijarPiezaEnTablero();
        piezaActual = siguientePieza;
        siguientePieza = new Pieza();
        actualizarPanelEspacio1();

        if (verificarGameOver() && vidas > 0) {
            gameOver = true;
            timer.stop();
            mostrarMessageCorazon();
            //insertarEstadisticasBD();
        } else if (verificarGameOver() && vidas == 0) {
            gameOver = true;
            timer.stop();
            mostrarGameOver();
        }
        repaint();
    }
    
    private void teclaPresionada(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (!verificarColisionLateral(-1)) { 
                    piezaActual.moverIzquierda();
                    repaint();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!verificarColisionLateral(1)) {
                    piezaActual.moverDerecha();
                    repaint();
                }
                break;
            case KeyEvent.VK_DOWN:
                moverPiezaAbajo();
                break;
            case KeyEvent.VK_UP:
                piezaActual.rotar();
                repaint();
                break;
            case KeyEvent.VK_SPACE:
                bajarPiezaRapidamente();
                break;
            case KeyEvent.VK_ENTER:
        
                piezaActual.cambiarTipoDePieza();
                
                break;       
        }
    }
   
    private boolean verificarColisionLateral(int k) {
        int[][] forma = piezaActual.obtenerForma();
        int columna = piezaActual.obtenerColumna();
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j] == 1) {
                    int columnaTablero = columna + j + k;
                    if (columnaTablero < 0 || columnaTablero >= ANCHO_TABLERO) {
                        return true; 
                    }
                }
            }
        }
        return false;
    }

    private boolean verificarColision() {
        int[][] forma = piezaActual.obtenerForma();
        int fila = piezaActual.obtenerFila();
        int columna = piezaActual.obtenerColumna();

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j] == 1) {
                    int filaTablero = fila + i + 1;
                    int columnaTablero = columna + j;
                    if (filaTablero >= ALTO_TABLERO || tablero[filaTablero][columnaTablero].isOcupada()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int getPuntuacion() {
		return puntos;
	}   

	public String getTiempoJugado() {
	    return etiquetaTiempo.getText();
	}

	public int getNivel() {
        return nivelActual;
    }
	
	 public void insertarEstadisticasBD() {
		 String textoPuntos = etiquetaPuntos.getText();
		// Elimina la parte no numérica, dejando solo los números
		String numerosComoTexto = textoPuntos.replaceAll("\\D", "");
		// Convierte la cadena de números a un entero
		int puntosEntero = Integer.parseInt(numerosComoTexto);
		 String tiempoJugado = getTiempoJugado();
		 int tiempoEnSegundos = convertirTiempoASegundos(tiempoJugado);
		 GestionBDUsuario.actualizarTiempoTotalJugado(obtenerUsuarioActual().getEmail(), tiempoEnSegundos);
		 GestionBDUsuario.actualizarMaxPoints(obtenerUsuarioActual().getEmail(), puntosEntero);
	     GestionBDUsuario.actualizarMinPoints(obtenerUsuarioActual().getEmail(), puntosEntero);
	     GestionBDUsuario.actualizarTotalPoints(obtenerUsuarioActual().getEmail(), puntosEntero);
	     LocalDate fechaActual = LocalDate.now();

	        if (ultimaFechaJuego == null || !ultimaFechaJuego.equals(fechaActual)) {
	            // Es un nuevo día, reinicia los contadores diarios
	            partidasDiarias.put(fechaActual, 1);
	            puntosDiarios.put(fechaActual, puntosEntero);
	        } else {
	            // Mismo día, incrementa el contador de partidas y suma los puntos
	            int partidasHoy = partidasDiarias.getOrDefault(fechaActual, 0);
	            partidasDiarias.put(fechaActual, partidasHoy + 1);

	            int puntosHoy = puntosDiarios.getOrDefault(fechaActual, 0);
	            puntosDiarios.put(fechaActual, puntosHoy + puntosEntero);
	        }

	        ultimaFechaJuego = fechaActual;

	        // Calcula la media y actualiza dailyAveragePoints en la base de datos
	        int partidasTotales = partidasDiarias.values().stream().mapToInt(Integer::intValue).sum();
	        int puntosTotales = puntosDiarios.values().stream().mapToInt(Integer::intValue).sum();

	        int mediaPuntosDiarios = (partidasTotales > 0) ? puntosTotales / partidasTotales : 0;

	        GestionBDUsuario.actualizarDailyAveragePoints(obtenerUsuarioActual().getEmail(), mediaPuntosDiarios);
	 }
	 
	 private int convertirTiempoASegundos(String tiempo) {
	        String[] partesTiempo = tiempo.split(":");
	        int minutos = Integer.parseInt(partesTiempo[0]);
	        int segundos = Integer.parseInt(partesTiempo[1]);

	        // Convierte minutos y segundos a segundos
	        return minutos * 60 + segundos;
	    }
	 
	 private Usuario obtenerUsuarioActual() {
	        Usuario usuarioSignIn = Ventana_SignIn.getUsuarioActual();

	        // Si el usuario de SignIn es nulo, obtener el usuario de SignUp
	        if (usuarioSignIn == null) {
	        	Usuario usuarioSingUp = Ventana_PerfilDeUsuario.getUsuarioActual();
	            return usuarioSingUp;
	        }

	        return usuarioSignIn;
	    }
	private void fijarPiezaEnTablero() {
	    int[][] forma = piezaActual.obtenerForma();
	    int fila = piezaActual.obtenerFila();
	    int columna = piezaActual.obtenerColumna();

	    for (int i = 0; i < forma.length; i++) {
	        for (int j = 0; j < forma[i].length; j++) {
	            if (forma[i][j] == 1) {
	                tablero[fila + i][columna + j].setOcupada(true);
	                tablero[fila + i][columna + j].setColor(piezaActual.obtenerColor());
	            }
	        }
	    }

	    for (int i = ALTO_TABLERO - 1; i >= 0; i--) {
	        boolean filaCompleta = true;
	        for (int j = 0; j < ANCHO_TABLERO; j++) {
	            if (!tablero[i][j].isOcupada()) {
	                filaCompleta = false;
	                break;
	            }
	        }

	        if (filaCompleta) {
	            for (int k = i; k > 0; k--) {
	                for (int l = 0; l < ANCHO_TABLERO; l++) {
	                    tablero[k][l].setOcupada(tablero[k - 1][l].isOcupada());
	                    tablero[k][l].setColor(tablero[k - 1][l].getColor());
	                }
	            }
	            for (int l = 0; l < ANCHO_TABLERO; l++) {
	                tablero[0][l].setOcupada(false);
	                tablero[0][l].setColor(null); 
	            }
	            puntos += 100;
	            actualizarEtiquetaPuntos();
	        }
	    }

	    piezasEnTablero.add(piezaActual);
	    piezaActual = new Pieza();
	}

    private void actualizarEtiquetaPuntos() {
        etiquetaPuntos.setText("Puntos: " + puntos);
        if(puntos==200) {
        	velocidadDeCaida=250;
            timer.setDelay(velocidadDeCaida);
      
            lblNivel.setText("Level: 2");
        }else if(puntos==1000) {
        	velocidadDeCaida=200;
            timer.setDelay(velocidadDeCaida);
            lblNivel.setText("Level: 3");


        }
    }

    private void dibujarFondo(Graphics g) {
        for (int i = 0; i < getHeight() / TAMANO_CELDA; i++) {
            for (int j = 0; j < getWidth() / TAMANO_CELDA; j++) {
                g.setColor((i + j) % 2 == 0 ? Color.GRAY : Color.DARK_GRAY);
                g.fillRect(j * TAMANO_CELDA, i * TAMANO_CELDA, TAMANO_CELDA, TAMANO_CELDA);
                
            
            }
        }
    }
    
  
    private void dibujarPiezasFijas(Graphics g) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j].isOcupada()) {
                    Color colorPieza = tablero[i][j].getColor(); 
                    dibujarCelda(g, j * TAMANO_CELDA, i * TAMANO_CELDA, colorPieza);
                }
            }
        }
    }

    private void dibujarPiezaActual(Graphics g) {
        int[][] forma = piezaActual.obtenerForma();
        int fila = piezaActual.obtenerFila();
        int columna = piezaActual.obtenerColumna();
        Color colorPieza = piezaActual.obtenerColor();

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j] == 1) {
                   
                    dibujarCelda(g, columna * TAMANO_CELDA + j * TAMANO_CELDA, fila * TAMANO_CELDA + i * TAMANO_CELDA, colorPieza);

                }
            }
        }
    }
   
    private void dibujarCelda(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, TAMANO_CELDA, TAMANO_CELDA);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, TAMANO_CELDA, TAMANO_CELDA);
    }
  
    class PanelJuego extends JPanel {

    	  public PanelJuego() {
              setPreferredSize(new Dimension(ANCHO_TABLERO * TAMANO_CELDA, ALTO_TABLERO * TAMANO_CELDA));
              setBackground(Color.BLACK);
              setFocusable(true);
        }

        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);
            dibujarFondo(g);
            dibujarPiezasFijas(g);
            dibujarPiezaActual(g);
        }
    }
   
    class PiezaPanel extends JPanel {
        private Pieza pieza;

        public PiezaPanel(Pieza pieza) {
            this.pieza = pieza;
            setPreferredSize(new Dimension(TAMANO_CELDA * pieza.obtenerForma()[0].length, TAMANO_CELDA * pieza.obtenerForma().length));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int[][] forma = pieza.obtenerForma();
        
            Color colorPieza = pieza.obtenerColor();

            for (int i = 0; i < forma.length; i++) {
                for (int j = 0; j < forma[i].length; j++) {
                    if (forma[i][j] == 1) {
                        dibujarCelda(g, j * TAMANO_CELDA, i * TAMANO_CELDA, colorPieza);
                    }
                }
            }
        }
    }


    public class CorazonPanel extends JPanel {
        protected int vidasMostradas;

        public CorazonPanel() {
            vidasMostradas = vidas;
        }

        public void restarVida() {
            vidasMostradas--;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int heartWidth = panelWidth / 4;
            int heartHeight = panelHeight / 5;

            int totalWidth = vidasMostradas * heartWidth;
            int separation = panelWidth / 13;
            int initialX = (panelWidth - totalWidth - (vidasMostradas - 1) * separation) / 2;
            int initialY = (panelHeight - heartHeight) / 2;

            for (int i = 0; i < vidasMostradas; i++) {
                drawHeart(g2d, initialX + i * (heartWidth + separation), initialY, heartWidth, heartHeight);
            }
        }

        private void drawHeart(Graphics2D g2d, int x, int y, int width, int height) {
            GeneralPath heart = new GeneralPath();


            heart.moveTo(x + width / 2, y + height / 4);
            heart.curveTo(x + width / 2, y, x, y, x, y + height / 4);
            heart.quadTo(x, y + height / 2, x + width / 2, y + height);
            heart.quadTo(x + width, y + height / 2, x + width, y + height / 4);
            heart.curveTo(x + width, y, x + width / 2, y, x + width / 2, y + height / 4);

            g2d.setColor(Color.RED);
            g2d.fill(heart);
        }
    }
    
    public static void cambiarTextosEspañol() {
    	etiquetaPuntos.setText("Puntos: " + puntos);
    	lblNivel.setText("Nivel: 1");
    }
    
    public static void cambiarTextosIngles() {
    	etiquetaPuntos.setText("Points: " + puntos);
    	lblNivel.setText("Level: 1");
    }
    
    public static void cambiarTextosFrances() {
    	etiquetaPuntos.setText("Points : " + puntos);
        lblNivel.setText("Niveau : 1");
	}
    
    public static void cambiarTextosEuskara() {
    	etiquetaPuntos.setText("Puntuak: " + puntos);
    	lblNivel.setText("Maila: 1");
    }

    public static void cambiarTextosAleman() {
		etiquetaPuntos.setText("Punkte : " + puntos);
		lblNivel.setText("Niveau : 1");
	}
   
}