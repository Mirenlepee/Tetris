package P1;




import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Ventana_Juego extends JFrame {

    //Tamaño de cada celda en el tablero del juego
    private static final int TAMANO_CELDA = 30;

    
    private Timer timer;//Temporizador para la caida de las piezas
    private Pieza piezaActual;//Pieza que el jugador  controla
    private int[][] tablero;//Matriz
    private BufferedImage buffer;//buffer para dibujar las piezas mejor
 
    
    
    public Ventana_Juego() {
    	setVisible(true);
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 600);

        piezaActual = new Pieza();
        tablero = new int[getHeight()/ TAMANO_CELDA][getWidth() / TAMANO_CELDA];
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);


        timer = new Timer(1000, (ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverPiezaAbajo();
                repaint();
            }
        });
        timer.start();


        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                teclaPresionada(evt);
            }
        });

        setFocusable(true);
    }

   
    private void moverPiezaAbajo() {
        piezaActual.moverAbajo();
        if (verificarColision()) {//Mirar si la pieza a tocado otra pieza o el suelo
            fijarPiezaEnTablero();
            piezaActual = new Pieza();
        }
    }


    private void teclaPresionada(java.awt.event.KeyEvent evt) {
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
        int[][] forma= piezaActual.obtenerForma(); //forma de la pieza que se esta manejando
        int fila= piezaActual.obtenerFila();
        int columna= piezaActual.obtenerColumna();

        for (int i=0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j]== 1) {
                    int filaTablero= fila + i + 1;
                    int columnaTablero= columna + j;
                    //si hay colisión
                    if (filaTablero >= getHeight() / TAMANO_CELDA || tablero[filaTablero][columnaTablero] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Fijar la pieza si hay colisión
    private void fijarPiezaEnTablero() {
        int[][] forma = piezaActual.obtenerForma();
        int fila = piezaActual.obtenerFila();
        int columna = piezaActual.obtenerColumna();

        for (int i = 0;i< forma.length; i++) {
            for (int j = 0; j< forma[i].length; j++) {
                if (forma[i][j] == 1) {
                    tablero[fila + i][columna + j] = 1;
                }
            }
        }
    }

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Gráficos para el búfer de imagen
        Graphics bufferGraphics = buffer.getGraphics();
        bufferGraphics.clearRect(0, 0, getWidth(), getHeight());

        //Dibujar el fondo, las piezas fijas y la pieza actual en el búfer
        dibujarFondo(bufferGraphics);
        dibujarPiezasFijas(bufferGraphics);
        dibujarPiezaActual(bufferGraphics);

        //Dibujar el búfer en la ventana
        g.drawImage(buffer, 0, 0, this);
    }

    //dibujar el fondo del tablero con cuadrados
    private void dibujarFondo(Graphics g) {
        for (int i = 0; i < getHeight() / TAMANO_CELDA; i++) {
            for (int j = 0; j < getWidth() / TAMANO_CELDA; j++) {
                g.setColor((i + j) % 2 == 0 ? Color.GRAY : Color.DARK_GRAY);
                g.fillRect(j * TAMANO_CELDA, i * TAMANO_CELDA, TAMANO_CELDA, TAMANO_CELDA);
            }
        }
    }

    //Piezas fijas dibujar
    private void dibujarPiezasFijas(Graphics g) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 1) {
                    dibujarCelda(g, j * TAMANO_CELDA, i * TAMANO_CELDA, Color.BLUE);
                }
            }
        }
    }

    //Pieza actual dibujo
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

    //dibujar una celda individual borde negro
    private void dibujarCelda(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, TAMANO_CELDA, TAMANO_CELDA);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, TAMANO_CELDA, TAMANO_CELDA);
    }

    
}
