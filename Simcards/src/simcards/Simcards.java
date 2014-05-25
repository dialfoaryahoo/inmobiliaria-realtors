
package simcards;

import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

public class Simcards {

    public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlackSteelSkin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceNegatedTheme");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_Inicial().setVisible(true);
            }
        });
    }
}
