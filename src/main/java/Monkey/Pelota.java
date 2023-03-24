package Monkey;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pelota extends JPanel implements Runnable {

    private int x = 815;
    private int y = 300;
    private int dx = 1;
    private int dy = 1;
    private JLabel Label;

    public Pelota() {
        Thread hilo = new Thread(this);
        hilo.start();
        Label = new JLabel("Tiempo transcurrido:");
        Label.setBounds(380, 20, 180, 24);
        Label.setFont(new Font("Dialog", Font.PLAIN, 18));
        add(Label);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(260, 260, 260, 355);
        g.drawLine(160, 315, 160, 355);
        g.drawLine(160, 315, 75, 315);
        g.drawLine(340, 180, 561, 180);
        g.drawLine(650, 270, 650, 355);
        g.drawLine(750, 315, 750, 355);
        g.drawLine(750, 315, 825, 315);
        g.setColor(Color.red);
        g.fillOval(x, y, 30, 30);
    }

    @Override
    public void run() {
        while (true) {
            x -= dx;

            if (x == 735) {
                dx = 0;
                dy = 1;
                y += dy;
            }

            if (y == 345) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {

                }
            }
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {

            }

        }
    }

}
