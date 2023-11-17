package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.sound.sampled.*;

public class Ventana_Juego extends JFrame {

    // Tamaño de cada celda en el tablero del juego
    private static final int TAMANO_CELDA = 30;

    private Timer timer; // Temporizador para la caída de las piezas
    private Pieza piezaActual; // Pieza que el jugador controla
    private int[][] tablero; // Matriz
    private BufferedImage buffer; // Buffer para dibujar las piezas mejor
    private Clip clip; 
    private ImageIcon vidaIcono;

    public Ventana_Juego() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\pemma\\git\\Tetris\\Tetris\\src\\P1\\tetris.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        vidaIcono = new ImageIcon( getClass().getResource("hearts.png"));
       

        setVentanaPropiedades();
        iniciarJuego();

        clip.start(); // Comenzar a reproducir la música

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                teclaPresionada(evt);
            }
        });

        setFocusable(true);
    }

    private void setVentanaPropiedades() {
        setVisible(true);
        setTitle("Tetris");
        setSize(300, 600);
    }

    private void iniciarJuego() {
        piezaActual = new Pieza();
        tablero = new int[getHeight() / TAMANO_CELDA][getWidth() / TAMANO_CELDA];
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

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
    }

    private void teclaPresionada(KeyEvent evt) {
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                piezaActual.moverIzquierda();
                break;
            case KeyEvent.VK_RIGHT:
                piezaActual.moverDerecha();
                break;
            case KeyEvent.VK_DOWN:
                moverPiezaAbajo();
                break;
            case KeyEvent.VK_UP:
                piezaActual.rotar();
                break;
        }
        repaint();
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

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics bufferGraphics = buffer.getGraphics();
        bufferGraphics.clearRect(0, 0, getWidth(), getHeight());

        dibujarFondo(bufferGraphics);
        dibujarPiezasFijas(bufferGraphics);
        dibujarPiezaActual(bufferGraphics);

        
                                                     //Derecha o Izquierda,Arriba o Abajo,Tamaño
        bufferGraphics.drawImage(vidaIcono.getImage(), 20, 35, 4 * TAMANO_CELDA, 2 * TAMANO_CELDA, this);
        g.drawImage(buffer, 0, 0, this);
        
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

}
