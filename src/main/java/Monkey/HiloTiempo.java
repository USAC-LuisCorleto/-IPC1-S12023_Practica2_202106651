package Monkey;

import javax.swing.JLabel;

public class HiloTiempo extends Thread {

    private int minutos;
    private int segundos;
    private JLabel tiempoLabel;

    public HiloTiempo(JLabel tiempoLabel) {
        this.tiempoLabel = tiempoLabel;
    }

    public void run() {
        while (true) {
            segundos++;

            if (segundos == 60) {
                segundos = 0;
                minutos++;
            }

            String cadena = String.format("%02d:%02d", minutos, segundos);
            tiempoLabel.setText(cadena);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {

            }
        }
    }
}
