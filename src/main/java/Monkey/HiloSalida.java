package Monkey;

import javax.swing.JLabel;

public class HiloSalida extends Thread {

    private JLabel salidaLabel;
    public static int actualEnSalida = 0;

    public HiloSalida(JLabel salidaLabel) {
        this.salidaLabel = salidaLabel;
    }

    @Override
    public void run() {
        while (true) {
            salidaLabel.setText(String.valueOf(actualEnSalida));
        }
    }
}
