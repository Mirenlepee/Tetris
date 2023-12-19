package juego;

import java.awt.Color;
import java.awt.*;

import javax.swing.*;

public class DibujodeEstrella {
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            JFrame frame = new JFrame("Star Block Example");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setSize(300, 300);

	            JPanel starPanel = new JPanel() {
	                @Override
	                protected void paintComponent(Graphics g) {
	                    super.paintComponent(g);
	                    drawStar(g, getWidth() / 2, getHeight() / 2, 20, 10);
	                }
	            };

	            frame.getContentPane().add(starPanel);
	            frame.setVisible(true);
	        });
	    }

	    private static void drawStar(Graphics g, int x, int y, int size, int spikes) {
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
