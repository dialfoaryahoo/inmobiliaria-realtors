/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.Estudiante;
import Clases_DAO.EstudiantesDB;
import System.Utilidades;
import java.sql.Timestamp;

/**
 *
 * @author Usuario
 */
public class Add_Estudiante extends javax.swing.JDialog {

    /**
     * Creates new form HojaEstudiante
     */
    public Add_Estudiante(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(870, 500);
        setLocationRelativeTo(null);
    }

    public void set_estudiantes(){
        Estudiante est = new Estudiante();
        est.setNombre_completo(jTextField_nombre.getText().toUpperCase());
        est.setIdentificacion(jTextField_NUIP.getText());
        est.setEdad(Integer.parseInt(jComboBox_edad.getSelectedItem().toString()));
        est.setLugar_nac(jComboBox_lugarNacimiento.getSelectedItem().toString());
        est.setFecha_nac(Timestamp.valueOf(dateChooserCombo_fechaNac.getText()));
        est.setDireccion(jTextField_direccion.getText());
        est.setEstado(1);
    }
    public void limpiar_camposestudiantes(){
        Estudiante est = new Estudiante();
        jTextField_nombre.setText("");
        jTextField_NUIP.setText("");
        jComboBox_edad.setSelectedIndex(0);
        jComboBox_lugarNacimiento.setSelectedIndex(0);
        jTextField_direccion.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField_nombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField_direccion = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboBox_edad = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField_NUIP = new javax.swing.JTextField();
        jComboBox_lugarNacimiento = new javax.swing.JComboBox();
        dateChooserCombo_fechaNac = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setText("NUIP Estudiante");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 150, 190, 40);

        jTextField_nombre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_nombre);
        jTextField_nombre.setBounds(220, 100, 470, 40);

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 40)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("AGREGAR ESTUDIANTES");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 0, 670, 80);

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel12.setText("Lugar Nacimiento");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(30, 250, 190, 40);

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel13.setText("Edad");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(30, 200, 190, 40);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel14.setText("Dirección");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(30, 350, 190, 40);

        jTextField_direccion.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_direccionActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_direccion);
        jTextField_direccion.setBounds(220, 350, 240, 40);

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel19.setText("Fecha Nacimiento");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(30, 300, 190, 40);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/16.png"))); // NOI18N
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(jLabel20);
        jLabel20.setBounds(490, 170, 200, 250);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "MENU", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 24))); // NOI18N
        jPanel1.setLayout(null);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Add_estudiante.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(10, 330, 110, 100);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Value_estudiante.png"))); // NOI18N
        jPanel1.add(jButton4);
        jButton4.setBounds(10, 230, 110, 100);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Photo_estudiante.png"))); // NOI18N
        jPanel1.add(jButton5);
        jButton5.setBounds(10, 130, 110, 100);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clean_estudiante.png"))); // NOI18N
        jPanel1.add(jButton6);
        jButton6.setBounds(10, 30, 110, 100);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(720, 10, 130, 440);

        jComboBox_edad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jComboBox_edad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE LA EDAD", "3", "4", "5", "6", "7", "8", "9", "10", " " }));
        getContentPane().add(jComboBox_edad);
        jComboBox_edad.setBounds(220, 200, 240, 40);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setText("Nombre Estudiante");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 100, 190, 40);

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel15.setText("Convive con?");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(30, 400, 190, 40);

        jComboBox2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "MADRE", "PADRE", "FAMILIAR" }));
        getContentPane().add(jComboBox2);
        jComboBox2.setBounds(220, 400, 240, 40);

        jTextField_NUIP.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jTextField_NUIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NUIPActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_NUIP);
        jTextField_NUIP.setBounds(220, 150, 240, 40);

        jComboBox_lugarNacimiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jComboBox_lugarNacimiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE UN MUNICIPIO", "MEDELLIN", "ABEJORRAL", "ABRIAQUI", "ALEJANDRIA", "AMAGA", "AMALFI", "ANDES", "ANGELOPOLIS", "ANGOSTURA", "ANORI", "SANTA FE DE ANTIOQUIA", "ANZA", "APARTADO", "ARBOLETES", "ARGELIA", "ARMENIA", "BARBOSA", "BELMIRA", "BELLO", "BETANIA", "BETULIA", "CIUDAD BOLIVAR", "BRICENO", "BURITICA", "CACERES", "CAICEDO", "CALDAS", "CAMPAMENTO", "CANASGORDAS", "CARACOLI", "CARAMANTA", "CAREPA", "CARMEN DE VIBORAL", "CAROLINA", "CAUCASIA", "CHIGORODO", "CISNEROS", "COCORNA", "CONCEPCION", "CONCORDIA", "COPACABANA", "DABEIBA", "DON MATIAS", "EBEJICO", "EL BAGRE", "ENTRERRIOS", "ENVIGADO", "FREDONIA", "FRONTINO", "GIRALDO", "GIRARDOTA", "GOMEZ PLATA", "GRANADA", "GUADALUPE", "GUARNE", "GUATAPE", "HELICONIA", "HISPANIA", "ITAGÜI", " " }));
        getContentPane().add(jComboBox_lugarNacimiento);
        jComboBox_lugarNacimiento.setBounds(220, 250, 240, 40);
        getContentPane().add(dateChooserCombo_fechaNac);
        dateChooserCombo_fechaNac.setBounds(220, 300, 240, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombreActionPerformed

    }//GEN-LAST:event_jTextField_nombreActionPerformed

    private void jTextField_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_direccionActionPerformed

    private void jTextField_NUIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NUIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NUIPActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                Object[] datos = {jTextField_nombre.getText(), jTextField_NUIP.getText(), jTextField_direccion.getText()};     
                Estudiante est = new Estudiante(0, "", "", null, "", "", 0, null, 0, 0);
                if(jComboBox_edad.getSelectedIndex()!=0&&jComboBox_lugarNacimiento.getSelectedIndex()==0){
                    Utilidades.JOptionShowMessage("+1", "SELECCIONA TODOS LOS DATOS CORRESPONDIENTES");
                }
                else{
                    if(Utilidades.validar(datos)==true){
                        set_estudiantes();
                        if (EstudiantesDB.insertar()==1) {
                            limpiar_camposestudiantes();
                            Utilidades.JOptionShowMessage("+1", "FAMILIAR REGISTRADO CON EXITO");
//                          activar_boton(false, false, false, false, false, false);
                            Estudiante est2 = new Estudiante(0, "", "", null, "", "", 0, null, 0, 0);
                            this.dispose();
                        }
                    }                
                }
                

    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserCombo_fechaNac;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox_edad;
    private javax.swing.JComboBox jComboBox_lugarNacimiento;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_NUIP;
    private javax.swing.JTextField jTextField_direccion;
    private javax.swing.JTextField jTextField_nombre;
    // End of variables declaration//GEN-END:variables
}
