package Monkey;

import javax.swing.JLabel;

public class HiloEmpaquetado extends Thread {

    private JLabel empaquetadoLabel;
    public static int actualEnEmpaquetado = 0;

    public HiloEmpaquetado(JLabel empaquetadoLabel) {
        this.empaquetadoLabel = empaquetadoLabel;
    }

    @Override
    public void run() {
        while (true) {
            empaquetadoLabel.setText(String.valueOf(actualEnEmpaquetado));
        }
    }
}
