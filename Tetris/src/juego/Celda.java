package juego;

import java.awt.Color;

public class Celda {
    private boolean ocupada;
    private Color color;

    public Celda() {
        ocupada = false;
        color = Color.BLACK; // Set a default color
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
