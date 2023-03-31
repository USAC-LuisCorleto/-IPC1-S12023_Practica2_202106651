package Monkey;

import javax.swing.JLabel;

public class HiloProducción extends Thread {

    private JLabel producciónLabel;
    public static int actualEnProducción = 0;

    public HiloProducción(JLabel producciónLabel) {
        this.producciónLabel = producciónLabel;
    }

    @Override
    public void run() {
        while (true) {
            producciónLabel.setText(String.valueOf(actualEnProducción));
        }
    }
}
