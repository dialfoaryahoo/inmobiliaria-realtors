/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import simcards.*;
import Models.Renderito;
import java.sql.ResultSet;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class Datos_Empresa extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    public Datos_Empresa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(660,350);
        setTitle("Datos Empresa");
        setLocationRelativeTo(rootPane);
        jButton2.setVisible(false);
        Cargardatos();
       
    }
   

        public int validar(Object[] datos) { 
            int validar = 1;
            for (int i = 0; i <= datos.length - 1; i++) { 
                if (datos[i].toString().isEmpty()) { 
                JOptionPane.showMessageDialog(null, "Por Favor Diligencias todos los Datos"); 
                validar = 2;
                break;
                }
            } 
            return validar; 
        }
        
        public void  Datos_empresavacio(){
        jTextField_Erazonsocial.setText("");
        jTextField_Enit.setText("");
        jTextField_Edireccion.setText("");
        jTextField_Etelefono.setText("");

        jTextField_Eemail.setText("");
        }
    
        public void Cargardatos(){
        conn.establecer_conexion();
        String consulta = "select razon_social, nit, direccion, telefono, ciudad, pais, email from datos_empresa ORDER BY cod_empresa desc limit 1";
        ResultSet query = conn.consulta(consulta);
        
            try {
                while (query.next()) {
                    jTextField_Erazonsocial.setText(query.getString("razon_social"));
                    jTextField_Enit.setText(query.getString("nit"));
                    jTextField_Edireccion.setText(query.getString("direccion"));
                    jTextField_Etelefono.setText(query.getString("telefono"));

                    jTextField_Eemail.setText(query.getString("email"));                    
                }
            } catch (Exception e) {
                
            }
        
        
        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_Erazonsocial = new javax.swing.JTextField();
        jTextField_Enit = new javax.swing.JTextField();
        jTextField_Edireccion = new javax.swing.JTextField();
        jTextField_Etelefono = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField_Eemail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Empresa");
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Razon Social");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 20, 190, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(430, 140, 180, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Nit");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 50, 190, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Dirección");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 80, 190, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Telefonos");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 110, 190, 30);

        jTextField_Erazonsocial.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Erazonsocial);
        jTextField_Erazonsocial.setBounds(210, 20, 400, 30);

        jTextField_Enit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Enit);
        jTextField_Enit.setBounds(210, 50, 400, 30);

        jTextField_Edireccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Edireccion);
        jTextField_Edireccion.setBounds(210, 80, 400, 30);

        jTextField_Etelefono.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Etelefono);
        jTextField_Etelefono.setBounds(210, 110, 400, 30);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1402870079_floppy.png"))); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(430, 170, 180, 30);

        jTextField_Eemail.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Eemail);
        jTextField_Eemail.setBounds(210, 140, 220, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Email");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 140, 190, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        conn.establecer_conexion();
        Object [] datos = {jTextField_Erazonsocial.getText(), jTextField_Enit.getText(), jTextField_Edireccion.getText(), jTextField_Etelefono.getText(), jTextField_Eemail.getText()};
        java.util.Date utiDate = new java.util.Date();
        long milisegundos = utiDate.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(milisegundos);
        
        String insert = "INSERT INTO datos_empresa(razon_social, nit, direccion, telefono, ciudad, pais, email, estado_empresa, fecha_reg) values "
                + "('"+jTextField_Erazonsocial.getText().toUpperCase()+"', '"+jTextField_Enit.getText().toUpperCase()+"', '"+jTextField_Edireccion.getText().toUpperCase()+"','"+jTextField_Etelefono.getText().toUpperCase()+"','"+"', '"+jTextField_Eemail.getText().toUpperCase()+"', 1, '"+sqlTimestamp+"')";
        
        
        String mensaje = "Actualizado exitosamente";
        if(validar(datos)==1)
        if(conn.Dactualizar(insert, mensaje)== 1){
            Cargardatos();
        }


       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    Conexion conn = new Conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jTextField_Edireccion;
    private javax.swing.JTextField jTextField_Eemail;
    private javax.swing.JTextField jTextField_Enit;
    private javax.swing.JTextField jTextField_Erazonsocial;
    private javax.swing.JTextField jTextField_Etelefono;
    // End of variables declaration//GEN-END:variables
}
