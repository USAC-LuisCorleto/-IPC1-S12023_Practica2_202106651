package Monkey;

import static Monkey.MenúPrincipal.tiempoInv;
import javax.swing.JLabel;

public class HiloInventario extends Thread {

    private JLabel inventarioLabel;
    private int actualEnInventario = 0;

    public HiloInventario(JLabel inventarioLabel) {
        this.inventarioLabel = inventarioLabel;
    }

    @Override
    public void run() {
        while (true) {
            inventarioLabel.setText(String.valueOf(actualEnInventario));
            int[] xy = Monkey.Animación.getXY();
            if (xy[1] == 340 && xy[0] == 735) {
                actualEnInventario++;
            }
        }
    }
}
