package Monkey;

import static Monkey.MenúPrincipal.costoEmp;
import static Monkey.MenúPrincipal.costoInv;
import static Monkey.MenúPrincipal.costoProd;
import static Monkey.MenúPrincipal.costoSal;
import static Monkey.MenúPrincipal.tiempoEmp;
import static Monkey.MenúPrincipal.tiempoInv;
import static Monkey.MenúPrincipal.tiempoProd;
import static Monkey.MenúPrincipal.tiempoSal;
import static Monkey.MenúPrincipal.ventana;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Animación extends JPanel implements Runnable {

    public static int x = 815;
    public static int y = 300;
    public static int dx = 1;
    public static int dy = 1;
    public JLabel labelTiempoTranscurrido;
    public JLabel labelTiempo;
    public JLabel labelActualEnInventario;
    public JLabel labelActualEnProducción;
    public JLabel labelActualEnEmpaquetado;
    public JLabel labelActualEnSalida;
    public JButton botónRegresar;
    public JButton botónReportes;
    public HiloTiempo hiloTiempo;
    public HiloInventario hiloInventario;
    public boolean empezado = true;

    public Animación() {
        Thread hilo = new Thread(this);
        hilo.start();
        /*Tamaño del panel*/
        setPreferredSize(new Dimension(900, 600));

        /*Quitando el Layout para el libre manejo de los objetos*/
        setLayout(null);

        /*Botones de regresar y reportes*/
 /*Botón de regresar*/
        botónRegresar = new JButton("Regresar");
        botónRegresar.setBounds(56, 20, 150, 50);
        botónRegresar.setFont(new Font("Dialog", Font.BOLD, 18));
        botónRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenúPrincipal menu = new MenúPrincipal();
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                ventana.dispose();
            }
        });

        /*Botón de reportes*/
        botónReportes = new JButton("Reportes");
        botónReportes.setBounds(680, 20, 150, 50);
        botónReportes.setFont(new Font("Dialog", Font.BOLD, 18));
        botónReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ex) {
                String nombreArchivo = "ReporteMONKEY-" + new Date().getTime() + ".html";
                int totalInventario = costoInv * tiempoInv * 30;
                int totalProducción = costoProd * tiempoProd * 30;
                int totalEmpaquetado = costoEmp * tiempoEmp * 30;
                int totalSalida = costoSal * tiempoSal * 30;
                String contenido = "<html>\n"
                        + "<head>\n"
                        + "<title>Reporte MONKEY</title>\n"
                        + "<style>\n"
                        + "table {\n"
                        + "  border-collapse: collapse;\n"
                        + "  width: 60%;\n"
                        + "  margin: auto;\n"
                        + "  border: 2px solid black;\n"
                        + "}\n"
                        + "th, td {\n"
                        + "  text-align: center;\n"
                        + "  padding: 8px;\n"
                        + "  border: 2px solid black;\n"
                        + "}\n"
                        + "th {\n"
                        + "  background-color: #AF794C;\n"
                        + "  color: black;\n"
                        + "}\n"
                        + "</style>\n"
                        + "<style>\n"
                        + "body, h1, h2, h3, h4, h5, h6 {\n"
                        + "  font-size: 16px;\n"
                        + "  font-family: Arial, sans-serif;\n"
                        + "}\n"
                        + ".text-box {\n"
                        + "  border: 4px solid black;\n"
                        + "  padding: 10px;\n"
                        + "}\n"
                        + "</style>\n"
                        + "</head>\n"
                        + "<body style=\"background-color:#E3B086;\">\n"
                        + "<br>"
                        + "<div style=\"text-align:center; font-size:42px; font-weight:bold;\">MONKEY</div>\n"
                        + "<br>"
                        + "<table>\n"
                        + "<thead>\n"
                        + "<tr>\n"
                        + "<th>Total Inventario</th>\n"
                        + "<th>Total Producción</th>\n"
                        + "<th>Total Empaquetado</th>\n"
                        + "<th>Total Salida</th>\n"
                        + "</tr>\n"
                        + "</thead>\n"
                        + "<tbody>\n"
                        + "<tr>\n"
                        + "<td>" + totalInventario + "</td>\n"
                        + "<td>" + totalProducción + " </td>\n"
                        + "<td>" + totalEmpaquetado + "</td>\n"
                        + "<td>" + totalSalida + "</td>\n"
                        + "</tr>\n"
                        + "</tbody>\n"
                        + "</table>\n"
                        + "</body>\n"
                        + "</html>";
                try {
                    FileWriter archivo = new FileWriter(nombreArchivo);
                    archivo.write(contenido);
                    archivo.close();
                } catch (IOException e) {
                    System.out.println("Ha ocurrido un error al generar el archivo HTML: " + e.getMessage());
                }
                JOptionPane.showMessageDialog(null, "Reporte descargado", "Proceso exitoso", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        /*Labels*/
 /*Label tiempo transcurrido*/
        labelTiempoTranscurrido = new JLabel("Tiempo transcurrido:");
        labelTiempoTranscurrido.setBounds(375, 20, 180, 24);
        labelTiempoTranscurrido.setFont(new Font("Dialog", Font.PLAIN, 18));
        /*Label que imprime el tiempo*/
        labelTiempo = new JLabel();
        labelTiempo.setBounds(440, 44, 90, 24);
        labelTiempo.setFont(new Font("Dialog", Font.PLAIN, 14));
        /*Inicio del hilo para el tiempo transcurrido*/
        hiloTiempo = new HiloTiempo(labelTiempo);
        hiloTiempo.start();
        /*Label que indica la cantidad que hay actualmente en el proceso "Inventario"*/
        labelActualEnInventario = new JLabel("Inventario:");
        labelActualEnInventario.setBounds(566, 360, 80, 20);
        labelActualEnInventario.setFont(new Font("Dialog", Font.PLAIN, 14));
        JLabel labelEnInventario = new JLabel();
        labelEnInventario.setBounds(636, 360, 20, 20);
        labelEnInventario.setFont(new Font("Dialog", Font.PLAIN, 14));
        hiloInventario = new HiloInventario(labelEnInventario);
        hiloInventario.start();
        /*Label que indica la cantidad que hay actualmente en el proceso "Producción"*/
        labelActualEnProducción = new JLabel("Producción:");
        labelActualEnProducción.setBounds(566, 120, 100, 20);
        labelActualEnProducción.setFont(new Font("Dialog", Font.PLAIN, 14));
        /*Label que indica la cantidad que hay actualmente en el proceso "Empaquetado"*/
        labelActualEnEmpaquetado = new JLabel("Empaquetado:");
        labelActualEnEmpaquetado.setBounds(75, 120, 120, 20);
        labelActualEnEmpaquetado.setFont(new Font("Dialog", Font.PLAIN, 14));
        /*Label que indica la cantidad que hay actualmente en el proceso "Salida"*/
        labelActualEnSalida = new JLabel("Salida:");
        labelActualEnSalida.setBounds(75, 360, 80, 20);
        labelActualEnSalida.setFont(new Font("Dialog", Font.PLAIN, 14));

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
        add(labelEnInventario);
        add(labelActualEnInventario);
        add(labelActualEnProducción);
        add(labelActualEnEmpaquetado);
        add(labelActualEnSalida);
        add(triánguloPanel);
        add(triánguloPanel2);
        add(inventarioPanel);
        add(producciónPanel);
        add(empaquetadoPanel);
        add(salidaPanel);
        /*Hilo para la pelota*/
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

        g.setColor(Color.WHITE);

        if (x == 571 && y == 385) {
            g.setColor(new Color(20, 116, 228));
        }
        if (x == 635) {
            g.setColor(new Color(20, 116, 228));
        }
        if (x == 571 && y == 145) {
            g.setColor(new Color(84, 196, 92));
        }
        if (y == 165) {
            g.setColor(new Color(84, 196, 92));
        }
        if (x == 80 && y == 145) {
            g.setColor(new Color(228, 140, 252));
        }
        if (x == 245) {
            g.setColor(new Color(228, 140, 252));
        }
        if (x == 80 && y == 385) {
            g.setColor(new Color(252, 140, 140));
        }
        if (x == 145) {
            g.setColor(new Color(252, 140, 140));
        }
        if (x <= 145 && x >= 0 && y >= 300 && y <= 310) {
            g.setColor(new Color(252, 140, 140));
        }
        g.fillOval(x, y, 30, 30);
    }

    @Override
    public void run() {
        while (true) {
            /*Trayectoria desde el inicio hasta el final de la primera línea vertical*/
            x -= dx;

            /*Pasa la quebrada y cambia su dirección de horizontal a vertical dirigiéndose hacia el panel de inventario*/
            if (x == 735) {
                dx = 0;
                dy = 1;
                y += dy;
            }

            /*Verifica que la bola esté entrando al panel y se posiciona en determinada posición dentro del panel, además de que cambia de color a azul*/
            if (y == 340 && x == 735) {
                /*Cambio de posición*/
                x = 571;
                y = 385;
                repaint();
                /*Espera indicada en el menú*/
                try {
                    Thread.sleep(tiempoInv * 1000);

                } catch (InterruptedException ex) {

                }
                /*Una vez termina el tiempo procede a cambiar de posición directo al otro panel "Producción"*/
                x = 635;
                y = 340;
                repaint();
            }

            /*Dándole velocidad de nuevo en dirección vertical directo al panel de producción*/
            if (x == 635) {
                dx = 0;
                dy = 1;
                y -= dy;
            }

            /*Verifica si está a punto de entrar al panel y de nuevo se teletransporta*/
            if (x == 635 && y == 250) {
                /*Cambio de posición*/
                x = 571;
                y = 145;
                repaint();
                /*Tiempo de espera indicado en el menú principal*/
                try {
                    Thread.sleep(tiempoProd * 1000);

                } catch (InterruptedException ex) {

                }
                /*Una vez termina el tiempo procede a cambiar de posición directo al otro panel "Empaquetado"*/
                x = 546;
                y = 165;
                dx = 1;
                dy = 0;
                repaint();
            }

            /*Verifica si está a punto de entrar al panel y de nuevo se teletransporta*/
            if (y == 165 && x == 320) {
                x = 80;
                y = 145;
                dx = 0;
                repaint();
                /*Tiempo de espera indicado en el menú principal*/
                try {
                    Thread.sleep(tiempoEmp * 1000);

                } catch (InterruptedException ex) {

                }
                /*Una vez termina el tiempo procede a cambiar de posición directo al otro panel "Salida"*/
                x = 245;
                y = 255;
                repaint();
            }

            /*Cambiando la dirección de horizontal a vertical hacia el panel salida*/
            if (x == 245) {
                dx = 0;
                dy = 1;
                y += dy;
            }

            /*Verirfica si la bola está a punto de entrar al panel y si teletransporta*/
            if (x == 245 && y == 340) {
                x = 80;
                y = 385;
                repaint();
                /*Tiempo de espera indicado en el menú principal*/
                try {
                    Thread.sleep(tiempoSal * 1000);

                } catch (InterruptedException ex) {

                }
                /*Una vez termina el tiempo de espera se telentranspo directo a la quebrada directo al final del recorrido*/
                x = 145;
                y = 340;
                repaint();
            }

            /*Cambiando la orientación del recorrido a vertical*/
            if (x == 145) {
                dx = 0;
                dy = 1;
                y -= dy;
            }

            /*Cambiando la orientación cuando llegue a x=145 y que solo se mueva por y=300 por eso la doble condición
            de lo contrario se movería horizontalmente desde el principio hasta el final sin tener el recorrido especificado*/
            if (x == 145) {
                if (y == 300) {
                    dy = 0;
                    dx = 1;
                    x -= dx;

                }
            }

            if (x == 10) {
                hiloTiempo.detener();
                JOptionPane.showMessageDialog(null, "La simulación ha terminado", "Proceso Exitoso", JOptionPane.INFORMATION_MESSAGE);
            }
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException ex) {

            }

        }
    }

}
