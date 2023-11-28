package juego;


import java.awt.Color;

public class PiezaTetris {

    static final int[][][] FORMAS = {
            //I
            {
                {1, 1, 1, 1}
            },
            //J
            {
                {1, 0, 0},
                {1, 1, 1}
            },
            //L
            {
                {0, 0, 1},
                {1, 1, 1}
            },
            //O
            {
                {1, 1},
                {1, 1}
            },
            //S
            {
                {0, 1, 1},
                {1, 1, 0}
            },
            //T
            {
                {0, 1, 0},
                {1, 1, 1}
            },
            //Z
            {
                {1, 1, 0},
                {0, 1, 1}
            }
    };

    // Obtener una forma aleatoria
    public static int[][] obtenerFormaAleatoria() {
        int ale=(int) (Math.random() * FORMAS.length);
    	return FORMAS[ale];
    }

    
    
}
