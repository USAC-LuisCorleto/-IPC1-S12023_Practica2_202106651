package Monkey;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Pelota extends JPanel implements Runnable {

    private int x = 815;
    private int y = 300;
    private int dx = 1;
    private int dy = 1;
    public JLabel labelTiempoTranscurrido;
    public JLabel labelTiempo;
    public JLabel labelActualEnInventario;
    public JButton botónRegresar;
    public JButton botónReportes;

    public Pelota() {

        /*Tamaño del panel*/
        setPreferredSize(new Dimension(900, 600));

        /*Quitando el Layout para el libre manejo de los objetos*/
        setLayout(null);

        /*Hilo para la pelota*/
        Thread hilo = new Thread(this);
        hilo.start();

        /*Botones de regresar y reportes*/
 /*Botón de regresar*/
        botónRegresar = new JButton("Regresar");
        botónRegresar.setBounds(56, 20, 150, 50);
        botónRegresar.setFont(new Font("Dialog", Font.BOLD, 18));
        /*Botón de reportes*/
        botónReportes = new JButton("Reportes");
        botónReportes.setBounds(680, 20, 150, 50);
        botónReportes.setFont(new Font("Dialog", Font.BOLD, 18));

        /*Labels*/
 /*Label tiempo transcurrido*/
        labelTiempoTranscurrido = new JLabel("Tiempo transcurrido:");
        labelTiempoTranscurrido.setBounds(375, 20, 180, 24);
        labelTiempoTranscurrido.setFont(new Font("Dialog", Font.PLAIN, 18));
        /*Label que imprime el tiempo*/
        labelTiempo = new JLabel();
        labelTiempo.setBounds(440, 44, 90, 24);
        labelTiempo.setFont(new Font("Dialog", Font.PLAIN, 14));
        /*Label que indica la cantidad que hay actualmente en el proceso "Inventario"*/
        labelActualEnInventario = new JLabel("Inventario:");
        labelActualEnInventario.setBounds(566, 360, 80, 20);
        labelActualEnInventario.setFont(new Font("Dialog", Font.PLAIN, 14));

        /*Rectángunlos para los procesos*/
 /*Inicio del hilo para el tiempo transcurrido*/
        HiloTiempo hiloTiempo = new HiloTiempo(labelTiempo);
        hiloTiempo.start();

        /*Triángulos*/
 /*Triángulo de la derecha*/
        Triángulo1 triánguloPanel = new Triángulo1();
        triánguloPanel.setBounds(840, 283, 65, 75);
        /*Triángulo de la izquierda*/
        Triángulo2 triánguloPanel2 = new Triángulo2();
        triánguloPanel2.setBounds(-10, 283, 65, 75);

        /*Paneles para los distintos tipos de producción*/
 /*Panel Inventario*/
        JPanel inventarioPanel = new JPanel();
        inventarioPanel.setBounds(561, 355, 269, 155);
        inventarioPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inventarioPanel.setBackground(new Color(156, 212, 244));

        /*Panel Producción*/
        JPanel producciónPanel = new JPanel();
        producciónPanel.setBounds(561, 115, 269, 155);
        producciónPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        producciónPanel.setBackground(new Color(228, 252, 228));

        /*Panel Empaquetado*/
        JPanel empaquetadoPanel = new JPanel();
        empaquetadoPanel.setBounds(70, 115, 269, 155);
        empaquetadoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        empaquetadoPanel.setBackground(new Color(244, 220, 252));

        /*Panel Salida*/
        JPanel salidaPanel = new JPanel();
        salidaPanel.setBounds(70, 355, 269, 155);
        salidaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        salidaPanel.setBackground(new Color(252, 220, 220));

        /*Agregando los objetos el JPanel*/
        add(botónRegresar);
        add(botónReportes);
        add(labelTiempoTranscurrido);
        add(labelTiempo);
        add(labelActualEnInventario);
        add(triánguloPanel);
        add(triánguloPanel2);
        add(inventarioPanel);
        add(producciónPanel);
        add(empaquetadoPanel);
        add(salidaPanel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        /*Quebrada del final*/
        g.setColor(Color.BLACK);
        g.drawLine(260, 270, 260, 355);
        g.drawLine(160, 315, 160, 355);

        /*Línea vertical que conecta empaquetado con salida*/
        g.drawLine(160, 315, 75, 315);

        /*Línea horizonatl que conecta producción con empaquetado*/
        g.drawLine(339, 180, 561, 180);

        /*Línea vertical que conecta el inventario con producción*/
        g.drawLine(650, 270, 650, 355);

        /*Quebrada del inicio*/
        g.drawLine(750, 315, 750, 355);
        g.drawLine(750, 315, 825, 315);

        /*Círculo que se movilizará por el panel*/
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
