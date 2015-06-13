/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.Estudiante;
import Clases_DAO.EstudiantesDB;
import System.Utilidades;

/**
 *
 * @author Usuario
 */
public class HojaEstudiante extends javax.swing.JDialog {

    /**
     * Creates new form HojaEstudiante
     */
    public HojaEstudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(880, 720);
        setLocationRelativeTo(null);
        iniciar();
    }
    public void iniciar(){
         jComboBox_nombre.setModel(new javax.swing.DefaultComboBoxModel(EstudiantesDB.Vector()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jComboBox_nombre = new javax.swing.JComboBox();
        jTextField_codigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_nombrepadre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField_telefonopadre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField_celularpadre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_correopadre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField_direccionpadre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_matricula = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField_salon = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField_estado = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_correopadre1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setText("Codigo Estudiante");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 80, 190, 40);

        jComboBox_nombre.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jComboBox_nombre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_nombreItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox_nombre);
        jComboBox_nombre.setBounds(220, 30, 470, 40);

        jTextField_codigo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_codigoActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_codigo);
        jTextField_codigo.setBounds(220, 80, 240, 40);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setText("Nombre Estudiante");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 30, 190, 40);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setText("Nombre Completo");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 440, 180, 40);

        jTextField_nombrepadre.setEditable(false);
        jTextField_nombrepadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_nombrepadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombrepadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_nombrepadre);
        jTextField_nombrepadre.setBounds(240, 440, 470, 40);

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setText("Teléfono");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 490, 180, 40);

        jTextField_telefonopadre.setEditable(false);
        jTextField_telefonopadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_telefonopadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_telefonopadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_telefonopadre);
        jTextField_telefonopadre.setBounds(240, 490, 180, 40);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setText("Celular");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(440, 490, 90, 40);

        jTextField_celularpadre.setEditable(false);
        jTextField_celularpadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_celularpadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_celularpadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_celularpadre);
        jTextField_celularpadre.setBounds(530, 490, 180, 40);

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setText("Dirección");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(50, 540, 180, 40);

        jTextField_correopadre.setEditable(false);
        jTextField_correopadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_correopadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_correopadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_correopadre);
        jTextField_correopadre.setBounds(240, 540, 470, 40);

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel8.setText("Cedula");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(50, 390, 180, 40);

        jTextField_direccionpadre.setEditable(false);
        jTextField_direccionpadre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_direccionpadre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_direccionpadreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_direccionpadre);
        jTextField_direccionpadre.setBounds(240, 390, 470, 40);

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel12.setText("Lugar de Nacimiento");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(30, 180, 190, 40);

        jTextField_matricula.setEditable(false);
        jTextField_matricula.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_matricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_matriculaActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_matricula);
        jTextField_matricula.setBounds(220, 180, 240, 40);

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel13.setText("Edad");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(30, 130, 190, 40);

        jTextField_salon.setEditable(false);
        jTextField_salon.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_salon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_salonActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_salon);
        jTextField_salon.setBounds(220, 130, 140, 40);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel14.setText("Dirección");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(30, 280, 190, 40);

        jTextField_estado.setEditable(false);
        jTextField_estado.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_estadoActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_estado);
        jTextField_estado.setBounds(220, 280, 240, 40);

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel19.setText("Fecha Nacimiento");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(30, 230, 190, 40);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16.png"))); // NOI18N
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel20);
        jLabel20.setBounds(490, 80, 200, 250);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "DATOS ACUDIENTE/PADRE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 360, 700, 320);

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel9.setText("Dirección Oficina");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(50, 590, 180, 40);

        jTextField_correopadre1.setEditable(false);
        jTextField_correopadre1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_correopadre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_correopadre1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_correopadre1);
        jTextField_correopadre1.setBounds(240, 590, 470, 40);

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jButton1.setText("VER DATOS COMPLETOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(510, 630, 190, 40);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "MENU", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 24))); // NOI18N
        jPanel1.setLayout(null);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Estudiante_PRINT.png"))); // NOI18N
        jPanel1.add(jButton2);
        jButton2.setBounds(10, 540, 110, 100);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Estudiante_add.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(10, 40, 110, 100);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Estudiante_del.png"))); // NOI18N
        jPanel1.add(jButton4);
        jButton4.setBounds(10, 140, 110, 100);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Estudiante_familia.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(10, 240, 110, 100);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Estudiante_recibo.png"))); // NOI18N
        jPanel1.add(jButton6);
        jButton6.setBounds(10, 340, 110, 100);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Value_estudiante.png"))); // NOI18N
        jPanel1.add(jButton7);
        jButton7.setBounds(10, 440, 110, 100);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(720, 10, 130, 670);
        getContentPane().add(dateChooserCombo1);
        dateChooserCombo1.setBounds(220, 230, 230, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_codigoActionPerformed

    }//GEN-LAST:event_jTextField_codigoActionPerformed

    private void jTextField_nombrepadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombrepadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nombrepadreActionPerformed

    private void jTextField_telefonopadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_telefonopadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_telefonopadreActionPerformed

    private void jTextField_celularpadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_celularpadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_celularpadreActionPerformed

    private void jTextField_direccionpadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_direccionpadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_direccionpadreActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Estudiante dialog = new Add_Estudiante(new javax.swing.JFrame(), true);
                
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(jComboBox_nombre.getSelectedIndex()!=0){
            Estudiante est = new Estudiante();
//            est.setNombre_completo();
        
        }else{
            Utilidades.JOptionShowMessage("+1", "Debes seleccionar un estudiante");
        
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Estudiante dialog = new Add_Estudiante(new javax.swing.JFrame(), true);
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox_nombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_nombreItemStateChanged

    }//GEN-LAST:event_jComboBox_nombreItemStateChanged

    private void jTextField_matriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_matriculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_matriculaActionPerformed

    private void jTextField_salonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_salonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_salonActionPerformed

    private void jTextField_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_estadoActionPerformed

    private void jTextField_correopadreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_correopadreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_correopadreActionPerformed

    private void jTextField_correopadre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_correopadre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_correopadre1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox_nombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_celularpadre;
    private javax.swing.JTextField jTextField_codigo;
    private javax.swing.JTextField jTextField_correopadre;
    private javax.swing.JTextField jTextField_correopadre1;
    private javax.swing.JTextField jTextField_direccionpadre;
    private javax.swing.JTextField jTextField_estado;
    private javax.swing.JTextField jTextField_matricula;
    private javax.swing.JTextField jTextField_nombrepadre;
    private javax.swing.JTextField jTextField_salon;
    private javax.swing.JTextField jTextField_telefonopadre;
    // End of variables declaration//GEN-END:variables
}
