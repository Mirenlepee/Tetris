package P1;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.sound.sampled.*;

public class Ventana_Juego extends JFrame {

    //Tamaño de cada celda en el tablero del juego
	private static final int ANCHO_TABLERO = 10;
    private static final int ALTO_TABLERO = 20;
    private static final int TAMANO_CELDA = 30;
    
    private int puntos = 0;    
    
    private int[][] tablero;
    private Pieza piezaActual;
    private Timer timer;
    private Clip clip; 
    private ImageIcon vidaIcono;

    public Ventana_Juego() {

    	setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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


        JPanel panelPrincipal = new JPanel(new BorderLayout());

        PanelJuego panelJuego = new PanelJuego();
        panelJuego.setPreferredSize(new Dimension(ANCHO_TABLERO * TAMANO_CELDA, ALTO_TABLERO * TAMANO_CELDA));

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

        JLabel etiquetaPuntos = new JLabel("Puntos: " + puntos);
        etiquetaPuntos.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel etiquetaEspacio1 = new JLabel("    ");
        etiquetaEspacio1.setPreferredSize(new Dimension(100, 50));
        etiquetaEspacio1.setBorder(new LineBorder(Color.BLACK));

        JLabel etiquetaEspacio2 = new JLabel("    "); 
        etiquetaEspacio2.setPreferredSize(new Dimension(100, 50)); 
        etiquetaEspacio2.setBorder(new LineBorder(Color.BLACK));

        panelDerecho.add(Box.createVerticalGlue());
        panelDerecho.add(etiquetaPuntos);
        panelDerecho.add(Box.createVerticalStrut(10)); 
        panelDerecho.add(etiquetaEspacio1);
        panelDerecho.add(Box.createVerticalStrut(10)); 
        panelDerecho.add(etiquetaEspacio2);
        panelDerecho.add(Box.createVerticalGlue());

        panelPrincipal.add(panelJuego, BorderLayout.CENTER);
        panelPrincipal.add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);
        
        add(panelPrincipal);
	    pack();
        
	    tablero = new int[ALTO_TABLERO][ANCHO_TABLERO];
        piezaActual = new Pieza();
        
        vidaIcono = new ImageIcon(getClass().getResource("hearts.png"));      

        //setVentanaPropiedades();
        iniciarJuego();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverPiezaAbajo();
                repaint();
            }
        });
        timer.start();

        if (clip != null) {
            clip.start();
        }

        panelJuego.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent evt) {
                teclaPresionada(evt);
            }
        });

        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setVentanaPropiedades() {
        setVisible(true);
        setTitle("Tetris");
        setSize(300, 600);
    }

    private void iniciarJuego() {
        piezaActual = new Pieza();
        tablero = new int[getHeight() / TAMANO_CELDA][getWidth() / TAMANO_CELDA];
        //buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverPiezaAbajo();
                repaint();
            }
        });
        timer.start();
    }

    private void moverPiezaAbajo() {
        piezaActual.moverAbajo();
        if (verificarColision()) {
            fijarPiezaEnTablero();
            piezaActual = new Pieza();
        }
        if (verificarColision()) {
            fijarPiezaEnTablero();
            piezaActual = new Pieza();
        }
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
                    if (filaTablero >= getHeight() / TAMANO_CELDA || tablero[filaTablero][columnaTablero] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void fijarPiezaEnTablero() {
        int[][] forma = piezaActual.obtenerForma();
        int fila = piezaActual.obtenerFila();
        int columna = piezaActual.obtenerColumna();
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j] == 1) {
                    tablero[fila + i][columna + j] = 1;
                }
            }
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
                if (tablero[i][j] == 1) {
                    dibujarCelda(g, j * TAMANO_CELDA, i * TAMANO_CELDA, Color.BLUE);
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
                    dibujarCelda(g, columna * TAMANO_CELDA + j * TAMANO_CELDA, fila * TAMANO_CELDA + i * TAMANO_CELDA,
                            colorPieza);
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
}
