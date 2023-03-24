package Monkey;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Pelota");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(900, 600);

        Pelota pelota = new Pelota();

        ventana.add(pelota);

        ventana.setVisible(true);
    }
}
