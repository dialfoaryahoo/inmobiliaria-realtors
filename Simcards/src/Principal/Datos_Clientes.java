/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import simcards.*;
import Models.Render;
import java.sql.ResultSet;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.postgresql.core.Query;

/**
 *
 * @author Usuario
 */
public class Datos_Clientes extends javax.swing.JDialog {

    /**
     * Creates new form Crear_Operador
     */
    private DefaultTableModel modeloDeMiJTable; 
    public Datos_Clientes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(660,350);
        setTitle("Datos Basicos");
        setLocationRelativeTo(rootPane);
        
       
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
        
        public void Clientesvacio(){
            jTextField_Cnombre.setText(""); 
            jTextField_Capellido1.setText(""); 
            jTextField_Capellido2.setText(""); 
            jTextField_Ccedula.setText(""); 
            jTextField_Cdireccion.setText(""); 
            jTextField_Cciudad.setText(""); 
            jTextField_Cbarrio.setText(""); 
            jTextField_Ctelefono.setText(""); 
            jTextField_Ccelular.setText(""); 
            jTextField_Ccupomax.setText(""); 
            jRadioButton_Ccredito_si.setSelected(false);
            jRadioButton_Ccredito_no.setSelected(false);
            credito = 0;            
        }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField_Cbuscar = new javax.swing.JTextField();
        jTextField_Cnombre = new javax.swing.JTextField();
        jTextField_Ccedula = new javax.swing.JTextField();
        jTextField_Cbarrio = new javax.swing.JTextField();
        jTextField_Cciudad = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jRadio_CBnombre = new javax.swing.JRadioButton();
        jTextField_Cdireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField_Ccelular = new javax.swing.JTextField();
        jTextField_Ctelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jRadioButton_CBcedual = new javax.swing.JRadioButton();
        jRadioButton_Ccredito_no = new javax.swing.JRadioButton();
        jRadioButton_Ccredito_si = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jTextField_Ccupomax = new javax.swing.JTextField();
        jTextField_nombre12 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jTextField_Capellido1 = new javax.swing.JTextField();
        jTextField_Capellido2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Buscar");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 10, 100, 30);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396514213_Remove.png"))); // NOI18N
        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(410, 270, 160, 40);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Barrio");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(320, 170, 90, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Nombre");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 80, 190, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("CC / Nit");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 140, 190, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Direccion");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(320, 140, 90, 30);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Ciudad");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 170, 190, 30);

        jTextField_Cbuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField_Cbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_CbuscarActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_Cbuscar);
        jTextField_Cbuscar.setBounds(140, 40, 490, 30);

        jTextField_Cnombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Cnombre);
        jTextField_Cnombre.setBounds(140, 80, 490, 30);

        jTextField_Ccedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Ccedula);
        jTextField_Ccedula.setBounds(140, 140, 180, 30);

        jTextField_Cbarrio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Cbarrio);
        jTextField_Cbarrio.setBounds(410, 170, 220, 30);

        jTextField_Cciudad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Cciudad);
        jTextField_Cciudad.setBounds(140, 170, 180, 30);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1402870079_floppy.png"))); // NOI18N
        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(90, 270, 160, 40);

        buttonGroup1.add(jRadio_CBnombre);
        jRadio_CBnombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadio_CBnombre.setText("Nombre");
        jRadio_CBnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadio_CBnombreActionPerformed(evt);
            }
        });
        getContentPane().add(jRadio_CBnombre);
        jRadio_CBnombre.setBounds(230, -1, 120, 40);

        jTextField_Cdireccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Cdireccion);
        jTextField_Cdireccion.setBounds(410, 140, 220, 30);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Celular");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(320, 200, 90, 30);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Dar Credito?");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(30, 230, 120, 30);

        jTextField_Ccelular.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Ccelular);
        jTextField_Ccelular.setBounds(410, 200, 220, 30);

        jTextField_Ctelefono.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Ctelefono);
        jTextField_Ctelefono.setBounds(140, 200, 180, 30);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Celular");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(320, 200, 90, 30);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Telefono");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 200, 190, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Telefono");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(30, 200, 190, 30);

        buttonGroup1.add(jRadioButton_CBcedual);
        jRadioButton_CBcedual.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_CBcedual.setText("Cedula");
        jRadioButton_CBcedual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_CBcedualActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_CBcedual);
        jRadioButton_CBcedual.setBounds(140, -1, 80, 40);

        buttonGroup2.add(jRadioButton_Ccredito_no);
        jRadioButton_Ccredito_no.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_Ccredito_no.setText("No");
        jRadioButton_Ccredito_no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Ccredito_noActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_Ccredito_no);
        jRadioButton_Ccredito_no.setBounds(220, 230, 50, 30);

        buttonGroup2.add(jRadioButton_Ccredito_si);
        jRadioButton_Ccredito_si.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jRadioButton_Ccredito_si.setText("Si");
        jRadioButton_Ccredito_si.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Ccredito_siActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton_Ccredito_si);
        jRadioButton_Ccredito_si.setBounds(160, 230, 50, 30);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Cupo Maximo");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(270, 230, 140, 30);

        jTextField_Ccupomax.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Ccupomax);
        jTextField_Ccupomax.setBounds(410, 230, 220, 30);

        jTextField_nombre12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_nombre12);
        jTextField_nombre12.setBounds(410, 230, 220, 30);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1396515201_document-edit.png"))); // NOI18N
        jButton3.setText("Actualizar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(250, 270, 160, 40);

        jTextField_Capellido1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Capellido1);
        jTextField_Capellido1.setBounds(140, 110, 180, 30);

        jTextField_Capellido2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        getContentPane().add(jTextField_Capellido2);
        jTextField_Capellido2.setBounds(410, 110, 220, 30);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Apellido 2");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(320, 110, 90, 30);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Apellido 1");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(30, 110, 190, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        conn.establecer_conexion();
        Object[] datos = {jTextField_Cnombre.getText(), jTextField_Capellido1.getText(), jTextField_Capellido2.getText(), jTextField_Ccedula.getText(), jTextField_Cdireccion.getText(), jTextField_Cciudad.getText(), jTextField_Cbarrio.getText(), jTextField_Ctelefono.getText(), jTextField_Ccelular.getText(), jTextField_Ccupomax.getText(), credito}; 
        java.util.Date utiDate = new java.util.Date();
        long milisegundos = utiDate.getTime();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(milisegundos);
        String insert = "INSERT INTO clientes(cedula, nombre, primero_apellido, segundo_apellido, direccion, ciudad, barrio, fijo, celular, credito, cupo_max, cartera, estado_cliente, fecha_reg) values "
                + "("+jTextField_Ccedula.getText()+", '"+jTextField_Cnombre.getText().toUpperCase()+"', '"+jTextField_Capellido1.getText().toUpperCase()+"', '"+jTextField_Capellido2.getText().toUpperCase()+"', '"+jTextField_Cdireccion.getText().toUpperCase()+"', '"+jTextField_Cciudad.getText().toUpperCase()+"', '"+jTextField_Cbarrio.getText().toUpperCase()+"', '"+jTextField_Ctelefono.getText().toUpperCase()+"', '"+jTextField_Ccelular.getText().toUpperCase()+"', '"+credito+"', "+jTextField_Ccupomax.getText()+", 0, 1, '"+sqlTimestamp+"')";
        System.out.println(insert);
        
        if(validar(datos)==1){
           if(conn.Dinsertar(insert)==1){
               Clientesvacio();
           }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButton_Ccredito_siActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Ccredito_siActionPerformed
        credito = 1;
        jTextField_Ccupomax.setText("");
        jTextField_Ccupomax.setEnabled(true);
    }//GEN-LAST:event_jRadioButton_Ccredito_siActionPerformed

    private void jRadioButton_Ccredito_noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Ccredito_noActionPerformed
        credito = 2;
        jTextField_Ccupomax.setText("0");
        jTextField_Ccupomax.setEnabled(false);
    }//GEN-LAST:event_jRadioButton_Ccredito_noActionPerformed

    private void jTextField_CbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_CbuscarActionPerformed
        conn.establecer_conexion();
        int vaconsulta = 0;        
        String select1 = "select cedula, nombre, primero_apellido, segundo_apellido, direccion, ciudad, barrio, fijo, celular, credito, cupo_max from clientes where "+buscar+"  = "+jTextField_Cbuscar.getText().toUpperCase();
        String select2 = "select cedula, nombre, primero_apellido, segundo_apellido, direccion, ciudad, barrio, fijo, celular, credito, cupo_max from clientes where "+buscar+"  = '"+jTextField_Cbuscar.getText().toUpperCase()+"'";
        String consulta = "";
        if (buscar=="cedula") {
            consulta = select1;
            vaconsulta = 1;
        }
        else if (buscar=="nombre") {
            consulta = select2;
            vaconsulta = 1;
        }        
        
        if (vaconsulta == 1) {
            ResultSet query = conn.consulta(consulta);        
            try {
                while (query.next()) {
                jTextField_Cnombre.setText(query.getString("nombre")); 
                jTextField_Capellido1.setText(query.getString("primero_apellido")); 
                jTextField_Capellido2.setText(query.getString("segundo_apellido")); 
                jTextField_Ccedula.setText(query.getString("cedula")); 
                jTextField_Cdireccion.setText(query.getString("direccion")); 
                jTextField_Cciudad.setText(query.getString("ciudad")); 
                jTextField_Cbarrio.setText(query.getString("barrio")); 
                jTextField_Ctelefono.setText(query.getString("fijo")); 
                jTextField_Ccelular.setText(query.getString("celular")); 
                credito = (query.getInt("credito"));
                jTextField_Ccupomax.setText(query.getString("cupomax")); 
                System.out.println(credito);
                    
                }
            } catch (Exception e) {
            }
        }
       
    }//GEN-LAST:event_jTextField_CbuscarActionPerformed

    private void jRadioButton_CBcedualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_CBcedualActionPerformed
        buscar = "cedula";
    }//GEN-LAST:event_jRadioButton_CBcedualActionPerformed

    private void jRadio_CBnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_CBnombreActionPerformed
        buscar = "nombre";
    }//GEN-LAST:event_jRadio_CBnombreActionPerformed

    /**
     * @param args the command line arguments
     */
    Conexion conn = new Conexion();
    int credito = 0;
    String buscar = "";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton_CBcedual;
    private javax.swing.JRadioButton jRadioButton_Ccredito_no;
    private javax.swing.JRadioButton jRadioButton_Ccredito_si;
    private javax.swing.JRadioButton jRadio_CBnombre;
    private javax.swing.JTextField jTextField_Capellido1;
    private javax.swing.JTextField jTextField_Capellido2;
    private javax.swing.JTextField jTextField_Cbarrio;
    private javax.swing.JTextField jTextField_Cbuscar;
    private javax.swing.JTextField jTextField_Ccedula;
    private javax.swing.JTextField jTextField_Ccelular;
    private javax.swing.JTextField jTextField_Cciudad;
    private javax.swing.JTextField jTextField_Ccupomax;
    private javax.swing.JTextField jTextField_Cdireccion;
    private javax.swing.JTextField jTextField_Cnombre;
    private javax.swing.JTextField jTextField_Ctelefono;
    private javax.swing.JTextField jTextField_nombre12;
    // End of variables declaration//GEN-END:variables
}
