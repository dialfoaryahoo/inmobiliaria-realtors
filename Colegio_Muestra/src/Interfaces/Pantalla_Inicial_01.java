/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Config.Ficheros;
import Config.Paneles;
import System.Conexion;
import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Usuario
 */
public class Pantalla_Inicial_01 extends javax.swing.JFrame {

    /**
     * Creates new form Pantalla_Inicial
     */
    public Pantalla_Inicial_01() {
        initComponents();
        setSize(850, 550);
        setLocationRelativeTo(null);
        jLabel1.requestFocus();
        setTitle("BIENVENIDO - KIDS PLANET SOFTWARE");
         

                
        jButton1.setMnemonic(KeyEvent.VK_X);
        
        
    }

    
    public void hola(){
            System.out.println("hola");
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
 
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(50, 60, 180, 150);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(330, 310, 180, 150);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/14.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12);
        jButton12.setBounds(330, 60, 180, 150);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/13.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton14);
        jButton14.setBounds(610, 60, 180, 150);

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("HOJA DE VIDA ESTUDIANTE");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 210, 180, 19);

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("PAGOS");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(330, 460, 180, 19);

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("MORA ESTUDIANTES");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(610, 210, 180, 19);

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("PADRES DE FAMILIA");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(330, 210, 180, 19);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/red_ico.png"))); // NOI18N
        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(130, 340, 109, 41);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Jpanel_Fondo.png"))); // NOI18N
        jLabel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel1KeyReleased(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 0, 850, 520);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HojaEstudiante dialog = new HojaEstudiante(new javax.swing.JFrame(), true);
                
                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
            Conexion.establecer_conexion();
            String ruta=System.getProperty("user.dir");
            ruta=ruta.replace("\\", "/");
            System.out.println(ruta);
            String path=ruta+"/src/Reportes/ResumenPuntos.jasper";
            JasperReport jr = null;
            try {
                JDialog viewer = new JDialog(new javax.swing.JFrame(),"Imprimir", true); 
                viewer.setSize(1000,800); 
                viewer.setLocationRelativeTo(null); 
                Map parametro = new HashMap();
                int a=1043007731;
                parametro.put("NCedula", a); 
                parametro.put("Nombre", "BAYRON TARAZONA");  
                parametro.put("Cedula", "1043007731");
                parametro.put("Puntos", "5000");
                
                //parametro.put("parameter1", jComboBox_usuarios.getSelectedItem());
                jr = (JasperReport) JRLoader.loadObjectFromFile(path);
                JasperPrint jp = JasperFillManager.fillReport(jr,parametro, Conexion.conexion);
                JasperViewer jv = new JasperViewer(jp);
                viewer.getContentPane().add(jv.getContentPane());
                viewer.setVisible(true);
                jv.setTitle("Detalle Puntos");


            } catch (JRException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
            }   
    
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Historial_Mora dialog = new Historial_Mora(new javax.swing.JFrame(), true);

                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Add_Padre dialog = new Add_Padre(new javax.swing.JFrame(), true);

                dialog.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jLabel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel1KeyReleased
  // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            PRUEBA dialog = new PRUEBA(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
        }
        });        
    }//GEN-LAST:event_jButton1ActionPerformed

    
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
