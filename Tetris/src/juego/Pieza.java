package juego;

import java.awt.Color;

public class Pieza {
    private int[][] forma;
    private int fila;
    private int columna;
    private Color color; 

    public Pieza() {
        //Apartir de las formas de la clase Pieza coger una forma aleatoria
    	forma = PiezaTetris.obtenerFormaAleatoria();
        
    	
        fila = 0; 
        //Para centrar la pieza en el tablero
        columna =(10- forma[0].length)/2;
        //Coger el color que le corresponda a la pieza que haya tocado
        color = asignarColorPorForma(forma);
    
    }
    public Color obtenerColor() {
        return color;
    }

    private Color asignarColorPorForma(int[][] forma) {
       
        if (forma == PiezaTetris.FORMAS[0]) { //Para I
            return Color.CYAN;
        } else if (forma == PiezaTetris.FORMAS[1]) { //J
            return Color.BLUE;
        } else if (forma == PiezaTetris.FORMAS[2]) { //L
            return Color.ORANGE;
        } else if (forma == PiezaTetris.FORMAS[3]) { //O
            return Color.YELLOW;
        } else if (forma == PiezaTetris.FORMAS[4]) { //S
            return Color.GREEN;
        } else if (forma == PiezaTetris.FORMAS[5]) { //T
            return Color.MAGENTA;
        } else { //Z
            return Color.RED;
        }
    }

    //Obtener la forma de la pieza
    public int[][] obtenerForma() {//Matriz
        return forma;
    }

    //Obtener la fila actual
    public int obtenerFila() {
        return fila;
    }

    //Obtener la columna actual
    public int obtenerColumna() {
        return columna;
    }

    //Mover la pieza hacia abajo,pieza hacia abajo en el eje vertical
    public void moverAbajo() {
        fila++;
    }

    public void moverIzquierda() {
        columna--;
    }

    public void moverDerecha() {
    	columna++;
    }
    public void moverArriba() {
        fila--;
    }
    
	public void rotar() {

       //Con la fila y columna se cogen las dimensiones de la pieza que esta cayendo
        int nuevaFila = forma.length;
        int nuevaColumna = forma[0].length;
        
        //matriz que almacena la forma después de la rotación (forma que es original)
        int[][] nuevaForma= new int[nuevaColumna][nuevaFila];
        //Se itinera sobre la forma original
        for (int i= 0; i< nuevaFila; i++) {
            for (int j= 0; j < nuevaColumna; j++) {
                nuevaForma[j][nuevaFila - 1 - i] = forma[i][j];
            }
        }
      //Actualiza la forma de la pieza con la nueva forma rotada
        forma = nuevaForma;
    }
	
}
