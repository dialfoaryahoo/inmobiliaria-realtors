/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package System;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.jvnet.substance.SubstanceLookAndFeel;

public class Utilidades {
        
    public static void JOptionShowMessage (String tamaño,String mensaje){   
            JOptionPane.showMessageDialog(null,"<html><font size="+tamaño+" face='Arial'><h3>"+mensaje+"</h3></font></html>");
    }
        
        
    public static boolean validar(Object[] datos) { 
            for (int i = 0; i <= datos.length - 1; i++) { 
                    if (datos[i].toString().isEmpty()) {
                        JOptionShowMessage("+1", "DEBE LLENAR TODOS LOS CAMPOR PARA CONTINUAR");
                        return false;
                    } 
            }
            return true;
    }
    
    public static void dialog(){
        JDialog.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceNegatedTheme");
        
    }

}
