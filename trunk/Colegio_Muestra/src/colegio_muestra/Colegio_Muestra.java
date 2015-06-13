
package colegio_muestra;

import Config.Systema;
import Interfaces.Pantalla_Inicial_01;
import System.Conexion;
import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;


public class Colegio_Muestra {


    public static void main(String[] args) {
        Systema sys = new Systema();
        sys.iniciar();
        Conexion.establecer_conexion();
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceNegatedTheme");
    
        JFrame.setDefaultLookAndFeelDecorated(true);
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_Inicial_01().setVisible(true);
            }
        });
    
    }
    
}
