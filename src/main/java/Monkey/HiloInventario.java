package Monkey;

import javax.swing.JLabel;

public class HiloInventario extends Thread {

    private JLabel inventarioLabel;
    public static int actualEnInventario = 0;

    public HiloInventario(JLabel inventarioLabel) {
        this.inventarioLabel = inventarioLabel;
    }

    @Override
    public void run() {
        while (true) {
            inventarioLabel.setText(String.valueOf(actualEnInventario));
        }
    }
}
