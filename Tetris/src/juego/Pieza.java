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
        // Comparar la forma con las formas predefinidas para asignar el color
        for (int i = 0; i < PiezaTetris.FORMAS.length; i++) {
            if (sonMatricesIguales(forma, PiezaTetris.FORMAS[i])) {
                return obtenerColorCorrespondiente(i);
            }
        }
        // Si no se encuentra ninguna coincidencia, devolver un color predeterminado
        return Color.GRAY; // Puedes cambiar esto al color que prefieras
    }

    private boolean sonMatricesIguales(int[][] matrizA, int[][] matrizB) {
        if (matrizA.length != matrizB.length || matrizA[0].length != matrizB[0].length) {
            return false;
        }
        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizA[0].length; j++) {
                if (matrizA[i][j] != matrizB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private Color obtenerColorCorrespondiente(int indice) {
        switch (indice) {
            case 0:
                return Color.CYAN;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.ORANGE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.MAGENTA;
            case 6:
                return Color.RED;
            default:
                return Color.GRAY; // Color predeterminado si no se encuentra ninguna coincidencia
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
	public void cambiarTipoDePieza() {
	    forma = PiezaTetris.obtenerFormaAleatoria();

	    fila = 0;
	    columna = (10 - forma[0].length) / 2;
	    color = asignarColorPorForma(forma);
	}
}
