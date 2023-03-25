package Monkey;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new GraphiteLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MenúPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        MenúPrincipal menu = new MenúPrincipal();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

}

