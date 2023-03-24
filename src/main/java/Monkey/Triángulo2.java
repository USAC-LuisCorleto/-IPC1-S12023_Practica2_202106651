package Monkey;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class Triángulo2 extends JPanel{
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int[] xPoints = {10, 30, 50}; // coordenadas x de los puntos del triángulo
        int[] yPoints = {50, 10, 50}; // coordenadas y de los puntos del triángulo

        g.setColor(Color.yellow); // color del triángulo
        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(450), 30, 30);
        g2d.fillPolygon(xPoints, yPoints, 3);
    }
}