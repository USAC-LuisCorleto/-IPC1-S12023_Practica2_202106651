package Monkey;

import javax.swing.JLabel;

public class HiloTiempo extends Thread {

    private int minutos;
    private int segundos;
    private JLabel tiempoLabel;
    private boolean activo;

    public HiloTiempo(JLabel tiempoLabel) {
        this.tiempoLabel = tiempoLabel;
        this.activo = true;
    }

    @Override
    public void run() {
        while (activo) {
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
    
    public void detener(){
        activo = false;
    }
}
