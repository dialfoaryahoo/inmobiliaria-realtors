package inmobiliaria_fase01;

public class Inmobiliaria_fase01 {
    public static void main(String[] args) {
            Conexion.dialog();
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ingreso_ dialog = new Ingreso_(new java.awt.Frame(), true);
                                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
           
    }
    
}
