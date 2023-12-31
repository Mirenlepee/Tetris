package juego;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DibujodeEstrella {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Ejemplo estrella");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);

            JPanel starPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    
                    int randomX = (int) (Math.random() * getWidth());
                    int randomY = (int) (Math.random() * getHeight());
                    drawStar(g, randomX, randomY, 20, 10);
                }
            };

            frame.getContentPane().add(starPanel);
            frame.setVisible(true);
        });
    }

    public static void drawStar(Graphics g, int x, int y, int size, int spikes) {
        int[] xPoints = new int[spikes * 2];
        int[] yPoints = new int[spikes * 2];

        double angle = 0;
        double delta = Math.PI / spikes;

        for (int i = 0; i < spikes * 2; i++) {
            double radius = (i % 2 == 0) ? size : size / 2;
            xPoints[i] = x + (int) (Math.cos(angle) * radius);
            yPoints[i] = y + (int) (Math.sin(angle) * radius);
            angle += delta;
        }

        g.setColor(Color.YELLOW);
        g.fillPolygon(xPoints, yPoints, spikes * 2);
    }
}
