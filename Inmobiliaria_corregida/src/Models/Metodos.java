
package Models;

import Cartera_y_caja.Index;
import inmobiliaria_fase01.Conexion;


public class Metodos {

    public void index(){
            Conexion.dialog();
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Index dialog = new Index(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });    
    }

    
}
