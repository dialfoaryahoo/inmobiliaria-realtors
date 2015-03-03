/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cartera_y_caja;
import inmobiliaria_fase01.Conexion;
/**
 *
 * @author Dialfoar
 */
public class Crear_Otros_Usuarios extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Otros_Usuarios
     */
    public Crear_Otros_Usuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(580, 300);
        setLocationRelativeTo(null);
        setTitle("Crear Otro Usuario");          
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jText_cedula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jText_nombres = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jText_direccioncasa = new javax.swing.JTextField();
        jButton_Iguardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Cedula");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 60, 160, 40);

        jText_cedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jText_cedula);
        jText_cedula.setBounds(140, 60, 410, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Nombres");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 100, 160, 40);

        jText_nombres.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jText_nombres);
        jText_nombres.setBounds(140, 100, 410, 40);

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Direccion Casa");
        getContentPane().add(jLabel80);
        jLabel80.setBounds(20, 140, 160, 40);

        jText_direccioncasa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jText_direccioncasa);
        jText_direccioncasa.setBounds(140, 140, 410, 40);

        jButton_Iguardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Iguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Save-as-icon.png"))); // NOI18N
        jButton_Iguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_IguardarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Iguardar);
        jButton_Iguardar.setBounds(210, 190, 140, 50);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Crear Otro Usuarios");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 370, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_IguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_IguardarActionPerformed
        conn.establecer_conexion();
        
        String insert ="insert into otros_usuarios (cedula, nombre_completo, direccion) values "
                + "("+jText_cedula.getText()+", '"+jText_nombres.getText().toUpperCase()+"', '"+jText_direccioncasa.getText().toUpperCase()+"')";
        System.out.println(insert);
        if(conn.Dinsertar(insert)==1){
            this.dispose();
        }
        
        
    }//GEN-LAST:event_jButton_IguardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Crear_Otros_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Crear_Otros_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Crear_Otros_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_Otros_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Crear_Otros_Usuarios dialog = new Crear_Otros_Usuarios(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    Conexion conn = new Conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Iguardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JTextField jText_cedula;
    private javax.swing.JTextField jText_direccioncasa;
    private javax.swing.JTextField jText_nombres;
    // End of variables declaration//GEN-END:variables
}