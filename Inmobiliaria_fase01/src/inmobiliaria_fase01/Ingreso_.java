/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria_fase01;

import javax.swing.JFrame;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author Usuario
 */
public class Ingreso_ extends javax.swing.JDialog {

    /**
     * Creates new form Ingreso_
     */
    public Ingreso_(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(700,300);
        setTitle("Ingreso");
        setResizable(false);
        setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(null);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(350, 70, 270, 40);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Contraseña");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(350, 130, 120, 22);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Usuario");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(350, 40, 120, 22);
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(350, 160, 270, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(420, 220, 130, 30);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Sin título-1.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(10, 20, 380, 240);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        JFrame.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.OfficeSilver2007Skin");
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceNegatedTheme");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_Inicial().setVisible(true);
            }
        });

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
